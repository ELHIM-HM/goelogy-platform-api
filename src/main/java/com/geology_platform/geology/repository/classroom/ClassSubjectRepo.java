package com.geology_platform.geology.repository.classroom;

import com.geology_platform.geology.dto.classroom.ReadMaterialDto;
import com.geology_platform.geology.entity.classroom.ClassSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface ClassSubjectRepo extends JpaRepository<ClassSubject,Long> {


    boolean existsByName(String name);
}
