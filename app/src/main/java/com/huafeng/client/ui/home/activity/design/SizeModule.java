package com.huafeng.client.ui.home.activity.design;

import java.util.List;

public class SizeModule {

    /**
     * id : 137
     * moduleId : 26
     * sizeId : 13
     * sizeName : M
     * sizeGroupId : 4
     * sizeGroupName : SML
     * factoryId : 3
     * sampleSizeInformationList : [{"id":308,"moduleSizeId":137,"moduleId":26,"sizeId":13,"sizeInfomationId":1,"sizeInformationName":"衣长","quantity":null,"factoryId":3},{"id":309,"moduleSizeId":137,"moduleId":26,"sizeId":13,"sizeInfomationId":2,"sizeInformationName":"袖长","quantity":null,"factoryId":3},{"id":310,"moduleSizeId":137,"moduleId":26,"sizeId":13,"sizeInfomationId":3,"sizeInformationName":"腰围","quantity":null,"factoryId":3}]
     */

    private int id;
    private int moduleId;
    private int sizeId;
    private String sizeName;
    private int sizeGroupId;
    private String sizeGroupName;
    private int factoryId;
    private List<SampleSizeInformationListBean> sampleSizeInformationList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

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

    public int getSizeGroupId() {
        return sizeGroupId;
    }

    public void setSizeGroupId(int sizeGroupId) {
        this.sizeGroupId = sizeGroupId;
    }

    public String getSizeGroupName() {
        return sizeGroupName;
    }

    public void setSizeGroupName(String sizeGroupName) {
        this.sizeGroupName = sizeGroupName;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public List<SampleSizeInformationListBean> getSampleSizeInformationList() {
        return sampleSizeInformationList;
    }

    public void setSampleSizeInformationList(List<SampleSizeInformationListBean> sampleSizeInformationList) {
        this.sampleSizeInformationList = sampleSizeInformationList;
    }

    public static class SampleSizeInformationListBean {
        /**
         * id : 308
         * moduleSizeId : 137
         * moduleId : 26
         * sizeId : 13
         * sizeInfomationId : 1
         * sizeInformationName : 衣长
         * quantity : null
         * factoryId : 3
         */

        private int id;
        private int moduleSizeId;
        private int moduleId;
        private int sizeId;
        private int sizeInfomationId;
        private String sizeInformationName;
        private Object quantity;
        private int factoryId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getModuleSizeId() {
            return moduleSizeId;
        }

        public void setModuleSizeId(int moduleSizeId) {
            this.moduleSizeId = moduleSizeId;
        }

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
        }

        public int getSizeId() {
            return sizeId;
        }

        public void setSizeId(int sizeId) {
            this.sizeId = sizeId;
        }

        public int getSizeInfomationId() {
            return sizeInfomationId;
        }

        public void setSizeInfomationId(int sizeInfomationId) {
            this.sizeInfomationId = sizeInfomationId;
        }

        public String getSizeInformationName() {
            return sizeInformationName;
        }

        public void setSizeInformationName(String sizeInformationName) {
            this.sizeInformationName = sizeInformationName;
        }

        public Object getQuantity() {
            return quantity;
        }

        public void setQuantity(Object quantity) {
            this.quantity = quantity;
        }

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
        }
    }
}
