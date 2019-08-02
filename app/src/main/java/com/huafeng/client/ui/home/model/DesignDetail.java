package com.huafeng.client.ui.home.model;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/5 20:26
 * 邮箱：771548229@qq..com
 */
public class DesignDetail {

    /**
     * operatorRecordList : [{"content":"string","person":"string","time":"2019-05-05T12:23:39.499Z","title":"string"}]
     * patternMakingCard : {"clientId":0,"clientName":"string","color":"string","createBy":0,"factoryId":0,"gmtCreate":"2019-05-05T12:23:39.499Z","gmtModified":"2019-05-05T12:23:39.499Z","id":0,"images":"string","imagesUrl":"string","materialList":[{"factoryId":0,"id":0,"patternMakingCardId":0,"quantity":0,"rawMaterialCategory1Id":0,"rawMaterialCategory1Name":"string","rawMaterialCategory2Id":0,"rawMaterialCategory2Name":"string","rawMaterialId":0,"rawMaterialName":"string"}],"patternMakingNo":"string","patternNo":0,"productCategory1Id":0,"productCategory1Name":"string","productCategory2Id":0,"productCategory2Name":"string","remarks":"string","sampleId":0,"sampleNo":"string","sizeGroupId":0,"sizeGroupName":"string","sizeId":0,"sizeInformationList":[{"factoryId":0,"id":0,"patternMakingCardId":0,"quantity":0,"sizeInfomationId":0,"sizeInfomationName":"string"}],"sizeName":"string","updateBy":0,"washingProcessList":[{"factoryId":0,"id":0,"patternMakingCardId":0,"washingProcessId":0,"washingProcessName":"string"}]}
     * patternMakingFlow : {"afterfinishById":0,"afterfinishByName":"string","afterfinishTime":"2019-05-05T12:23:39.499Z","clientId":0,"clientName":"string","createById":0,"createByName":"string","createTime":"2019-05-05T12:23:39.499Z","designById":0,"designByName":"string","designTime":"2019-05-05T12:23:39.499Z","factoryId":0,"id":0,"images":"string","imagesUrl":"string","patternById":0,"patternByName":"string","patternCartId":0,"patternMakingNo":"string","patternTime":"2019-05-05T12:23:39.499Z","requirement":"string","sampleId":0,"sampleNo":"string","sewingById":0,"sewingByName":"string","sewingTime":"2019-05-05T12:23:39.499Z","status":0,"stopById":0,"stopByName":"string","stopTime":"2019-05-05T12:23:39.499Z","washingById":0,"washingByName":"string","washingTime":"2019-05-05T12:23:39.499Z"}
     * sampleVo : {"clientRecordId":0,"clientRecordName":"string","createBy":0,"designerBy":0,"designerName":"string","factoryId":0,"gmtCreate":"2019-05-05T12:23:39.499Z","gmtModified":"2019-05-05T12:23:39.499Z","id":0,"images":"string","imagesUrl":"string","isDeleted":0,"materialList":[{"factoryId":0,"id":0,"quantity":0,"rawMaterialCategory1Id":0,"rawMaterialCategory1Name":"string","rawMaterialCategory2Id":0,"rawMaterialCategory2Name":"string","rawMaterialId":0,"rawMaterialName":"string","sampleId":0}],"patternNo":0,"productCategory1Id":0,"productCategory1Name":"string","productCategory2Name":"string","productCategoryId":0,"productProcessList":[{"factoryId":0,"id":0,"isOutsourcing":0,"processId":0,"processName":"string","sampleId":0,"sequence":0,"stageId":0,"stageName":"string"}],"remarks":"string","sampleNo":"string","sizeList":[{"factoryId":0,"id":0,"sampleId":0,"sampleSizeInformationList":[{"factoryId":0,"id":0,"quantity":0,"sampleId":0,"sampleSizeId":0,"sizeId":0,"sizeInfomationId":0,"sizeInformationName":"string"}],"sizeGroupId":0,"sizeGroupName":"string","sizeId":0,"sizeName":"string"}],"updateBy":0,"washingProcessList":[{"factoryId":0,"id":0,"sampleId":0,"washingProcessId":0,"washingProcessName":"string"}]}
     */

    private PatternMakingCardBean patternMakingCard;
    private PatternMakingFlowBean patternMakingFlow;
    private SampleVoBean sampleVo;
    private List<OperatorRecordListBean> operatorRecordList;

    public PatternMakingCardBean getPatternMakingCard() {
        return patternMakingCard;
    }

