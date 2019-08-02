package com.huafeng.client.ui.home.activity.design;

import java.util.List;

public class ModuleSubmit {

    /**
     * id : 0
     * materialList : [{"quantity":0,"rawMaterialId":0}]
     * name : string
     * processList : [{"isOutsourcing":0,"price":0,"processId":0}]
     * sizeList : [{"moduleSizeInformationList":[{"quantity":0,"sizeInfomationId":0}],"sizeId":0}]
     */

    private int id;

    private List<MaterialListBean> materialList;
    private List<ProcessListBean> processList;
    private List<SizeListBean> sizeList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public List<MaterialListBean> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<MaterialListBean> materialList) {
        this.materialList = materialList;
    }

    public List<ProcessListBean> getProcessList() {
        return processList;
    }

    public void setProcessList(List<ProcessListBean> processList) {
        this.processList = processList;
    }

    public List<SizeListBean> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<SizeListBean> sizeList) {
        this.sizeList = sizeList;
    }

    public static class MaterialListBean {
        /**
         * quantity : 0
         * rawMaterialId : 0
         */

        private Double quantity;
        private int rawMaterialId;

        public Double getQuantity() {
            return quantity;
        }

        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        public int getRawMaterialId() {
            return rawMaterialId;
        }

        public void setRawMaterialId(int rawMaterialId) {
            this.rawMaterialId = rawMaterialId;
        }
    }

    public static class ProcessListBean {
        /**
         * isOutsourcing : 0
         * price : 0
         * processId : 0
         */

        private int isOutsourcing;
        private Double price;
        private int processId;

        public int getIsOutsourcing() {
            return isOutsourcing;
        }

        public void setIsOutsourcing(int isOutsourcing) {
            this.isOutsourcing = isOutsourcing;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public int getProcessId() {
            return processId;
        }

        public void setProcessId(int processId) {
            this.processId = processId;
        }
    }

    public static class SizeListBean {
        /**
         * moduleSizeInformationList : [{"quantity":0,"sizeInfomationId":0}]
         * sizeId : 0
         */

        private int sizeId;
        private List<ModuleSizeInformationListBean> moduleSizeInformationList;

        public int getSizeId() {
            return sizeId;
        }

        public void setSizeId(int sizeId) {
            this.sizeId = sizeId;
        }

        public List<ModuleSizeInformationListBean> getModuleSizeInformationList() {
            return moduleSizeInformationList;
        }

        public void setModuleSizeInformationList(List<ModuleSizeInformationListBean> moduleSizeInformationList) {
            this.moduleSizeInformationList = moduleSizeInformationList;
        }

        public static class ModuleSizeInformationListBean {
            /**
             * quantity : 0
             * sizeInfomationId : 0
             */

            private int quantity;
            private int sizeInfomationId;

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getSizeInfomationId() {
                return sizeInfomationId;
            }

            public void setSizeInfomationId(int sizeInfomationId) {
                this.sizeInfomationId = sizeInfomationId;
            }
        }
    }
}
