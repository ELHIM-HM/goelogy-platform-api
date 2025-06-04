package com.geology_platform.geology.dto.classroom;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ELHIM Hamza
 **/


@Getter
@Setter
@NoArgsConstructor
public class ReadMaterialDto {
    private Long id;

    private String title;

    private String description;


    private String type;

    private LocalDateTime createdAt ;

    private String fileName;

    public ReadMaterialDto(Long id, String title, String description, String type, LocalDateTime createdAt, String fileName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.createdAt = createdAt;
        this.fileName = fileName;
    }
}
