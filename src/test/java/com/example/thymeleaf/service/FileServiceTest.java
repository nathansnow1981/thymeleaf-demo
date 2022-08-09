package com.example.thymeleaf.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceTest {

    @Autowired
    FileService fileService;

    @Test
    void getMediaType() {
        var fileName = "myFile.xlxs";
        System.out.println(fileService.getMediaType(fileName));
    }
}