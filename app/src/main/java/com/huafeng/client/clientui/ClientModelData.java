package com.huafeng.client.clientui;

import java.io.Serializable;

public class ClientModelData implements Serializable {

    /**
     * factoryName : string
     * gmtCreate : 2019-06-30T10:53:08.759Z
     * id : 0
     * image : string
     * imageUrl : string
     * images : string
     * productCategory1Name : string
     * productCategory2Name : string
     * sampleNo : string
     */

    private String factoryName;
    private String gmtCreate;
    private int id;
    private String image;
    private String imageUrl;
    private String images;
    private String productCategory1Name;
    private String productCategory2Name;
    private String sampleNo;

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getProductCategory1Name() {
        return productCategory1Name;
    }

    public void setProductCategory1Name(String productCategory1Name) {
        this.productCategory1Name = productCategory1Name;
    }

    public String getProductCategory2Name() {
        return productCategory2Name;
    }

    public void setProductCategory2Name(String productCategory2Name) {
        this.productCategory2Name = productCategory2Name;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }
}
