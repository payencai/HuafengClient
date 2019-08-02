package com.huafeng.client.ui.home.model;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/10 19:32
 * 邮箱：771548229@qq..com
 */
public class BuyOrder {

    /**
     * factoryId : 0
     * fillBy : 0
     * fillByName : string
     * fillByPostName : string
     * fillTime : 2019-05-14T03:26:22.652Z
     * gmtCreate : 2019-05-14T03:26:22.652Z
     * id : 0
     * noteList : [{"clothQuantity":"string","factoryId":0,"fillTime":"2019-05-14T03:26:22.652Z","gmtCreate":"2019-05-14T03:26:22.652Z","id":0,"isCloth":true,"materialId":0,"materialName":"string","noteGroupId":0,"orderId":0,"productionOrderId":0,"realQuantity":0,"requirementId":0,"requisitionId":0,"status":0,"supplierList":[{"createTime":"2019-05-14T03:26:22.652Z","factoryId":0,"id":0,"materialId":0,"noteId":0,"quantity":0,"supplierId":0,"supplierName":"string","unitPrice":0}],"type":0}]
     * noteNumber : string
     * orderId : 0
     * orderNumber : string
     * payMethod : 0
     * productionOrderId : 0
     * remarks : string
     * requisitionCreateBy : 0
     * requisitionCreateByName : string
     * requisitionCreateTime : 2019-05-14T03:26:22.652Z
     * requisitionGroupId : 0
     * requisitionNumber : string
     * status : 0
     * type : 0
     */
    private String materialNames;
    private String sampleNo;

    public String getMaterialNames() {
        return materialNames;
    }

    public void setMaterialNames(String materialNames) {
        this.materialNames = materialNames;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

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
    private String remarks;
    private int requisitionCreateBy;
    private String requisitionCreateByName;
    private String requisitionCreateTime;
    private int requisitionGroupId;
    private String requisitionNumber;
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
         * fillTime : 2019-05-14T03:26:22.652Z
         * gmtCreate : 2019-05-14T03:26:22.652Z
         * id : 0
         * isCloth : true
         * materialId : 0
         * materialName : string
         * noteGroupId : 0
         * orderId : 0
         * productionOrderId : 0
         * realQuantity : 0
         * requirementId : 0
         * requisitionId : 0
         * status : 0
         * supplierList : [{"createTime":"2019-05-14T03:26:22.652Z","factoryId":0,"id":0,"materialId":0,"noteId":0,"quantity":0,"supplierId":0,"supplierName":"string","unitPrice":0}]
         * type : 0
         */

        private String clothQuantity;
        private int factoryId;
        private String fillTime;
        private String gmtCreate;
        private int id;
        private boolean isCloth;
        private int materialId;
        private String materialName;
        private int noteGroupId;
        private int orderId;
        private int productionOrderId;
        private int realQuantity;
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

        public int getRealQuantity() {
            return realQuantity;
        }

        public void setRealQuantity(int realQuantity) {
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
             * createTime : 2019-05-14T03:26:22.652Z
             * factoryId : 0
             * id : 0
             * materialId : 0
             * noteId : 0
             * quantity : 0
             * supplierId : 0
             * supplierName : string
             * unitPrice : 0
             */

            private String createTime;
            private int factoryId;
            private int id;
            private int materialId;
            private int noteId;
            private int quantity;
            private int supplierId;
            private String supplierName;
            private int unitPrice;

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

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
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

            public int getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(int unitPrice) {
                this.unitPrice = unitPrice;
            }
        }
    }
}
