package com.corepateco.lib.main.model;

import java.util.List;

/**
 * Created by HieuHD on 9/28/2016.
 */

public class EpgResponse {


    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * id : 343f9251-c515-4bc7-bb7c-32a2690d0d10
         * slug : vtv1
         * title : VTV1
         * imgBanner : http://static.mobitv.online/6a111bbb-bf8a-4057-b84b-1ce1794e2640.jpg
         * imgPortrait : http://static.mobitv.online/71f1bd88-1fb3-4c0a-acb8-6c1501c384b0.jpg
         * imgLandscape : http://static.mobitv.online/4e8f36fe-f69b-4ba6-8eed-0c21a9ac3c58.jpg
         * relativeId : 24
         * programs : [{"id":"11979","start_at":"1484929800","stop_at":"1484931300","channel_profile_id":"24","title":"THẾ GIỚI 24H CHUYỂN ĐỘNG","watching":"0","description":""}]
         */

        private String id;
        private String slug;
        private String title;
        private String imgBanner;
        private String imgPortrait;
        private String imgLandscape;
        private int relativeId;
        private String icon;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        private List<Epg> programs;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgBanner() {
            return imgBanner;
        }

        public void setImgBanner(String imgBanner) {
            this.imgBanner = imgBanner;
        }

        public String getImgPortrait() {
            return imgPortrait;
        }

        public void setImgPortrait(String imgPortrait) {
            this.imgPortrait = imgPortrait;
        }

        public String getImgLandscape() {
            return imgLandscape;
        }

        public void setImgLandscape(String imgLandscape) {
            this.imgLandscape = imgLandscape;
        }

        public int getRelativeId() {
            return relativeId;
        }

        public void setRelativeId(int relativeId) {
            this.relativeId = relativeId;
        }

        public List<Epg> getPrograms() {
            return programs;
        }

        public void setPrograms(List<Epg> programs) {
            this.programs = programs;
        }


    }
}
