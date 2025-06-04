package com.geology_platform.geology.entity.classroom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geology_platform.geology.entity.FileData;
import com.geology_platform.geology.entity.student.Student;
import com.geology_platform.geology.entity.teacher.Teacher;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.security.auth.Subject;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ELHIM Hamza
 **/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(unique = true)
    private String joinCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_data")
    private FileData image;

    private LocalDate createdAt = LocalDate.now();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    @ManyToMany(mappedBy = "classroomList",fetch = FetchType.LAZY)
    private Set<Student> studentList;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "classroom")
    private Set<ClassSubject> subjectList = new HashSet<>();


    public void addStudentToClass(Student student){
        studentList.add(student);
        student.getClassroomList().add(this);
    }

    public void addSubject(ClassSubject subject){
        subjectList.add(subject);
        subject.setClassroom(this);
    }

}
