package com.huafeng.client.ui.home.activity.origin;

import java.util.List;

public class SendGoodsSubmit {

    /**
     * address : string
     * amount : 0
     * carrier : string
     * contactNumber : string
     * dispatchQuantity : 0
     * dispatchType : 0
     * freightCharge : 0
     * images : string
     * name : string
     * price : 0
     * priceType : 0
     * remarks : string
     * sampleId : 0
     * sizeList : [{"quantity":0,"sizeId":0}]
     * trackingNumber : string
     */

    private String address;
    private double amount;
    private String carrier;
    private String contactNumber;
    private int dispatchQuantity;
    private int dispatchType;
    private double freightCharge;
    private String images;
    private String name;
    private double price;
    private int priceType;
    private String remarks;
    private int sampleId;
    private String trackingNumber;
    private List<SizeListBean> sizeList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getDispatchQuantity() {
        return dispatchQuantity;
    }

    public void setDispatchQuantity(int dispatchQuantity) {
        this.dispatchQuantity = dispatchQuantity;
    }

    public int getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(int dispatchType) {
        this.dispatchType = dispatchType;
    }

    public double getFreightCharge() {
        return freightCharge;
    }

    public void setFreightCharge(double freightCharge) {
        this.freightCharge = freightCharge;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public List<SizeListBean> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<SizeListBean> sizeList) {
        this.sizeList = sizeList;
    }

    public static class SizeListBean {
        /**
         * quantity : 0
         * sizeId : 0
         */

        private int quantity;
        private int sizeId;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getSizeId() {
            return sizeId;
        }

        public void setSizeId(int sizeId) {
            this.sizeId = sizeId;
        }
    }
}
