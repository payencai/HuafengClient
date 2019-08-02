package com.huafeng.client.JPush;

public class PushData {

    /**
     * content : Hhh-25的设计任务已指派给您
     * id : 978
     * isLinked : 1
     * serviceId : 318
     * type : 1
     */

    private String content;
    private String id;
    private String isLinked;
    private String serviceId;
    private String type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsLinked() {
        return isLinked;
    }

    public void setIsLinked(String isLinked) {
        this.isLinked = isLinked;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
