package com.huafeng.client.model;

/**
 * 作者：凌涛 on 2019/5/5 14:54
 * 邮箱：771548229@qq..com
 */
public class MyBanner {

    /**
     * id : 1
     * image : journey/2019050511540812
     * imageUrl : http://huafengzhiyi.oss-cn-shenzhen.aliyuncs.com/journey/2019050511540812?Expires=1557082442&OSSAccessKeyId=LTAI2Z40o3uCKGQ6&Signature=l7%2BsuhnIqGa1UjjDZIDczhVwNME%3D
     * factoryId : 3
     * sequence : 1
     */

    private int id;
    private String image;
    private String imageUrl;
    private int factoryId;
    private int sequence;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
