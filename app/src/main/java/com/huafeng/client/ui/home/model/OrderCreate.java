package com.huafeng.client.ui.home.model;

import java.io.Serializable;
import java.util.List;

public class OrderCreate implements Serializable {

    /**
     * clientRecordId : 0
     * orderNumber : string
     * purchaseAssignmentList : [{"quantity":0,"rawMaterialId":0,"type":0}]
     * quantity : 0
     * remarks : string
     * sampleId : 0
     * shippingAddress : string
     * sizeQuantityList : [{"quantity":0,"sizeId":0}]
     * unitPrice : 0.0
     */
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    private int clientRecordId;
    private String orderNumber;
    private int quantity;
    private String remarks;
    private int sampleId;
    private String shippingAddress;
    private double unitPrice;
    private List<PurchaseAssignmentListBean> purchaseAssignmentList;
    private List<SizeQuantityListBean> sizeQuantityList;

    public int getClientRecordId() {
        return clientRecordId;
    }

    public void setClientRecordId(int clientRecordId) {
        this.clientRecordId = clientRecordId;
    }

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

    public List<PurchaseAssignmentListBean> getPurchaseAssignmentList() {
        return purchaseAssignmentList;
    }

    public void setPurchaseAssignmentList(List<PurchaseAssignmentListBean> purchaseAssignmentList) {
        this.purchaseAssignmentList = purchaseAssignmentList;
    }

    public List<SizeQuantityListBean> getSizeQuantityList() {
        return sizeQuantityList;
    }

    public void setSizeQuantityList(List<SizeQuantityListBean> sizeQuantityList) {
        this.sizeQuantityList = sizeQuantityList;
    }

    public static class PurchaseAssignmentListBean implements Serializable{
        /**
         * quantity : 0
         * rawMaterialId : 0
         * type : 0
         */

        private double quantity;
        private int rawMaterialId;
        private int type;

        public double getQuantity() {
            return quantity;

        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public int getRawMaterialId() {
            return rawMaterialId;
        }

        public void setRawMaterialId(int rawMaterialId) {
            this.rawMaterialId = rawMaterialId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class SizeQuantityListBean implements Serializable{
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
