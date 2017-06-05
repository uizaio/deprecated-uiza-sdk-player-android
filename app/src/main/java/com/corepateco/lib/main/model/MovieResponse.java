package com.corepateco.lib.main.model;

import java.util.List;

/**
 * Created by HieuHD on 8/16/2016.
 */

public class MovieResponse {


    /**
     * id : 544441
     * title : Biệt Đội Chống Tội Phạm
     * is_folder : 0
     * category_id : 11947
     * search : Biệt Đội Chống Tội Phạm
     * favorite : false
     * duration : 6420
     * poster : http://telebreeze.com/images/posters/544441_cb614976f77eb416ae951d0683258fd6_20160728103450_big.jpg
     * parental_control : 0
     * category : HÀNH ĐỘNG
     */

    private List<FeaturedVideosBean> videos;

    public List<FeaturedVideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<FeaturedVideosBean> videos) {
        this.videos = videos;
    }

}
