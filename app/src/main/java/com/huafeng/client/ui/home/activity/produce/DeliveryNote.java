package com.huafeng.client.ui.home.activity.produce;

import java.util.List;

public class DeliveryNote {

    /**
     * confirmBy : 0
     * confirmTime : 2019-06-10T06:57:29.072Z
     * executeTime : 2019-06-10T06:57:29.072Z
     * factoryId : 0
     * id : 0
     * lossQuantity : 0
     * noteNo : string
     * price : 0
     * processNameList : ["string"]
     * productStageId : 0
     * productionOrderFlowId : 0
     * productionOrderStageId : 0
     * quantity : 0
     * remarks : string
     * supplierId : 0
     * supplierName : string
     */

    private int confirmBy;
    private String confirmTime;
    private String executeTime;
    private int factoryId;
    private int id;
    private Integer lossQuantity;
    private String noteNo;
    private double price;
    private int productStageId;
    private int productionOrderFlowId;
    private int productionOrderStageId;
    private int quantity;
    private String remarks;
    private int supplierId;
    private String supplierName;
    private List<String> processNameList;

    public int getConfirmBy() {
        return confirmBy;
    }

    public void setConfirmBy(int confirmBy) {
        this.confirmBy = confirmBy;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLossQuantity() {
        return lossQuantity;
    }

    public void setLossQuantity(Integer lossQuantity) {
        this.lossQuantity = lossQuantity;
    }

    public String getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(String noteNo) {
        this.noteNo = noteNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductStageId() {
        return productStageId;
    }

    public void setProductStageId(int productStageId) {
        this.productStageId = productStageId;
    }

    public int getProductionOrderFlowId() {
        return productionOrderFlowId;
    }

    public void setProductionOrderFlowId(int productionOrderFlowId) {
        this.productionOrderFlowId = productionOrderFlowId;
    }

    public int getProductionOrderStageId() {
        return productionOrderStageId;
    }

    public void setProductionOrderStageId(int productionOrderStageId) {
        this.productionOrderStageId = productionOrderStageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<String> getProcessNameList() {
        return processNameList;
    }

    public void setProcessNameList(List<String> processNameList) {
        this.processNameList = processNameList;
    }
}
