package com.corepateco.lib.main.model;

import java.util.List;

/**
 * Created by HieuHD on 12/17/2016.
 */
public class RibbonBean {
    /**
     * id : 2
     * slug : collection2
     * display : portrait
     * title : title
     * name : name
     * description : description
     * icon : icon
     * position : ribbon
     * total : 4
     * items : [{"id":"1","type":"channel","title":"title","description":"description","shortDescription":"shortDescription","icon":"icon","imgBanner":"","imgPortrait":"","imgLandscape":"","subTitle":"subTitle","profile":"sd","duration":0,"releaseDay":"2016-12-17T13:33:46.000Z","imdb":"0","vendor":"","relativeId":"1"},{"id":"2","type":"channel","title":"title","description":"description","shortDescription":"shortDescription","icon":"icon","imgBanner":"","imgPortrait":"","imgLandscape":"","subTitle":"subTitle","profile":"sd","duration":0,"releaseDay":"2016-12-17T13:33:46.000Z","imdb":"0","vendor":"","relativeId":"2"},{"id":"3","type":"video","title":"title","description":"description","shortDescription":"shortDescription","icon":"icon","imgBanner":"","imgPortrait":"","imgLandscape":"","subTitle":"subTitle","profile":"sd","duration":0,"releaseDay":"2016-12-17T13:33:46.000Z","imdb":"0","vendor":"","relativeId":"3"},{"id":"4","type":"video","title":"title","description":"description","shortDescription":"shortDescription","icon":"icon","imgBanner":"","imgPortrait":"","imgLandscape":"","subTitle":"subTitle","profile":"sd","duration":0,"releaseDay":"2016-12-17T13:33:46.000Z","imdb":"0","vendor":"","relativeId":"4"}]
     */

    private String id;
    private String slug;
    private String display;
    private String title;
    private String name;
    private String description;
    private String icon;
    private String position;
    private int total;
    private List<ItemsBean> items;

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

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

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
