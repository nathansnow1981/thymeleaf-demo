package com.example.thymeleaf.service;

import com.example.thymeleaf.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private AppConfig appConfig;

    public String getMediaType(String filename){
        var extension = filename.substring(filename.lastIndexOf('.') + 1);
        return appConfig.getMimeMap().getOrDefault(extension, "application/octet-stream");
    }

}
