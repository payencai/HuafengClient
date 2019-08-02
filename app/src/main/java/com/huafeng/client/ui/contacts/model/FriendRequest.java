package com.huafeng.client.ui.contacts.model;

/**
 * 作者：凌涛 on 2019/4/4 16:05
 * 邮箱：771548229@qq..com
 */
public class FriendRequest {

    /**
     * applyReason : string
     * createTime : 2019-05-12T07:25:22.352Z
     * headPortrait : string
     * id : 0
     * nickname : string
     * rejectReason : string
     * state : 0
     * userId : 0
     */

    private String applyReason;
    private String createTime;
    private String headPortrait;
    private int id;
    private String nickname;
    private String rejectReason;
    private int state;
    private int userId;
    private String headPortraitUrl;

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
