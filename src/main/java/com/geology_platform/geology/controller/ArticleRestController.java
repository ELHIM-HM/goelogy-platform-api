package com.geology_platform.geology.controller;

import com.geology_platform.geology.dto.request.library.RequestArticleCategory;

import com.geology_platform.geology.dto.request.library.RequestUpdateArticle;
import com.geology_platform.geology.entity.library.Article;
import com.geology_platform.geology.projection.ArticleProjection;
import com.geology_platform.geology.service.fileUpload.FileUploadServiceImpl;
import com.geology_platform.geology.service.library.LibraryServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@AllArgsConstructor
@RestController
@RequestMapping("/library")
@Tag(name = "library api")
public class ArticleRestController {

    private LibraryServiceImpl libraryService;
    private FileUploadServiceImpl fileUploadService;


    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestArticleCategory> getAllCategories(){
        return libraryService.getAllCategories();
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCategory(@RequestBody RequestArticleCategory articleCategory){
         libraryService.addCategory(articleCategory);
    }

    @PostMapping("{categoryId}/articles")
    @ResponseStatus(HttpStatus.CREATED)
    public void addArticle(@PathVariable Long categoryId,
                           @RequestPart("article") Article article,
                           @RequestPart(value = "cover",required = false) MultipartFile cover,
                           @RequestPart(value = "data",required = false) MultipartFile data ) throws IOException {

         libraryService.addArticle(categoryId,article,cover,data);
    }

    @GetMapping("/articles")
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleProjection> getAllArticlesByCategoryId(
                                                              @RequestParam(required = false,defaultValue = "0") Integer page,
                                                              @RequestParam(required = false,defaultValue = "10") Integer size,
                                                              @RequestParam(required = false) Long categoryId
                                                              ){
        return libraryService.getArticles(page, size, categoryId);
    }

    @PatchMapping("/articles/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateArticleById(@PathVariable Long articleId,
                                                    @RequestBody RequestUpdateArticle article){
         libraryService.updateArticleById(articleId,article);
    }

    @DeleteMapping("/articles/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticleById(@PathVariable Long articleId){
        libraryService.removeArticleById(articleId);
    }









}



