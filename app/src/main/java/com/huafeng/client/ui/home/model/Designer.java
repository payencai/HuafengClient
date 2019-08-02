package com.huafeng.client.ui.home.model;

import java.io.Serializable;

/**
 * 作者：凌涛 on 2019/5/8 16:22
 * 邮箱：771548229@qq..com
 */
public class Designer implements Serializable {

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
