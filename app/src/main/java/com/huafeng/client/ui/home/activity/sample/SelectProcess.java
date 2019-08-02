package com.huafeng.client.ui.home.activity.sample;

import java.io.Serializable;
import java.util.List;

public class SelectProcess implements Serializable{

    /**
     * createBy : 0
     * factoryId : 0
     * gmtCreate : 2019-06-11T12:57:35.955Z
     * gmtModified : 2019-06-11T12:57:35.955Z
     * id : 0
     * isDeleted : 0
     * name : string
     * natureId : string
     * processList : [{"createBy":0,"factoryId":0,"gmtCreate":"2019-06-11T12:57:35.955Z","gmtModified":"2019-06-11T12:57:35.955Z","id":0,"isDeleted":0,"name":"string","price":0,"stageId":0,"updateBy":0}]
     * updateBy : 0
     */

    private int createBy;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private int id;
    private int isDeleted;
    private String name;
    private String natureId;
    private int updateBy;
    private List<ProcessListBean> processList;

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

    public String getNatureId() {
        return natureId;
    }

    public void setNatureId(String natureId) {
        this.natureId = natureId;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public List<ProcessListBean> getProcessList() {
        return processList;
    }

    public void setProcessList(List<ProcessListBean> processList) {
        this.processList = processList;
    }

    public static class ProcessListBean implements Serializable {
        /**
         * createBy : 0
         * factoryId : 0
         * gmtCreate : 2019-06-11T12:57:35.955Z
         * gmtModified : 2019-06-11T12:57:35.955Z
         * id : 0
         * isDeleted : 0
         * name : string
         * price : 0.0
         * stageId : 0
         * updateBy : 0
         */
        private String processName;
        private int isOutsourcing;
        private boolean isHeader;

        public boolean isHeader() {
            return isHeader;
        }

        public void setHeader(boolean header) {
            isHeader = header;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        private boolean isCheck;
        public int getIsOutsourcing() {
            return isOutsourcing;
        }

        public void setIsOutsourcing(int isOutsourcing) {
            this.isOutsourcing = isOutsourcing;
        }

        public String getProcessName() {
            return processName;
        }

        public void setProcessName(String processName) {
            this.processName = processName;
        }

        private int createBy;
        private int factoryId;
        private String gmtCreate;
        private String gmtModified;
        private int id;
        private int isDeleted;
        private String name;
        private Double price;
        private int stageId;
        private int updateBy;

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

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public int getStageId() {
            return stageId;
        }

        public void setStageId(int stageId) {
            this.stageId = stageId;
        }

        public int getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(int updateBy) {
            this.updateBy = updateBy;
        }
    }
}
