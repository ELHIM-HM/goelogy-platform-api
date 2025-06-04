package com.geology_platform.geology.entity.classroom;

import com.geology_platform.geology.entity.FileData;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ELHIM Hamza
 **/

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;


    private String type;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private ClassSubject subject;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private FileData file;
}
