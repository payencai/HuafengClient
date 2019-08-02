package com.huafeng.client.ui.home.activity.buy;

public class StatisticsOrigin {

    /**
     * category1Id : 0
     * category1Name : string
     * category2Id : 0
     * category2Name : string
     * inPurchaseNumber : 0
     * inventoryNumber : 0
     * materialId : 0
     * materialName : string
     * toPurchaseNumber : 0
     */

    private int category1Id;
    private String category1Name;
    private int category2Id;
    private String category2Name;
    private double inPurchaseNumber;
    private double inventoryNumber;
    private int materialId;
    private String materialName;
    private double toPurchaseNumber;

    public int getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(int category1Id) {
        this.category1Id = category1Id;
    }

    public String getCategory1Name() {
        return category1Name;
    }

    public void setCategory1Name(String category1Name) {
        this.category1Name = category1Name;
    }

    public int getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(int category2Id) {
        this.category2Id = category2Id;
    }

    public String getCategory2Name() {
        return category2Name;
    }

    public void setCategory2Name(String category2Name) {
        this.category2Name = category2Name;
    }

    public double getInPurchaseNumber() {
        return inPurchaseNumber;
    }

    public void setInPurchaseNumber(double inPurchaseNumber) {
        this.inPurchaseNumber = inPurchaseNumber;
    }

    public double getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(double inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public double getToPurchaseNumber() {
        return toPurchaseNumber;
    }

    public void setToPurchaseNumber(double toPurchaseNumber) {
        this.toPurchaseNumber = toPurchaseNumber;
    }
}
