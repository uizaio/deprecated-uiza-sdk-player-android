package com.corepateco.lib.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HieuHD on 9/28/2016.
 */

public class Epg implements Parcelable {


    /**
     * id : 3222757
     * start_at : 1474882200
     * stop_at : 1474884000
     * channel_profile_id : 76076
     * title : Đồng tiền thông minh: Ông Lê H..
     * watching : 0
     * description :
     */

    private String id;
    private String start_at;
    private String stop_at;
    private String channel_profile_id;
    private String title;
    private String watching;
    private String description;
    boolean live;
    String url;
    private int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public String getStop_at() {
        return stop_at;
    }

    public void setStop_at(String stop_at) {
        this.stop_at = stop_at;
    }

    public String getChannel_profile_id() {
        return channel_profile_id;
    }

    public void setChannel_profile_id(String channel_profile_id) {
        this.channel_profile_id = channel_profile_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWatching() {
        return watching;
    }

    public void setWatching(String watching) {
        this.watching = watching;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Epg() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.start_at);
        dest.writeString(this.stop_at);
        dest.writeString(this.channel_profile_id);
        dest.writeString(this.title);
        dest.writeString(this.watching);
        dest.writeString(this.description);
        dest.writeByte(this.live ? (byte) 1 : (byte) 0);
        dest.writeString(this.url);
        dest.writeInt(this.pos);
    }

    protected Epg(Parcel in) {
        this.id = in.readString();
        this.start_at = in.readString();
        this.stop_at = in.readString();
        this.channel_profile_id = in.readString();
        this.title = in.readString();
        this.watching = in.readString();
        this.description = in.readString();
        this.live = in.readByte() != 0;
        this.url = in.readString();
        this.pos = in.readInt();
    }

    public static final Creator<Epg> CREATOR = new Creator<Epg>() {
        @Override
        public Epg createFromParcel(Parcel source) {
            return new Epg(source);
        }

        @Override
        public Epg[] newArray(int size) {
            return new Epg[size];
        }
    };
}
