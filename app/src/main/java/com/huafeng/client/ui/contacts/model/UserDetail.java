package com.huafeng.client.ui.contacts.model;

/**
 * 作者：凌涛 on 2019/5/13 20:12
 * 邮箱：771548229@qq..com
 */
public class UserDetail {

    /**
     * city : string
     * departmentId : 0
     * departmentName : string
     * factoryId : 0
     * factoryName : string
     * friends : {"friendId":0,"id":0,"isNotice":0,"isRemind":0,"isTopChat":0,"nickname":"string","userId":0}
     * headPortrait : string
     * headPortraitUrl : string
     * id : 0
     * isFriend : 0
     * nickname : string
     * postId : 0
     * postName : string
     * province : string
     * sex : 0
     * telephone : string
     * userId : 0
     * userType : 0
     */

    private String city;
    private int departmentId;
    private String departmentName;
    private int factoryId;
    private String factoryName;
    private FriendsBean friends;
    private String headPortrait;
    private String headPortraitUrl;
    private int id;
    private int isFriend;
    private String nickname;
    private int postId;
    private String postName;
    private String province;
    private int sex;
    private String telephone;
    private int userId;
    private int userType;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public FriendsBean getFriends() {
        return friends;
    }

    public void setFriends(FriendsBean friends) {
        this.friends = friends;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public static class FriendsBean {
        /**
         * friendId : 0
         * id : 0
         * isNotice : 0
         * isRemind : 0
         * isTopChat : 0
         * nickname : string
         * userId : 0
         */

        private int friendId;
        private int id;
        private int isNotice;
        private int isRemind;
        private int isTopChat;
        private String nickname;
        private int userId;

        public int getFriendId() {
            return friendId;
        }

        public void setFriendId(int friendId) {
            this.friendId = friendId;
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
