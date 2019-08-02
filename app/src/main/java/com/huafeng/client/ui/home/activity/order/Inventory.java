package com.huafeng.client.ui.home.activity.order;

public class Inventory {

    /**
     * category1Id : 0
     * category2Id : 0
     * clothQuantity : string
     * factoryId : 0
     * freeQuantity : 0
     * materialId : 0
     * normalQuantity : 0
     */

    private int category1Id;
    private int category2Id;
    private String clothQuantity;

    public int getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(int category1Id) {
        this.category1Id = category1Id;
    }

    public int getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(int category2Id) {
        this.category2Id = category2Id;
    }

    public String getClothQuantity() {
        return clothQuantity;
    }

    public void setClothQuantity(String clothQuantity) {
        this.clothQuantity = clothQuantity;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public double getFreeQuantity() {
        return freeQuantity;
    }

    public void setFreeQuantity(double freeQuantity) {
        this.freeQuantity = freeQuantity;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public double getNormalQuantity() {
        return normalQuantity;
    }

    public void setNormalQuantity(double normalQuantity) {
        this.normalQuantity = normalQuantity;
    }

    private int factoryId;
    private double freeQuantity;
    private int materialId;
    private double normalQuantity;


}
