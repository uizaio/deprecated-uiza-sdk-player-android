package com.corepateco.lib.main.model;

import java.util.List;

/**
 * Created by HieuHD on 12/17/2016.
 */

public class HomeResponse {

    private List<BannerBean> banner;
    private List<RibbonBean> ribbon;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<RibbonBean> getRibbon() {
        return ribbon;
    }

    public void setRibbon(List<RibbonBean> ribbon) {
        this.ribbon = ribbon;
    }



}
