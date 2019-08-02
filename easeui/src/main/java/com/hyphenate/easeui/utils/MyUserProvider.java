package com.hyphenate.easeui.utils;

import com.ess.filepicker.util.LogUtils;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：凌涛 on 2019/5/14 15:49
 * 邮箱：771548229@qq..com
 */
public class MyUserProvider implements EaseUI.EaseUserProfileProvider {
    private static MyUserProvider myUserProvider;
    // 设计成了单类。。
    private Map<String,EaseUser> userList = new HashMap<>();
    // 我服务器上获得头像的url

    @Override
    public EaseUser getUser(String username) {
        if(userList.containsKey(username))
            return userList.get(username);
        return null;
    }
    // 封装了一下
    public void setUser(String userId,String nickname,String avatar,String key){
        if(!userList.containsKey(userId)) {
            EaseUser easeUser = new EaseUser(userId);
            userList.put(userId,easeUser);
        }
        EaseUser easeUser = getUser(userId);
        LogUtils.error(nickname);
        // 不用怀疑。环信的easerUser的父类有一个setNickname 的方法可以用来设置暱称，直接调用就好。。
        easeUser.setNickname(nickname);
        easeUser.setKey(key);
        // 同理，设置一个图像的url就好，因为他加载头像是使用glide加载的
        easeUser.setAvatar(avatar);
    }
    // 按照你喜欢的修改一下初始化函数吧，
    private MyUserProvider(){
        System.out.println("init myTestProfileProvider");
    }
    // 获取单类。。
    public static MyUserProvider getInstance() {
        if (myUserProvider == null) {
            myUserProvider = new MyUserProvider();
        }
        return myUserProvider;
    }
}
