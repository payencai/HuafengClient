package com.huafeng.client.ui.home.activity.buy;

import java.util.List;

public class BuyOrderSubmit {

    /**
     * groupId : 0
     * noteList : [{"clothQuantity":"string","id":0}]
     * noteSupplierList : [{"noteId":0,"quantity":0,"supplierId":0,"unitPrice":0}]
     * remarks : string
     */

    private int groupId;
    private String remarks;
    private List<NoteListBean> noteList;
    private List<NoteSupplierListBean> noteSupplierList;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<NoteListBean> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<NoteListBean> noteList) {
        this.noteList = noteList;
    }

    public List<NoteSupplierListBean> getNoteSupplierList() {
        return noteSupplierList;
    }

    public void setNoteSupplierList(List<NoteSupplierListBean> noteSupplierList) {
        this.noteSupplierList = noteSupplierList;
    }

    public static class NoteListBean {
        /**
         * clothQuantity : string
         * id : 0
         */

        private String clothQuantity;
        private int id;

        public String getClothQuantity() {
            return clothQuantity;
        }

        public void setClothQuantity(String clothQuantity) {
            this.clothQuantity = clothQuantity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class NoteSupplierListBean {
        /**
         * noteId : 0
         * quantity : 0
         * supplierId : 0
         * unitPrice : 0
         */

        private int noteId;
        private Double quantity;
        private int supplierId;
        private Double unitPrice;

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

        public Double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
        }
    }
}
