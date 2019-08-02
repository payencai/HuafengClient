package com.huafeng.client.ui.home.activity.origin;

public class HalfCheckSubmit {

    /**
     * clothQuantity : 0
     * quantity : 0
     * remarks : string
     * repairQuantity : 0
     * sampleId : 0
     * sewQuantity : 0
     * washQuantity : 0
     */

    private int sampleId;
    private int quantity;
    private String remarks;
    private int repairQuantity;
    private int sewQuantity;
    private int washQuantity;
    private int clothQuantity;
    public int getClothQuantity() {
        return clothQuantity;
    }

    public void setClothQuantity(int clothQuantity) {
        this.clothQuantity = clothQuantity;
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

    public int getRepairQuantity() {
        return repairQuantity;
    }

    public void setRepairQuantity(int repairQuantity) {
        this.repairQuantity = repairQuantity;
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public int getSewQuantity() {
        return sewQuantity;
    }

    public void setSewQuantity(int sewQuantity) {
        this.sewQuantity = sewQuantity;
    }

    public int getWashQuantity() {
        return washQuantity;
    }

    public void setWashQuantity(int washQuantity) {
        this.washQuantity = washQuantity;
    }
}
