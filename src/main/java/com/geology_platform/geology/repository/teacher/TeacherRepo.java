package com.geology_platform.geology.repository.teacher;

import com.geology_platform.geology.dto.classroom.TeacherClassroomResponseDto;
import com.geology_platform.geology.dto.classroom.TeacherHerderInfoDto;
import com.geology_platform.geology.entity.teacher.Teacher;
import com.geology_platform.geology.projection.TeacherProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ELHIM Hamza
 **/
@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Long> {

    Page<TeacherProjection> findAllByOrderByIdDesc(Pageable pageable);

    Page<TeacherProjection> findAllByGradeId(Pageable pageable,Long gradeId);


    boolean existsByEmail(String email);

    Teacher findByEmail(String email);




    @Query("SELECT new com.geology_platform.geology.dto.classroom.TeacherHerderInfoDto(" +
            "COUNT(distinct c),COUNT(distinct s),COUNT(distinct sub),COUNT(distinct mat))" +
            "FROM Teacher t "+
            "LEFT JOIN t.classroomList c "+
            "LEFT JOIN c.studentList s "+
            "LEFT JOIN c.subjectList sub "+
            "LEFT JOIN sub.materialList mat "+
            "where t.email = :email"
    )
    TeacherHerderInfoDto findTeacherByEmailClassroomDetails(@Param("email") String email);


    @Query(
            "SELECT new com.geology_platform.geology.dto.classroom.TeacherClassroomResponseDto("+
                    "c.id,img.name,c.title,c.joinCode,COUNT(distinct s),COUNT(distinct sub),COUNT(distinct mat),c.createdAt) "+
                    "FROM Teacher t "+
                    "LEFT JOIN t.classroomList c "+
                    "LEFT JOIN c.studentList s "+
                    "LEFT JOIN c.subjectList sub "+
                    "LEFT JOIN sub.materialList mat "+
                    "LEFT JOIN c.image img "+
                    "where t.email=:email "+
                    "GROUP BY c.id, img.name, c.title, c.joinCode, c.createdAt"

    )
    List<TeacherClassroomResponseDto> findByTeacherEmailAllClassrooms(@Param("email") String email);

}
