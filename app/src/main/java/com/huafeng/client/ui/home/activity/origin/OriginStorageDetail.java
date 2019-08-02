package com.huafeng.client.ui.home.activity.origin;

import java.io.Serializable;
import java.util.List;

public class OriginStorageDetail implements Serializable {

    /**
     * inventoryMaterial : {"category1Id":0,"category2Id":0,"clothQuantity":"string","factoryId":0,"freeQuantity":0,"materialId":0,"normalQuantity":0}
     * inventoryMaterialLogList : [{"clothQuantity":"string","employeeRecordId":0,"employeeRecordName":"string","factoryId":0,"gmtCreate":"2019-06-17T02:39:22.975Z","id":0,"materialId":0,"quantity":0,"remainingClothQuantity":"string","remainingQuantity":0,"remarks":"string","title":"string","type":0}]
     * purchaseNoteSupplierList : [{"createTime":"2019-06-17T02:39:22.975Z","factoryId":0,"id":0,"materialId":0,"noteId":0,"quantity":0,"supplierId":0,"supplierName":"string","unitPrice":0}]
     * rawMaterial : {"category1Id":0,"category1Name":"string","category2Id":0,"category2Name":"string","createBy":0,"factoryId":0,"gmtCreate":"2019-06-17T02:39:22.975Z","gmtModified":"2019-06-17T02:39:22.975Z","id":0,"image":"string","imageUrl":"string","isDeleted":0,"name":"string","purchaseType":0,"updateBy":0}
     */

    private InventoryMaterialBean inventoryMaterial;
    private RawMaterialBean rawMaterial;
    private List<InventoryMaterialLogListBean> inventoryMaterialLogList;
    private List<PurchaseNoteSupplierListBean> purchaseNoteSupplierList;

    public InventoryMaterialBean getInventoryMaterial() {
        return inventoryMaterial;
    }

    public void setInventoryMaterial(InventoryMaterialBean inventoryMaterial) {
        this.inventoryMaterial = inventoryMaterial;
    }

    public RawMaterialBean getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterialBean rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public List<InventoryMaterialLogListBean> getInventoryMaterialLogList() {
        return inventoryMaterialLogList;
    }

    public void setInventoryMaterialLogList(List<InventoryMaterialLogListBean> inventoryMaterialLogList) {
        this.inventoryMaterialLogList = inventoryMaterialLogList;
    }

    public List<PurchaseNoteSupplierListBean> getPurchaseNoteSupplierList() {
        return purchaseNoteSupplierList;
    }

    public void setPurchaseNoteSupplierList(List<PurchaseNoteSupplierListBean> purchaseNoteSupplierList) {
        this.purchaseNoteSupplierList = purchaseNoteSupplierList;
    }

    public static class InventoryMaterialBean implements Serializable{
        /**
         * category1Id : 0
         * category2Id : 0
         * clothQuantity : string
         * factoryId : 0
         * freeQuantity : 0.0
         * materialId : 0
         * normalQuantity : 0.0
         */

        private int category1Id;
        private int category2Id;
        private String clothQuantity;
        private int factoryId;
        private Double freeQuantity;
        private int materialId;
        private Double normalQuantity;

        public int getCategory1Id() {
            return category1Id;
        }

        public void setCategory1Id(int category1Id) {
            this.category1Id = category1Id;
        }

        public int getCategory2Id() {
            return category2Id;
        }

        public void setCategory2Id(int category2Id) {
            this.category2Id = category2Id;
        }

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

        public Double getFreeQuantity() {
            return freeQuantity;
        }

        public void setFreeQuantity(Double freeQuantity) {
            this.freeQuantity = freeQuantity;
        }

        public int getMaterialId() {
            return materialId;
        }

        public void setMaterialId(int materialId) {
            this.materialId = materialId;
        }

        public Double getNormalQuantity() {
            return normalQuantity;
        }

        public void setNormalQuantity(Double normalQuantity) {
            this.normalQuantity = normalQuantity;
        }
    }

