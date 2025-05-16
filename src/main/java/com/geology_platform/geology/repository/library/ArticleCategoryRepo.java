package com.geology_platform.geology.repository.library;

import com.geology_platform.geology.entity.library.Article;
import com.geology_platform.geology.entity.library.ArticleCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface ArticleCategoryRepo extends JpaRepository<ArticleCategory,Long> {
    boolean existsArticleCategoriesByName(String name);

}
