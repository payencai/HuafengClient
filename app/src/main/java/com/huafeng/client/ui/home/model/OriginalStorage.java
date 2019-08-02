package com.huafeng.client.ui.home.model;

/**
 * 作者：凌涛 on 2019/4/10 15:43
 * 邮箱：771548229@qq..com
 */
public class OriginalStorage {

    /**
     * category1Id : 0
     * category1Name : string
     * category2Id : 0
     * category2Name : string
     * image : string
     * imageUrl : string
     * inventoryQuantity : 0
     * latestPrice : 0.0
     * latestTime : 2019-06-04T02:06:54.388Z
     * materialId : 0
     * name : string
     */

    private int category1Id;
    private String category1Name;
    private int category2Id;
    private String category2Name;
    private String image;
    private String imageUrl;
    private double inventoryQuantity;
    private double latestPrice;
    private String latestTime;
    private int materialId;
    private String name;

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

    public double getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(double inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
