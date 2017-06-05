package com.corepateco.lib.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HieuHD on 8/3/2016.
 */

public  class FeaturedVideosBean implements Parcelable {


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

    private String id;
    private String title;
    private String is_folder;
    private String category_id;
    private String search;
    private boolean favorite;
    private int duration;
    private String poster;
    private int parental_control;
    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIs_folder() {
        return is_folder;
    }

    public void setIs_folder(String is_folder) {
        this.is_folder = is_folder;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getParental_control() {
        return parental_control;
    }

    public void setParental_control(int parental_control) {
        this.parental_control = parental_control;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.is_folder);
        dest.writeString(this.category_id);
        dest.writeString(this.search);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
        dest.writeInt(this.duration);
        dest.writeString(this.poster);
        dest.writeInt(this.parental_control);
        dest.writeString(this.category);
    }

    public FeaturedVideosBean() {
    }

    protected FeaturedVideosBean(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.is_folder = in.readString();
        this.category_id = in.readString();
        this.search = in.readString();
        this.favorite = in.readByte() != 0;
        this.duration = in.readInt();
        this.poster = in.readString();
        this.parental_control = in.readInt();
        this.category = in.readString();
    }

    public static final Creator<FeaturedVideosBean> CREATOR = new Creator<FeaturedVideosBean>() {
        @Override
        public FeaturedVideosBean createFromParcel(Parcel source) {
            return new FeaturedVideosBean(source);
        }

        @Override
        public FeaturedVideosBean[] newArray(int size) {
            return new FeaturedVideosBean[size];
        }
    };
}