package com.huafeng.client.ui.home.activity.order;

import java.util.List;

public class OrderDetail {

    /**
     * clientRecord : {"city":"string","clientUserId":0,"contactAddress":"string","contactNumber":"string","createBy":0,"factoryId":0,"gmtCreate":"2019-06-06T04:42:58.916Z","gmtModified":"2019-06-06T04:42:58.916Z","id":0,"image":"string","imageUrl":"string","isDeleted":0,"name":"string","orderQuantity":0,"principal":"string","province":"string","receivingAddress":"string","remarks":"string","updateBy":0}
     * createByName : string
     * orderSampleVo : {"clientRecordId":0,"clientRecordName":"string","color":"string","createBy":0,"designerBy":0,"designerName":"string","factoryId":0,"gmtCreate":"2019-06-06T04:42:58.916Z","gmtModified":"2019-06-06T04:42:58.916Z","id":0,"images":"string","imagesUrl":"string","isDeleted":0,"materialList":[{"factoryId":0,"id":0,"orderSampleId":0,"quantity":0,"rawMaterialCategory1Id":0,"rawMaterialCategory1Name":"string","rawMaterialCategory2Id":0,"rawMaterialCategory2Name":"string","rawMaterialId":0,"rawMaterialName":"string"}],"patternNo":"string","productCategory1Id":0,"productCategory1Name":"string","productCategory2Name":"string","productCategoryId":0,"productProcessList":[{"factoryId":0,"id":0,"isOutsourcing":0,"orderSampleId":0,"price":0,"processId":0,"processName":"string","sequence":0,"stageId":0,"stageName":"string"}],"remarks":"string","sampleId":0,"sampleNo":"string","sizeList":[{"factoryId":0,"id":0,"orderSampleId":0,"sampleSizeInformationList":[{"factoryId":0,"id":0,"orderSampleId":0,"orderSampleSizeId":0,"quantity":0,"sizeId":0,"sizeInfomationId":0,"sizeInformationName":"string"}],"sizeGroupId":0,"sizeGroupName":"string","sizeId":0,"sizeName":"string"}],"updateBy":0,"washingProcessList":[{"factoryId":0,"id":0,"orderSampleId":0,"washingProcessId":0,"washingProcessName":"string"}]}
     * orders : {"clientRecordId":0,"createBy":0,"factoryId":0,"finishBy":0,"gmtCreate":"2019-06-06T04:42:58.916Z","gmtFinish":"2019-06-06T04:42:58.916Z","gmtModified":"2019-06-06T04:42:58.916Z","gmtStop":"2019-06-06T04:42:58.916Z","id":0,"orderNumber":"string","orderSampleId":0,"quantity":0,"remarks":"string","sampleId":0,"shippingAddress":"string","status":0,"stopBy":0,"unitPrice":0}
     * quantityInventory : 0
     * quantityProducted : 0
     */

    private ClientRecordBean clientRecord;
    private String createByName;
    private OrderSampleVoBean orderSampleVo;
    private OrdersBean orders;
    private int quantityInventory;
    private int quantityProducted;

    public ClientRecordBean getClientRecord() {
        return clientRecord;
    }

