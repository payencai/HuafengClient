package com.huafeng.client.ui.home.activity.origin;

import java.util.List;

public class CheckEntry {

    /**
     * quantity : 0
     * remarks : string
     * sampleId : 0
     * sizeList : [{"quantity":0,"sizeId":0}]
     */

    private int quantity;
    private String remarks;
    private int sampleId;
    private List<SizeListBean> sizeList;

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

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
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

        private Integer quantity;
        private int sizeId;

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
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
