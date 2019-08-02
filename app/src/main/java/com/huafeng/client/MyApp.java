package com.huafeng.client;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;


import com.amap.api.location.AMapLocation;
import com.huafeng.client.JPush.JpushConfig;
import com.huafeng.client.model.UserInfo;
import com.huafeng.client.net.NetUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.litepal.LitePal;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * 作者：凌涛 on 2019/4/10 10:18
 * 邮箱：771548229@qq..com
 */
public class MyApp extends MultiDexApplication {
    public static boolean isLogin = false;
    public static String token;
    public static boolean isInit = true;
    public static Context sContext;
    private static UserInfo mUserInfo;
    private static AMapLocation aMapLocation;

    public static AMapLocation getaMapLocation() {
        return aMapLocation;
    }

    public static void setaMapLocation(AMapLocation aMapLocation) {
        MyApp.aMapLocation = aMapLocation;
    }

    public static UserInfo getUserInfo() {
        return mUserInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }
    //MD5:  17:E5:CE:62:4F:31:CE:7A:CA:02:06:2A:5F:7F:4C:53
    //SHA1: C3:6B:6E:37:5A:D7:5C:7F:70:C2:61:46:28:10:89:D6:68:32:4F:58  yichankey.jks
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/pingfang_sc_medium.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CrashReport.initCrashReport(getApplicationContext(), "ce7b3f9225", true); // bugly
        NetUtils.getInstance().initNetWorkUtils(this);//okgo初始化
        ZXingLibrary.initDisplayOpinion(this);
        LitePal.initialize(this);
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
        JpushConfig.getInstance().initJpush(this);
        initChat(this);//初始化环信
    }

    private void initChat(Context context) {
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        options.setAutoLogin(false);
        options.isAutoAcceptGroupInvitation();
        options.setAppKey("1121190511097928#huafeng");
// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        //options.setAutoLogin(false);
//初始化
        EaseUI.getInstance().init(context, options);
        EMClient.getInstance().init(context, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
        //注册一个监听连接状态的listener


    }



}
