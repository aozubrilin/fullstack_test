package ru.nelf.backend.entity;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class StorageConfig {
    private final String BUCKET_NAME = "bucket12345";

    private AmazonS3 s3client;

    @Value("${storage.id}")
    private String id;
    @Value("${storage.key}")
    private String key;

    @PostConstruct
    public void s3clientInit() {
        AwsClientBuilder.EndpointConfiguration ep = new AwsClientBuilder
                .EndpointConfiguration("storage.yandexcloud.net", "ru-central1");
        BasicAWSCredentials bAWSc = new BasicAWSCredentials(id, key);
        s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(bAWSc))
                .withEndpointConfiguration(ep).build();
    }
}
