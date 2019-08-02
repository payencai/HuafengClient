package com.huafeng.client.ui.home.activity.origin;

import java.util.List;

public class Test {

    /**
     * address : string
     * amount : 0
     * carrier : string
     * clientRecordId : 0
     * contactNumber : string
     * createBy : 0
     * dispatchQuantity : 0
     * dispatchType : 0
     * factoryId : 0
     * freightCharge : 0
     * gmtCreate : 2019-07-30T10:28:31.432Z
     * id : 0
     * images : string
     * imagesUrl : string
     * inventorySampleLogId : 0
     * logId : 0
     * name : string
     * price : 0
     * priceType : 0
     * remarks : string
     * sampleId : 0
     * sampleNo : string
     * sizeList : [{"factoryId":0,"inventorySampleLogId":0,"quantity":0,"sampleId":0,"sizeId":0,"sizeName":"string"}]
     * trackingNumber : string
     */

    private String address;
    private int amount;
    private String carrier;
    private int clientRecordId;
    private String contactNumber;
    private int createBy;
    private int dispatchQuantity;
    private int dispatchType;
    private int factoryId;
    private int freightCharge;
    private String gmtCreate;
    private int id;
    private String images;
    private String imagesUrl;
    private int inventorySampleLogId;
    private int logId;
    private String name;
    private int price;
    private int priceType;
    private String remarks;
    private int sampleId;
    private String sampleNo;
    private String trackingNumber;
    private List<SizeListBean> sizeList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getClientRecordId() {
        return clientRecordId;
    }

    public void setClientRecordId(int clientRecordId) {
        this.clientRecordId = clientRecordId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
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

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getFreightCharge() {
        return freightCharge;
    }

    public void setFreightCharge(int freightCharge) {
        this.freightCharge = freightCharge;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public int getInventorySampleLogId() {
        return inventorySampleLogId;
    }

    public void setInventorySampleLogId(int inventorySampleLogId) {
        this.inventorySampleLogId = inventorySampleLogId;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
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
         * factoryId : 0
         * inventorySampleLogId : 0
         * quantity : 0
         * sampleId : 0
         * sizeId : 0
         * sizeName : string
         */

        private int factoryId;
        private int inventorySampleLogId;
        private int quantity;
        private int sampleId;
        private int sizeId;
        private String sizeName;

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
        }

        public int getInventorySampleLogId() {
            return inventorySampleLogId;
        }

        public void setInventorySampleLogId(int inventorySampleLogId) {
            this.inventorySampleLogId = inventorySampleLogId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getSampleId() {
            return sampleId;
        }

        public void setSampleId(int sampleId) {
            this.sampleId = sampleId;
        }

        public int getSizeId() {
            return sizeId;
        }

        public void setSizeId(int sizeId) {
            this.sizeId = sizeId;
        }

        public String getSizeName() {
            return sizeName;
        }

        public void setSizeName(String sizeName) {
            this.sizeName = sizeName;
        }
    }
}
