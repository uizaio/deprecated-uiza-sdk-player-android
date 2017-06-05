package com.corepateco.lib.main.model;

import java.util.List;

/**
 * Created by HieuHD on 8/4/2016.
 */
public class MovieDetail {


    /**
     * id : 544317
     * poster : http://telebreeze.com/images/posters/544317_bfe49ebed3136ed84d005e5c541ed903_20160722064830_big.jpg
     * title : Lão Vệ Sĩ
     * category : HÀNH ĐỘNG
     * language : Chinese
     * director :
     * acting : Hồng Kim Bảo , Lưu Đức Hoa , Trần Phái Nghiên
     * description : Phim Lão Vệ Sĩ - Ông Nội Tôi Là Đặc Công - My Beloved Bodyguard 2016: xoay quanh quá trình cứu "đứa cháu rơi" Tiểu Xuân Hoa (Trần Phái Nghiên) của Đinh Hổ (Hồng Kim Bảo). Cô là con gái của Lý Chính Cửu (Lưu Đức Hoa), ở căn nhà đối diện với gia đình lão Đinh. Trong một lúc thất thần, Đinh Hổ đã để lạc mất cô cháu gái thân yêu của mình và được Xuân Hoa an ủi. Sau này, Xuân Hoa vướng vào việc hắc đạo phân tranh nên lão Đinh quyết định ra tay giúp đỡ, cũng đồng thời tìm lại được cháu gái ruột của mình.
     * details :
     * price : 0
     * currency : USD
     * tags :
     * year : 2016
     * month : 4
     * day : 1
     * rating : 2.5
     * duration : 99
     * views_count : 11
     * num_purchased : 0
     * days_remaining :
     * protection_type :
     * favorite : false
     * url : http://103.205.104.207/vod/my_beloved_bodyguard2.m3u8?token=dfd2076dbdf97e5f2cc7c974eb68d365&user=107387&e=1470391013
     * trailer_url :
     * tracks : [{"id":"153010","type":"0","button_title":"Xem Ngay","url":"http://103.205.104.207/vod/my_beloved_bodyguard2.m3u8?token=dfd2076dbdf97e5f2cc7c974eb68d365&user=107387&e=1470391013","srt":""}]
     */

    private String id;
    private String poster;
    private String title;
    private String category;
    private String language;
    private String director;
    private String acting;
    private String description;
    private String details;
    private String price;
    private String currency;
    private String tags;
    private String year;
    private String month;
    private String day;
    private String rating;
    private String duration;
    private String views_count;
    private String num_purchased;
    private String days_remaining;
    private String protection_type;
    private boolean favorite;
    private String url;
    private String trailer_url;
    /**
     * id : 153010
     * type : 0
     * button_title : Xem Ngay
     * url : http://103.205.104.207/vod/my_beloved_bodyguard2.m3u8?token=dfd2076dbdf97e5f2cc7c974eb68d365&user=107387&e=1470391013
     * srt :
     */

    private List<TracksBean> tracks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActing() {
        return acting;
    }

    public void setActing(String acting) {
        this.acting = acting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getViews_count() {
        return views_count;
    }

    public void setViews_count(String views_count) {
        this.views_count = views_count;
    }

    public String getNum_purchased() {
        return num_purchased;
    }

    public void setNum_purchased(String num_purchased) {
        this.num_purchased = num_purchased;
    }

    public String getDays_remaining() {
        return days_remaining;
    }

    public void setDays_remaining(String days_remaining) {
        this.days_remaining = days_remaining;
    }

    public String getProtection_type() {
        return protection_type;
    }

    public void setProtection_type(String protection_type) {
        this.protection_type = protection_type;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTrailer_url() {
        return trailer_url;
    }

    public void setTrailer_url(String trailer_url) {
        this.trailer_url = trailer_url;
    }

    public List<TracksBean> getTracks() {
        return tracks;
    }

    public void setTracks(List<TracksBean> tracks) {
        this.tracks = tracks;
    }

    public static class TracksBean {
        private String id;
        private String type;
        private String button_title;
        private String url;
        private String srt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getButton_title() {
            return button_title;
        }

        public void setButton_title(String button_title) {
            this.button_title = button_title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSrt() {
            return srt;
        }

        public void setSrt(String srt) {
            this.srt = srt;
        }
    }
}
