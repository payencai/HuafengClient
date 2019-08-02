package com.huafeng.client.ui.home.activity.sample;

import java.util.List;

public class SampleSubmit {

    /**
     * clientRecordId : 0
     * color : string
     * designerBy : 0
     * flowId : 0
     * id : 0
     * images : string
     * materialList : [{"quantity":0,"rawMaterialId":0}]
     * patternNo : string
     * productCategoryId : 0
     * productProcessList : [{"isOutsourcing":0,"price":0,"processId":0}]
     * remarks : string
     * sampleNo : string
     * sizeList : [{"sampleSizeInformationList":[{"quantity":0,"sizeInfomationId":0}],"sizeId":0}]
     * washingProcessList : [{"washingProcessId":0}]
     */

    private int clientRecordId;
    private String color;
    private int designerBy;
    private int flowId;
    private int id;
    private String images;
    private String patternNo;
    private int productCategoryId;
    private String remarks;
    private String sampleNo;
    private List<MaterialListBean> materialList;
    private List<ProductProcessListBean> productProcessList;
    private List<SizeListBean> sizeList;
    private List<WashingProcessListBean> washingProcessList;

    public int getClientRecordId() {
        return clientRecordId;
    }

    public void setClientRecordId(int clientRecordId) {
        this.clientRecordId = clientRecordId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDesignerBy() {
        return designerBy;
    }

    public void setDesignerBy(int designerBy) {
        this.designerBy = designerBy;
    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPatternNo() {
        return patternNo;
    }

    public void setPatternNo(String patternNo) {
        this.patternNo = patternNo;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public List<MaterialListBean> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<MaterialListBean> materialList) {
        this.materialList = materialList;
    }

    public List<ProductProcessListBean> getProductProcessList() {
        return productProcessList;
    }

    public void setProductProcessList(List<ProductProcessListBean> productProcessList) {
        this.productProcessList = productProcessList;
    }

    public List<SizeListBean> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<SizeListBean> sizeList) {
        this.sizeList = sizeList;
    }

    public List<WashingProcessListBean> getWashingProcessList() {
        return washingProcessList;
    }

    public void setWashingProcessList(List<WashingProcessListBean> washingProcessList) {
        this.washingProcessList = washingProcessList;
    }

    public static class MaterialListBean {
        public int getRawMaterialCategory2Id() {
            return rawMaterialCategory2Id;
        }

        public void setRawMaterialCategory2Id(int rawMaterialCategory2Id) {
            this.rawMaterialCategory2Id = rawMaterialCategory2Id;
        }

        public String getRawMaterialName() {
            return rawMaterialName;
        }

        public void setRawMaterialName(String rawMaterialName) {
            this.rawMaterialName = rawMaterialName;
        }

        /**
         * quantity : 0.0
         * rawMaterialId : 0
         */

        private int rawMaterialCategory2Id;
        private String rawMaterialName;
        private double quantity;
        private int rawMaterialId;

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public int getRawMaterialId() {
            return rawMaterialId;
        }

        public void setRawMaterialId(int rawMaterialId) {
            this.rawMaterialId = rawMaterialId;
        }
    }

    public static class ProductProcessListBean {
        /**
         * isOutsourcing : 0
         * price : 0.0
         * processId : 0
         */
        private String input;

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

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
         * sampleSizeInformationList : [{"quantity":0,"sizeInfomationId":0}]
         * sizeId : 0
         */

        private int sizeId;
        private List<SampleSizeInformationListBean> sampleSizeInformationList;

        public int getSizeId() {
            return sizeId;
        }

        public void setSizeId(int sizeId) {
            this.sizeId = sizeId;
        }

        public List<SampleSizeInformationListBean> getSampleSizeInformationList() {
            return sampleSizeInformationList;
        }

        public void setSampleSizeInformationList(List<SampleSizeInformationListBean> sampleSizeInformationList) {
            this.sampleSizeInformationList = sampleSizeInformationList;
        }

        public static class SampleSizeInformationListBean {
            /**
             * quantity : 0
             * sizeInfomationId : 0
             */


            private double quantity;
            private int sizeInfomationId;

            public double getQuantity() {
                return quantity;
            }

            public void setQuantity(double quantity) {
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

    public static class WashingProcessListBean {
        /**
         * washingProcessId : 0
         */

        private int washingProcessId;

        public int getWashingProcessId() {
            return washingProcessId;
        }

        public void setWashingProcessId(int washingProcessId) {
            this.washingProcessId = washingProcessId;
        }
    }
}
