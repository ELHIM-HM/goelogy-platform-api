package com.geology_platform.geology.repository.teacher;

import com.geology_platform.geology.entity.teacher.AccountValidationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface CodeGenerationRepo extends JpaRepository<AccountValidationCode,Long> {
    String findByCode(String code);
}
