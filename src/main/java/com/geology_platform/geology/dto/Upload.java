package com.geology_platform.geology.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ELHIM Hamza
 **/
@Data
public class Upload {
    private MultipartFile image;
}
