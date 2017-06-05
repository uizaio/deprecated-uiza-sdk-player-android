package com.corepateco.lib.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HieuHD on 8/3/2016.
 */

public  class ChannelsBean implements Parcelable {


    private String id;
    private String lang;
    private String name;
    private String force_live_url;
    private String category;
    private String parental_control;
    private String description;
    private String title;
    private String url;
    private int duration;
    private String thumb;
    private boolean favorite;
    private String token;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForce_live_url() {
        return force_live_url;
    }

    public void setForce_live_url(String force_live_url) {
        this.force_live_url = force_live_url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParental_control() {
        return parental_control;
    }

    public void setParental_control(String parental_control) {
        this.parental_control = parental_control;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.lang);
        dest.writeString(this.name);
        dest.writeString(this.force_live_url);
        dest.writeString(this.category);
        dest.writeString(this.parental_control);
        dest.writeString(this.description);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeInt(this.duration);
        dest.writeString(this.thumb);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
        dest.writeString(this.token);
        dest.writeString(this.type);
    }

    public ChannelsBean() {
    }

    protected ChannelsBean(Parcel in) {
        this.id = in.readString();
        this.lang = in.readString();
        this.name = in.readString();
        this.force_live_url = in.readString();
        this.category = in.readString();
        this.parental_control = in.readString();
        this.description = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.duration = in.readInt();
        this.thumb = in.readString();
        this.favorite = in.readByte() != 0;
        this.token = in.readString();
        this.type = in.readString();
    }

    public static final Creator<ChannelsBean> CREATOR = new Creator<ChannelsBean>() {
        @Override
        public ChannelsBean createFromParcel(Parcel source) {
            return new ChannelsBean(source);
        }

        @Override
        public ChannelsBean[] newArray(int size) {
            return new ChannelsBean[size];
        }
    };
}

