package com.huafeng.client.ui.message.model;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/13 19:28
 * 邮箱：771548229@qq..com
 */
public class GroupDetail {

    /**
     * createTime : 2019-05-13T11:28:10.067Z
     * crowdName : string
     * crowdUserId : 0
     * crowdUserList : [{"headPortrait":"string","id":0,"isNotice":0,"isRemind":0,"isTopChat":0,"nickname":"string","userId":0}]
     * description : string
     * id : string
     * image : string
     * indexUser : {"headPortrait":"string","id":0,"isNotice":0,"isRemind":0,"isTopChat":0,"nickname":"string","userId":0}
     */
    private String imageUrl;
    private String createTime;
    private String crowdName;
    private int crowdUserId;
    private String description;
    private String id;
    private String image;
    private IndexUserBean indexUser;
    private List<CrowdUserListBean> crowdUserList;

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

    public IndexUserBean getIndexUser() {
        return indexUser;
    }

    public void setIndexUser(IndexUserBean indexUser) {
        this.indexUser = indexUser;
    }

    public List<CrowdUserListBean> getCrowdUserList() {
        return crowdUserList;
    }

    public void setCrowdUserList(List<CrowdUserListBean> crowdUserList) {
        this.crowdUserList = crowdUserList;
    }

    public static class IndexUserBean {
        /**
         * headPortrait : string
         * id : 0
         * isNotice : 0
         * isRemind : 0
         * isTopChat : 0
         * nickname : string
         * userId : 0
         */

        private String headPortrait;
        private int id;
        private int isNotice;
        private int isRemind;
        private int isTopChat;
        private String nickname;
        private int userId;

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsNotice() {
            return isNotice;
        }

        public void setIsNotice(int isNotice) {
            this.isNotice = isNotice;
        }

        public int getIsRemind() {
            return isRemind;
        }

        public void setIsRemind(int isRemind) {
            this.isRemind = isRemind;
        }

        public int getIsTopChat() {
            return isTopChat;
        }

        public void setIsTopChat(int isTopChat) {
            this.isTopChat = isTopChat;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

    public static class CrowdUserListBean {
        /**
         * headPortrait : string
         * id : 0
         * isNotice : 0
         * isRemind : 0
         * isTopChat : 0
         * nickname : string
         * userId : 0
         */
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        private String headPortraitUrl;

        public String getHeadPortraitUrl() {
            return headPortraitUrl;
        }

        public void setHeadPortraitUrl(String headPortraitUrl) {
            this.headPortraitUrl = headPortraitUrl;
        }

        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        private String headPortrait;
        private int id;
        private int isNotice;
        private int isRemind;
        private int isTopChat;
        private String nickname;
        private int userId;

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsNotice() {
            return isNotice;
        }

        public void setIsNotice(int isNotice) {
            this.isNotice = isNotice;
        }

        public int getIsRemind() {
            return isRemind;
        }

        public void setIsRemind(int isRemind) {
            this.isRemind = isRemind;
        }

        public int getIsTopChat() {
            return isTopChat;
        }

        public void setIsTopChat(int isTopChat) {
            this.isTopChat = isTopChat;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
