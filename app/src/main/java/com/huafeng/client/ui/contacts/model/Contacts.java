package com.huafeng.client.ui.contacts.model;

import com.huafeng.client.view.sidebar.IndexableEntity;

/**
 * 作者：凌涛 on 2019/4/3 11:58
 * 邮箱：771548229@qq..com
 */
public class Contacts implements IndexableEntity {

    private String index;
    /**
     * headPortrait : string
     * headPortraitUrl : string
     * id : 0
     * isNotice : 0
     * isRemind : 0
     * isTopChat : 0
     * nickname : string
     * userId : 0
     */
    private boolean isCheck = false;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private String headPortrait;
    private String headPortraitUrl;
    private int id;
    private int isNotice;
    private int isRemind;
    private int isTopChat;
    private String nickname;
    private int userId;

//    public Contacts(String name) {
//        this.index = FirstLetterUtil.getFirstLetter(name);
//        this.name = name;
//    }


    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    @Override
    public String getFieldIndexBy() {
        return nickname;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
          this.nickname=indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }
}
