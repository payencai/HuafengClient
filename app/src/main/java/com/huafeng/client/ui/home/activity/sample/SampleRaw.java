package com.huafeng.client.ui.home.activity.sample;

public class SampleRaw {

    /**
     * quantity : 0.0
     * rawMaterialId : 0
     * rawMaterialName : string
     */


    public int getRawMaterialCategory2Id() {
        return rawMaterialCategory2Id;
    }

    public void setRawMaterialCategory2Id(int rawMaterialCategory2Id) {
        this.rawMaterialCategory2Id = rawMaterialCategory2Id;
    }

    private Double quantity;
    private int rawMaterialId;
    private int rawMaterialCategory2Id;
    private String rawMaterialName;
    private int category1Id;
    private String category1Name;
    private int category2Id;

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

    private String category2Name;
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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
