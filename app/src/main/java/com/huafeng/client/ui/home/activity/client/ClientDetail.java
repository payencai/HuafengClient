package com.huafeng.client.ui.home.activity.client;

import java.io.Serializable;

public class ClientDetail implements Serializable {


    /**
     * clientRecord : {"city":"string","clientUserId":0,"contactAddress":"string","contactNumber":"string","createBy":0,"factoryId":0,"gmtCreate":"2019-06-18T03:19:56.240Z","gmtModified":"2019-06-18T03:19:56.240Z","id":0,"image":"string","imageUrl":"string","isDeleted":0,"name":"string","orderQuantity":0,"principal":"string","province":"string","receivingAddress":"string","remarks":"string","updateBy":0}
     * clientUser : {"city":"string","gmtCreate":"2019-06-18T03:19:56.240Z","gmtModified":"2019-06-18T03:19:56.240Z","headPortrait":"string","id":0,"lastPasswordResetDate":"2019-06-18T03:19:56.240Z","nickname":"string","openId":"string","password":"string","province":"string","sex":0,"token":"string","username":"string"}
     */

    private ClientRecordBean clientRecord;
    private ClientUserBean clientUser;

    public ClientRecordBean getClientRecord() {
        return clientRecord;
    }

    public void setClientRecord(ClientRecordBean clientRecord) {
        this.clientRecord = clientRecord;
    }

    public ClientUserBean getClientUser() {
        return clientUser;
    }

    public void setClientUser(ClientUserBean clientUser) {
        this.clientUser = clientUser;
    }

    public static class ClientRecordBean implements Serializable{
        /**
         * city : string
         * clientUserId : 0
         * contactAddress : string
         * contactNumber : string
         * createBy : 0
         * factoryId : 0
         * gmtCreate : 2019-06-18T03:19:56.240Z
         * gmtModified : 2019-06-18T03:19:56.240Z
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
    }

    public static class ClientUserBean implements Serializable{
        /**
         * city : string
         * gmtCreate : 2019-06-18T03:19:56.240Z
         * gmtModified : 2019-06-18T03:19:56.240Z
         * headPortrait : string
         * id : 0
         * lastPasswordResetDate : 2019-06-18T03:19:56.240Z
         * nickname : string
         * openId : string
         * password : string
         * province : string
         * sex : 0
         * token : string
         * username : string
         */

        private String city;
        private String gmtCreate;
        private String gmtModified;
        private String headPortrait;
        private int id;
        private String lastPasswordResetDate;
        private String nickname;
        private String openId;
        private String password;
        private String province;
        private int sex;
        private String token;
        private String username;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLastPasswordResetDate() {
            return lastPasswordResetDate;
        }

        public void setLastPasswordResetDate(String lastPasswordResetDate) {
            this.lastPasswordResetDate = lastPasswordResetDate;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
