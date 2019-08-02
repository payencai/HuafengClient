package com.huafeng.client.ui.home.activity.origin;

public class OriginDataSubmit {

    /**
     * category1Id : 0
     * category2Id : 0
     * clothQuantity : string
     * description : string
     * image : string
     * name : string
     * normalQuantity : 0
     * purchaseType : 0
     * remarks : string
     * supplierId : 0
     * unitPrice : 0
     */

    private int category1Id;
    private int category2Id;
    private String clothQuantity;
    private String description;
    private String image;
    private String name;
    private Double normalQuantity;
    private int purchaseType;
    private String remarks;
    private int supplierId;
    private Double unitPrice;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNormalQuantity() {
        return normalQuantity;
    }

    public void setNormalQuantity(Double normalQuantity) {
        this.normalQuantity = normalQuantity;
    }

    public int getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(int purchaseType) {
        this.purchaseType = purchaseType;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
