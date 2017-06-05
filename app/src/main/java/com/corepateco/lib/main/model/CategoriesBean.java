package com.corepateco.lib.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HieuHD on 8/3/2016.
 */

public class CategoriesBean implements Parcelable {


    private String name;
    private String type;
    private String id;
    private int parental_control;
    private String url;
    private String group;
    private int icon=0;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getParental_control() {
        return parental_control;
    }

    public void setParental_control(int parental_control) {
        this.parental_control = parental_control;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.id);
        dest.writeInt(this.parental_control);
        dest.writeString(this.url);
        dest.writeString(this.group);
    }

    public CategoriesBean() {
    }

    protected CategoriesBean(Parcel in) {
        this.name = in.readString();
        this.type = in.readString();
        this.id = in.readString();
        this.parental_control = in.readInt();
        this.url = in.readString();
        this.group = in.readString();
    }

    public static final Creator<CategoriesBean> CREATOR = new Creator<CategoriesBean>() {
        @Override
        public CategoriesBean createFromParcel(Parcel source) {
            return new CategoriesBean(source);
        }

        @Override
        public CategoriesBean[] newArray(int size) {
            return new CategoriesBean[size];
        }
    };
}
