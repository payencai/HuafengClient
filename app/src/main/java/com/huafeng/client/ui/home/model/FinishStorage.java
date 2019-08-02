package com.huafeng.client.ui.home.model;

/**
 * 作者：凌涛 on 2019/4/10 10:46
 * 邮箱：771548229@qq..com
 */
public class FinishStorage {

    /**
     * category1Name : string
     * category2Name : string
     * clientName : string
     * factoryId : 0
     * gmtCreate : 2019-06-17T07:01:38.265Z
     * gmtModified : 2019-06-17T07:01:38.265Z
     * quantity : 0
     * sampleId : 0
     * sampleNo : string
     * unitPrice : 0
     */
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String category1Name;
    private String category2Name;
    private String clientName;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private int quantity;
    private int sampleId;
    private String sampleNo;
    private Double unitPrice;

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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
