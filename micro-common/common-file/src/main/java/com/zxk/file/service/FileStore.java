package com.zxk.file.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileStore {
    String uploadFile(MultipartFile file, String bucketName);
    void downloadFile(String url, String fileName, String bucketName, HttpServletResponse response);
    byte[] bytes(String key,String bucket);
}
