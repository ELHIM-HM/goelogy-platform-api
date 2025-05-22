package com.geology_platform.geology.repository.library;

import com.geology_platform.geology.entity.library.Article;
import com.geology_platform.geology.projection.ArticleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ELHIM Hamza
 **/
@Repository
public interface ArticleRepo extends JpaRepository<Article,Long> {




    Page<ArticleProjection> findAllByOrderByIdDesc(Pageable pageable);
    Page<ArticleProjection> findAllByCategoryIdOrderByIdDesc(Long categoryId,Pageable pageable);
}
