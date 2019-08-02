package com.huafeng.client.ui.home.model;

/**
 * 作者：凌涛 on 2019/4/4 15:42
 * 邮箱：771548229@qq..com
 */
public class Order {

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * id : 2
     * sampleNo : sample001
     * orderNumber : 654645324-6
     * factoryId : 3
     * createBy : 3
     * gmtCreate : 2019-04-28 19:09:41
     * clientRecordId : 5
     * clientRecordName : 123服饰
     * status : 2
     * unitPrice : 80
     * quantity : 1000
     */
    private String imageUrl;
    private int id;
    private String sampleNo;
    private String orderNumber;
    private int factoryId;
    private int createBy;
    private String gmtCreate;
    private int clientRecordId;
    private String clientRecordName;
    private int status;
    private double unitPrice;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getClientRecordId() {
        return clientRecordId;
    }

    public void setClientRecordId(int clientRecordId) {
        this.clientRecordId = clientRecordId;
    }

    public String getClientRecordName() {
        return clientRecordName;
    }

    public void setClientRecordName(String clientRecordName) {
        this.clientRecordName = clientRecordName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
