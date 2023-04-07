package ru.nelf.backend.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nelf.backend.entity.File;
import ru.nelf.backend.entity.Request;
import ru.nelf.backend.entity.Response;
import ru.nelf.backend.entity.StorageConfig;
import ru.nelf.backend.repository.MongoDBRepository;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageConfig storageConfig;
    @Autowired
    private MongoDBRepository mongoDBRepository;

    @PostConstruct
    private void init() {
        mongoDBRepository.deleteAll();
        List<S3ObjectSummary> list = storageConfig.getS3client().listObjects(storageConfig.getBUCKET_NAME()).getObjectSummaries();
        for (S3ObjectSummary e : list) {
            mongoDBRepository.save(new File(
                    e.getKey(),
                    "all",
                    storageConfig.getS3client().getUrl(storageConfig.getBUCKET_NAME(), e.getKey()).toString())
            );
        }
    }

    public List<File> getAll() {
        return mongoDBRepository.findAll();
    }

    public Response getImage(String fileName) {
        Optional<File> optionalFile = mongoDBRepository.findByFilename(fileName);
        if (optionalFile.isPresent()) {
            File file = optionalFile.get();
            return new Response(200, file.getId(), file.getFilename(), file.getCategory(), file.getUrl());
        } else
            return new Response(409, fileName);
    }

    public Response putImage(Request request) {
        try (InputStream inputStream = new URL(request.getUrl()).openStream()) {
            Response response;
            // если файл существует
            if (mongoDBRepository.findByFilename(request.getFilename()).isPresent()) {
                response = new Response(666, request.getFilename());
            } else {
                storageConfig.getS3client().putObject(storageConfig.getBUCKET_NAME(), request.getFilename(), inputStream, new ObjectMetadata());
                String storageUrl = storageConfig.getS3client().getUrl(storageConfig.getBUCKET_NAME(), request.getFilename()).toString();
                File file = mongoDBRepository.save(new File(
                        request.getFilename(),
                        request.getCategory() == null ? "all" : request.getCategory(),
                        storageUrl));
                response = new Response(200, file.getId(), file.getFilename(), file.getCategory(), file.getUrl());
            }
            return response;
        } catch (Exception e) {
            return new Response(409, request.getFilename());
        }
    }
}
