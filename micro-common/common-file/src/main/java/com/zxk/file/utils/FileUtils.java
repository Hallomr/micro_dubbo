package com.zxk.file.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
@Slf4j
public class FileUtils {

    public static String customFileName(MultipartFile file){
        String original = file.getOriginalFilename();
        int index = original.lastIndexOf(".");
        return UUID.randomUUID().toString().replace("-","")
                + original.substring(index);
    }

    public static void clearFiles(String workspaceRootPath) {
        File file = new File(workspaceRootPath);
        deleteFile(file);
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
        }
        file.delete();
    }

    public static void unZip(MultipartFile  srcFile, String destDirPath,String savePath) throws RuntimeException, IOException {
        long startTime = System.currentTimeMillis();

        File file = null;
        InputStream ins = srcFile.getInputStream();
        file=new File(savePath+srcFile.getOriginalFilename());
        log.info("MultipartFile transform to File,MultipartFile name:"+srcFile.getOriginalFilename());
        inputStreamToFile(ins, file);

        if (!file.exists()) {
            throw new RuntimeException(file.getPath() + ",file is not found");
        }
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(file,Charset.forName("GBK"));
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                log.info("zipFile context name:"+entry.getName());
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + File.separator+ entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                }else {
                    File targetFile = new File(destDirPath + File.separator + entry.getName());
                    targetFile.setExecutable(true);
                    if(!targetFile.getParentFile().exists()){
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.close();
                    is.close();
                }
            }
            long endTime = System.currentTimeMillis();
            log.info("unZip time-->" + (endTime - startTime) + " ms");
        }catch(Exception e) {
            throw new RuntimeException("unzip error from FileUtil", e);
        } finally {
            if(zipFile != null){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            File del = new File(file.toURI());
            del.delete();
        }
    }

    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<File> getSubFiles(String  desFile, List<File> fileList) {
        File file = new File(desFile);
        File[] files = file.listFiles();
        for (File fileIndex : files) {
            if (!fileIndex.exists()) {
                throw new NullPointerException("Cannot find " + fileIndex);
            } else if (fileIndex.isFile()) {
                fileList.add(fileIndex);
            } else {
                if (fileIndex.isDirectory()) {
                    getSubFiles(fileIndex.getAbsolutePath(),fileList);
                }
            }
        }
        return fileList;
    }

    public List<File> getXlsFiles(String  desFile,List<File> fileList) {
        File file = new File(desFile);
        File[] files = file.listFiles();
        for (File fileIndex : files) {
            if (!fileIndex.exists()) {
                throw new NullPointerException("Cannot find " + fileIndex);
            } else if (fileIndex.isFile()&&(fileIndex.getName().toLowerCase().endsWith(".xls") || fileIndex.getName().toLowerCase().endsWith(".xlsx") )) {
                fileList.add(fileIndex);
            } else {
                if (fileIndex.isDirectory()) {
                    getXlsFiles(fileIndex.getAbsolutePath(),fileList);
                }
            }
        }
        return fileList;
    }

    public List<File> getImageFiles(String  desFile,List<File> fileList) {
        File file = new File(desFile);
        File[] files = file.listFiles();
        for (File fileIndex : files) {
            if (!fileIndex.exists()) {
                throw new NullPointerException("Cannot find " + fileIndex);
            } else if (fileIndex.isFile()&&fileIndex.getName().toLowerCase().endsWith(".jpg")) {
                fileList.add(fileIndex);
            } else {
                if (fileIndex.isDirectory()) {
                    getImageFiles(fileIndex.getAbsolutePath(),fileList);
                }
            }
        }
        return fileList;
    }
}
