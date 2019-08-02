package com.huafeng.client.ui.home.activity.buy;

import java.util.List;

public class AskSubmit {

    /**
     * id : 0
     * payMethod : 0
     * remarks : string
     * requisitionList : [{"id":0,"realQuantity":0}]
     */

    private int id;
    private int payMethod;
    private String remarks;
    private List<RequisitionListBean> requisitionList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<RequisitionListBean> getRequisitionList() {
        return requisitionList;
    }

    public void setRequisitionList(List<RequisitionListBean> requisitionList) {
        this.requisitionList = requisitionList;
    }

    public static class RequisitionListBean {
        /**
         * id : 0
         * realQuantity : 0
         */

        private int id;
        private Double realQuantity;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Double getRealQuantity() {
            return realQuantity;
        }

        public void setRealQuantity(Double realQuantity) {
            this.realQuantity = realQuantity;
        }
    }
}
