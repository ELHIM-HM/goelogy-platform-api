package com.geology_platform.geology.service.library;

import com.geology_platform.geology.dto.request.library.RequestArticleCategory;
import com.geology_platform.geology.dto.request.library.RequestUpdateArticle;
import com.geology_platform.geology.entity.library.Article;
import com.geology_platform.geology.entity.library.ArticleCategory;

import com.geology_platform.geology.exception.collection.CategoryAlreadyExist;
import com.geology_platform.geology.exception.collection.CategoryNotFound;
import com.geology_platform.geology.exception.ibrary.ArticleNotFound;
import com.geology_platform.geology.mapper.library.ArticleCategoryMapper;
import com.geology_platform.geology.projection.ArticleProjection;
import com.geology_platform.geology.repository.FileDataRepo;
import com.geology_platform.geology.repository.library.ArticleCategoryRepo;
import com.geology_platform.geology.repository.library.ArticleRepo;
import com.geology_platform.geology.service.fileUpload.FileUploadServiceImpl;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Service
@AllArgsConstructor
public class LibraryServiceImpl implements LibraryService{

    private ArticleCategoryRepo articleCategoryRepo;
    private ArticleCategoryMapper articleCategoryMapper;
    private ArticleRepo articleRepo;
    private FileUploadServiceImpl fileUploadService;
    private FileDataRepo fileDataRepo;

    @Override
    public List<RequestArticleCategory> getAllCategories() {
        return articleCategoryMapper.toResponseList(articleCategoryRepo.findAll());
    }

    @Override
    public List<ArticleProjection> getArticles(Integer page, Integer size, Long categoryId) {

        if(page < 0) page = 0;
        if(size < 0) size = 10;

        Pageable pageable = PageRequest.of(page,size);

        Page<ArticleProjection> pageContent;

        if(categoryId!=null){
            pageContent = articleRepo.findAllByCategoryIdOrderByIdDesc(categoryId,pageable);
        }else {
            pageContent = articleRepo.findAllByOrderByIdDesc(pageable);
        }



        return pageContent.getContent();

    }

    @Transactional
    @Override
    public void addCategory(RequestArticleCategory articleCategory) {

        if(articleCategoryRepo.existsArticleCategoriesByName(articleCategory.getName())){
            throw new CategoryAlreadyExist(articleCategory.getName());
        }

        ArticleCategory category = new ArticleCategory();

        category.setName(articleCategory.getName());

        articleCategoryRepo.save(category);
    }

    @Transactional
    @Override
    public void addArticle(Long categoryId, Article article, MultipartFile cover, MultipartFile data) throws IOException {
        ArticleCategory category = articleCategoryRepo.findById(categoryId).orElseThrow(()-> new CategoryNotFound(categoryId));


        if(cover != null){

        article.setCoverImage(fileUploadService.uploadFileToFileSystem(cover));
        }

        if(data != null){
        article.setFileData(fileUploadService.uploadFileToFileSystem(data));
        }


        category.addArticle(article);

        articleCategoryRepo.save(category);
    }

    @Override
    public void updateArticleById(Long id, RequestUpdateArticle udpdatedArticle) {

        Article article = articleRepo.findById(id).orElseThrow(()->new ArticleNotFound(id));

        if(udpdatedArticle.getTitle() != null){
            article.setTitle(udpdatedArticle.getTitle());
        }

        if(udpdatedArticle.getDescreption() != null){
            article.setDescreption(udpdatedArticle.getDescreption());
        }

        articleRepo.save(article);

    }

    @Override
    public void removeArticleById(Long id) {
        articleRepo.deleteById(id);
    }
}
