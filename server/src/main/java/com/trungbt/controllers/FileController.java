package com.trungbt.controllers;

import com.trungbt.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/files")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        return file.getOriginalFilename();
    }

    @GetMapping("/files/{fileName}")
    public ResponseEntity<?> downloadFileByName(@PathVariable("fileName") String fileName) {
        Resource file = storageService.loadAsResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
