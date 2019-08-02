package com.huafeng.client.ui.home.model;

import java.io.Serializable;

/**
 * 作者：凌涛 on 2019/5/9 17:45
 * 邮箱：771548229@qq..com
 */
public class Approvers implements Serializable {

    /**
     * id : 0
     * name : string
     */

    private int id;
    private String name;

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
