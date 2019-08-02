package com.huafeng.client.ui.home.activity.buy;

import java.util.List;

public class BuyOrderDetail {

    /**
     * factoryId : 0
     * fillBy : 0
     * fillByName : string
     * fillByPostName : string
     * fillTime : 2019-06-16T03:27:08.393Z
     * gmtCreate : 2019-06-16T03:27:08.393Z
     * id : 0
     * noteList : [{"clothQuantity":"string","factoryId":0,"fillTime":"2019-06-16T03:27:08.393Z","gmtCreate":"2019-06-16T03:27:08.393Z","id":0,"isCloth":true,"materialId":0,"materialName":"string","needQuantity":0,"noteGroupId":0,"orderId":0,"productionOrderId":0,"realQuantity":0,"requirementId":0,"requisitionId":0,"status":0,"supplierList":[{"createTime":"2019-06-16T03:27:08.393Z","factoryId":0,"id":0,"materialId":0,"noteId":0,"quantity":0,"supplierId":0,"supplierName":"string","unitPrice":0}],"type":0}]
     * noteNumber : string
     * orderId : 0
     * orderNumber : string
     * payMethod : 0
     * productionOrderId : 0
     * productionOrderNumber : string
     * remarks : string
     * requisitionCreateBy : 0
     * requisitionCreateByName : string
     * requisitionCreateTime : 2019-06-16T03:27:08.393Z
     * requisitionGroupId : 0
     * requisitionNumber : string
     * sampleNo : string
     * status : 0
     * type : 0
     */

    private int factoryId;
    private int fillBy;
    private String fillByName;
    private String fillByPostName;
    private String fillTime;
    private String gmtCreate;
    private int id;
    private String noteNumber;
    private int orderId;
    private String orderNumber;
    private int payMethod;
    private int productionOrderId;
    private String productionOrderNumber;
    private String remarks;
    private int requisitionCreateBy;
    private String requisitionCreateByName;
    private String requisitionCreateTime;
    private int requisitionGroupId;
    private String requisitionNumber;
    private String sampleNo;
    private int status;
    private int type;
    private List<NoteListBean> noteList;

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getFillBy() {
        return fillBy;
    }

    public void setFillBy(int fillBy) {
        this.fillBy = fillBy;
    }

    public String getFillByName() {
        return fillByName;
    }

    public void setFillByName(String fillByName) {
        this.fillByName = fillByName;
    }

    public String getFillByPostName() {
        return fillByPostName;
    }

    public void setFillByPostName(String fillByPostName) {
        this.fillByPostName = fillByPostName;
    }

    public String getFillTime() {
        return fillTime;
    }

