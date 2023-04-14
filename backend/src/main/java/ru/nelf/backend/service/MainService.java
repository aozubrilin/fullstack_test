package ru.nelf.backend.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nelf.backend.entity.*;
import ru.nelf.backend.repository.MongoDBRepository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class MainService {

    @Autowired
    private StorageConfig storageConfig;
    @Autowired
    private MongoDBRepository mongoDBRepository;

    private final String PATH_TO_TEMP = "./src/main/resources/temp/";

    public ResponseCategoryFiles getAll() {
        List<File> fileList = mongoDBRepository.findAll();
        return new ResponseCategoryFiles(200, fileList);
    }

    public Response getImage(String fileName) {
        Optional<File> optionalFile = mongoDBRepository.findByFilename(fileName);
        // если существует, то вернуть. Иначе ошибка 404
        return optionalFile.map(file -> new Response(200, file)).orElseGet(() -> new Response(404, fileName));
    }

    public Response putImage(Request request, MultipartFile multipartFile) {
        Response response;
        try {
            // если добавление через URL(есть URL)
            if (multipartFile.isEmpty()) {
                try (InputStream inputStream = new URL(request.getUrl()).openStream()) {
                    response = uploadToStorage(request, inputStream);
                }
            } else {
                String filenameExtension = multipartFile.getOriginalFilename()
                        .substring(multipartFile.getOriginalFilename().indexOf('.'));
                request.setFilename(request.getFilename() + filenameExtension);
                java.io.File file = new java.io.File(PATH_TO_TEMP + request.getFilename());
                file.createNewFile();
                try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    fileOutputStream.write(multipartFile.getBytes());
                }
                response = uploadToStorage(request, new FileInputStream(file));
            }
            deleteTempFiles();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            deleteTempFiles();
            return new Response(400, request.getFilename());
        }
    }

    private Response uploadToStorage(Request request, InputStream inputStream) {
        // если файл существует
        if (mongoDBRepository.findByFilename(request.getFilename()).isPresent()) {
            return new Response(409, request.getFilename());
        } else {
            storageConfig.getS3client().putObject(storageConfig.getBUCKET_NAME(), request.getFilename(), inputStream, new ObjectMetadata());
            String storageUrl = storageConfig.getS3client().getUrl(storageConfig.getBUCKET_NAME(), request.getFilename()).toString();
            File file = mongoDBRepository.save(new File(
                    request.getFilename(),
                    request.getCategory() == null ? "all" : request.getCategory(),
                    storageUrl));
            return new Response(200, file.getId(), file.getFilename(), file.getCategory(), file.getUrl());
        }
    }

    public Response deleteImage(String filename) {
        Optional<File> optionalFile = mongoDBRepository.findByFilename(filename);
        if (optionalFile.isPresent()) {
            mongoDBRepository.delete(optionalFile.get());
            storageConfig.getS3client().deleteObject(storageConfig.getBUCKET_NAME(), filename);
            optionalFile.get().setUrl(null);
            return new Response(200, optionalFile.get());
        } else
            return new Response(404, filename);
    }

    public ResponseCategoryFiles getByCategory(String category) {
        Optional<List<File>> optionalFileList = mongoDBRepository.findAllByCategory(category);
        // если существует, то вернуть. Иначе ошибка 404
        return optionalFileList.map(files -> new ResponseCategoryFiles(200, files)).orElseGet(() -> new ResponseCategoryFiles(404, null));
    }

    // временный метод, потом удалить
    public void refreshMongo() {
        mongoDBRepository.deleteAll();
        String c = Category.ANIMAL;
        List<S3ObjectSummary> list = storageConfig.getS3client().listObjects(storageConfig.getBUCKET_NAME()).getObjectSummaries();
        for (S3ObjectSummary e : list) {
            switch (e.getKey()) {
                case "1.jpg", "2.jpg", "3.jpg", "4.jpg" -> c = Category.ANIMAL;
                case "5.jpg", "6.jpg", "7.jpg", "8.jpg" -> c = Category.NATURE;
                case "9.jpg", "10.jpg", "11.jpg", "12.jpg" -> c = Category.FANTASY;
            }
            mongoDBRepository.save(new File(
                    e.getKey(), c,
                    storageConfig.getS3client().getUrl(storageConfig.getBUCKET_NAME(), e.getKey()).toString())
            );
        }
    }

    @SneakyThrows
    public void deleteTempFiles() {
        try {
            FileUtils.cleanDirectory(new java.io.File(PATH_TO_TEMP));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
