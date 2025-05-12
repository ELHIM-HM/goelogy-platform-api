package com.geology_platform.geology.repository.collection;

import com.geology_platform.geology.entity.collection.MineraisProperties;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ELHIM Hamza
 **/
public interface MineraisRepo extends JpaRepository<MineraisProperties,Long> {
}
