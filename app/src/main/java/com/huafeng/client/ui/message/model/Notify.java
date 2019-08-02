package com.huafeng.client.ui.message.model;

/**
 * 作者：凌涛 on 2019/4/11 14:59
 * 邮箱：771548229@qq..com
 */
public class Notify {


    /**
     * approval : {"amountOfMoney":0,"applyBy":0,"applyByDepartmentName":"string","applyByEmployeeNumber":"string","applyByName":"string","applyByPostName":"string","applyTime":"2019-07-29T06:21:16.824Z","approveBy":0,"approveByName":"string","approveTime":"2019-07-29T06:21:16.824Z","content":"string","factoryId":0,"id":0,"images":"string","imagesUrl":"string","status":0,"title":"string","type":0}
     * orders : {"clientRecordId":0,"clientRecordName":"string","createBy":0,"factoryId":0,"gmtCreate":"2019-07-29T06:21:16.824Z","id":0,"image":"string","imageUrl":"string","images":"string","orderNumber":"string","quantity":0,"quantityAssignment":0,"quantityProducted":0,"sampleNo":"string","status":0,"unitPrice":0}
     * patternMaking : {"clientName":"string","createTime":"2019-07-29T06:21:16.824Z","designById":0,"designByName":"string","id":0,"image":"string","imageUrl":"string","images":"string","patternMakingNo":"string","patternNo":"string","productCategory1Name":"string","productCategory2Name":"string","sampleId":0,"status":0,"statusName":"string"}
     * productionOrder : {"currentFlowId":0,"estimatedTimeOfFinishment":"2019-07-29T06:21:16.824Z","finishTime":"2019-07-29T06:21:16.824Z","gmtCreate":"2019-07-29T06:21:16.824Z","goStatus":0,"id":0,"image":"string","imageUrl":"string","images":"string","lossQuantity":0,"month":"string","principal":"string","productOrderNumber":"string","quantity":0,"sampleNo":"string","stageName":"string","status":0,"stopTime":"2019-07-29T06:21:16.824Z"}
     * push : {"content":"string","employeeRecordId":0,"factoryId":0,"gmtCreate":"2019-07-29T06:21:16.824Z","id":0,"isLinked":0,"isRead":0,"serviceId":0,"title":"string","type":0}
     */

    private ApprovalBean approval;
    private OrdersBean orders;
    private PatternMakingBean patternMaking;
    private ProductionOrderBean productionOrder;
    private PushBean push;

    public ApprovalBean getApproval() {
        return approval;
    }

    public void setApproval(ApprovalBean approval) {
        this.approval = approval;
    }

    public OrdersBean getOrders() {
        return orders;
    }

    public void setOrders(OrdersBean orders) {
        this.orders = orders;
    }

    public PatternMakingBean getPatternMaking() {
        return patternMaking;
    }

    public void setPatternMaking(PatternMakingBean patternMaking) {
        this.patternMaking = patternMaking;
    }

    public ProductionOrderBean getProductionOrder() {
        return productionOrder;
    }

    public void setProductionOrder(ProductionOrderBean productionOrder) {
        this.productionOrder = productionOrder;
    }

    public PushBean getPush() {
        return push;
    }

    public void setPush(PushBean push) {
        this.push = push;
    }

    public static class ApprovalBean {
        /**
         * amountOfMoney : 0
         * applyBy : 0
         * applyByDepartmentName : string
         * applyByEmployeeNumber : string
         * applyByName : string
         * applyByPostName : string
         * applyTime : 2019-07-29T06:21:16.824Z
         * approveBy : 0
         * approveByName : string
         * approveTime : 2019-07-29T06:21:16.824Z
         * content : string
         * factoryId : 0
         * id : 0
         * images : string
         * imagesUrl : string
         * status : 0
         * title : string
         * type : 0
         */

        private int amountOfMoney;
        private int applyBy;
        private String applyByDepartmentName;
        private String applyByEmployeeNumber;
        private String applyByName;
        private String applyByPostName;
        private String applyTime;
        private int approveBy;
        private String approveByName;
        private String approveTime;
        private String content;
        private int factoryId;
        private int id;
        private String images;
        private String imagesUrl;
        private int status;
        private String title;
        private int type;

