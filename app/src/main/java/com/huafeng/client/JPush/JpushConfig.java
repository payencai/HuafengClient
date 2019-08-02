package com.huafeng.client.JPush;

import android.content.Context;
import android.text.TextUtils;

import com.huafeng.client.MyApp;


import java.util.LinkedHashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

public class JpushConfig {
    private Context mContext;

    private JpushConfig() {
        mContext= MyApp.sContext;
    }

    private static class Holder {
        private static JpushConfig instance = new JpushConfig();
    }

    public static JpushConfig getInstance() {
        return Holder.instance;
    }

    /**初始化极光,在Application的oncreate()方法中调用**/
    public void initJpush(Context context){
        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(context);
    }

    /**添加tag**/
    public void addTag(String tag){
        handleTag(tag,JpushHelper.ACTION_ADD);
    }

    /**设置tag**/
    public void setTag(String tag){
        handleTag(tag,JpushHelper.ACTION_SET);
    }

    /**删除tag**/
    public void deleteTag(String tag){
        handleTag(tag,JpushHelper.ACTION_DELETE);
    }

    /**获取所有tag**/
    public void getAllTags(){
        handleTag(null,JpushHelper.ACTION_GET);
    }

    /**清除所有tag**/
    public void cleanAllTags(){
        handleTag(null,JpushHelper.ACTION_CLEAN);
    }

    /**检测tag**/
    public void checkTag(Set<String> tags){
        if(tags==null){
            return;
        }
        TagAliasBean tagAliasBean = new TagAliasBean();
        tagAliasBean.setAction(JpushHelper.ACTION_CHECK);
        tagAliasBean.setAlias(null);
        tagAliasBean.setTags(tags);
        tagAliasBean.setAliasAction(false);
        JpushHelper.mSequence++;
        JpushHelper.getInstance().handleAction(JpushHelper.mSequence,tagAliasBean);
    }

    /**设置Alias**/
    public void setAlias(String alias){
        if(!TextUtils.isEmpty(alias)){
            handleAlias(alias,JpushHelper.ACTION_SET);
        }
    }

    /**获取alias**/
    public void getAlias(){
        handleAlias(null,JpushHelper.ACTION_GET);
    }

    /**删除alias**/
    public void deleteAlias(){
        handleAlias(null,JpushHelper.ACTION_DELETE);
    }


    private void handleTag(String tag,int action){
        Set<String> tags = new LinkedHashSet<>();
        if(!TextUtils.isEmpty(tag)){
            tags.add(tag);
        }
        TagAliasBean tagAliasBean = new TagAliasBean();
        tagAliasBean.setAction(action);
        tagAliasBean.setAlias(null);
        tagAliasBean.setTags(tags);
        tagAliasBean.setAliasAction(false);
        JpushHelper.mSequence++;
        JpushHelper.getInstance().handleAction(JpushHelper.mSequence,tagAliasBean);
    }

    private void handleAlias(String alias,int action){
        TagAliasBean tagAliasBean = new TagAliasBean();
        tagAliasBean.setAction(action);
        tagAliasBean.setAlias(alias);
        tagAliasBean.setTags(null);
        tagAliasBean.setAliasAction(true);
        JpushHelper.mSequence++;
        JpushHelper.getInstance().handleAction(JpushHelper.mSequence,tagAliasBean);
    }

    /**停止极光服务**/
    public void stopJpush(){
        if(!JPushInterface.isPushStopped(mContext)){
            JPushInterface.stopPush(mContext);
        }
    }

    /**恢复极光推送**/
    public void resumeJPush(){
        JPushInterface.resumePush(mContext);
    }

//    作者：奔跑的佩恩
//    链接：https://www.jianshu.com/p/1b1dd62b2d13
//    來源：简书
//    简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
}
