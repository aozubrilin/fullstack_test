package ru.nelf.backend.service;

import com.amazonaws.services.s3.iterable.S3Objects;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nelf.backend.entity.StorageConfig;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class StorageService {

    @Autowired
    StorageConfig storageConfig;

    public String getImage(String fileName) {
//        S3Objects s3Objects = S3Objects.inBucket(storageConfig.getS3client(), storageConfig.getBUCKET_NAME());

        List<S3ObjectSummary> list = storageConfig.getS3client().listObjects(storageConfig.getBUCKET_NAME()).getObjectSummaries();
        for(S3ObjectSummary e: list) {
            System.out.println(e.getKey());
            if (e.getKey().equals(fileName))
                return storageConfig.getS3client().getUrl(storageConfig.getBUCKET_NAME(), fileName).toString();
        }
        return "error";
    }

    public String putImage(String url){
        try {
            storageConfig.getS3client().putObject(storageConfig.getBUCKET_NAME(),url,new File(new URI(url)));
            return storageConfig.getS3client().getUrl(storageConfig.getBUCKET_NAME(),url).toString();
        } catch (URISyntaxException e) {
            return e.getMessage();
        }
    }
}
