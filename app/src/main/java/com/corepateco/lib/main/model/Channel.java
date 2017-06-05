package com.corepateco.lib.main.model;

import java.util.List;

/**
 * Created by HieuHD on 8/3/2016.
 */

public class Channel {


    /**
     * id : 76140
     * lang : Vietnamese
     * name : can_tho
     * force_live_url :
     * category : ĐỊA PHƯƠNG
     * parental_control : 0
     * description : Kênh tổng hợp của Đài Phát thanh và Truyền hình Cần Thơ
     * title : Can Tho
     * url : http://103.205.104.20/can_tho.m3u8?user=112511&token=98016dde329ef09a7f1a291af83e246a&e=1471186317&bands=956
     * duration : 86400
     * thumb : http://telebreeze.com/images/channels/76140_1468835454_small.jpg
     * favorite : false
     * token : 1471186317-ec5829fb49e43b8af7c61cd7015760b2
     * type : video+audio
     * epg_start : 0
     * epg_end : 0
     */

    private List<ChannelsBean> channels;

    public List<ChannelsBean> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelsBean> channels) {
        this.channels = channels;
    }

   }
