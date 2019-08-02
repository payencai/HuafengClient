package com.huafeng.client.ui.home.activity.order;

import java.io.Serializable;

public class OrderClothes implements Serializable  {

    /**
     * factoryId : 0
     * id : 0
     * orderSampleId : 0
     * quantity : 0
     * rawMaterialCategory1Id : 0
     * rawMaterialCategory1Name : string
     * rawMaterialCategory2Id : 0
     * rawMaterialCategory2Name : string
     * rawMaterialId : 0
     * rawMaterialName : string
     */

    private int factoryId;
    private int id;
    private int orderSampleId;
    private double quantity;
    private int rawMaterialCategory1Id;
    private String rawMaterialCategory1Name;
    private int rawMaterialCategory2Id;
    private String rawMaterialCategory2Name;
    private int rawMaterialId;
    private String rawMaterialName;

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

    public int getOrderSampleId() {
        return orderSampleId;
    }

    public void setOrderSampleId(int orderSampleId) {
        this.orderSampleId = orderSampleId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getRawMaterialCategory1Id() {
        return rawMaterialCategory1Id;
    }

    public void setRawMaterialCategory1Id(int rawMaterialCategory1Id) {
        this.rawMaterialCategory1Id = rawMaterialCategory1Id;
    }

    public String getRawMaterialCategory1Name() {
        return rawMaterialCategory1Name;
    }

    public void setRawMaterialCategory1Name(String rawMaterialCategory1Name) {
        this.rawMaterialCategory1Name = rawMaterialCategory1Name;
    }

    public int getRawMaterialCategory2Id() {
        return rawMaterialCategory2Id;
    }

    public void setRawMaterialCategory2Id(int rawMaterialCategory2Id) {
        this.rawMaterialCategory2Id = rawMaterialCategory2Id;
    }

    public String getRawMaterialCategory2Name() {
        return rawMaterialCategory2Name;
    }

    public void setRawMaterialCategory2Name(String rawMaterialCategory2Name) {
        this.rawMaterialCategory2Name = rawMaterialCategory2Name;
    }

    public int getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(int rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public String getRawMaterialName() {
        return rawMaterialName;
    }

    public void setRawMaterialName(String rawMaterialName) {
        this.rawMaterialName = rawMaterialName;
    }
}
