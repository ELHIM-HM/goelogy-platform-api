package com.geology_platform.geology.repository.student;

import com.geology_platform.geology.dto.classroom.ClassroomInfoDto;
import com.geology_platform.geology.entity.student.Student;
import com.geology_platform.geology.projection.StudentProjection;
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
public interface StudentRepo extends JpaRepository<Student,Long> {

    Page<StudentProjection> findAllByOrderByIdDesc(Pageable pageable);


    Page<StudentProjection> findAllByBrancheIdOrderByIdDesc(Pageable pageable,Long brancheId);

    Boolean existsByEmail(String email);


    @Query("SELECT new com.geology_platform.geology.dto.classroom.ClassroomInfoDto(" +
            "c.id, c.title , CONCAT(t.firstName, ' ', t.lastName), SIZE(c.studentList) , im.name)" +
            "FROM Student s " +
            "JOIN s.classroomList c " +
            "JOIN c.teacher t " +
            "LEFT JOIN c.image im "+
            "WHERE s.email = :email")
    List<ClassroomInfoDto> findClassroomInfoByStudentEmail(@Param("email") String email);

    Optional<Student> findByEmail(String studentEmail);
}
