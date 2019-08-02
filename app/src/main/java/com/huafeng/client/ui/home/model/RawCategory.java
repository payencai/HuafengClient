package com.huafeng.client.ui.home.model;

import java.io.Serializable;

/**
 * 作者：凌涛 on 2019/5/8 12:12
 * 邮箱：771548229@qq..com
 */
public class RawCategory implements Serializable {

    /**
     * childrenNumber : 0
     * createBy : 0
     * factoryId : 0
     * gmtCreate : 2019-05-08T04:11:49.494Z
     * gmtModified : 2019-05-08T04:11:49.494Z
     * id : 0
     * isDeleted : 0
     * materialNumber : 0
     * name : string
     * natureId : string
     * parentId : 0
     * rank : 0
     * suquence : 0
     * updateBy : 0
     */

    private int childrenNumber;
    private int createBy;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private int id;
    private int isDeleted;
    private int materialNumber;
    private String name;
    private String natureId;
    private int parentId;
    private int rank;
    private int suquence;
    private int updateBy;

    public int getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(int childrenNumber) {
        this.childrenNumber = childrenNumber;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(int materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNatureId() {
        return natureId;
    }

    public void setNatureId(String natureId) {
        this.natureId = natureId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getSuquence() {
        return suquence;
    }

    public void setSuquence(int suquence) {
        this.suquence = suquence;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }
}
