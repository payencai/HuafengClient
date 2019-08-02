package com.huafeng.client.ui.home.activity.sample;

import java.util.List;

public class SampleSize {

    /**
     * sampleSizeInformationList : [{"quantity":0,"sizeInformationName":"","sizeInfomationId":0}]
     * sizeId : 0
     * sizeName :
     */

    private int sizeId;
    private String sizeName;
    private List<SampleSizeInformationListBean> sampleSizeInformationList;

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

    public List<SampleSizeInformationListBean> getSampleSizeInformationList() {
        return sampleSizeInformationList;
    }

    public void setSampleSizeInformationList(List<SampleSizeInformationListBean> sampleSizeInformationList) {
        this.sampleSizeInformationList = sampleSizeInformationList;
    }

    public static class SampleSizeInformationListBean {
        /**
         * quantity : 0
         * sizeInformationName :
         * sizeInfomationId : 0
         */

        private double quantity;
        private String sizeInformationName;
        private int sizeInfomationId;

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getSizeInformationName() {
            return sizeInformationName;
        }

        public void setSizeInformationName(String sizeInformationName) {
            this.sizeInformationName = sizeInformationName;
        }

        public int getSizeInfomationId() {
            return sizeInfomationId;
        }

        public void setSizeInfomationId(int sizeInfomationId) {
            this.sizeInfomationId = sizeInfomationId;
        }
    }
}
