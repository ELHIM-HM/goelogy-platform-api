package com.geology_platform.geology.repository.collection;

import com.geology_platform.geology.entity.collection.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    boolean existsByName(String name);

    @EntityGraph(attributePaths = "subCategories")
    Optional<Category> findWithSubCategoriesByCategoryId(Long categoryId);

}
