package com.huafeng.client.ui.home.model;

import java.util.List;

public class Authority {

    /**
     * id : 1010
     * name : 设计制版
     * parentId : -1
     * isEffective : 0
     * sequence : null
     * children : [{"id":1011,"name":"管理员","parentId":1010,"isEffective":1,"sequence":null,"children":null},{"id":1012,"name":"设计","parentId":1010,"isEffective":1,"sequence":null,"children":null},{"id":1013,"name":"纸样","parentId":1010,"isEffective":1,"sequence":null,"children":null},{"id":1014,"name":"车版","parentId":1010,"isEffective":1,"sequence":null,"children":null},{"id":1015,"name":"洗水","parentId":1010,"isEffective":1,"sequence":null,"children":null}]
     */

    private int id;
    private String name;
    private int parentId;
    private int isEffective;
    private int  sequence;
    private List<ChildrenBean> children;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(int isEffective) {
        this.isEffective = isEffective;
    }



    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * id : 1011
         * name : 管理员
         * parentId : 1010
         * isEffective : 1
         * sequence : null
         * children : null
         */

        private int id;
        private String name;
        private int parentId;
        private int isEffective;
        private int  sequence;


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

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getIsEffective() {
            return isEffective;
        }

        public void setIsEffective(int isEffective) {
            this.isEffective = isEffective;
        }

        public int getSequence() {
            return sequence;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }
    }
}
