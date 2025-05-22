package com.geology_platform.geology.service.fileUpload;

import com.geology_platform.geology.entity.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ELHIM Hamza
 **/
public interface FileUploadService {


    FileData uploadFileToFileSystem(MultipartFile file) throws IOException;

    byte[] downloadFileFromFileSystem(String  fileName) throws IOException;

}
