package com.geology_platform.geology.repository.collection;

import com.geology_platform.geology.entity.collection.FossilProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface FossilPropRepo extends JpaRepository<FossilProperties,Long> {
}
