package com.huafeng.client.ui.home.activity.sample;

import java.util.List;

public class ProcesModel {

    /**
     * createBy : 0
     * factoryId : 0
     * gmtCreate : 2019-06-12T08:01:17.876Z
     * gmtModified : 2019-06-12T08:01:17.876Z
     * id : 0
     * materialList : [{"factoryId":0,"id":0,"moduleId":0,"quantity":0,"rawMaterialCategory1Id":0,"rawMaterialCategory1Name":"string","rawMaterialCategory2Id":0,"rawMaterialCategory2Name":"string","rawMaterialId":0,"rawMaterialName":"string"}]
     * name : string
     * processList : [{"factoryId":0,"id":0,"isOutsourcing":0,"moduleId":0,"price":0,"processId":0,"processName":"string","sequence":0,"stageId":0,"stageName":"string"}]
     * sizeList : [{"factoryId":0,"id":0,"moduleId":0,"sampleSizeInformationList":[{"factoryId":0,"id":0,"moduleId":0,"moduleSizeId":0,"quantity":0,"sizeId":0,"sizeInfomationId":0,"sizeInformationName":"string"}],"sizeGroupId":0,"sizeGroupName":"string","sizeId":0,"sizeName":"string"}]
     * type : 0
     * updateBy : 0
     */

    private int createBy;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private int id;
    private String name;
    private int type;
    private int updateBy;
    private List<MaterialListBean> materialList;
    private List<ProcessListBean> processList;
    private List<SizeListBean> sizeList;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
         * factoryId : 0
         * id : 0
         * moduleId : 0
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
        private int moduleId;
        private Double quantity;
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

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
        }

        public Double getQuantity() {
            return quantity;
        }

        public void setQuantity(Double quantity) {
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

    public static class ProcessListBean {
        /**
         * factoryId : 0
         * id : 0
         * isOutsourcing : 0
         * moduleId : 0
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
        private int moduleId;
        private Double price;
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

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
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
         * moduleId : 0
         * sampleSizeInformationList : [{"factoryId":0,"id":0,"moduleId":0,"moduleSizeId":0,"quantity":0,"sizeId":0,"sizeInfomationId":0,"sizeInformationName":"string"}]
         * sizeGroupId : 0
         * sizeGroupName : string
         * sizeId : 0
         * sizeName : string
         */

        private int factoryId;
        private int id;
        private int moduleId;
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

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
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
             * moduleId : 0
             * moduleSizeId : 0
             * quantity : 0
             * sizeId : 0
             * sizeInfomationId : 0
             * sizeInformationName : string
             */

            private int factoryId;
            private int id;
            private int moduleId;
            private int moduleSizeId;
            private int quantity;
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

            public int getModuleId() {
                return moduleId;
            }

            public void setModuleId(int moduleId) {
                this.moduleId = moduleId;
            }

            public int getModuleSizeId() {
                return moduleSizeId;
            }

            public void setModuleSizeId(int moduleSizeId) {
                this.moduleSizeId = moduleSizeId;
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
