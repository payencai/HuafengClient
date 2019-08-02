package com.huafeng.client.ui.home.activity.produce;

import java.io.Serializable;
import java.util.List;

public class ProduceDetail implements Serializable {

    /**
     * createByName : string
     * flowList : [{"factoryId":0,"gmtCreate":"2019-06-06T08:40:25.603Z","gmtFinish":"2019-06-06T08:40:25.603Z","id":0,"lossQuantity":0,"materialCountClerkId":0,"materialCountConfirmTime":"2019-06-06T08:40:25.603Z","materialCountPreFillEmployeeId":0,"materialCountPreFillTime":"2019-06-06T08:40:25.603Z","materialCountStatus":0,"outsourcingDeliveryNoteId":0,"outsourcingDeliveryTime":"2019-06-06T08:40:25.603Z","outsourcingEmployeeId":0,"outsourcingPickupNoteId":0,"outsourcingPickupTime":"2019-06-06T08:40:25.603Z","outsourcingStatus":0,"principalName":"string","principalRecordId":0,"processNames":"string","productionOrderId":0,"productionOrderStageId":0,"quantity":0,"sequence":0,"stageId":0,"status":0,"title":"string","workAllcationClerkId":0,"workAllcationConfirmTime":"2019-06-06T08:40:25.603Z","workAllcationPreFillEmployeeId":0,"workAllcationPreFillTime":"2019-06-06T08:40:25.603Z","workAllcationStatus":0}]
     * materialTakeList : [{"factoryId":0,"gmtCreate":"2019-06-06T08:40:25.603Z","id":0,"isTake":0,"materialCategory1Id":0,"materialCategory1Name":"string","materialCategory2Id":0,"materialCategory2Name":"string","materialId":0,"materialName":"string","orderId":0,"productionOrderId":0,"purchaseRequirementId":0,"quantityCanTake":0,"quantityNeed":0,"quantityTake":0,"takeBy":0,"takeTime":"2019-06-06T08:40:25.603Z"}]
     * orderSampleVo : {"clientRecordId":0,"clientRecordName":"string","color":"string","createBy":0,"designerBy":0,"designerName":"string","factoryId":0,"gmtCreate":"2019-06-06T08:40:25.603Z","gmtModified":"2019-06-06T08:40:25.603Z","id":0,"images":"string","imagesUrl":"string","isDeleted":0,"materialList":[{"factoryId":0,"id":0,"orderSampleId":0,"quantity":0,"rawMaterialCategory1Id":0,"rawMaterialCategory1Name":"string","rawMaterialCategory2Id":0,"rawMaterialCategory2Name":"string","rawMaterialId":0,"rawMaterialName":"string"}],"patternNo":"string","productCategory1Id":0,"productCategory1Name":"string","productCategory2Name":"string","productCategoryId":0,"productProcessList":[{"factoryId":0,"id":0,"isOutsourcing":0,"orderSampleId":0,"price":0,"processId":0,"processName":"string","sequence":0,"stageId":0,"stageName":"string"}],"remarks":"string","sampleId":0,"sampleNo":"string","sizeList":[{"factoryId":0,"id":0,"orderSampleId":0,"sampleSizeInformationList":[{"factoryId":0,"id":0,"orderSampleId":0,"orderSampleSizeId":0,"quantity":0,"sizeId":0,"sizeInfomationId":0,"sizeInformationName":"string"}],"sizeGroupId":0,"sizeGroupName":"string","sizeId":0,"sizeName":"string"}],"updateBy":0,"washingProcessList":[{"factoryId":0,"id":0,"orderSampleId":0,"washingProcessId":0,"washingProcessName":"string"}]}
     * productProcessList : [{"factoryId":0,"id":0,"isOutsourcing":0,"processId":0,"processName":"string","productionOrderId":0,"productionOrderStageId":0,"sequence":0,"stageId":0,"stageName":"string"}]
     * productionOrder : {"createBy":0,"currentFlowId":0,"estimatedTimeOfFinishment":"2019-06-06T08:40:25.603Z","factoryId":0,"finishTime":"2019-06-06T08:40:25.603Z","gmtCreate":"2019-06-06T08:40:25.603Z","goStatus":0,"id":0,"lossQuantity":0,"month":"string","orderId":0,"orderSampleId":0,"productOrderNumber":"string","quantity":0,"status":0,"stopBy":0,"stopTime":"2019-06-06T08:40:25.603Z"}
     * sizeQuantityList : [{"factoryId":0,"id":0,"productionOrderId":0,"quantity":0,"sizeId":0,"sizeName":"string"}]
     */
    private int nextFlowStageId;

