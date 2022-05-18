package com.zxk.file.config;

import com.zxk.file.service.AliFileService;
import com.zxk.file.service.FileStore;
import com.zxk.file.service.MinioFileService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class FileStoreConfig {

    @Bean
    @Primary
    @ConditionalOnProperty(name = "file.store.type", havingValue = "ali")
    public FileStore aliFileStore() {
        return new AliFileService();
    }

    @Bean
    @Primary
    @ConditionalOnProperty(name = "file.store.type", havingValue = "minio")
    public FileStore awsFileStore() {
        return new MinioFileService();
    }
}