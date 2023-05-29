package com.concert.services;

public interface S3Service {

    void putImage(String bucketName, String key, byte[] image);
}
