package com.geology_platform.geology.repository;

import com.geology_platform.geology.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface FileDataRepo extends JpaRepository<FileData,Long> {


    Optional<FileData> findByName(String fileName);


}
