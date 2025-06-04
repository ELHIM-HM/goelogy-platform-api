package com.geology_platform.geology.entity.teacher;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * @author ELHIM Hamza
 **/

@Data
@Entity
public class AccountValidationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code = RandomStringUtils.randomAlphanumeric(8).toLowerCase();



    private Long duration;

    private Date expiredDate;

}
