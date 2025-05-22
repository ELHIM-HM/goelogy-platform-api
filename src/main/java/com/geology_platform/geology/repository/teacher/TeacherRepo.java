package com.geology_platform.geology.repository.teacher;

import com.geology_platform.geology.entity.teacher.Teacher;
import com.geology_platform.geology.projection.TeacherProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ELHIM Hamza
 **/
@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Long> {

    Page<TeacherProjection> findAllByOrderByIdDesc(Pageable pageable);

    Page<TeacherProjection> findAllByGradeId(Pageable pageable,Long gradeId);

}
