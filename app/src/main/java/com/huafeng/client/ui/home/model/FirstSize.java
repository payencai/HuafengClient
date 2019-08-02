package com.huafeng.client.ui.home.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：凌涛 on 2019/5/6 11:06
 * 邮箱：771548229@qq..com
 */
public class FirstSize implements Serializable {

    /**
     * id : 1
     * name : SML
     * sizes : [{"id":1,"name":"S","groupId":1},{"id":2,"name":"M","groupId":1},{"id":3,"name":"L","groupId":1},{"id":4,"name":"XL","groupId":1}]
     */

    private int id;
    private String name;
    private List<SizesBean> sizes;

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

    public List<SizesBean> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizesBean> sizes) {
        this.sizes = sizes;
    }

    public static class SizesBean implements Serializable{
        /**
         * id : 1
         * name : S
         * groupId : 1
         */
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        private int id;
        private String name;
        private int groupId;

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

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }
    }
}
