package com.huafeng.client.ui.home.activity.buy;

import java.util.List;

public class AskList {

    /**
     * checkBy : 0
     * checkTime : 2019-06-13T11:01:41.676Z
     * createBy : 0
     * createByName : string
     * factoryId : 0
     * gmtCreate : 2019-06-13T11:01:41.676Z
     * id : 0
     * orderId : 0
     * orderNumber : string
     * payMethod : 0
     * postName : string
     * productionOrderId : 0
     * productionOrderNumber : string
     * remarks : string
     * requirementGroupId : 0
     * requisitionList : [{"factoryId":0,"gmtCreate":"2019-06-13T11:01:41.676Z","id":0,"materialId":0,"materialName":"string","orderId":0,"productionOrderId":0,"quantityDemanded":0,"realQuantity":0,"requisitionGroupId":0,"status":0,"type":0}]
     * requisitionNumber : string
     * sampleNo : string
     * status : 0
     * type : 0
     */

    private int checkBy;
    private String checkTime;
    private int createBy;
    private String createByName;
    private int factoryId;
    private String gmtCreate;
    private int id;
    private int orderId;
    private String orderNumber;
    private int payMethod;
    private String postName;
    private int productionOrderId;
    private String productionOrderNumber;
    private String remarks;
    private int requirementGroupId;
    private String requisitionNumber;
    private String sampleNo;
    private int status;
    private int type;
    private List<RequisitionListBean> requisitionList;

    public int getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(int checkBy) {
        this.checkBy = checkBy;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public int getProductionOrderId() {
        return productionOrderId;
    }

    public void setProductionOrderId(int productionOrderId) {
        this.productionOrderId = productionOrderId;
    }

    public String getProductionOrderNumber() {
        return productionOrderNumber;
    }

    public void setProductionOrderNumber(String productionOrderNumber) {
        this.productionOrderNumber = productionOrderNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getRequirementGroupId() {
        return requirementGroupId;
    }

    public void setRequirementGroupId(int requirementGroupId) {
        this.requirementGroupId = requirementGroupId;
    }

    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setRequisitionNumber(String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<RequisitionListBean> getRequisitionList() {
        return requisitionList;
    }

    public void setRequisitionList(List<RequisitionListBean> requisitionList) {
        this.requisitionList = requisitionList;
    }

    public static class RequisitionListBean {
        /**
         * factoryId : 0
         * gmtCreate : 2019-06-13T11:01:41.676Z
         * id : 0
         * materialId : 0
         * materialName : string
         * orderId : 0
         * productionOrderId : 0
         * quantityDemanded : 0
         * realQuantity : 0
         * requisitionGroupId : 0
         * status : 0
         * type : 0
         */

        private int factoryId;
        private String gmtCreate;
        private int id;
        private int materialId;
        private String materialName;
        private int orderId;
        private int productionOrderId;
        private Double quantityDemanded;
        private Double realQuantity;
        private int requisitionGroupId;
        private int status;
        private int type;
        private Double askNum;

        public Double getAskNum() {
            return askNum;
        }

        public void setAskNum(Double askNum) {
            this.askNum = askNum;
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

        public int getMaterialId() {
            return materialId;
        }

        public void setMaterialId(int materialId) {
            this.materialId = materialId;
        }

        public String getMaterialName() {
            return materialName;
        }

        public void setMaterialName(String materialName) {
            this.materialName = materialName;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getProductionOrderId() {
            return productionOrderId;
        }

        public void setProductionOrderId(int productionOrderId) {
            this.productionOrderId = productionOrderId;
        }

        public Double getQuantityDemanded() {
            return quantityDemanded;
        }

        public void setQuantityDemanded(Double quantityDemanded) {
            this.quantityDemanded = quantityDemanded;
        }

        public Double getRealQuantity() {
            return realQuantity;
        }

        public void setRealQuantity(Double realQuantity) {
            this.realQuantity = realQuantity;
        }

        public int getRequisitionGroupId() {
            return requisitionGroupId;
        }

        public void setRequisitionGroupId(int requisitionGroupId) {
            this.requisitionGroupId = requisitionGroupId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
