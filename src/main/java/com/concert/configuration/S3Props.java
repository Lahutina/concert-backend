package com.concert.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "aws")
@Component
@Getter
@Setter
public class S3Props {
    private String s3Key;
    private String s3SecretKey;
    private String s3bucket;
}
