package com.corepateco.lib.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TelcomTV on 12/22/2016.
 */

public class DetailsResponse implements Parcelable {


    /**
     * id : 0415639f-ed5b-4b8a-820d-dfe17fe23798
     * slug : camera-giau-kin
     * type : episode
     * title : Camera Giấu Kín
     * description :
     * shortDescription :
     * status : 1
     * imgBanner :
     * imgPortrait :
     * imgLandscape :
     * subTitle : Camera Giấu Kín
     * profile : hd
     * duration : 0
     * releaseDate :
     * imdb : 0
     * parentId :
     * totalChild : 0
     * currentChild : 5
     * order : 0
     * vendor : Mobitv
     * relativeId :
     * createdAt : 2016-12-19T20:48:55.000Z
     * updatedAt : 2016-12-19T20:53:34.000Z
     */

    private String id;
    private String slug;
    private String type;
    private String title;
    private String description;
    private String shortDescription;
    private int status;
    private String imgBanner;
    private String imgPortrait;
    private String imgLandscape;
    private String subTitle;
    private String profile;
    private int duration;
    private String releaseDate;
    private String imdb;
    private String parentId;
    private int totalChild;
    private int currentChild;
    private int order;
    private String vendor;
    private String relativeId;
    private String createdAt;
    private String updatedAt;
    private int favorite;

    public int isFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getTotalChild() {
        return totalChild;
    }

    public void setTotalChild(int totalChild) {
        this.totalChild = totalChild;
    }

    public int getCurrentChild() {
        return currentChild;
    }

    public void setCurrentChild(int currentChild) {
        this.currentChild = currentChild;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(String relativeId) {
        this.relativeId = relativeId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DetailsResponse() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.slug);
        dest.writeString(this.type);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.shortDescription);
        dest.writeInt(this.status);
        dest.writeString(this.imgBanner);
        dest.writeString(this.imgPortrait);
        dest.writeString(this.imgLandscape);
        dest.writeString(this.subTitle);
        dest.writeString(this.profile);
        dest.writeInt(this.duration);
        dest.writeString(this.releaseDate);
        dest.writeString(this.imdb);
        dest.writeString(this.parentId);
        dest.writeInt(this.totalChild);
        dest.writeInt(this.currentChild);
        dest.writeInt(this.order);
        dest.writeString(this.vendor);
        dest.writeString(this.relativeId);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeInt(this.favorite);
    }

    protected DetailsResponse(Parcel in) {
        this.id = in.readString();
        this.slug = in.readString();
        this.type = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.shortDescription = in.readString();
        this.status = in.readInt();
        this.imgBanner = in.readString();
        this.imgPortrait = in.readString();
        this.imgLandscape = in.readString();
        this.subTitle = in.readString();
        this.profile = in.readString();
        this.duration = in.readInt();
        this.releaseDate = in.readString();
        this.imdb = in.readString();
        this.parentId = in.readString();
        this.totalChild = in.readInt();
        this.currentChild = in.readInt();
        this.order = in.readInt();
        this.vendor = in.readString();
        this.relativeId = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.favorite = in.readInt();
    }

    public static final Creator<DetailsResponse> CREATOR = new Creator<DetailsResponse>() {
        @Override
        public DetailsResponse createFromParcel(Parcel source) {
            return new DetailsResponse(source);
        }

        @Override
        public DetailsResponse[] newArray(int size) {
            return new DetailsResponse[size];
        }
    };
}
