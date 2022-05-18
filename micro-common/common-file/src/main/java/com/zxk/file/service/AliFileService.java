package com.zxk.file.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.zxk.file.config.FileStoreProperties;
import com.zxk.file.utils.FileUtils;
import com.zxk.file.utils.FlieContentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Objects;

@Slf4j
@Service
public class AliFileService implements FileStore,ApplicationRunner {
    @Autowired
    private FileStoreProperties properties;

    private static OSS oss;

    @Override
    public String uploadFile(MultipartFile file, String bucketName) {
        String fileName = null;
        try (InputStream inputStream = file.getInputStream()) {
            fileName = FileUtils.customFileName(file);
            oss.putObject(bucketName, fileName, inputStream);
        } catch (Exception e) {
            log.info("文件上传异常：{}",e.getMessage());
        }
        // 关闭OSSClient。
        oss.shutdown();
        return "http://" + bucketName + "." + properties.getEndpoint() + "/" + fileName;
    }

    /**
     * @param key      例子：2021/11/01/e920b7e6-cdd1-4444-85b3-158f736c6887.xls
     * @param fileName
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    public void downloadFile(String key, String fileName, String bucketName, HttpServletResponse response){
        OSSObject ossObject = oss.getObject(bucketName, key);

        try (BufferedInputStream input = new BufferedInputStream(ossObject.getObjectContent());
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
        // 关闭OSSClient。
        oss.shutdown();
    }

    /**
     * @param key    例子：2021/11/01/e920b7e6-cdd1-4444-85b3-158f736c6887.xls
     * @param bucket
     * @return
     * @throws Exception
     */
    @Override
    public byte[] bytes(String key, String bucket) {
        byte[] bytes = new byte[0];
        OSSObject ossObject = oss.getObject(bucket, key);
        try (InputStream content = ossObject.getObjectContent()) {
            bytes = IOUtils.toByteArray(content);
        } catch (Exception e) {
            log.info("获取文件字节数组异常：{}",e.getMessage());
        }
        return bytes;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 创建OSSClient实例。
        if(Objects.isNull(oss)){
            oss = new OSSClientBuilder().build(properties.getEndpoint(), properties.getAccessKey(), properties.getSecretKey());
        }
    }
}




