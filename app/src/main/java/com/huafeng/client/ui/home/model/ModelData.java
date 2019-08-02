package com.huafeng.client.ui.home.model;

import java.io.Serializable;

/**
 * 作者：凌涛 on 2019/4/10 13:25
 * 邮箱：771548229@qq..com
 */
public class ModelData implements Serializable {

    /**
     * clientName : string
     * designerName : string
     * gmtCreate : 2019-05-09T06:47:41.530Z
     * id : 0
     * image : string
     * imageUrl : string
     * images : string
     * productCategory1Name : string
     * productCategory2Name : string
     * sampleNo : string
     */

    private String clientName;
    private String designerName;
    private String gmtCreate;
    private int id;
    private String image;
    private String imageUrl;
    private String images;
    private String productCategory1Name;
    private String productCategory2Name;
    private String sampleNo;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
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