    public void setClientRecord(ClientRecordBean clientRecord) {
        this.clientRecord = clientRecord;
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

    public OrdersBean getOrders() {
        return orders;
    }

    public void setOrders(OrdersBean orders) {
        this.orders = orders;
    }

    public int getQuantityInventory() {
        return quantityInventory;
    }

    public void setQuantityInventory(int quantityInventory) {
        this.quantityInventory = quantityInventory;
    }

    public int getQuantityProducted() {
        return quantityProducted;
    }

    public void setQuantityProducted(int quantityProducted) {
        this.quantityProducted = quantityProducted;
    }

    public static class ClientRecordBean {
        /**
         * city : string
         * clientUserId : 0
         * contactAddress : string
         * contactNumber : string
         * createBy : 0
         * factoryId : 0
         * gmtCreate : 2019-06-06T04:42:58.916Z
         * gmtModified : 2019-06-06T04:42:58.916Z
         * id : 0
         * image : string
         * imageUrl : string
         * isDeleted : 0
         * name : string
         * orderQuantity : 0
         * principal : string
         * province : string
         * receivingAddress : string
         * remarks : string
         * updateBy : 0
         */

        private String city;
        private int clientUserId;
        private String contactAddress;
        private String contactNumber;
        private int createBy;
        private int factoryId;
        private String gmtCreate;
        private String gmtModified;
        private int id;
        private String image;
        private String imageUrl;
        private int isDeleted;
        private String name;
        private int orderQuantity;
        private String principal;
        private String province;
        private String receivingAddress;
        private String remarks;
        private int updateBy;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getClientUserId() {
            return clientUserId;
        }

        public void setClientUserId(int clientUserId) {
            this.clientUserId = clientUserId;
        }

        public String getContactAddress() {
            return contactAddress;
        }

        public void setContactAddress(String contactAddress) {
            this.contactAddress = contactAddress;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
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

        public int getOrderQuantity() {
            return orderQuantity;
        }

        public void setOrderQuantity(int orderQuantity) {
            this.orderQuantity = orderQuantity;
        }

        public String getPrincipal() {
            return principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getReceivingAddress() {
            return receivingAddress;
        }

        public void setReceivingAddress(String receivingAddress) {
            this.receivingAddress = receivingAddress;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(int updateBy) {
            this.updateBy = updateBy;
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
         * gmtCreate : 2019-06-06T04:42:58.916Z
         * gmtModified : 2019-06-06T04:42:58.916Z
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
             * price : 0
             * processId : 0
             * processName : string
             * sequence : 0
             * stageId : 0
             * stageName : string
             */
            private boolean isCheck;

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }
            private boolean isHeader;

            public boolean isHeader() {
                return isHeader;
            }

            public void setHeader(boolean header) {
                isHeader = header;
            }

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
            private String input;
            private int factoryId;

            public String getInput() {
                return input;
            }

            public void setInput(String input) {
                this.input = input;
            }

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

    public static class OrdersBean {
        /**
         * clientRecordId : 0
         * createBy : 0
         * factoryId : 0
         * finishBy : 0
         * gmtCreate : 2019-06-06T04:42:58.916Z
         * gmtFinish : 2019-06-06T04:42:58.916Z
         * gmtModified : 2019-06-06T04:42:58.916Z
         * gmtStop : 2019-06-06T04:42:58.916Z
         * id : 0
         * orderNumber : string
         * orderSampleId : 0
         * quantity : 0
         * remarks : string
         * sampleId : 0
         * shippingAddress : string
         * status : 0
         * stopBy : 0
         * unitPrice : 0.0
         */

        private int clientRecordId;
        private int createBy;
        private int factoryId;
        private int finishBy;
        private String gmtCreate;
        private String gmtFinish;
        private String gmtModified;
        private String gmtStop;
        private int id;
        private String orderNumber;
        private int orderSampleId;
        private int quantity;
        private String remarks;
        private int sampleId;
        private String shippingAddress;
        private int status;
        private int stopBy;
        private double unitPrice;

        public int getClientRecordId() {
            return clientRecordId;
        }

        public void setClientRecordId(int clientRecordId) {
            this.clientRecordId = clientRecordId;
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

        public int getFinishBy() {
            return finishBy;
        }

        public void setFinishBy(int finishBy) {
            this.finishBy = finishBy;
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

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }

        public String getGmtStop() {
            return gmtStop;
        }

        public void setGmtStop(String gmtStop) {
            this.gmtStop = gmtStop;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getOrderSampleId() {
            return orderSampleId;
        }

        public void setOrderSampleId(int orderSampleId) {
            this.orderSampleId = orderSampleId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
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

        public String getShippingAddress() {
            return shippingAddress;
        }

        public void setShippingAddress(String shippingAddress) {
            this.shippingAddress = shippingAddress;
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

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }
    }
}
