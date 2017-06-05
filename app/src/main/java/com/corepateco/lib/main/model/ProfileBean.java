package com.corepateco.lib.main.model;

/**
 * Created by HieuHD on 8/7/2016.
 */

public  class ProfileBean {

    /**
     * id : bbbedd71-2aef-4489-9a07-94cc302654e1
     * mobile : 0906120521
     * email : ducbaovn@gmail.com
     * firstname : Bao
     * lastname : Nguyen
     * status : active
     * middlewareUser : 11
     * createdAt : 2016-10-04T01:44:27.000Z
     * updatedAt : 2017-02-07T14:46:03.000Z
     * active_package : {"id":"9c02a48d-f080-11e6-827a-fa163ee27d4c","partnerPackageId":"T1","method":"mobifone","price":2000,"length":1,"trialDays":0,"nextPackageId":"9c02a48d-f080-11e6-827a-fa163ee27d4c","description":"DK T1 gửi 9898","status":1,"createdAt":"2017-02-11T17:36:24.000Z","updatedAt":"2017-02-11T17:36:24.000Z","expiryDate":"2017-12-13T04:20:26.000Z"}
     */

    private String id;
    private String mobile;
    private String email;
    private String firstname;
    private String lastname;
    private String status;
    private String middlewareUser;
    private String createdAt;
    private String updatedAt;
    private ActivePackageBean active_package;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMiddlewareUser() {
        return middlewareUser;
    }

    public void setMiddlewareUser(String middlewareUser) {
        this.middlewareUser = middlewareUser;
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

    public ActivePackageBean getActive_package() {
        return active_package;
    }

    public void setActive_package(ActivePackageBean active_package) {
        this.active_package = active_package;
    }


}