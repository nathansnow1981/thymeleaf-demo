package com.example.thymeleaf.controller;

import com.example.thymeleaf.config.AppConfig;
import com.example.thymeleaf.service.StudentCVService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
@Slf4j
@Controller
public class FileController {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private StudentCVService StudentCVService;

    /**
     * Default endpoint for visitors to the upload page.
     * All files are passed to the model for being displayed in the files table
     * @param model The context model for transporting data to and from the view template
     * @return the upload.html view template
     */
    @GetMapping("/upload")
    public String upload(Model model){
        model.addAllAttributes(Map.of(
                "siteTitle", appConfig.getAppDisplayName(),
                "pageTitle", "StudentCV Upload",
                "sizeLimit", appConfig.getUploadSizeLimit(),
                "files", StudentCVService.getAllCVs()
        ));
        return "/upload";
    }

    /**
     * Called after the file upload form has been submitted.
     * If a valid file is provided, it will be uploaded to system storage. For security measures
     * a log is placed noting the date, filename, and IP address of the uploading user.
     * @param file The file to be uploaded
     * @param redirectAttributes Values to be used after a redirection
     * @param request The context servlet request
     * @return the upload.html view template
     * @throws IOException Generic input/output exception for file handling
     */
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","Please select a file to upload");
            return "redirect:/upload";
        }
        var savedFile = StudentCVService.uploadCV(file);
        log.info(savedFile.getFilename() +" saved by client with IP = "+ request.getRemoteAddr());
        redirectAttributes.addFlashAttribute("message","You successfully uploaded " + savedFile.getFilename());
        return "redirect:/upload";
    }

    /**
     * Called when a file is requested for download. For security measures
     * a log is placed noting the date, filename of the file being downloaded,
     * and IP address of the downloading user.
     * @param id The id of the {@link com.example.thymeleaf.entity.StudentCV} entity.
     * @param request The context servlet request
     * @return A response entity having the required file download data
     * @throws IOException Generic input/output exception for file handling
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id, HttpServletRequest request) throws IOException {
        var file = StudentCVService.getFilepathById(id);
        log.info(file.getFilename() +" downloaded by client with IP = "+ request.getRemoteAddr());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"".concat(file.getFilename()).concat("\""))
                .body(Files.readAllBytes(Path.of(file.getFilepath())));

    }
}
