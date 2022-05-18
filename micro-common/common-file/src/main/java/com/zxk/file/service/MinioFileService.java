package com.zxk.file.service;

import com.zxk.file.config.FileStoreProperties;
import com.zxk.file.utils.FileUtils;
import com.zxk.file.utils.FlieContentType;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

@Service
@Slf4j
public class MinioFileService implements FileStore, InitializingBean {
    @Autowired
    private FileStoreProperties properties;

    private MinioClient client;

    @Override
    public String uploadFile(MultipartFile file, String bucketName) {
        String fileName = null;
        try (InputStream inputStream = file.getInputStream()) {
            fileName = FileUtils.customFileName(file);
            client.putObject(PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(inputStream, inputStream.available(), -1)
                            .contentType(file.getContentType())
                            .build());
        } catch (Exception e) {
            log.info("文件上传异常：{}",e.getMessage());
        }
        return properties.getEndpoint() + "/" + bucketName + "/" + fileName;
    }

    @Override
    public void downloadFile(String key, String fileName, String bucketName,
                             HttpServletResponse response) {
        try (BufferedInputStream input = new BufferedInputStream(client.getObject(GetObjectArgs.builder()
                .bucket(bucketName).object(key).build()));
             ServletOutputStream outputStream = response.getOutputStream()){
            String suffix = key.substring(key.lastIndexOf("."));
            String type = FlieContentType.getContentType(suffix);
            if (StringUtils.isNotBlank(type)) {
                response.setContentType(type);
            } else {
                response.setContentType("application/octet-stream");
            }

            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + suffix, "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            byte[] buffBytes = new byte[1024];
            int read;
            while ((read = input.read(buffBytes)) != -1) {
                outputStream.write(buffBytes, 0, read);
            }
        } catch (Exception e) {
            log.info("文件下载异常：{}",e.getMessage());
        }
    }

    @Override
    public byte[] bytes(String key, String bucket) {
        byte[] bytes = new byte[0];
        try (InputStream content = client.getObject(GetObjectArgs.builder()
                .bucket(bucket).object(key).build())) {
            bytes = IOUtils.toByteArray(content);
        } catch (Exception e) {
            log.info("获取文件字节数组异常：{}",e.getMessage());
        }
        return bytes;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(properties.getEndpoint(), "Minio url 为空");
        Assert.hasText(properties.getAccessKey(), "Minio accessKey为空");
        Assert.hasText(properties.getSecretKey(), "Minio secretKey为空");
        this.client = MinioClient.builder().endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey()).build();
    }
}
