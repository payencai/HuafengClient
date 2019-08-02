package com.huafeng.client.ui.message.model;

/**
 * 作者：凌涛 on 2019/5/13 16:31
 * 邮箱：771548229@qq..com
 */
public class GroupApply {

    /**
     * applyReason : string
     * createTime : 2019-05-13T08:30:26.359Z
     * crowdId : string
     * crowdName : string
     * headPortrait : string
     * id : 0
     * nickname : string
     * rejectReason : string
     * state : 0
     * userId : 0
     */

    private String applyReason;
    private String createTime;
    private String crowdId;
    private String crowdName;
    private String headPortrait;
    private String id;
    private String nickname;
    private String rejectReason;
    private int state;
    private int userId;

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

    public String getCrowdId() {
        return crowdId;
    }

    public void setCrowdId(String crowdId) {
        this.crowdId = crowdId;
    }

    public String getCrowdName() {
        return crowdName;
    }

    public void setCrowdName(String crowdName) {
        this.crowdName = crowdName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
