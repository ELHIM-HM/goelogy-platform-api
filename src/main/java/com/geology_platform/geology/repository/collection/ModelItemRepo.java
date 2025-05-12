package com.geology_platform.geology.repository.collection;

import com.geology_platform.geology.dto.response.collection.ResponseModelItem;
import com.geology_platform.geology.entity.collection.ModelItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface ModelItemRepo extends JpaRepository<ModelItem,Long> {


    @Query(value = "SELECT m from ModelItem m order by m.modelId desc ")
    Page<ModelItem> findModels(Pageable pageable);

    @Query(value = "SELECT * FROM model_item m WHERE subcategory_id = :subcategoryId ORDER BY model_id desc ",
            countQuery = "SELECT COUNT(*) FROM model_item WHERE subcategory_id = :subcategoryId",
            nativeQuery = true)
    Page<ModelItem> findModelsBySubcategoryId(@Param("subcategoryId") Long subcategoryId, Pageable pageable);

    List<ModelItem> findAllByCategoryName(String categoryName);

}
