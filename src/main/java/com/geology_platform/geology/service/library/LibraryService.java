package com.geology_platform.geology.service.library;

import com.geology_platform.geology.dto.request.library.RequestArticleCategory;
import com.geology_platform.geology.dto.request.library.RequestUpdateArticle;
import com.geology_platform.geology.entity.library.Article;
import com.geology_platform.geology.entity.library.ArticleCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/
public interface LibraryService {

    List<RequestArticleCategory> getAllCategories();

    List<Article> getArticlesForCategory(Integer page,Integer size,Long categoryId);

    void addCategory(RequestArticleCategory articleCategory);

    void addArticle(Long categoryId,Article article);

    void updateArticleById(Long id, RequestUpdateArticle udpdatedArticle);

    void removeArticleById(Long id);

}
