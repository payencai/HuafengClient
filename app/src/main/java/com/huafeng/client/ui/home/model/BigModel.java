package com.huafeng.client.ui.home.model;

import java.io.Serializable;

public class BigModel implements Serializable {

    /**
     * clientRecordId : 0
     * color : string
     * createBy : 0
     * designerBy : 0
     * factoryId : 0
     * gmtCreate : 2019-06-05T07:09:55.940Z
     * gmtModified : 2019-06-05T07:09:55.940Z
     * id : 0
     * images : string
     * isDeleted : 0
     * patternNo : string
     * productCategoryId : 0
     * remarks : string
     * sampleNo : string
     * updateBy : 0
     */

    private int clientRecordId;
    private String color;
    private int createBy;
    private int designerBy;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private int id;
    private String images;
    private int isDeleted;
    private String patternNo;
    private int productCategoryId;
    private String remarks;
    private String sampleNo;
    private int updateBy;

    public int getClientRecordId() {
        return clientRecordId;
    }

    public void setClientRecordId(int clientRecordId) {
        this.clientRecordId = clientRecordId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public int getDesignerBy() {
        return designerBy;
    }

    public void setDesignerBy(int designerBy) {
        this.designerBy = designerBy;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getPatternNo() {
        return patternNo;
    }

    public void setPatternNo(String patternNo) {
        this.patternNo = patternNo;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }
}
