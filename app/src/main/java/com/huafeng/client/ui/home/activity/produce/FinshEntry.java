package com.huafeng.client.ui.home.activity.produce;

import java.util.List;

public class FinshEntry {

    /**
     * productionOrderId : 0
     * sizeList : [{"quantity":0,"sizeId":0}]
     */
    private int repairQuantity;
    private int sewQuantity;
    private int washQuantity;
    private int clothQuantity;

    public int getRepairQuantity() {
        return repairQuantity;
    }

    public void setRepairQuantity(int repairQuantity) {
        this.repairQuantity = repairQuantity;
    }

    public int getSewQuantity() {
        return sewQuantity;
    }

    public void setSewQuantity(int sewQuantity) {
        this.sewQuantity = sewQuantity;
    }

    public int getWashQuantity() {
        return washQuantity;
    }

    public void setWashQuantity(int washQuantity) {
        this.washQuantity = washQuantity;
    }

    public int getClothQuantity() {
        return clothQuantity;
    }

    public void setClothQuantity(int clothQuantity) {
        this.clothQuantity = clothQuantity;
    }

    private int productionOrderId;
    private List<SizeListBean> sizeList;

    public int getProductionOrderId() {
        return productionOrderId;
    }

    public void setProductionOrderId(int productionOrderId) {
        this.productionOrderId = productionOrderId;
    }

    public List<SizeListBean> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<SizeListBean> sizeList) {
        this.sizeList = sizeList;
    }

    public static class SizeListBean {
        /**
         * quantity : 0
         * sizeId : 0
         */

        private int quantity;
        private int sizeId;

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
    }
}