    public void setPatternMakingCard(PatternMakingCardBean patternMakingCard) {
        this.patternMakingCard = patternMakingCard;
    }

    public PatternMakingFlowBean getPatternMakingFlow() {
        return patternMakingFlow;
    }

    public void setPatternMakingFlow(PatternMakingFlowBean patternMakingFlow) {
        this.patternMakingFlow = patternMakingFlow;
    }

    public SampleVoBean getSampleVo() {
        return sampleVo;
    }

    public void setSampleVo(SampleVoBean sampleVo) {
        this.sampleVo = sampleVo;
    }

    public List<OperatorRecordListBean> getOperatorRecordList() {
        return operatorRecordList;
    }

    public void setOperatorRecordList(List<OperatorRecordListBean> operatorRecordList) {
        this.operatorRecordList = operatorRecordList;
    }

    public static class PatternMakingCardBean {
        /**
         * clientId : 0
         * clientName : string
         * color : string
         * createBy : 0
         * factoryId : 0
         * gmtCreate : 2019-05-05T12:23:39.499Z
         * gmtModified : 2019-05-05T12:23:39.499Z
         * id : 0
         * images : string
         * imagesUrl : string
         * materialList : [{"factoryId":0,"id":0,"patternMakingCardId":0,"quantity":0,"rawMaterialCategory1Id":0,"rawMaterialCategory1Name":"string","rawMaterialCategory2Id":0,"rawMaterialCategory2Name":"string","rawMaterialId":0,"rawMaterialName":"string"}]
         * patternMakingNo : string
         * patternNo : 0
         * productCategory1Id : 0
         * productCategory1Name : string
         * productCategory2Id : 0
         * productCategory2Name : string
         * remarks : string
         * sampleId : 0
         * sampleNo : string
         * sizeGroupId : 0
         * sizeGroupName : string
         * sizeId : 0
         * sizeInformationList : [{"factoryId":0,"id":0,"patternMakingCardId":0,"quantity":0,"sizeInfomationId":0,"sizeInfomationName":"string"}]
         * sizeName : string
         * updateBy : 0
         * washingProcessList : [{"factoryId":0,"id":0,"patternMakingCardId":0,"washingProcessId":0,"washingProcessName":"string"}]
         */

        private int clientId;
        private String clientName;
        private String color;
        private int createBy;
        private int factoryId;
        private String gmtCreate;
        private String gmtModified;
        private int id;
        private String images;
        private String imagesUrl;
        private String patternMakingNo;
        private String patternNo;
        private int productCategory1Id;
        private String productCategory1Name;
        private int productCategory2Id;
        private String productCategory2Name;
        private String remarks;
        private int sampleId;
        private String sampleNo;
        private int sizeGroupId;
        private String sizeGroupName;
        private int sizeId;
        private String sizeName;
        private int updateBy;
        private List<MaterialListBean> materialList;
        private List<SizeInformationListBean> sizeInformationList;
        private List<WashingProcessListBean> washingProcessList;

        public int getClientId() {
            return clientId;
        }

