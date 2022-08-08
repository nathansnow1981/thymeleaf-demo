package com.example.thymeleaf.service;

import com.example.thymeleaf.config.AppConfig;
import com.example.thymeleaf.entity.StudentCV;
import com.example.thymeleaf.repository.StudentCVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class StudentCVService {

    @Autowired
    private StudentCVRepository studentCVRepository;
    @Autowired
    private AppConfig appConfig;

    public StudentCV addCV(StudentCV cv){
        return studentCVRepository.save(cv);
    }

    public List<StudentCV> getAllCVs() {
        return studentCVRepository.findAll();
    }

    public StudentCV uploadCV(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(appConfig.getUploadPath().concat(Objects.requireNonNull(file.getOriginalFilename())));
        Files.write(path, bytes);
        var uploadedCV = new StudentCV(null, file.getOriginalFilename(), path.toString());
        return addCV(uploadedCV);
    }

    public StudentCV getFilepathById(Long id) {
        return studentCVRepository.findById(id).orElseThrow();
    }
}
