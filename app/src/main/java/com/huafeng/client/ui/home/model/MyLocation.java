package com.huafeng.client.ui.home.model;

/**
 * 作者：凌涛 on 2019/5/13 21:07
 * 邮箱：771548229@qq..com
 */
public class MyLocation {

    /**
     * address : string
     * distance : 0
     * factoryId : 0
     * gmtCreate : 2019-05-13T13:06:45.898Z
     * gmtModified : 2019-05-13T13:06:45.898Z
     * id : 0
     * latitude : string
     * longitude : string
     * remarks : string
     */

    private String address;
    private double distance;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private int id;
    private String latitude;
    private String longitude;
    private String remarks;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
