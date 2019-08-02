package com.huafeng.client.ui.message.model;

/**
 * 作者：凌涛 on 2019/5/13 16:00
 * 邮箱：771548229@qq..com
 */
public class MyGroup {

    /**
     * createTime : 2019-05-13T08:06:33.892Z
     * crowdName : string
     * crowdUserId : 0
     * description : string
     * id : string
     * image : string
     */

    private String createTime;
    private String crowdName;
    private int crowdUserId;
    private String description;
    private String id;
    private String image;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCrowdName() {
        return crowdName;
    }

    public void setCrowdName(String crowdName) {
        this.crowdName = crowdName;
    }

    public int getCrowdUserId() {
        return crowdUserId;
    }

    public void setCrowdUserId(int crowdUserId) {
        this.crowdUserId = crowdUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