        public int getAmountOfMoney() {
            return amountOfMoney;
        }

        public void setAmountOfMoney(int amountOfMoney) {
            this.amountOfMoney = amountOfMoney;
        }

        public int getApplyBy() {
            return applyBy;
        }

        public void setApplyBy(int applyBy) {
            this.applyBy = applyBy;
        }

        public String getApplyByDepartmentName() {
            return applyByDepartmentName;
        }

        public void setApplyByDepartmentName(String applyByDepartmentName) {
            this.applyByDepartmentName = applyByDepartmentName;
        }

        public String getApplyByEmployeeNumber() {
            return applyByEmployeeNumber;
        }

        public void setApplyByEmployeeNumber(String applyByEmployeeNumber) {
            this.applyByEmployeeNumber = applyByEmployeeNumber;
        }

        public String getApplyByName() {
            return applyByName;
        }

        public void setApplyByName(String applyByName) {
            this.applyByName = applyByName;
        }

        public String getApplyByPostName() {
            return applyByPostName;
        }

        public void setApplyByPostName(String applyByPostName) {
            this.applyByPostName = applyByPostName;
        }

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public int getApproveBy() {
            return approveBy;
        }

        public void setApproveBy(int approveBy) {
            this.approveBy = approveBy;
        }

        public String getApproveByName() {
            return approveByName;
        }

        public void setApproveByName(String approveByName) {
            this.approveByName = approveByName;
        }

        public String getApproveTime() {
            return approveTime;
        }

