package com.zxk.file.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class FileUtils {

    public static String customFileName(MultipartFile file){
        String original = file.getOriginalFilename();
        int index = original.lastIndexOf(".");
        return UUID.randomUUID().toString().replace("-","")
                + original.substring(index);
    }
}
