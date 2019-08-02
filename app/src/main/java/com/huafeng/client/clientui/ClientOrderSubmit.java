package com.huafeng.client.clientui;

import java.util.List;

public class ClientOrderSubmit {

    /**
     * orderNumber : string
     * quantity : 0
     * remarks : string
     * sampleId : 0
     * shippingAddress : string
     * sizeQuantityList : [{"quantity":0,"sizeId":0}]
     * unitPrice : 0
     */

    private String orderNumber;
    private int quantity;
    private String remarks;
    private int sampleId;
    private String shippingAddress;
    private double unitPrice;
    private List<SizeQuantityListBean> sizeQuantityList;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<SizeQuantityListBean> getSizeQuantityList() {
        return sizeQuantityList;
    }

    public void setSizeQuantityList(List<SizeQuantityListBean> sizeQuantityList) {
        this.sizeQuantityList = sizeQuantityList;
    }

    public static class SizeQuantityListBean {
        /**
         * quantity : 0
         * sizeId : 0
         */

        private int quantity;
        private int sizeId;
        private String sizeName;

        public String getSizeName() {
            return sizeName;
        }

        public void setSizeName(String sizeName) {
            this.sizeName = sizeName;
        }

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
