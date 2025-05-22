package com.geology_platform.geology.projection;

/**
 * @author ELHIM Hamza
 **/
public interface ArticleProjection {

    Long getId();
    String getTitle();
    String getDescreption();
    String getVideoUrl();

    FileDataInfo getFileData();
    CoverImageInfo getCoverImage();
    CategoryInfo getCategory();

    interface FileDataInfo {
        String getName();
    }

    interface CoverImageInfo {
        String getName();
    }
    interface CategoryInfo {
        String getName();
    }

}
