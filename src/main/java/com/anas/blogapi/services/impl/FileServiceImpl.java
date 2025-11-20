package com.anas.blogapi.services.impl;

import com.anas.blogapi.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        String randomId = java.util.UUID.randomUUID().toString();
        String fileName1 = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

        String filePath = path + java.io.File.separator + fileName1;

        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String filePath = path + java.io.File.separator + fileName;
        InputStream inputStream = new FileInputStream(filePath);
        return inputStream;
    }
}
