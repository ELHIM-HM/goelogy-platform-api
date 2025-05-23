package com.geology_platform.geology.service.fileUpload;

import com.geology_platform.geology.entity.FileData;
import com.geology_platform.geology.exception.global.ResourceNotFound;
import com.geology_platform.geology.repository.FileDataRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

/**

@author ELHIM Hamza
    
   **/
@Service
@AllArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private FileDataRepo fileDataRepo;
    private final String FOLDER_PATH = System.getProperty("user.dir")+ "/uploads/";

    @Override
    public FileData uploadFileToFileSystem(MultipartFile file) throws IOException {

        String filePath = FOLDER_PATH+System.currentTimeMillis()+file.getOriginalFilename();

        String fileName = System.currentTimeMillis()+file.getOriginalFilename();

        FileData fileData = FileData.builder()
                        .name(fileName)
                        .type(file.getContentType())
                        .filePath(filePath)
                .build();

        file.transferTo(new File(filePath));

        return fileData;
    }

    @Override
    public byte[] downloadFileFromFileSystem(String fileName) throws IOException {

        FileData dbFileData = fileDataRepo.findByName(fileName).orElseThrow(()->new ResourceNotFound("file not found",fileName));

        String filePath = dbFileData.getFilePath();

        byte[] file = Files.readAllBytes(new File(filePath).toPath());

        return file;
    }

    public String getfileContentType(String fileName){

        return  fileDataRepo.findByName(fileName).orElseThrow(()->new ResourceNotFound("file not found",fileName)).getType();

    }
}