    public static class RawMaterialBean implements Serializable{
        /**
         * category1Id : 0
         * category1Name : string
         * category2Id : 0
         * category2Name : string
         * createBy : 0
         * factoryId : 0
         * gmtCreate : 2019-06-17T02:39:22.975Z
         * gmtModified : 2019-06-17T02:39:22.975Z
         * id : 0
         * image : string
         * imageUrl : string
         * isDeleted : 0
         * name : string
         * purchaseType : 0
         * updateBy : 0
         */
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        private int category1Id;
        private String category1Name;
        private int category2Id;
        private String category2Name;
        private int createBy;
        private int factoryId;
        private String gmtCreate;
        private String gmtModified;
        private int id;
        private String image;
        private String imageUrl;
        private int isDeleted;
        private String name;
        private int purchaseType;
        private int updateBy;

        public int getCategory1Id() {
            return category1Id;
        }

        public void setCategory1Id(int category1Id) {
            this.category1Id = category1Id;
        }

        public String getCategory1Name() {
            return category1Name;
        }

        public void setCategory1Name(String category1Name) {
            this.category1Name = category1Name;
        }

        public int getCategory2Id() {
            return category2Id;
        }

        public void setCategory2Id(int category2Id) {
            this.category2Id = category2Id;
        }

        public String getCategory2Name() {
            return category2Name;
        }

        public void setCategory2Name(String category2Name) {
            this.category2Name = category2Name;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPurchaseType() {
            return purchaseType;
        }

        public void setPurchaseType(int purchaseType) {
            this.purchaseType = purchaseType;
        }

        public int getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(int updateBy) {
            this.updateBy = updateBy;
        }
    }

    public static class InventoryMaterialLogListBean implements Serializable{
        /**
         * clothQuantity : string
         * employeeRecordId : 0
         * employeeRecordName : string
         * factoryId : 0
         * gmtCreate : 2019-06-17T02:39:22.975Z
         * id : 0
         * materialId : 0
         * quantity : 0.0
         * remainingClothQuantity : string
         * remainingQuantity : 0.0
         * remarks : string
         * title : string
         * type : 0
         */

        private String clothQuantity;
        private int employeeRecordId;
        private String employeeRecordName;
        private int factoryId;
        private String gmtCreate;
        private int id;
        private int materialId;
        private Double quantity;
        private String remainingClothQuantity;
        private Double remainingQuantity;
        private String remarks;
        private String title;
        private int type;

        public String getClothQuantity() {
            return clothQuantity;
        }

        public void setClothQuantity(String clothQuantity) {
            this.clothQuantity = clothQuantity;
        }

        public int getEmployeeRecordId() {
            return employeeRecordId;
        }

        public void setEmployeeRecordId(int employeeRecordId) {
            this.employeeRecordId = employeeRecordId;
        }

        public String getEmployeeRecordName() {
            return employeeRecordName;
        }

        public void setEmployeeRecordName(String employeeRecordName) {
            this.employeeRecordName = employeeRecordName;
        }

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
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

        public int getMaterialId() {
            return materialId;
        }

        public void setMaterialId(int materialId) {
            this.materialId = materialId;
        }

        public Double getQuantity() {
            return quantity;
        }

        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        public String getRemainingClothQuantity() {
            return remainingClothQuantity;
        }

        public void setRemainingClothQuantity(String remainingClothQuantity) {
            this.remainingClothQuantity = remainingClothQuantity;
        }

        public Double getRemainingQuantity() {
            return remainingQuantity;
        }

        public void setRemainingQuantity(Double remainingQuantity) {
            this.remainingQuantity = remainingQuantity;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class PurchaseNoteSupplierListBean implements Serializable{
        /**
         * createTime : 2019-06-17T02:39:22.975Z
         * factoryId : 0
         * id : 0
         * materialId : 0
         * noteId : 0
         * quantity : 0.0
         * supplierId : 0
         * supplierName : string
         * unitPrice : 0.0
         */

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
