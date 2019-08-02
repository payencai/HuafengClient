package com.huafeng.client.ui.home.activity.produce;

import java.util.List;

public class ProduceCal {

    /**
     * clothConsume : string
     * factoryId : 0
     * gmtCreate : 2019-06-06T11:39:41.098Z
     * id : 0
     * materialId : 0
     * materialName : string
     * orderSampleMaterialId : 0
     * productionOrderFlowId : 0
     * productionOrderId : 0
     * productionOrderMaterialConsumeSizeAccountVoList : [{"consumeAccountId":0,"factoryId":0,"id":0,"orderSampleSizeId":0,"productionOrderFlowId":0,"quantity":0,"sizeId":0,"sizeName":"string"}]
     * remarks : string
     */

    private String clothConsume;
    private int factoryId;
    private String gmtCreate;
    private int id;
    private int materialId;
    private String materialName;
    private int orderSampleMaterialId;
    private int productionOrderFlowId;
    private int productionOrderId;
    private String remarks;
    private List<ProductionOrderMaterialConsumeSizeAccountVoListBean> productionOrderMaterialConsumeSizeAccountVoList;

    public String getClothConsume() {
        return clothConsume;
    }

    public void setClothConsume(String clothConsume) {
        this.clothConsume = clothConsume;
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

    public int getOrderSampleMaterialId() {
        return orderSampleMaterialId;
    }

    public void setOrderSampleMaterialId(int orderSampleMaterialId) {
        this.orderSampleMaterialId = orderSampleMaterialId;
    }

    public int getProductionOrderFlowId() {
        return productionOrderFlowId;
    }

    public void setProductionOrderFlowId(int productionOrderFlowId) {
        this.productionOrderFlowId = productionOrderFlowId;
    }

    public int getProductionOrderId() {
        return productionOrderId;
    }

    public void setProductionOrderId(int productionOrderId) {
        this.productionOrderId = productionOrderId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<ProductionOrderMaterialConsumeSizeAccountVoListBean> getProductionOrderMaterialConsumeSizeAccountVoList() {
        return productionOrderMaterialConsumeSizeAccountVoList;
    }

    public void setProductionOrderMaterialConsumeSizeAccountVoList(List<ProductionOrderMaterialConsumeSizeAccountVoListBean> productionOrderMaterialConsumeSizeAccountVoList) {
        this.productionOrderMaterialConsumeSizeAccountVoList = productionOrderMaterialConsumeSizeAccountVoList;
    }

    public static class ProductionOrderMaterialConsumeSizeAccountVoListBean {
        /**
         * consumeAccountId : 0
         * factoryId : 0
         * id : 0
         * orderSampleSizeId : 0
         * productionOrderFlowId : 0
         * quantity : 0
         * sizeId : 0
         * sizeName : string
         */

        private int consumeAccountId;
        private int factoryId;
        private int id;
        private int orderSampleSizeId;
        private int productionOrderFlowId;
        private int quantity;
        private int sizeId;
        private String sizeName;

        public int getConsumeAccountId() {
            return consumeAccountId;
        }

        public void setConsumeAccountId(int consumeAccountId) {
            this.consumeAccountId = consumeAccountId;
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

        public int getOrderSampleSizeId() {
            return orderSampleSizeId;
        }

        public void setOrderSampleSizeId(int orderSampleSizeId) {
            this.orderSampleSizeId = orderSampleSizeId;
        }

        public int getProductionOrderFlowId() {
            return productionOrderFlowId;
        }

        public void setProductionOrderFlowId(int productionOrderFlowId) {
            this.productionOrderFlowId = productionOrderFlowId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getSizeId() {
            return sizeId;
        }

        public void setSizeId(int sizeId) {
            this.sizeId = sizeId;
        }

        public String getSizeName() {
            return sizeName;
        }

        public void setSizeName(String sizeName) {
            this.sizeName = sizeName;
        }
    }
}
