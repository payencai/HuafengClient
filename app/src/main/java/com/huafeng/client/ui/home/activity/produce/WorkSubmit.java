package com.huafeng.client.ui.home.activity.produce;

import java.util.List;

public class WorkSubmit {


    /**
     * productionOrderWorkAllocationProcessId : 0
     * workallocationList : [{"employeeRecordId":0,"payType":0,"price":0,"quantityAllotted":0,"temporaryWorkerName":"string"}]
     */

    private int productionOrderWorkAllocationProcessId;
    private List<WorkallocationListBean> workallocationList;

    public int getProductionOrderWorkAllocationProcessId() {
        return productionOrderWorkAllocationProcessId;
    }

    public void setProductionOrderWorkAllocationProcessId(int productionOrderWorkAllocationProcessId) {
        this.productionOrderWorkAllocationProcessId = productionOrderWorkAllocationProcessId;
    }

    public List<WorkallocationListBean> getWorkallocationList() {
        return workallocationList;
    }

    public void setWorkallocationList(List<WorkallocationListBean> workallocationList) {
        this.workallocationList = workallocationList;
    }

    public static class WorkallocationListBean {
        /**
         * employeeRecordId : 0
         * payType : 0
         * price : 0
         * quantityAllotted : 0
         * temporaryWorkerName : string
         */

        private int employeeRecordId;
        private int payType;
        private double price;
        private int quantityAllotted;
        private String temporaryWorkerName;

        public int getEmployeeRecordId() {
            return employeeRecordId;
        }

        public void setEmployeeRecordId(int employeeRecordId) {
            this.employeeRecordId = employeeRecordId;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantityAllotted() {
            return quantityAllotted;
        }

        public void setQuantityAllotted(int quantityAllotted) {
            this.quantityAllotted = quantityAllotted;
        }

        public String getTemporaryWorkerName() {
            return temporaryWorkerName;
        }

        public void setTemporaryWorkerName(String temporaryWorkerName) {
            this.temporaryWorkerName = temporaryWorkerName;
        }
    }
}
