package com.geology_platform.geology.repository.classroom;

import com.geology_platform.geology.dto.classroom.ClassroomInfoDto;
import com.geology_platform.geology.dto.classroom.ReadMaterialDto;
import com.geology_platform.geology.dto.classroom.ReadTeacherMaterialDto;
import com.geology_platform.geology.entity.classroom.ClassMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface ClassMaterialRepo extends JpaRepository<ClassMaterial,Long> {

    @Query("SELECT new com.geology_platform.geology.dto.classroom.ReadMaterialDto(" +
            "mtr.id, mtr.title , mtr.description , mtr.type , mtr.createdAt , f.name  ) " +
            "FROM ClassMaterial mtr " +
            "JOIN  mtr.file f  " +
            "WHERE mtr.subject.id = :subjectId")
    List<ReadMaterialDto> findClassMaterialBySubjectId(@Param("subjectId") Long subjectId);





    @Query("SELECT new com.geology_platform.geology.dto.classroom.ReadTeacherMaterialDto(" +
            "mtr.id, mtr.title , mtr.description , mtr.type , mtr.createdAt , f.name , sub.name  ) " +
            "FROM ClassMaterial mtr " +
            "LEFT JOIN  mtr.file f  " +
            "LEFT JOIN mtr.subject sub "+
            "WHERE sub.classroom.id = :classId order by mtr.createdAt desc ")
    List<ReadTeacherMaterialDto> findClassroomMaterialsByClassroomId(@Param("classId") Long classId);



}
