package com.example.secondcode.Configs;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MinioConfig {

    @Value("${obs.endpoint}")
    private String endpoint;

    @Value("${obs.accessKey}")
    private String accessKey;

    @Value("${obs.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient minioClient(){
        return new MinioClient.Builder()
                .endpoint(endpoint)
                .credentials(accessKey,secretKey)
                .build();
    }
}
