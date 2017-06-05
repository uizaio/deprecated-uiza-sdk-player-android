package com.corepateco.lib.main.model;

/**
 * Created by HieuHD on 12/17/2016.
 */
public class MenuBean {
    /**
     * id : 1
     * slug : page1
     * title : title
     * description : description
     * icon : icon
     */

    private String id;
    private String slug;
    private String title;
    private String description;
    private String icon;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}