    public int getNextFlowStageId() {
        return nextFlowStageId;
    }

    public void setNextFlowStageId(int nextFlowStageId) {
        this.nextFlowStageId = nextFlowStageId;
    }

    private String createByName;
    private OrderSampleVoBean orderSampleVo;
    private ProductionOrderBean productionOrder;
    private List<FlowListBean> flowList;
    private List<MaterialTakeListBean> materialTakeList;
    private List<ProductProcessListBeanX> productProcessList;
    private List<SizeQuantityListBean> sizeQuantityList;
    private List<InventorySizeQuantityListBean> inventorySizeQuantityList;

    public List<InventorySizeQuantityListBean> getInventorySizeQuantityList() {
        return inventorySizeQuantityList;
    }

    public void setInventorySizeQuantityList(List<InventorySizeQuantityListBean> inventorySizeQuantityList) {
        this.inventorySizeQuantityList = inventorySizeQuantityList;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public OrderSampleVoBean getOrderSampleVo() {
        return orderSampleVo;
    }

    public void setOrderSampleVo(OrderSampleVoBean orderSampleVo) {
        this.orderSampleVo = orderSampleVo;
    }

    public ProductionOrderBean getProductionOrder() {
        return productionOrder;
    }

    public void setProductionOrder(ProductionOrderBean productionOrder) {
        this.productionOrder = productionOrder;
    }

    public List<FlowListBean> getFlowList() {
        return flowList;
    }

    public void setFlowList(List<FlowListBean> flowList) {
        this.flowList = flowList;
    }

    public List<MaterialTakeListBean> getMaterialTakeList() {
        return materialTakeList;
    }

    public void setMaterialTakeList(List<MaterialTakeListBean> materialTakeList) {
        this.materialTakeList = materialTakeList;
    }

    public List<ProductProcessListBeanX> getProductProcessList() {
        return productProcessList;
    }

    public void setProductProcessList(List<ProductProcessListBeanX> productProcessList) {
        this.productProcessList = productProcessList;
    }

    public List<SizeQuantityListBean> getSizeQuantityList() {
        return sizeQuantityList;
    }

    public void setSizeQuantityList(List<SizeQuantityListBean> sizeQuantityList) {
        this.sizeQuantityList = sizeQuantityList;
    }
    public static class InventorySizeQuantityListBean{

        /**
         * factoryId : 0
         * inventorySampleLogId : 0
         * quantity : 0
         * sampleId : 0
         * sizeId : 0
         * sizeName : string
         */

        private int factoryId;
        private int inventorySampleLogId;
        private int quantity;
        private int sampleId;
        private int sizeId;
        private String sizeName;

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
        }

        public int getInventorySampleLogId() {
            return inventorySampleLogId;
        }

        public void setInventorySampleLogId(int inventorySampleLogId) {
            this.inventorySampleLogId = inventorySampleLogId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getSampleId() {
            return sampleId;
        }

        public void setSampleId(int sampleId) {
            this.sampleId = sampleId;
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
    }
    public static class OrderSampleVoBean {
        /**
         * clientRecordId : 0
         * clientRecordName : string
         * color : string
         * createBy : 0
         * designerBy : 0
         * designerName : string
         * factoryId : 0
         * gmtCreate : 2019-06-06T08:40:25.603Z
         * gmtModified : 2019-06-06T08:40:25.603Z
         * id : 0
         * images : string
         * imagesUrl : string
         * isDeleted : 0
         * materialList : [{"factoryId":0,"id":0,"orderSampleId":0,"quantity":0,"rawMaterialCategory1Id":0,"rawMaterialCategory1Name":"string","rawMaterialCategory2Id":0,"rawMaterialCategory2Name":"string","rawMaterialId":0,"rawMaterialName":"string"}]
         * patternNo : string
         * productCategory1Id : 0
         * productCategory1Name : string
         * productCategory2Name : string
         * productCategoryId : 0
         * productProcessList : [{"factoryId":0,"id":0,"isOutsourcing":0,"orderSampleId":0,"price":0,"processId":0,"processName":"string","sequence":0,"stageId":0,"stageName":"string"}]
         * remarks : string
         * sampleId : 0
         * sampleNo : string
         * sizeList : [{"factoryId":0,"id":0,"orderSampleId":0,"sampleSizeInformationList":[{"factoryId":0,"id":0,"orderSampleId":0,"orderSampleSizeId":0,"quantity":0,"sizeId":0,"sizeInfomationId":0,"sizeInformationName":"string"}],"sizeGroupId":0,"sizeGroupName":"string","sizeId":0,"sizeName":"string"}]
         * updateBy : 0
         * washingProcessList : [{"factoryId":0,"id":0,"orderSampleId":0,"washingProcessId":0,"washingProcessName":"string"}]
         */

        private int clientRecordId;
        private String clientRecordName;
        private String color;
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
        private int sampleId;
        private String sampleNo;
        private int updateBy;
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

        public String getClientRecordName() {
            return clientRecordName;
        }

        public void setClientRecordName(String clientRecordName) {
            this.clientRecordName = clientRecordName;
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
            /**
             * factoryId : 0
             * id : 0
             * orderSampleId : 0
             * quantity : 0.0
             * rawMaterialCategory1Id : 0
             * rawMaterialCategory1Name : string
             * rawMaterialCategory2Id : 0
             * rawMaterialCategory2Name : string
             * rawMaterialId : 0
             * rawMaterialName : string
             */

            private int factoryId;
            private int id;
            private int orderSampleId;
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

            public int getOrderSampleId() {
                return orderSampleId;
            }

            public void setOrderSampleId(int orderSampleId) {
                this.orderSampleId = orderSampleId;
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

        public static class ProductProcessListBean {
            /**
             * factoryId : 0
             * id : 0
             * isOutsourcing : 0
             * orderSampleId : 0
             * price : 0.0
             * processId : 0
             * processName : string
             * sequence : 0
             * stageId : 0
             * stageName : string
             */

            private int factoryId;
            private int id;
            private int isOutsourcing;
            private int orderSampleId;
            private double price;
            private int processId;
            private String processName;
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

            public int getOrderSampleId() {
                return orderSampleId;
            }

            public void setOrderSampleId(int orderSampleId) {
                this.orderSampleId = orderSampleId;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
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
             * orderSampleId : 0
             * sampleSizeInformationList : [{"factoryId":0,"id":0,"orderSampleId":0,"orderSampleSizeId":0,"quantity":0,"sizeId":0,"sizeInfomationId":0,"sizeInformationName":"string"}]
             * sizeGroupId : 0
             * sizeGroupName : string
             * sizeId : 0
             * sizeName : string
             */

            private int factoryId;
            private int id;
            private int orderSampleId;
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

            public int getOrderSampleId() {
                return orderSampleId;
            }

            public void setOrderSampleId(int orderSampleId) {
                this.orderSampleId = orderSampleId;
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
                 * orderSampleId : 0
                 * orderSampleSizeId : 0
                 * quantity : 0
                 * sizeId : 0
                 * sizeInfomationId : 0
                 * sizeInformationName : string
                 */

                private int factoryId;
                private int id;
                private int orderSampleId;
                private int orderSampleSizeId;
                private double quantity;
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

                public int getOrderSampleId() {
                    return orderSampleId;
                }

                public void setOrderSampleId(int orderSampleId) {
                    this.orderSampleId = orderSampleId;
                }

                public int getOrderSampleSizeId() {
                    return orderSampleSizeId;
                }

                public void setOrderSampleSizeId(int orderSampleSizeId) {
                    this.orderSampleSizeId = orderSampleSizeId;
                }

                public double getQuantity() {
                    return quantity;
                }

                public void setQuantity(double quantity) {
                    this.quantity = quantity;
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

        public static class WashingProcessListBean {
            /**
             * factoryId : 0
             * id : 0
             * orderSampleId : 0
             * washingProcessId : 0
             * washingProcessName : string
             */

            private int factoryId;
            private int id;
            private int orderSampleId;
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

            public int getOrderSampleId() {
                return orderSampleId;
            }

            public void setOrderSampleId(int orderSampleId) {
                this.orderSampleId = orderSampleId;
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

    public static class ProductionOrderBean implements Serializable{
        /**
         * createBy : 0
         * currentFlowId : 0
         * estimatedTimeOfFinishment : 2019-06-06T08:40:25.603Z
         * factoryId : 0
         * finishTime : 2019-06-06T08:40:25.603Z
         * gmtCreate : 2019-06-06T08:40:25.603Z
         * goStatus : 0
         * id : 0
         * lossQuantity : 0
         * month : string
         * orderId : 0
         * orderSampleId : 0
         * productOrderNumber : string
         * quantity : 0
         * status : 0
         * stopBy : 0
         * stopTime : 2019-06-06T08:40:25.603Z
         */

        private int createBy;
        private int currentFlowId;
        private String estimatedTimeOfFinishment;
        private int factoryId;
        private String finishTime;
        private String gmtCreate;
        private int goStatus;
        private int id;
        private int lossQuantity;
        private String month;
        private int orderId;
        private int orderSampleId;
        private String productOrderNumber;
        private int quantity;
        private int status;
        private int stopBy;
        private String stopTime;

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public int getCurrentFlowId() {
            return currentFlowId;
        }

        public void setCurrentFlowId(int currentFlowId) {
            this.currentFlowId = currentFlowId;
        }

        public String getEstimatedTimeOfFinishment() {
            return estimatedTimeOfFinishment;
        }

        public void setEstimatedTimeOfFinishment(String estimatedTimeOfFinishment) {
            this.estimatedTimeOfFinishment = estimatedTimeOfFinishment;
        }

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public int getGoStatus() {
            return goStatus;
        }

        public void setGoStatus(int goStatus) {
            this.goStatus = goStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLossQuantity() {
            return lossQuantity;
        }

        public void setLossQuantity(int lossQuantity) {
            this.lossQuantity = lossQuantity;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getOrderSampleId() {
            return orderSampleId;
        }

        public void setOrderSampleId(int orderSampleId) {
            this.orderSampleId = orderSampleId;
        }

        public String getProductOrderNumber() {
            return productOrderNumber;
        }

        public void setProductOrderNumber(String productOrderNumber) {
            this.productOrderNumber = productOrderNumber;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStopBy() {
            return stopBy;
        }

        public void setStopBy(int stopBy) {
            this.stopBy = stopBy;
        }

        public String getStopTime() {
            return stopTime;
        }

        public void setStopTime(String stopTime) {
            this.stopTime = stopTime;
        }
    }

    public static class FlowListBean implements Serializable{
        /**
         * factoryId : 0
         * gmtCreate : 2019-06-06T08:40:25.603Z
         * gmtFinish : 2019-06-06T08:40:25.603Z
         * id : 0
         * lossQuantity : 0
         * materialCountClerkId : 0
         * materialCountConfirmTime : 2019-06-06T08:40:25.603Z
         * materialCountPreFillEmployeeId : 0
         * materialCountPreFillTime : 2019-06-06T08:40:25.603Z
         * materialCountStatus : 0
         * outsourcingDeliveryNoteId : 0
         * outsourcingDeliveryTime : 2019-06-06T08:40:25.603Z
         * outsourcingEmployeeId : 0
         * outsourcingPickupNoteId : 0
         * outsourcingPickupTime : 2019-06-06T08:40:25.603Z
         * outsourcingStatus : 0
         * principalName : string
         * principalRecordId : 0
         * processNames : string
         * productionOrderId : 0
         * productionOrderStageId : 0
         * quantity : 0
         * sequence : 0
         * stageId : 0
         * status : 0
         * title : string
         * workAllcationClerkId : 0
         * workAllcationConfirmTime : 2019-06-06T08:40:25.603Z
         * workAllcationPreFillEmployeeId : 0
         * workAllcationPreFillTime : 2019-06-06T08:40:25.603Z
         * workAllcationStatus : 0
         */
        private boolean isShow;

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        private int factoryId;
        private String gmtCreate;
        private String gmtFinish;
        private int id;
        private Integer lossQuantity;
        private int materialCountClerkId;
        private String materialCountConfirmTime;
        private int materialCountPreFillEmployeeId;
        private String materialCountPreFillTime;
        private int materialCountStatus;
        private int outsourcingDeliveryNoteId;
        private String outsourcingDeliveryTime;
        private int outsourcingEmployeeId;
        private int outsourcingPickupNoteId;
        private String outsourcingPickupTime;
        private int outsourcingStatus;
        private String principalName;
        private int principalRecordId;
        private String processNames;
        private int productionOrderId;
        private int productionOrderStageId;
        private int quantity;
        private int sequence;
        private int stageId;
        private int status;
        private String title;
        private int workAllcationClerkId;
        private String workAllcationConfirmTime;
        private int workAllcationPreFillEmployeeId;
        private String workAllcationPreFillTime;
        private int workAllcationStatus;

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

        public String getGmtFinish() {
            return gmtFinish;
        }

        public void setGmtFinish(String gmtFinish) {
            this.gmtFinish = gmtFinish;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Integer getLossQuantity() {
            return lossQuantity;
        }

        public void setLossQuantity(Integer lossQuantity) {
            this.lossQuantity = lossQuantity;
        }

        public int getMaterialCountClerkId() {
            return materialCountClerkId;
        }

        public void setMaterialCountClerkId(int materialCountClerkId) {
            this.materialCountClerkId = materialCountClerkId;
        }

        public String getMaterialCountConfirmTime() {
            return materialCountConfirmTime;
        }

        public void setMaterialCountConfirmTime(String materialCountConfirmTime) {
            this.materialCountConfirmTime = materialCountConfirmTime;
        }

        public int getMaterialCountPreFillEmployeeId() {
            return materialCountPreFillEmployeeId;
        }

        public void setMaterialCountPreFillEmployeeId(int materialCountPreFillEmployeeId) {
            this.materialCountPreFillEmployeeId = materialCountPreFillEmployeeId;
        }

        public String getMaterialCountPreFillTime() {
            return materialCountPreFillTime;
        }

        public void setMaterialCountPreFillTime(String materialCountPreFillTime) {
            this.materialCountPreFillTime = materialCountPreFillTime;
        }

        public int getMaterialCountStatus() {
            return materialCountStatus;
        }

        public void setMaterialCountStatus(int materialCountStatus) {
            this.materialCountStatus = materialCountStatus;
        }

        public int getOutsourcingDeliveryNoteId() {
            return outsourcingDeliveryNoteId;
        }

        public void setOutsourcingDeliveryNoteId(int outsourcingDeliveryNoteId) {
            this.outsourcingDeliveryNoteId = outsourcingDeliveryNoteId;
        }

        public String getOutsourcingDeliveryTime() {
            return outsourcingDeliveryTime;
        }

        public void setOutsourcingDeliveryTime(String outsourcingDeliveryTime) {
            this.outsourcingDeliveryTime = outsourcingDeliveryTime;
        }

        public int getOutsourcingEmployeeId() {
            return outsourcingEmployeeId;
        }

        public void setOutsourcingEmployeeId(int outsourcingEmployeeId) {
            this.outsourcingEmployeeId = outsourcingEmployeeId;
        }

        public int getOutsourcingPickupNoteId() {
            return outsourcingPickupNoteId;
        }

        public void setOutsourcingPickupNoteId(int outsourcingPickupNoteId) {
            this.outsourcingPickupNoteId = outsourcingPickupNoteId;
        }

        public String getOutsourcingPickupTime() {
            return outsourcingPickupTime;
        }

        public void setOutsourcingPickupTime(String outsourcingPickupTime) {
            this.outsourcingPickupTime = outsourcingPickupTime;
        }

        public int getOutsourcingStatus() {
            return outsourcingStatus;
        }

        public void setOutsourcingStatus(int outsourcingStatus) {
            this.outsourcingStatus = outsourcingStatus;
        }

        public String getPrincipalName() {
            return principalName;
        }

        public void setPrincipalName(String principalName) {
            this.principalName = principalName;
        }

        public int getPrincipalRecordId() {
            return principalRecordId;
        }

        public void setPrincipalRecordId(int principalRecordId) {
            this.principalRecordId = principalRecordId;
        }

        public String getProcessNames() {
            return processNames;
        }

        public void setProcessNames(String processNames) {
            this.processNames = processNames;
        }

        public int getProductionOrderId() {
            return productionOrderId;
        }

        public void setProductionOrderId(int productionOrderId) {
            this.productionOrderId = productionOrderId;
        }

        public int getProductionOrderStageId() {
            return productionOrderStageId;
        }

        public void setProductionOrderStageId(int productionOrderStageId) {
            this.productionOrderStageId = productionOrderStageId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWorkAllcationClerkId() {
            return workAllcationClerkId;
        }

        public void setWorkAllcationClerkId(int workAllcationClerkId) {
            this.workAllcationClerkId = workAllcationClerkId;
        }

        public String getWorkAllcationConfirmTime() {
            return workAllcationConfirmTime;
        }

        public void setWorkAllcationConfirmTime(String workAllcationConfirmTime) {
            this.workAllcationConfirmTime = workAllcationConfirmTime;
        }

        public int getWorkAllcationPreFillEmployeeId() {
            return workAllcationPreFillEmployeeId;
        }

        public void setWorkAllcationPreFillEmployeeId(int workAllcationPreFillEmployeeId) {
            this.workAllcationPreFillEmployeeId = workAllcationPreFillEmployeeId;
        }

        public String getWorkAllcationPreFillTime() {
            return workAllcationPreFillTime;
        }

        public void setWorkAllcationPreFillTime(String workAllcationPreFillTime) {
            this.workAllcationPreFillTime = workAllcationPreFillTime;
        }

        public int getWorkAllcationStatus() {
            return workAllcationStatus;
        }

        public void setWorkAllcationStatus(int workAllcationStatus) {
            this.workAllcationStatus = workAllcationStatus;
        }
    }

    public static class MaterialTakeListBean {
        /**
         * factoryId : 0
         * gmtCreate : 2019-06-06T08:40:25.603Z
         * id : 0
         * isTake : 0
         * materialCategory1Id : 0
         * materialCategory1Name : string
         * materialCategory2Id : 0
         * materialCategory2Name : string
         * materialId : 0
         * materialName : string
         * orderId : 0
         * productionOrderId : 0
         * purchaseRequirementId : 0
         * quantityCanTake : 0.0
         * quantityNeed : 0.0
         * quantityTake : 0.0
         * takeBy : 0
         * takeTime : 2019-06-06T08:40:25.603Z
         */

        private int factoryId;
        private String gmtCreate;
        private int id;
        private int isTake;
        private int materialCategory1Id;
        private String materialCategory1Name;
        private int materialCategory2Id;
        private String materialCategory2Name;
        private int materialId;
        private String materialName;
        private int orderId;
        private int productionOrderId;
        private int purchaseRequirementId;
        private double quantityCanTake;
        private double quantityNeed;
        private double quantityTake;
        private int takeBy;
        private String takeTime;

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

        public int getIsTake() {
            return isTake;
        }

        public void setIsTake(int isTake) {
            this.isTake = isTake;
        }

        public int getMaterialCategory1Id() {
            return materialCategory1Id;
        }

        public void setMaterialCategory1Id(int materialCategory1Id) {
            this.materialCategory1Id = materialCategory1Id;
        }

        public String getMaterialCategory1Name() {
            return materialCategory1Name;
        }

        public void setMaterialCategory1Name(String materialCategory1Name) {
            this.materialCategory1Name = materialCategory1Name;
        }

        public int getMaterialCategory2Id() {
            return materialCategory2Id;
        }

        public void setMaterialCategory2Id(int materialCategory2Id) {
            this.materialCategory2Id = materialCategory2Id;
        }

        public String getMaterialCategory2Name() {
            return materialCategory2Name;
        }

        public void setMaterialCategory2Name(String materialCategory2Name) {
            this.materialCategory2Name = materialCategory2Name;
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

        public int getPurchaseRequirementId() {
            return purchaseRequirementId;
        }

        public void setPurchaseRequirementId(int purchaseRequirementId) {
            this.purchaseRequirementId = purchaseRequirementId;
        }

        public double getQuantityCanTake() {
            return quantityCanTake;
        }

        public void setQuantityCanTake(double quantityCanTake) {
            this.quantityCanTake = quantityCanTake;
        }

        public double getQuantityNeed() {
            return quantityNeed;
        }

        public void setQuantityNeed(double quantityNeed) {
            this.quantityNeed = quantityNeed;
        }

        public double getQuantityTake() {
            return quantityTake;
        }

        public void setQuantityTake(double quantityTake) {
            this.quantityTake = quantityTake;
        }

        public int getTakeBy() {
            return takeBy;
        }

        public void setTakeBy(int takeBy) {
            this.takeBy = takeBy;
        }

        public String getTakeTime() {
            return takeTime;
        }

        public void setTakeTime(String takeTime) {
            this.takeTime = takeTime;
        }
    }

    public static class ProductProcessListBeanX {
        /**
         * factoryId : 0
         * id : 0
         * isOutsourcing : 0
         * processId : 0
         * processName : string
         * productionOrderId : 0
         * productionOrderStageId : 0
         * sequence : 0
         * stageId : 0
         * stageName : string
         */

        private int factoryId;
        private int id;
        private int isOutsourcing;
        private int processId;
        private String processName;
        private int productionOrderId;
        private int productionOrderStageId;
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

        public int getProductionOrderId() {
            return productionOrderId;
        }

        public void setProductionOrderId(int productionOrderId) {
            this.productionOrderId = productionOrderId;
        }

        public int getProductionOrderStageId() {
            return productionOrderStageId;
        }

        public void setProductionOrderStageId(int productionOrderStageId) {
            this.productionOrderStageId = productionOrderStageId;
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

    public static class SizeQuantityListBean {
        /**
         * factoryId : 0
         * id : 0
         * productionOrderId : 0
         * quantity : 0
         * sizeId : 0
         * sizeName : string
         */

        private int factoryId;
        private int id;
        private int productionOrderId;
        private int quantity;
        private int sizeId;
        private String sizeName;

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

        public int getProductionOrderId() {
            return productionOrderId;
        }

        public void setProductionOrderId(int productionOrderId) {
            this.productionOrderId = productionOrderId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
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
    }
}