        public void setClientId(int clientId) {
            this.clientId = clientId;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
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

        public String getPatternMakingNo() {
            return patternMakingNo;
        }

        public void setPatternMakingNo(String patternMakingNo) {
            this.patternMakingNo = patternMakingNo;
        }

        public String getPatternNo() {
            return patternNo;
        }

        public void setPatternNo(String patternNo) {
            this.patternNo = patternNo;
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

        public int getProductCategory2Id() {
            return productCategory2Id;
        }

        public void setProductCategory2Id(int productCategory2Id) {
            this.productCategory2Id = productCategory2Id;
        }

        public String getProductCategory2Name() {
            return productCategory2Name;
        }

        public void setProductCategory2Name(String productCategory2Name) {
            this.productCategory2Name = productCategory2Name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getSampleId() {
            return sampleId;
        }

        public void setSampleId(int sampleId) {
            this.sampleId = sampleId;
        }

        public String getSampleNo() {
            return sampleNo;
        }

        public void setSampleNo(String sampleNo) {
            this.sampleNo = sampleNo;
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

        public int getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(int updateBy) {
            this.updateBy = updateBy;
        }

        public List<MaterialListBean> getMaterialList() {
            return materialList;
        }

        public void setMaterialList(List<MaterialListBean> materialList) {
            this.materialList = materialList;
        }

        public List<SizeInformationListBean> getSizeInformationList() {
            return sizeInformationList;
        }

        public void setSizeInformationList(List<SizeInformationListBean> sizeInformationList) {
            this.sizeInformationList = sizeInformationList;
        }

        public List<WashingProcessListBean> getWashingProcessList() {
            return washingProcessList;
        }

        public void setWashingProcessList(List<WashingProcessListBean> washingProcessList) {
            this.washingProcessList = washingProcessList;
        }

        public static class MaterialListBean {
            /**
             * factoryId : 0
             * id : 0
             * patternMakingCardId : 0
             * quantity : 0
             * rawMaterialCategory1Id : 0
             * rawMaterialCategory1Name : string
             * rawMaterialCategory2Id : 0
             * rawMaterialCategory2Name : string
             * rawMaterialId : 0
             * rawMaterialName : string
             */

            private int factoryId;
            private int id;
            private int patternMakingCardId;
            private double quantity;
            private int rawMaterialCategory1Id;
            private String rawMaterialCategory1Name;
            private int rawMaterialCategory2Id;
            private String rawMaterialCategory2Name;
            private int rawMaterialId;
            private String rawMaterialName;

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

            public int getPatternMakingCardId() {
                return patternMakingCardId;
            }

            public void setPatternMakingCardId(int patternMakingCardId) {
                this.patternMakingCardId = patternMakingCardId;
            }

            public double getQuantity() {
                return quantity;
            }

            public void setQuantity(double quantity) {
                this.quantity = quantity;
            }

            public int getRawMaterialCategory1Id() {
                return rawMaterialCategory1Id;
            }

            public void setRawMaterialCategory1Id(int rawMaterialCategory1Id) {
                this.rawMaterialCategory1Id = rawMaterialCategory1Id;
            }

            public String getRawMaterialCategory1Name() {
                return rawMaterialCategory1Name;
            }

            public void setRawMaterialCategory1Name(String rawMaterialCategory1Name) {
                this.rawMaterialCategory1Name = rawMaterialCategory1Name;
            }

            public int getRawMaterialCategory2Id() {
                return rawMaterialCategory2Id;
            }

            public void setRawMaterialCategory2Id(int rawMaterialCategory2Id) {
                this.rawMaterialCategory2Id = rawMaterialCategory2Id;
            }

            public String getRawMaterialCategory2Name() {
                return rawMaterialCategory2Name;
            }

            public void setRawMaterialCategory2Name(String rawMaterialCategory2Name) {
                this.rawMaterialCategory2Name = rawMaterialCategory2Name;
            }

            public int getRawMaterialId() {
                return rawMaterialId;
            }

            public void setRawMaterialId(int rawMaterialId) {
                this.rawMaterialId = rawMaterialId;
            }

            public String getRawMaterialName() {
                return rawMaterialName;
            }

            public void setRawMaterialName(String rawMaterialName) {
                this.rawMaterialName = rawMaterialName;
            }
        }

        public static class SizeInformationListBean {
            /**
             * factoryId : 0
             * id : 0
             * patternMakingCardId : 0
             * quantity : 0
             * sizeInfomationId : 0
             * sizeInfomationName : string
             */

            private int factoryId;
            private int id;
            private int patternMakingCardId;
            private double quantity;
            private int sizeInfomationId;
            private String sizeInfomationName;

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

            public int getPatternMakingCardId() {
                return patternMakingCardId;
            }

            public void setPatternMakingCardId(int patternMakingCardId) {
                this.patternMakingCardId = patternMakingCardId;
            }

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

            public String getSizeInfomationName() {
                return sizeInfomationName;
            }

            public void setSizeInfomationName(String sizeInfomationName) {
                this.sizeInfomationName = sizeInfomationName;
            }
        }

        public static class WashingProcessListBean {
            /**
             * factoryId : 0
             * id : 0
             * patternMakingCardId : 0
             * washingProcessId : 0
             * washingProcessName : string
             */

            private int factoryId;
            private int id;
            private int patternMakingCardId;
            private int washingProcessId;
            private String washingProcessName;

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

            public int getPatternMakingCardId() {
                return patternMakingCardId;
            }

            public void setPatternMakingCardId(int patternMakingCardId) {
                this.patternMakingCardId = patternMakingCardId;
            }

            public int getWashingProcessId() {
                return washingProcessId;
            }

            public void setWashingProcessId(int washingProcessId) {
                this.washingProcessId = washingProcessId;
            }

            public String getWashingProcessName() {
                return washingProcessName;
            }

            public void setWashingProcessName(String washingProcessName) {
                this.washingProcessName = washingProcessName;
            }
        }
    }

    public static class PatternMakingFlowBean {
        /**
         * afterfinishById : 0
         * afterfinishByName : string
         * afterfinishTime : 2019-05-05T12:23:39.499Z
         * clientId : 0
         * clientName : string
         * createById : 0
         * createByName : string
         * createTime : 2019-05-05T12:23:39.499Z
         * designById : 0
         * designByName : string
         * designTime : 2019-05-05T12:23:39.499Z
         * factoryId : 0
         * id : 0
         * images : string
         * imagesUrl : string
         * patternById : 0
         * patternByName : string
         * patternCartId : 0
         * patternMakingNo : string
         * patternTime : 2019-05-05T12:23:39.499Z
         * requirement : string
         * sampleId : 0
         * sampleNo : string
         * sewingById : 0
         * sewingByName : string
         * sewingTime : 2019-05-05T12:23:39.499Z
         * status : 0
         * stopById : 0
         * stopByName : string
         * stopTime : 2019-05-05T12:23:39.499Z
         * washingById : 0
         * washingByName : string
         * washingTime : 2019-05-05T12:23:39.499Z
         */

        private int afterfinishById;
        private String afterfinishByName;
        private String afterfinishTime;
        private int clientId;
        private String clientName;
        private int createById;
        private String createByName;
        private String createTime;
        private int designById;
        private String designByName;
        private String designTime;
        private int factoryId;
        private int id;
        private String images;
        private String imagesUrl;
        private int patternById;
        private String patternByName;
        private int patternCartId;
        private String patternMakingNo;
        private String patternTime;
        private String requirement;
        private int sampleId;
        private String sampleNo;
        private int sewingById;
        private String sewingByName;
        private String sewingTime;
        private int status;
        private int stopById;
        private String stopByName;
        private String stopTime;
        private int washingById;
        private String washingByName;
        private String washingTime;

        public int getAfterfinishById() {
            return afterfinishById;
        }

        public void setAfterfinishById(int afterfinishById) {
            this.afterfinishById = afterfinishById;
        }

        public String getAfterfinishByName() {
            return afterfinishByName;
        }

        public void setAfterfinishByName(String afterfinishByName) {
            this.afterfinishByName = afterfinishByName;
        }

        public String getAfterfinishTime() {
            return afterfinishTime;
        }

        public void setAfterfinishTime(String afterfinishTime) {
            this.afterfinishTime = afterfinishTime;
        }

        public int getClientId() {
            return clientId;
        }

        public void setClientId(int clientId) {
            this.clientId = clientId;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public int getCreateById() {
            return createById;
        }

        public void setCreateById(int createById) {
            this.createById = createById;
        }

        public String getCreateByName() {
            return createByName;
        }

        public void setCreateByName(String createByName) {
            this.createByName = createByName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDesignById() {
            return designById;
        }

        public void setDesignById(int designById) {
            this.designById = designById;
        }

        public String getDesignByName() {
            return designByName;
        }

        public void setDesignByName(String designByName) {
            this.designByName = designByName;
        }

        public String getDesignTime() {
            return designTime;
        }

        public void setDesignTime(String designTime) {
            this.designTime = designTime;
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

        public int getPatternById() {
            return patternById;
        }

        public void setPatternById(int patternById) {
            this.patternById = patternById;
        }

        public String getPatternByName() {
            return patternByName;
        }

        public void setPatternByName(String patternByName) {
            this.patternByName = patternByName;
        }

        public int getPatternCartId() {
            return patternCartId;
        }

        public void setPatternCartId(int patternCartId) {
            this.patternCartId = patternCartId;
        }

        public String getPatternMakingNo() {
            return patternMakingNo;
        }

        public void setPatternMakingNo(String patternMakingNo) {
            this.patternMakingNo = patternMakingNo;
        }

        public String getPatternTime() {
            return patternTime;
        }

        public void setPatternTime(String patternTime) {
            this.patternTime = patternTime;
        }

        public String getRequirement() {
            return requirement;
        }

        public void setRequirement(String requirement) {
            this.requirement = requirement;
        }

        public int getSampleId() {
            return sampleId;
        }

        public void setSampleId(int sampleId) {
            this.sampleId = sampleId;
        }

        public String getSampleNo() {
            return sampleNo;
        }

        public void setSampleNo(String sampleNo) {
            this.sampleNo = sampleNo;
        }

        public int getSewingById() {
            return sewingById;
        }

        public void setSewingById(int sewingById) {
            this.sewingById = sewingById;
        }

        public String getSewingByName() {
            return sewingByName;
        }

        public void setSewingByName(String sewingByName) {
            this.sewingByName = sewingByName;
        }

        public String getSewingTime() {
            return sewingTime;
        }

        public void setSewingTime(String sewingTime) {
            this.sewingTime = sewingTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStopById() {
            return stopById;
        }

        public void setStopById(int stopById) {
            this.stopById = stopById;
        }

        public String getStopByName() {
            return stopByName;
        }

        public void setStopByName(String stopByName) {
            this.stopByName = stopByName;
        }

        public String getStopTime() {
            return stopTime;
        }

        public void setStopTime(String stopTime) {
            this.stopTime = stopTime;
        }

        public int getWashingById() {
            return washingById;
        }

        public void setWashingById(int washingById) {
            this.washingById = washingById;
        }

        public String getWashingByName() {
            return washingByName;
        }

        public void setWashingByName(String washingByName) {
            this.washingByName = washingByName;
        }

        public String getWashingTime() {
            return washingTime;
        }

        public void setWashingTime(String washingTime) {
            this.washingTime = washingTime;
        }
    }

    public static class SampleVoBean {
        /**
         * clientRecordId : 0
         * clientRecordName : string
         * createBy : 0
         * designerBy : 0
         * designerName : string
         * factoryId : 0
         * gmtCreate : 2019-05-05T12:23:39.499Z
         * gmtModified : 2019-05-05T12:23:39.499Z
         * id : 0
         * images : string
         * imagesUrl : string
         * isDeleted : 0
         * materialList : [{"factoryId":0,"id":0,"quantity":0,"rawMaterialCategory1Id":0,"rawMaterialCategory1Name":"string","rawMaterialCategory2Id":0,"rawMaterialCategory2Name":"string","rawMaterialId":0,"rawMaterialName":"string","sampleId":0}]
         * patternNo : 0
         * productCategory1Id : 0
         * productCategory1Name : string
         * productCategory2Name : string
         * productCategoryId : 0
         * productProcessList : [{"factoryId":0,"id":0,"isOutsourcing":0,"processId":0,"processName":"string","sampleId":0,"sequence":0,"stageId":0,"stageName":"string"}]
         * remarks : string
         * sampleNo : string
         * sizeList : [{"factoryId":0,"id":0,"sampleId":0,"sampleSizeInformationList":[{"factoryId":0,"id":0,"quantity":0,"sampleId":0,"sampleSizeId":0,"sizeId":0,"sizeInfomationId":0,"sizeInformationName":"string"}],"sizeGroupId":0,"sizeGroupName":"string","sizeId":0,"sizeName":"string"}]
         * updateBy : 0
         * washingProcessList : [{"factoryId":0,"id":0,"sampleId":0,"washingProcessId":0,"washingProcessName":"string"}]
         */

        private int clientRecordId;
        private String clientRecordName;
        private int createBy;
        private int designerBy;
        private String designerName;
        private int factoryId;
        private String gmtCreate;
        private String gmtModified;
        private int id;
        private String images;
        private String imagesUrl;
        private int isDeleted;
        private String patternNo;
        private int productCategory1Id;
        private String productCategory1Name;
        private String productCategory2Name;
        private int productCategoryId;
        private String remarks;
        private String sampleNo;
        private int updateBy;
        private List<MaterialListBeanX> materialList;
        private List<ProductProcessListBean> productProcessList;
        private List<SizeListBean> sizeList;
        private List<WashingProcessListBeanX> washingProcessList;

        public int getClientRecordId() {
            return clientRecordId;
        }

        public void setClientRecordId(int clientRecordId) {
            this.clientRecordId = clientRecordId;
        }

        public String getClientRecordName() {
            return clientRecordName;
        }

        public void setClientRecordName(String clientRecordName) {
            this.clientRecordName = clientRecordName;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public int getDesignerBy() {
            return designerBy;
        }

        public void setDesignerBy(int designerBy) {
            this.designerBy = designerBy;
        }

        public String getDesignerName() {
            return designerName;
        }

        public void setDesignerName(String designerName) {
            this.designerName = designerName;
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

        public String getPatternNo() {
            return patternNo;
        }

        public void setPatternNo(String patternNo) {
            this.patternNo = patternNo;
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

        public int getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(int updateBy) {
            this.updateBy = updateBy;
        }

        public List<MaterialListBeanX> getMaterialList() {
            return materialList;
        }

        public void setMaterialList(List<MaterialListBeanX> materialList) {
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

        public List<WashingProcessListBeanX> getWashingProcessList() {
            return washingProcessList;
        }

        public void setWashingProcessList(List<WashingProcessListBeanX> washingProcessList) {
            this.washingProcessList = washingProcessList;
        }

        public static class MaterialListBeanX {
            /**
             * factoryId : 0
             * id : 0
             * quantity : 0
             * rawMaterialCategory1Id : 0
             * rawMaterialCategory1Name : string
             * rawMaterialCategory2Id : 0
             * rawMaterialCategory2Name : string
             * rawMaterialId : 0
             * rawMaterialName : string
             * sampleId : 0
             */

            private int factoryId;
            private int id;
            private double quantity;
            private int rawMaterialCategory1Id;
            private String rawMaterialCategory1Name;
            private int rawMaterialCategory2Id;
            private String rawMaterialCategory2Name;
            private int rawMaterialId;
            private String rawMaterialName;
            private int sampleId;

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

            public int getRawMaterialCategory1Id() {
                return rawMaterialCategory1Id;
            }

            public void setRawMaterialCategory1Id(int rawMaterialCategory1Id) {
                this.rawMaterialCategory1Id = rawMaterialCategory1Id;
            }

            public String getRawMaterialCategory1Name() {
                return rawMaterialCategory1Name;
            }

            public void setRawMaterialCategory1Name(String rawMaterialCategory1Name) {
                this.rawMaterialCategory1Name = rawMaterialCategory1Name;
            }

            public int getRawMaterialCategory2Id() {
                return rawMaterialCategory2Id;
            }

            public void setRawMaterialCategory2Id(int rawMaterialCategory2Id) {
                this.rawMaterialCategory2Id = rawMaterialCategory2Id;
            }

            public String getRawMaterialCategory2Name() {
                return rawMaterialCategory2Name;
            }

            public void setRawMaterialCategory2Name(String rawMaterialCategory2Name) {
                this.rawMaterialCategory2Name = rawMaterialCategory2Name;
            }

            public int getRawMaterialId() {
                return rawMaterialId;
            }

            public void setRawMaterialId(int rawMaterialId) {
                this.rawMaterialId = rawMaterialId;
            }

            public String getRawMaterialName() {
                return rawMaterialName;
            }

            public void setRawMaterialName(String rawMaterialName) {
                this.rawMaterialName = rawMaterialName;
            }

            public int getSampleId() {
                return sampleId;
            }

            public void setSampleId(int sampleId) {
                this.sampleId = sampleId;
            }
        }

        public static class ProductProcessListBean {
            /**
             * factoryId : 0
             * id : 0
             * isOutsourcing : 0
             * processId : 0
             * processName : string
             * sampleId : 0
             * sequence : 0
             * stageId : 0
             * stageName : string
             */

            private int factoryId;
            private int id;
            private int isOutsourcing;
            private int processId;
            private String processName;
            private int sampleId;
            private int sequence;
            private int stageId;
            private String stageName;

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

            public int getIsOutsourcing() {
                return isOutsourcing;
            }

            public void setIsOutsourcing(int isOutsourcing) {
                this.isOutsourcing = isOutsourcing;
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

            public int getSampleId() {
                return sampleId;
            }

            public void setSampleId(int sampleId) {
                this.sampleId = sampleId;
            }

            public int getSequence() {
                return sequence;
            }

            public void setSequence(int sequence) {
                this.sequence = sequence;
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

        public static class WashingProcessListBeanX {
            /**
             * factoryId : 0
             * id : 0
             * sampleId : 0
             * washingProcessId : 0
             * washingProcessName : string
             */

            private int factoryId;
            private int id;
            private int sampleId;
            private int washingProcessId;
            private String washingProcessName;

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

            public int getWashingProcessId() {
                return washingProcessId;
            }

            public void setWashingProcessId(int washingProcessId) {
                this.washingProcessId = washingProcessId;
            }

            public String getWashingProcessName() {
                return washingProcessName;
            }

            public void setWashingProcessName(String washingProcessName) {
                this.washingProcessName = washingProcessName;
            }
        }
    }

    public static class OperatorRecordListBean {
        /**
         * content : string
         * person : string
         * time : 2019-05-05T12:23:39.499Z
         * title : string
         */

        private String content;
        private String person;
        private String time;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPerson() {
            return person;
        }

        public void setPerson(String person) {
            this.person = person;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
