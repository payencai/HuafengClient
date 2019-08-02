package com.huafeng.client.ui.home.model;

import java.io.Serializable;

/**
 * 作者：凌涛 on 2019/5/8 16:20
 * 邮箱：771548229@qq..com
 */
public class NoPrefixs implements Serializable {

    /**
     * createBy : 0
     * factoryId : 0
     * gmtCreate : 2019-05-08T08:20:53.640Z
     * id : 0
     * name : string
     */

    private int createBy;
    private int factoryId;
    private String gmtCreate;
    private int id;
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;
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
}
