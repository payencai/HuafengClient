package com.huafeng.client.ui.home.model;

/**
 * 作者：凌涛 on 2019/4/3 16:13
 * 邮箱：771548229@qq..com
 */
public class ProduceMana  {

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * currentFlowId : 0
     * estimatedTimeOfFinishment : 2019-05-08T11:35:27.877Z
     * finishTime : 2019-05-08T11:35:27.877Z
     * gmtCreate : 2019-05-08T11:35:27.877Z
     * id : 0
     * lossQuantity : 0
     * month : string
     * principal : string
     * productOrderNumber : string
     * quantity : 0
     * sampleNo : string
     * stageName : string
     * status : 0
     * stopTime : 2019-05-08T11:35:27.877Z
     */
    private String imageUrl;
    private int currentFlowId;
    private String estimatedTimeOfFinishment;
    private String finishTime;
    private String gmtCreate;
    private int id;
    private int lossQuantity;
    private String month;
    private String principal;
    private String productOrderNumber;
    private int quantity;
    private String sampleNo;
    private String stageName;
    private int status;
    private String stopTime;

    public int getCurrentFlowId() {
        return currentFlowId;
    }

    public void setCurrentFlowId(int currentFlowId) {
        this.currentFlowId = currentFlowId;
    }

    public String getEstimatedTimeOfFinishment() {
        return estimatedTimeOfFinishment;
    }

    public void setEstimatedTimeOfFinishment(String estimatedTimeOfFinishment) {
        this.estimatedTimeOfFinishment = estimatedTimeOfFinishment;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
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

    public int getLossQuantity() {
        return lossQuantity;
    }

    public void setLossQuantity(int lossQuantity) {
        this.lossQuantity = lossQuantity;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getProductOrderNumber() {
        return productOrderNumber;
    }

    public void setProductOrderNumber(String productOrderNumber) {
        this.productOrderNumber = productOrderNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }
}
