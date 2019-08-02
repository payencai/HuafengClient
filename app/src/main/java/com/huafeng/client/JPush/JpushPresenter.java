package com.huafeng.client.JPush;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.blankj.utilcode.util.ActivityUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.apply.ApprovalApplyDetailActivity;
import com.huafeng.client.ui.home.activity.design.DesignDetailActivity;
import com.huafeng.client.ui.home.activity.order.OrderDetailActivity;
import com.huafeng.client.ui.home.activity.produce.ProduceDetailActivity;
import com.huafeng.client.ui.message.activity.NotifyCenterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class JpushPresenter implements JpushContract {
    private Context mContext;
    public static final String TAG="JpushPresenter";
    public JpushPresenter() {

    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    /**
     * 接收到自定义的消息，调用自定义的通知显示出来
     */

    @Override
    public void doProcessPushMessage(Bundle bundle) {
        //showNotify(bundle);
        String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.e("msg",extra);
    }

    /**
     * 发送通知
     * @param bundle
     */
    @Override
    public void doProcessPusNotify(Bundle bundle) {
        String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.e("nitify",extra);
        showNotify(bundle);
    }


    /**
     * 使用Jpush内置的样式构建通知
     */

    public void showNotify(Bundle bundle){

        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(mContext);
        builder.statusBarDrawable = R.mipmap.ic_app_logo;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(0, builder);
        showPushDialog(ActivityUtils.getTopActivity(),bundle);
        // LogUtil.d(TAG,"=====doProcessPusNotify=======");
    }
    /**
     * 点击通知之后的操作在这里
     * @param bundle
     */
    @Override
    public void doOpenPusNotify(Bundle bundle) {
        if(!TextUtils.isEmpty(MyApp.token)){
            Intent intent2=new Intent(mContext, NotifyCenterActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent2);
        }
    }
    PushData pushData;
    public  void showPushDialog(Context context,Bundle bundle) {
        String result=bundle.getString(JPushInterface.EXTRA_EXTRA);

        try {
            JSONObject jsonObject=new JSONObject(result);
            JSONObject data=jsonObject.getJSONObject("Result");
             pushData=new Gson().fromJson(data.toString(), PushData.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final Dialog dialog = new Dialog(context, R.style.dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_push, null);
        TextView tv_title=view.findViewById(R.id.tv_title);
        tv_title.setText(pushData.getContent());
        TextView tv_confirm=view.findViewById(R.id.tv_confirm);
        TextView tv_cancel=view.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if("1".equals(pushData.getIsLinked())){
                    Intent intent=null;
                    switch (Integer.parseInt(pushData.getType())){
                        case 1:
                            intent=new Intent(ActivityUtils.getTopActivity(), DesignDetailActivity.class);
                            intent.putExtra("id",Integer.parseInt(pushData.getServiceId()));
                            break;
                        case 2:
                            intent=new Intent(ActivityUtils.getTopActivity(), ProduceDetailActivity.class);
                            intent.putExtra("id",Integer.parseInt(pushData.getServiceId()));
                            break;
                        case 3:
                        case 4:
                            intent=new Intent(ActivityUtils.getTopActivity(), ApprovalApplyDetailActivity.class);
                            intent.putExtra("id",Integer.parseInt(pushData.getServiceId()));
                            intent.putExtra("flag",2);
                            break;
                        case 5:
                            intent=new Intent(ActivityUtils.getTopActivity(), OrderDetailActivity.class);
                            intent.putExtra("id",Integer.parseInt(pushData.getServiceId()));
                            break;
                    }
                    ActivityUtils.getTopActivity().startActivityForResult(intent,1);
                }
            }
        });
        Activity activity= ActivityUtils.getTopActivity();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = (int) (display.getWidth() * 0.7);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

    }

}
