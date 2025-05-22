package com.geology_platform.geology.repository.student;

import com.geology_platform.geology.entity.student.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface FormationRepo extends JpaRepository<Formation,Long> {
}
