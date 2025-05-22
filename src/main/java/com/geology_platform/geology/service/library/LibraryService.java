package com.geology_platform.geology.service.library;

import com.geology_platform.geology.dto.request.library.RequestArticleCategory;
import com.geology_platform.geology.dto.request.library.RequestUpdateArticle;
import com.geology_platform.geology.entity.library.Article;
import com.geology_platform.geology.projection.ArticleProjection;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ELHIM Hamza
 **/
public interface LibraryService {

    List<RequestArticleCategory> getAllCategories();

    List<ArticleProjection> getArticles(Integer page, Integer size, Long categoryId);

    void addCategory(RequestArticleCategory articleCategory);

    void addArticle(Long categoryId, Article article, MultipartFile cover,MultipartFile data) throws IOException;

    void updateArticleById(Long id, RequestUpdateArticle udpdatedArticle);

    void removeArticleById(Long id);

}
