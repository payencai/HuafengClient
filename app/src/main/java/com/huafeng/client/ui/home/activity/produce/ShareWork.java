package com.huafeng.client.ui.home.activity.produce;

import java.util.List;

public class ShareWork {

    /**
     * price : 0
     * processName : string
     * productionOrderProductProcessId : 0
     * workallocationList : [{"employeeRecordId":0,"payType":0,"price":0,"quantityAllotted":0,"temporaryWorkerName":"string"}]
     */

    private double price;
    private String processName;
    private int num;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private int productionOrderProductProcessId;
    private List<WorkallocationListBean> workallocationList;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getProductionOrderProductProcessId() {
        return productionOrderProductProcessId;
    }

    public void setProductionOrderProductProcessId(int productionOrderProductProcessId) {
        this.productionOrderProductProcessId = productionOrderProductProcessId;
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
         * price : 0.0
         * quantityAllotted : 0
         * temporaryWorkerName : string
         */
        private String name;
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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
