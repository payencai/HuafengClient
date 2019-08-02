package com.huafeng.client.ui.home.activity.produce;

import java.util.List;

public class PreWork {

    /**
     * checkClerkId : 0
     * factoryId : 0
     * fillClerkId : 0
     * fillLeaderId : 0
     * gmtClerkCheck : 2019-07-04T10:07:01.041Z
     * gmtClerkFill : 2019-07-04T10:07:01.041Z
     * gmtLast : 2019-07-04T10:07:01.041Z
     * gmtLeaderFill : 2019-07-04T10:07:01.041Z
     * id : 0
     * lastRecordName : string
     * processId : 0
     * processName : string
     * processPrice : 0
     * productionOrderFlowId : 0
     * productionOrderId : 0
     * productionOrderProductProcessId : 0
     * quantityAllotted : 0
     * stageId : 0
     * stageName : string
     * status : 0
     * workAllocationList : [{"employeeName":"string","employeeNumber":"string","employeeRecordId":0,"factoryId":0,"id":0,"payType":0,"price":0,"productionOrderFlowId":0,"productionOrderId":0,"productionOrderProductProcessId":0,"productionOrderWorkAllocationProcessId":0,"quantityAllotted":0,"sequence":0,"temporaryWorkerName":"string"}]
     */

    private int checkClerkId;
    private int factoryId;
    private int fillClerkId;
    private int fillLeaderId;
    private String gmtClerkCheck;
    private String gmtClerkFill;
    private String gmtLast;
    private String gmtLeaderFill;
    private int id;
    private String lastRecordName;
    private int processId;
    private String processName;
    private double processPrice;
    private int productionOrderFlowId;
    private int productionOrderId;
    private int productionOrderProductProcessId;
    private int quantityAllotted;
    private int stageId;
    private String stageName;
    private int status;
    private List<WorkAllocationListBean> workAllocationList;

    public int getCheckClerkId() {
        return checkClerkId;
    }

    public void setCheckClerkId(int checkClerkId) {
        this.checkClerkId = checkClerkId;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getFillClerkId() {
        return fillClerkId;
    }

    public void setFillClerkId(int fillClerkId) {
        this.fillClerkId = fillClerkId;
    }

    public int getFillLeaderId() {
        return fillLeaderId;
    }

    public void setFillLeaderId(int fillLeaderId) {
        this.fillLeaderId = fillLeaderId;
    }

    public String getGmtClerkCheck() {
        return gmtClerkCheck;
    }

    public void setGmtClerkCheck(String gmtClerkCheck) {
        this.gmtClerkCheck = gmtClerkCheck;
    }

    public String getGmtClerkFill() {
        return gmtClerkFill;
    }

    public void setGmtClerkFill(String gmtClerkFill) {
        this.gmtClerkFill = gmtClerkFill;
    }

    public String getGmtLast() {
        return gmtLast;
    }

    public void setGmtLast(String gmtLast) {
        this.gmtLast = gmtLast;
    }

    public String getGmtLeaderFill() {
        return gmtLeaderFill;
    }

    public void setGmtLeaderFill(String gmtLeaderFill) {
        this.gmtLeaderFill = gmtLeaderFill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastRecordName() {
        return lastRecordName;
    }

    public void setLastRecordName(String lastRecordName) {
        this.lastRecordName = lastRecordName;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public double getProcessPrice() {
        return processPrice;
    }

    public void setProcessPrice(double processPrice) {
        this.processPrice = processPrice;
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

    public int getProductionOrderProductProcessId() {
        return productionOrderProductProcessId;
    }

    public void setProductionOrderProductProcessId(int productionOrderProductProcessId) {
        this.productionOrderProductProcessId = productionOrderProductProcessId;
    }

    public int getQuantityAllotted() {
        return quantityAllotted;
    }

    public void setQuantityAllotted(int quantityAllotted) {
        this.quantityAllotted = quantityAllotted;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<WorkAllocationListBean> getWorkAllocationList() {
        return workAllocationList;
    }

    public void setWorkAllocationList(List<WorkAllocationListBean> workAllocationList) {
        this.workAllocationList = workAllocationList;
    }

    public static class WorkAllocationListBean {
        /**
         * employeeName : string
         * employeeNumber : string
         * employeeRecordId : 0
         * factoryId : 0
         * id : 0
         * payType : 0
         * price : 0
         * productionOrderFlowId : 0
         * productionOrderId : 0
         * productionOrderProductProcessId : 0
         * productionOrderWorkAllocationProcessId : 0
         * quantityAllotted : 0
         * sequence : 0
         * temporaryWorkerName : string
         */

        private String employeeName;
        private String employeeNumber;
        private int employeeRecordId;
        private int factoryId;
        private int id;
        private int payType;
        private double price;
        private int productionOrderFlowId;
        private int productionOrderId;
        private int productionOrderProductProcessId;
        private int productionOrderWorkAllocationProcessId;
        private int quantityAllotted;
        private int sequence;
        private String temporaryWorkerName;

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getEmployeeNumber() {
            return employeeNumber;
        }

        public void setEmployeeNumber(String employeeNumber) {
            this.employeeNumber = employeeNumber;
        }

        public int getEmployeeRecordId() {
            return employeeRecordId;
        }

        public void setEmployeeRecordId(int employeeRecordId) {
            this.employeeRecordId = employeeRecordId;
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

        public int getProductionOrderProductProcessId() {
            return productionOrderProductProcessId;
        }

        public void setProductionOrderProductProcessId(int productionOrderProductProcessId) {
            this.productionOrderProductProcessId = productionOrderProductProcessId;
        }

        public int getProductionOrderWorkAllocationProcessId() {
            return productionOrderWorkAllocationProcessId;
        }

        public void setProductionOrderWorkAllocationProcessId(int productionOrderWorkAllocationProcessId) {
            this.productionOrderWorkAllocationProcessId = productionOrderWorkAllocationProcessId;
        }

        public int getQuantityAllotted() {
            return quantityAllotted;
        }

        public void setQuantityAllotted(int quantityAllotted) {
            this.quantityAllotted = quantityAllotted;
        }

        public int getSequence() {
            return sequence;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }

        public String getTemporaryWorkerName() {
            return temporaryWorkerName;
        }

        public void setTemporaryWorkerName(String temporaryWorkerName) {
            this.temporaryWorkerName = temporaryWorkerName;
        }
    }
}
