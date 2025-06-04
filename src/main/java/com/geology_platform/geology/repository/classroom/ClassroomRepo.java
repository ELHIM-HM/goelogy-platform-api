package com.geology_platform.geology.repository.classroom;

import com.geology_platform.geology.entity.classroom.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface ClassroomRepo extends JpaRepository<Classroom,Long> {

    Optional<Classroom> findByJoinCode(String joinCode);

    boolean existsByJoinCode(String joinCode);


}
