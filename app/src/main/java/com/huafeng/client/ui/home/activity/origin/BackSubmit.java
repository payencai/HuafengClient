package com.huafeng.client.ui.home.activity.origin;

import java.util.List;

public class BackSubmit {


    private List<EntryListBean> entryList;

    public List<EntryListBean> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<EntryListBean> entryList) {
        this.entryList = entryList;
    }

    public static class EntryListBean {
        /**
         * clothQuantity : 0
         * quantity : 0
         * remarks : string
         * repairQuantity : 0
         * sampleId : 0
         * sewQuantity : 0
         * washQuantity : 0
         */

        private Integer clothQuantity;
        private int quantity;
        private String remarks;
        private Integer repairQuantity;
        private int sampleId;
        private Integer sewQuantity;
        private Integer washQuantity;

        public Integer getClothQuantity() {
            return clothQuantity;
        }

        public void setClothQuantity(Integer clothQuantity) {
            this.clothQuantity = clothQuantity;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public Integer getRepairQuantity() {
            return repairQuantity;
        }

        public void setRepairQuantity(Integer repairQuantity) {
            this.repairQuantity = repairQuantity;
        }

        public int getSampleId() {
            return sampleId;
        }

        public void setSampleId(int sampleId) {
            this.sampleId = sampleId;
        }

        public Integer getSewQuantity() {
            return sewQuantity;
        }

        public void setSewQuantity(Integer sewQuantity) {
            this.sewQuantity = sewQuantity;
        }

        public Integer getWashQuantity() {
            return washQuantity;
        }

        public void setWashQuantity(Integer washQuantity) {
            this.washQuantity = washQuantity;
        }
    }
}
