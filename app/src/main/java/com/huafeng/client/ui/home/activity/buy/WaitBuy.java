package com.huafeng.client.ui.home.activity.buy;

public class WaitBuy {

    /**
     * factoryId : 0
     * gmtCreate : 2019-06-13T09:22:45.580Z
     * id : 0
     * orderId : 0
     * orderNumber : string
     * productionOrderId : 0
     * productionOrderNumber : string
     * sampleNo : string
     * type : 0
     */

    private int factoryId;
    private String gmtCreate;
    private int id;
    private int orderId;
    private String orderNumber;
    private int productionOrderId;
    private String productionOrderNumber;
    private String sampleNo;
    private int type;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getProductionOrderId() {
        return productionOrderId;
    }

    public void setProductionOrderId(int productionOrderId) {
        this.productionOrderId = productionOrderId;
    }

    public String getProductionOrderNumber() {
        return productionOrderNumber;
    }

    public void setProductionOrderNumber(String productionOrderNumber) {
        this.productionOrderNumber = productionOrderNumber;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
