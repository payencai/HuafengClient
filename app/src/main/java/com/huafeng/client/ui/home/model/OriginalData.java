package com.huafeng.client.ui.home.model;

import java.io.Serializable;

/**
 * 作者：凌涛 on 2019/4/10 11:14
 * 邮箱：771548229@qq..com
 */
public class OriginalData implements Serializable {

    /**
     * category1Id : 0
     * category1Name : string
     * category2Id : 0
     * category2Name : string
     * createBy : 0
     * factoryId : 0
     * gmtCreate : 2019-05-09T03:44:14.427Z
     * gmtModified : 2019-05-09T03:44:14.427Z
     * id : 0
     * image : string
     * imageUrl : string
     * isDeleted : 0
     * name : string
     * updateBy : 0
     */

    private int category1Id;
    private String category1Name;
    private int category2Id;
    private String category2Name;
    private int createBy;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private int id;
    private String image;
    private String imageUrl;
    private int isDeleted;
    private String name;
    private int updateBy;

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

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }
}
