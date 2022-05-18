package com.zxk.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "file.oss")
@Component
@Data
public class FileStoreProperties {
    private String accessKey;
    private String secretKey;
    //阿里云endpoint路径
    private String endpoint;
}
