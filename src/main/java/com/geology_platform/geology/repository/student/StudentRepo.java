package com.geology_platform.geology.repository.student;

import com.geology_platform.geology.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
}
