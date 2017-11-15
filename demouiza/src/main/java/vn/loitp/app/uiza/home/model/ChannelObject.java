package vn.loitp.app.uiza.home.model;

import java.util.List;

/**
 * Created by www.muathu@gmail.com on 11/15/2017.
 */

public class ChannelObject {
    private String channelName;
    private List<VideoObject> videoObjectList;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<VideoObject> getVideoObjectList() {
        return videoObjectList;
    }

    public void setVideoObjectList(List<VideoObject> videoObjectList) {
        this.videoObjectList = videoObjectList;
    }
}
