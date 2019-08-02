package com.huafeng.client.clientui;

import java.util.List;

public class ClientModelDetail {

    /**
     * color : string
     * factoryId : 0
     * gmtCreate : 2019-07-01T06:47:56.198Z
     * gmtModified : 2019-07-01T06:47:56.198Z
     * id : 0
     * images : string
     * imagesUrl : string
     * isDeleted : 0
     * productCategory1Id : 0
     * productCategory1Name : string
     * productCategory2Name : string
     * productCategoryId : 0
     * remarks : string
     * sampleNo : string
     * sizeList : [{"factoryId":0,"id":0,"sampleId":0,"sampleSizeInformationList":[{"factoryId":0,"id":0,"quantity":0,"sampleId":0,"sampleSizeId":0,"sizeId":0,"sizeInfomationId":0,"sizeInformationName":"string"}],"sizeGroupId":0,"sizeGroupName":"string","sizeId":0,"sizeName":"string"}]
     */

    private String color;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private int id;
    private String images;
    private String imagesUrl;
    private int isDeleted;
    private int productCategory1Id;
    private String productCategory1Name;
    private String productCategory2Name;
    private int productCategoryId;
    private String remarks;
    private String sampleNo;
    private List<SizeListBean> sizeList;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getProductCategory1Id() {
        return productCategory1Id;
    }

    public void setProductCategory1Id(int productCategory1Id) {
        this.productCategory1Id = productCategory1Id;
    }

    public String getProductCategory1Name() {
        return productCategory1Name;
    }

    public void setProductCategory1Name(String productCategory1Name) {
        this.productCategory1Name = productCategory1Name;
    }

    public String getProductCategory2Name() {
        return productCategory2Name;
    }

    public void setProductCategory2Name(String productCategory2Name) {
        this.productCategory2Name = productCategory2Name;
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

    public List<SizeListBean> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<SizeListBean> sizeList) {
        this.sizeList = sizeList;
    }

    public static class SizeListBean {
        /**
         * factoryId : 0
         * id : 0
         * sampleId : 0
         * sampleSizeInformationList : [{"factoryId":0,"id":0,"quantity":0,"sampleId":0,"sampleSizeId":0,"sizeId":0,"sizeInfomationId":0,"sizeInformationName":"string"}]
         * sizeGroupId : 0
         * sizeGroupName : string
         * sizeId : 0
         * sizeName : string
         */

        private int factoryId;
        private int id;
        private int sampleId;
        private int sizeGroupId;
        private String sizeGroupName;
        private int sizeId;
        private String sizeName;
        private List<SampleSizeInformationListBean> sampleSizeInformationList;

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

        public int getSampleId() {
            return sampleId;
        }

        public void setSampleId(int sampleId) {
            this.sampleId = sampleId;
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

        public List<SampleSizeInformationListBean> getSampleSizeInformationList() {
            return sampleSizeInformationList;
        }

        public void setSampleSizeInformationList(List<SampleSizeInformationListBean> sampleSizeInformationList) {
            this.sampleSizeInformationList = sampleSizeInformationList;
        }

        public static class SampleSizeInformationListBean {
            /**
             * factoryId : 0
             * id : 0
             * quantity : 0
             * sampleId : 0
             * sampleSizeId : 0
             * sizeId : 0
             * sizeInfomationId : 0
             * sizeInformationName : string
             */

            private int factoryId;
            private int id;
            private double quantity;
            private int sampleId;
            private int sampleSizeId;
            private int sizeId;
            private int sizeInfomationId;
            private String sizeInformationName;

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

            public double getQuantity() {
                return quantity;
            }

            public void setQuantity(double quantity) {
                this.quantity = quantity;
            }

            public int getSampleId() {
                return sampleId;
            }

            public void setSampleId(int sampleId) {
                this.sampleId = sampleId;
            }

            public int getSampleSizeId() {
                return sampleSizeId;
            }

            public void setSampleSizeId(int sampleSizeId) {
                this.sampleSizeId = sampleSizeId;
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
        }
    }
}
