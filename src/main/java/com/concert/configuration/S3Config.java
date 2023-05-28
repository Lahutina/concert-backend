package com.concert.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@RequiredArgsConstructor
public class S3Config {

    private final S3Props s3Props;

    @Bean
    public S3Client s3Client() {
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider
                .create(AwsBasicCredentials
                        .create(s3Props.getS3Key(), s3Props.getS3SecretKey()));
        return S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(staticCredentialsProvider)
                .build();
    }
}
