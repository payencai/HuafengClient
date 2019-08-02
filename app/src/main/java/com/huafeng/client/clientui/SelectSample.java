package com.huafeng.client.clientui;

import java.io.Serializable;

public class SelectSample implements Serializable {

    /**
     * factoryName : string
     * id : 0
     * sampleNo : string
     */

    private String factoryName;
    private int id;
    private String sampleNo;

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }
}
