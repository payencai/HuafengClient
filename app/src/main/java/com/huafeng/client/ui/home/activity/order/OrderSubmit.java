package com.huafeng.client.ui.home.activity.order;

import java.util.List;

public class OrderSubmit {

    /**
     * estimatedTimeOfFinishment : 2019-06-10T03:40:58.874Z
     * month : string
     * orderId : 0
     * productProcessList : [{"isOutsourcing":0,"processId":0}]
     * productionOrderMaterialConsumeAccountParam : {"clothConsume":"string","orderSampleMaterialId":0}
     * quantity : 0
     * sizeQuantityList : [{"quantity":0,"sizeId":0}]
     */
    private int nextflowPrincipalId;

    public int getNextflowPrincipalId() {
        return nextflowPrincipalId;
    }

    public void setNextflowPrincipalId(int nextflowPrincipalId) {
        this.nextflowPrincipalId = nextflowPrincipalId;
    }

    private String estimatedTimeOfFinishment;
    private String month;
    private int orderId;
    private ProductionOrderMaterialConsumeAccountParamBean productionOrderMaterialConsumeAccountParam;
    private double quantity;
    private List<ProductProcessListBean> productProcessList;
    private List<SizeQuantityListBean> sizeQuantityList;

    public String getEstimatedTimeOfFinishment() {
        return estimatedTimeOfFinishment;
    }

    public void setEstimatedTimeOfFinishment(String estimatedTimeOfFinishment) {
        this.estimatedTimeOfFinishment = estimatedTimeOfFinishment;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ProductionOrderMaterialConsumeAccountParamBean getProductionOrderMaterialConsumeAccountParam() {
        return productionOrderMaterialConsumeAccountParam;
    }

    public void setProductionOrderMaterialConsumeAccountParam(ProductionOrderMaterialConsumeAccountParamBean productionOrderMaterialConsumeAccountParam) {
        this.productionOrderMaterialConsumeAccountParam = productionOrderMaterialConsumeAccountParam;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public List<ProductProcessListBean> getProductProcessList() {
        return productProcessList;
    }

    public void setProductProcessList(List<ProductProcessListBean> productProcessList) {
        this.productProcessList = productProcessList;
    }

    public List<SizeQuantityListBean> getSizeQuantityList() {
        return sizeQuantityList;
    }

    public void setSizeQuantityList(List<SizeQuantityListBean> sizeQuantityList) {
        this.sizeQuantityList = sizeQuantityList;
    }

    public static class ProductionOrderMaterialConsumeAccountParamBean {
        /**
         * clothConsume : string
         * orderSampleMaterialId : 0
         */

        private String clothConsume;
        private int orderSampleMaterialId;

        public String getClothConsume() {
            return clothConsume;
        }

        public void setClothConsume(String clothConsume) {
            this.clothConsume = clothConsume;
        }

        public int getOrderSampleMaterialId() {
            return orderSampleMaterialId;
        }

        public void setOrderSampleMaterialId(int orderSampleMaterialId) {
            this.orderSampleMaterialId = orderSampleMaterialId;
        }
    }

    public static class ProductProcessListBean {
        /**
         * isOutsourcing : 0
         * processId : 0
         */

        private int isOutsourcing;
        private int processId;

        public int getIsOutsourcing() {
            return isOutsourcing;
        }

        public void setIsOutsourcing(int isOutsourcing) {
            this.isOutsourcing = isOutsourcing;
        }

        public int getProcessId() {
            return processId;
        }

        public void setProcessId(int processId) {
            this.processId = processId;
        }
    }

    public static class SizeQuantityListBean {
        /**
         * quantity : 0
         * sizeId : 0
         */

        private double quantity;
        private int sizeId;

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public int getSizeId() {
            return sizeId;
        }

        public void setSizeId(int sizeId) {
            this.sizeId = sizeId;
        }
    }
}
