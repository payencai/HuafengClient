package com.huafeng.client.ui.home.model;

import java.io.Serializable;

public class Supplier implements Serializable {

    /**
     * address : string
     * city : string
     * contactNumber : string
     * createBy : 0
     * factoryId : 0
     * gmtCreate : 2019-06-09T10:28:08.186Z
     * gmtModified : 2019-06-09T10:28:08.186Z
     * id : 0
     * isDeleted : 0
     * isInUse : 0
     * name : string
     * principal : string
     * province : string
     * remark : string
     * type : 0
     * updateBy : 0
     */

    private String address;
    private String city;
    private String contactNumber;
    private int createBy;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private int id;
    private int isDeleted;
    private int isInUse;
    private String name;
    private String principal;
    private String province;
    private String remark;
    private int type;
    private int updateBy;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public int getIsInUse() {
        return isInUse;
    }

    public void setIsInUse(int isInUse) {
        this.isInUse = isInUse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }
}
