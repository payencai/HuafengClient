package com.huafeng.client.ui.start.bean;

import java.io.Serializable;

public class Splash implements Serializable {

    /**
     * id : 9
     * image : journey/2019073112015190
     * imageUrl : http://huafengzhiyi.oss-cn-shenzhen.aliyuncs.com/journey/2019073112015190?Expires=1564589132&OSSAccessKeyId=LTAI2Z40o3uCKGQ6&Signature=BAf11kfjXatOUkaELUvebt7QUSY%3D
     * hyperlink : http://www.baidu.com
     * isDeleted : 0
     * gmtCreate : 2019-07-31 12:02:08
     */

    private int id;
    private String image;
    private String imageUrl;
    private String hyperlink;
    private int isDeleted;
    private String gmtCreate;

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

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
