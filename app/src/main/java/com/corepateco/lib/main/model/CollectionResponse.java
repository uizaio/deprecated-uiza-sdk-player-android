package com.corepateco.lib.main.model;

import java.util.List;

/**
 * Created by HieuHD on 12/17/2016.
 */

public class CollectionResponse {

    /**
     * total : 4
     * items : [{"id":"1","type":"channel","title":"title","description":"description","shortDescription":"shortDescription","icon":"icon","imgBanner":"","imgPortrait":"","imgLandscape":"","subTitle":"subTitle","profile":"sd","duration":0,"releaseDay":"2016-12-17T13:33:46.000Z","imdb":"0","vendor":"","relativeId":"1"},{"id":"2","type":"channel","title":"title","description":"description","shortDescription":"shortDescription","icon":"icon","imgBanner":"","imgPortrait":"","imgLandscape":"","subTitle":"subTitle","profile":"sd","duration":0,"releaseDay":"2016-12-17T13:33:46.000Z","imdb":"0","vendor":"","relativeId":"2"},{"id":"3","type":"video","title":"title","description":"description","shortDescription":"shortDescription","icon":"icon","imgBanner":"","imgPortrait":"","imgLandscape":"","subTitle":"subTitle","profile":"sd","duration":0,"releaseDay":"2016-12-17T13:33:46.000Z","imdb":"0","vendor":"","relativeId":"3"},{"id":"4","type":"video","title":"title","description":"description","shortDescription":"shortDescription","icon":"icon","imgBanner":"","imgPortrait":"","imgLandscape":"","subTitle":"subTitle","profile":"sd","duration":0,"releaseDay":"2016-12-17T13:33:46.000Z","imdb":"0","vendor":"","relativeId":"4"}]
     */

    private int total;
    private List<ItemsBean> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }


}