        public void setApproveTime(String approveTime) {
            this.approveTime = approveTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class OrdersBean {
        /**
         * clientRecordId : 0
         * clientRecordName : string
         * createBy : 0
         * factoryId : 0
         * gmtCreate : 2019-07-29T06:21:16.824Z
         * id : 0
         * image : string
         * imageUrl : string
         * images : string
         * orderNumber : string
         * quantity : 0
         * quantityAssignment : 0
         * quantityProducted : 0
         * sampleNo : string
         * status : 0
         * unitPrice : 0
         */

        private int clientRecordId;
        private String clientRecordName;
        private int createBy;
        private int factoryId;
        private String gmtCreate;
        private int id;
        private String image;
        private String imageUrl;
        private String images;
        private String orderNumber;
        private int quantity;
        private int quantityAssignment;
        private int quantityProducted;
        private String sampleNo;
        private int status;
        private int unitPrice;

        public int getClientRecordId() {
            return clientRecordId;
        }

        public void setClientRecordId(int clientRecordId) {
            this.clientRecordId = clientRecordId;
        }

        public String getClientRecordName() {
            return clientRecordName;
        }

        public void setClientRecordName(String clientRecordName) {
            this.clientRecordName = clientRecordName;
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

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getQuantityAssignment() {
            return quantityAssignment;
        }

        public void setQuantityAssignment(int quantityAssignment) {
            this.quantityAssignment = quantityAssignment;
        }

        public int getQuantityProducted() {
            return quantityProducted;
        }

        public void setQuantityProducted(int quantityProducted) {
            this.quantityProducted = quantityProducted;
        }

        public String getSampleNo() {
            return sampleNo;
        }

        public void setSampleNo(String sampleNo) {
            this.sampleNo = sampleNo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }
    }

    public static class PatternMakingBean {
        /**
         * clientName : string
         * createTime : 2019-07-29T06:21:16.824Z
         * designById : 0
         * designByName : string
         * id : 0
         * image : string
         * imageUrl : string
         * images : string
         * patternMakingNo : string
         * patternNo : string
         * productCategory1Name : string
         * productCategory2Name : string
         * sampleId : 0
         * status : 0
         * statusName : string
         */

        private String clientName;
        private String createTime;
        private int designById;
        private String designByName;
        private int id;
        private String image;
        private String imageUrl;
        private String images;
        private String patternMakingNo;
        private String patternNo;
        private String productCategory1Name;
        private String productCategory2Name;
        private int sampleId;
        private int status;
        private String statusName;

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDesignById() {
            return designById;
        }

        public void setDesignById(int designById) {
            this.designById = designById;
        }

        public String getDesignByName() {
            return designByName;
        }

        public void setDesignByName(String designByName) {
            this.designByName = designByName;
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

        public String getPatternMakingNo() {
            return patternMakingNo;
        }

        public void setPatternMakingNo(String patternMakingNo) {
            this.patternMakingNo = patternMakingNo;
        }

        public String getPatternNo() {
            return patternNo;
        }

        public void setPatternNo(String patternNo) {
            this.patternNo = patternNo;
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

        public int getSampleId() {
            return sampleId;
        }

        public void setSampleId(int sampleId) {
            this.sampleId = sampleId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
    }

    public static class ProductionOrderBean {
        /**
         * currentFlowId : 0
         * estimatedTimeOfFinishment : 2019-07-29T06:21:16.824Z
         * finishTime : 2019-07-29T06:21:16.824Z
         * gmtCreate : 2019-07-29T06:21:16.824Z
         * goStatus : 0
         * id : 0
         * image : string
         * imageUrl : string
         * images : string
         * lossQuantity : 0
         * month : string
         * principal : string
         * productOrderNumber : string
         * quantity : 0
         * sampleNo : string
         * stageName : string
         * status : 0
         * stopTime : 2019-07-29T06:21:16.824Z
         */

        private int currentFlowId;
        private String estimatedTimeOfFinishment;
        private String finishTime;
        private String gmtCreate;
        private int goStatus;
        private int id;
        private String image;
        private String imageUrl;
        private String images;
        private int lossQuantity;
        private String month;
        private String principal;
        private String productOrderNumber;
        private int quantity;
        private String sampleNo;
        private String stageName;
        private int status;
        private String stopTime;

        public int getCurrentFlowId() {
            return currentFlowId;
        }

        public void setCurrentFlowId(int currentFlowId) {
            this.currentFlowId = currentFlowId;
        }

        public String getEstimatedTimeOfFinishment() {
            return estimatedTimeOfFinishment;
        }

        public void setEstimatedTimeOfFinishment(String estimatedTimeOfFinishment) {
            this.estimatedTimeOfFinishment = estimatedTimeOfFinishment;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public int getGoStatus() {
            return goStatus;
        }

        public void setGoStatus(int goStatus) {
            this.goStatus = goStatus;
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

        public int getLossQuantity() {
            return lossQuantity;
        }

        public void setLossQuantity(int lossQuantity) {
            this.lossQuantity = lossQuantity;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getPrincipal() {
            return principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public String getProductOrderNumber() {
            return productOrderNumber;
        }

        public void setProductOrderNumber(String productOrderNumber) {
            this.productOrderNumber = productOrderNumber;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getSampleNo() {
            return sampleNo;
        }

        public void setSampleNo(String sampleNo) {
            this.sampleNo = sampleNo;
        }

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStopTime() {
            return stopTime;
        }

        public void setStopTime(String stopTime) {
            this.stopTime = stopTime;
        }
    }

    public static class PushBean {
        /**
         * content : string
         * employeeRecordId : 0
         * factoryId : 0
         * gmtCreate : 2019-07-29T06:21:16.824Z
         * id : 0
         * isLinked : 0
         * isRead : 0
         * serviceId : 0
         * title : string
         * type : 0
         */

        private String content;
        private int employeeRecordId;
        private int factoryId;
        private String gmtCreate;
        private int id;
        private int isLinked;
        private int isRead;
        private int serviceId;
        private String title;
        private int type;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getEmployeeRecordId() {
            return employeeRecordId;
        }

        public void setEmployeeRecordId(int employeeRecordId) {
            this.employeeRecordId = employeeRecordId;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsLinked() {
            return isLinked;
        }

        public void setIsLinked(int isLinked) {
            this.isLinked = isLinked;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public int getServiceId() {
            return serviceId;
        }

        public void setServiceId(int serviceId) {
            this.serviceId = serviceId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
