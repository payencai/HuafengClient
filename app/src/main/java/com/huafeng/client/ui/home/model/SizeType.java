package com.huafeng.client.ui.home.model;

import java.io.Serializable;

/**
 * 作者：凌涛 on 2019/5/8 10:12
 * 邮箱：771548229@qq..com
 */
public class SizeType implements Serializable {

    /**
     * createBy : 0
     * factoryId : 0
     * gmtCreate : 2019-05-08T02:08:30.276Z
     * gmtModified : 2019-05-08T02:08:30.276Z
     * id : 0
     * isDeleted : 0
     * name : string
     * updateBy : 0
     */
    private String input;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    private boolean isSelect;
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    private int createBy;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private int id;
    private int isDeleted;
    private String name;
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

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }
}
