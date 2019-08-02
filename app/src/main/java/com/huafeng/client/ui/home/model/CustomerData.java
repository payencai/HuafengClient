package com.huafeng.client.ui.home.model;


import com.huafeng.client.view.sidebar.IndexableEntity;

/**
 * 作者：凌涛 on 2019/4/10 14:41
 * 邮箱：771548229@qq..com
 */
public class CustomerData  implements IndexableEntity {

    private String index;
    /**
     * city : string
     * clientUserId : 0
     * contactAddress : string
     * contactNumber : string
     * createBy : 0
     * factoryId : 0
     * gmtCreate : 2019-05-08T11:47:52.579Z
     * gmtModified : 2019-05-08T11:47:52.579Z
     * id : 0
     * image : string
     * imageUrl : string
     * isDeleted : 0
     * name : string
     * orderQuantity : 0
     * principal : string
     * province : string
     * receivingAddress : string
     * remarks : string
     * updateBy : 0
     */

    private String city;
    private int clientUserId;
    private String contactAddress;
    private String contactNumber;
    private int createBy;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private int id;
    private String image;
    private String imageUrl;
    private int isDeleted;
    private String name;
    private int orderQuantity;
    private String principal;
    private String province;
    private String receivingAddress;
    private String remarks;
    private int updateBy;




    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(int clientUserId) {
        this.clientUserId = clientUserId;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
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

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
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

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String getFieldIndexBy() {
        return name;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        this.name=indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }
}
