package com.huafeng.client.clientui;

public class SupplierApply {

    /**
     * clientApplication : {"city":"string","contactNumber":"string","factoryId":0,"gmtCheck":"2019-07-19T09:25:17.318Z","gmtCreate":"2019-07-19T09:25:17.318Z","headPortrait":"string","id":0,"name":"string","province":"string","remarks":"string","sex":0,"status":0,"userId":0}
     * clientInvitation : {"clientRecordId":0,"clientUserId":0,"createBy":0,"factoryId":0,"gmtCheck":"2019-07-19T09:25:17.318Z","gmtCreate":"2019-07-19T09:25:17.318Z","id":0,"remarks":"string","status":0}
     * clientUserId : 0
     * factoryId : 0
     * factoryName : string
     * factorySystemId : string
     * gmtCreate : 2019-07-19T09:25:17.318Z
     * id : 0
     * image : string
     * imageUrl : string
     * type : 0
     */

    private ClientApplicationBean clientApplication;
    private ClientInvitationBean clientInvitation;
    private int clientUserId;
    private int factoryId;
    private String factoryName;
    private String factorySystemId;
    private String gmtCreate;
    private int id;
    private String image;
    private String imageUrl;
    private int type;

    public ClientApplicationBean getClientApplication() {
        return clientApplication;
    }

    public void setClientApplication(ClientApplicationBean clientApplication) {
        this.clientApplication = clientApplication;
    }

    public ClientInvitationBean getClientInvitation() {
        return clientInvitation;
    }

    public void setClientInvitation(ClientInvitationBean clientInvitation) {
        this.clientInvitation = clientInvitation;
    }

    public int getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(int clientUserId) {
        this.clientUserId = clientUserId;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactorySystemId() {
        return factorySystemId;
    }

    public void setFactorySystemId(String factorySystemId) {
        this.factorySystemId = factorySystemId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class ClientApplicationBean {
        /**
         * city : string
         * contactNumber : string
         * factoryId : 0
         * gmtCheck : 2019-07-19T09:25:17.318Z
         * gmtCreate : 2019-07-19T09:25:17.318Z
         * headPortrait : string
         * id : 0
         * name : string
         * province : string
         * remarks : string
         * sex : 0
         * status : 0
         * userId : 0
         */

        private String city;
        private String contactNumber;
        private int factoryId;
        private String gmtCheck;
        private String gmtCreate;
        private String headPortrait;
        private int id;
        private String name;
        private String province;
        private String remarks;
        private int sex;
        private int status;
        private int userId;

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

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
        }

        public String getGmtCheck() {
            return gmtCheck;
        }

        public void setGmtCheck(String gmtCheck) {
            this.gmtCheck = gmtCheck;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

    public static class ClientInvitationBean {
        /**
         * clientRecordId : 0
         * clientUserId : 0
         * createBy : 0
         * factoryId : 0
         * gmtCheck : 2019-07-19T09:25:17.318Z
         * gmtCreate : 2019-07-19T09:25:17.318Z
         * id : 0
         * remarks : string
         * status : 0
         */

        private int clientRecordId;
        private int clientUserId;
        private int createBy;
        private int factoryId;
        private String gmtCheck;
        private String gmtCreate;
        private int id;
        private String remarks;
        private int status;

        public int getClientRecordId() {
            return clientRecordId;
        }

        public void setClientRecordId(int clientRecordId) {
            this.clientRecordId = clientRecordId;
        }

        public int getClientUserId() {
            return clientUserId;
        }

        public void setClientUserId(int clientUserId) {
            this.clientUserId = clientUserId;
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

        public String getGmtCheck() {
            return gmtCheck;
        }

        public void setGmtCheck(String gmtCheck) {
            this.gmtCheck = gmtCheck;
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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
