package com.corepateco.lib.main.model;

/**
 * Created by HieuHD on 8/7/2016.
 */

public class Package {


    /**
     * id : 9c02a48d-f080-11e6-827a-fa163ee27d4c
     * partnerPackageId : T1
     * method : mobifone
     * price : 2000
     * length : 1
     * trialDays : 0
     * nextPackageId : 9c02a48d-f080-11e6-827a-fa163ee27d4c
     * description : DK T1 gửi 9898
     * status : 1
     * createdAt : 2017-02-11T17:36:24.000Z
     * updatedAt : 2017-02-11T17:36:24.000Z
     */

    private String id;
    private String partnerPackageId;
    private String method;
    private int price;
    private int length;
    private int trialDays;
    private String nextPackageId;
    private String description;
    private int status;
    private String createdAt;
    private String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartnerPackageId() {
        return partnerPackageId;
    }

    public void setPartnerPackageId(String partnerPackageId) {
        this.partnerPackageId = partnerPackageId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTrialDays() {
        return trialDays;
    }

    public void setTrialDays(int trialDays) {
        this.trialDays = trialDays;
    }

    public String getNextPackageId() {
        return nextPackageId;
    }

    public void setNextPackageId(String nextPackageId) {
        this.nextPackageId = nextPackageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
