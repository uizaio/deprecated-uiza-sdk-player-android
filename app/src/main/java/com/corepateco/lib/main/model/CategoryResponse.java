package com.corepateco.lib.main.model;

import java.util.List;

/**
 * Created by HieuHD on 8/16/2016.
 */

public class CategoryResponse {

    /**
     * name : All Videos
     * type : vod
     * id : *
     * parental_control : 0
     * url : http://telebreeze.com/videos/list?category=*
     * group : *
     */

    private List<CategoriesBean> categories;

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    }
