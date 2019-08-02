package com.huafeng.client.ui.home.model;

/**
 * 作者：凌涛 on 2019/4/10 15:30
 * 邮箱：771548229@qq..com
 */
public class HalfStorage {

    /**
     * category1Name : string
     * category2Name : string
     * clientName : string
     * factoryId : 0
     * gmtCreate : 2019-07-24T07:09:59.833Z
     * gmtModified : 2019-07-24T07:09:59.833Z
     * image : string
     * imageUrl : string
     * images : string
     * quantity : 0
     * sampleId : 0
     * sampleNo : string
     * unitPrice : 0
     */

    private String category1Name;
    private String category2Name;
    private String clientName;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private String image;
    private String imageUrl;
    private String images;
    private int quantity;
    private int sampleId;
    private String sampleNo;
    private double unitPrice;

    public String getCategory1Name() {
        return category1Name;
    }

    public void setCategory1Name(String category1Name) {
        this.category1Name = category1Name;
    }

    public String getCategory2Name() {
        return category2Name;
    }

    public void setCategory2Name(String category2Name) {
        this.category2Name = category2Name;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
