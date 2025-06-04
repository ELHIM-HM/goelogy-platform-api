package com.geology_platform.geology.controller;

import com.geology_platform.geology.service.fileUpload.FileUploadServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ELHIM Hamza
 **/

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class FileRestController {

    private FileUploadServiceImpl fileUploadService;


//    @PostMapping("/store-file")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void uploadFileToFileSystem(@RequestParam("image") MultipartFile file) throws IOException {
//
//        String uploadFile = fileUploadService.uploadFileToFileSystem(file);
//
//        System.out.println(uploadFile);
//
//    }

    @GetMapping(value = "/read-file/{fileName}")
    public ResponseEntity<?> readFileFromFileSystem(@PathVariable String fileName) throws IOException {

        String type = fileUploadService.getfileContentType(fileName);

        MediaType mediaType = MediaType.valueOf(type);

        return ResponseEntity.status(HttpStatus.OK).contentType(mediaType).body(fileUploadService.downloadFileFromFileSystem(fileName));


    }


}