    public void setFillTime(String fillTime) {
        this.fillTime = fillTime;
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

    public String getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(String noteNumber) {
        this.noteNumber = noteNumber;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public int getProductionOrderId() {
        return productionOrderId;
    }

    public void setProductionOrderId(int productionOrderId) {
        this.productionOrderId = productionOrderId;
    }

    public String getProductionOrderNumber() {
        return productionOrderNumber;
    }

    public void setProductionOrderNumber(String productionOrderNumber) {
        this.productionOrderNumber = productionOrderNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getRequisitionCreateBy() {
        return requisitionCreateBy;
    }

    public void setRequisitionCreateBy(int requisitionCreateBy) {
        this.requisitionCreateBy = requisitionCreateBy;
    }

    public String getRequisitionCreateByName() {
        return requisitionCreateByName;
    }

    public void setRequisitionCreateByName(String requisitionCreateByName) {
        this.requisitionCreateByName = requisitionCreateByName;
    }

    public String getRequisitionCreateTime() {
        return requisitionCreateTime;
    }

    public void setRequisitionCreateTime(String requisitionCreateTime) {
        this.requisitionCreateTime = requisitionCreateTime;
    }

    public int getRequisitionGroupId() {
        return requisitionGroupId;
    }

    public void setRequisitionGroupId(int requisitionGroupId) {
        this.requisitionGroupId = requisitionGroupId;
    }

    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setRequisitionNumber(String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<NoteListBean> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<NoteListBean> noteList) {
        this.noteList = noteList;
    }

    public static class NoteListBean {
        /**
         * clothQuantity : string
         * factoryId : 0
         * fillTime : 2019-06-16T03:27:08.393Z
         * gmtCreate : 2019-06-16T03:27:08.393Z
         * id : 0
         * isCloth : true
         * materialId : 0
         * materialName : string
         * needQuantity : 0.0
         * noteGroupId : 0
         * orderId : 0
         * productionOrderId : 0
         * realQuantity : 0.0
         * requirementId : 0
         * requisitionId : 0
         * status : 0
         * supplierList : [{"createTime":"2019-06-16T03:27:08.393Z","factoryId":0,"id":0,"materialId":0,"noteId":0,"quantity":0,"supplierId":0,"supplierName":"string","unitPrice":0}]
         * type : 0
         */
        private String materialName;
        private Double needQuantity;
        private String clothQuantity;
        private int factoryId;
        private String fillTime;
        private String gmtCreate;
        private int id;
        private boolean isCloth;
        private int materialId;
        private int noteGroupId;
        private int orderId;
        private int productionOrderId;
        private Double realQuantity;
        private int requirementId;
        private int requisitionId;
        private int status;
        private int type;
        private List<SupplierListBean> supplierList;

        public String getClothQuantity() {
            return clothQuantity;
        }

        public void setClothQuantity(String clothQuantity) {
            this.clothQuantity = clothQuantity;
        }

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
        }

        public String getFillTime() {
            return fillTime;
        }

        public void setFillTime(String fillTime) {
            this.fillTime = fillTime;
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

        public boolean isIsCloth() {
            return isCloth;
        }

        public void setIsCloth(boolean isCloth) {
            this.isCloth = isCloth;
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

        public Double getNeedQuantity() {
            return needQuantity;
        }

        public void setNeedQuantity(Double needQuantity) {
            this.needQuantity = needQuantity;
        }

        public int getNoteGroupId() {
            return noteGroupId;
        }

        public void setNoteGroupId(int noteGroupId) {
            this.noteGroupId = noteGroupId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getProductionOrderId() {
            return productionOrderId;
        }

        public void setProductionOrderId(int productionOrderId) {
            this.productionOrderId = productionOrderId;
        }

        public Double getRealQuantity() {
            return realQuantity;
        }

        public void setRealQuantity(Double realQuantity) {
            this.realQuantity = realQuantity;
        }

        public int getRequirementId() {
            return requirementId;
        }

        public void setRequirementId(int requirementId) {
            this.requirementId = requirementId;
        }

        public int getRequisitionId() {
            return requisitionId;
        }

        public void setRequisitionId(int requisitionId) {
            this.requisitionId = requisitionId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<SupplierListBean> getSupplierList() {
            return supplierList;
        }

        public void setSupplierList(List<SupplierListBean> supplierList) {
            this.supplierList = supplierList;
        }

        public static class SupplierListBean {
            /**
             * createTime : 2019-06-16T03:27:08.393Z
             * factoryId : 0
             * id : 0
             * materialId : 0
             * noteId : 0
             * quantity : 0.0
             * supplierId : 0
             * supplierName : string
             * unitPrice : 0.0
             */
            private String materialName;
            private Double needQuantity;
            private String clothQuantity;
            private boolean isShowDel;
            private boolean isShowAdd;
            private boolean isHeader;

            public boolean isHeader() {
                return isHeader;
            }

            public void setHeader(boolean header) {
                isHeader = header;
            }

            public boolean isShowAdd() {
                return isShowAdd;
            }

            public void setShowAdd(boolean showAdd) {
                isShowAdd = showAdd;
            }

            public boolean isShowDel() {
                return isShowDel;
            }

            public void setShowDel(boolean showDel) {
                isShowDel = showDel;
            }

            public String getMaterialName() {
                return materialName;
            }

            public void setMaterialName(String materialName) {
                this.materialName = materialName;
            }

            public Double getNeedQuantity() {
                return needQuantity;
            }

            public void setNeedQuantity(Double needQuantity) {
                this.needQuantity = needQuantity;
            }

            public String getClothQuantity() {
                return clothQuantity;
            }

            public void setClothQuantity(String clothQuantity) {
                this.clothQuantity = clothQuantity;
            }

            private String createTime;
            private int factoryId;
            private int id;
            private int materialId;
            private int noteId;
            private Double quantity;
            private int supplierId;
            private String supplierName;
            private Double unitPrice;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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

            public int getMaterialId() {
                return materialId;
            }

            public void setMaterialId(int materialId) {
                this.materialId = materialId;
            }

            public int getNoteId() {
                return noteId;
            }

            public void setNoteId(int noteId) {
                this.noteId = noteId;
            }

            public Double getQuantity() {
                return quantity;
            }

            public void setQuantity(Double quantity) {
                this.quantity = quantity;
            }

            public int getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(int supplierId) {
                this.supplierId = supplierId;
            }

            public String getSupplierName() {
                return supplierName;
            }

            public void setSupplierName(String supplierName) {
                this.supplierName = supplierName;
            }

            public Double getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(Double unitPrice) {
                this.unitPrice = unitPrice;
            }
        }
    }
}
