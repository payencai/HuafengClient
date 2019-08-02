package com.huafeng.client.tools;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.ui.start.activity.LoginActivity;
import com.ycbjie.notificationlib.NotificationUtils;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class LoginUtil {


    public static void onConnectionConflict(Activity context) {
        //ActivityUtils.finishAllActivities();

        logout();
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                NotificationUtils notificationUtils = new NotificationUtils(context);
                notificationUtils.sendNotification(1,"安全提醒","你的账号在别处登录",R.mipmap.ic_app_logo);
                showLogoutDialog(context);
            }
        });


    }

    public static void showLogoutDialog(Activity context) {
        final Dialog dialog = new Dialog(context, R.style.dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_logout, null);
        TextView tv_submit = (TextView) view.findViewById(R.id.tv_confirm);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ActivityUtils.finishAllActivities();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        if (!((Activity) context).isFinishing()) {
            dialog.show();
        }else{
            ActivityUtils.finishAllActivities();
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
            return;
        }

        Window window = dialog.getWindow();
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = (int) (display.getWidth() * 0.7);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

    }

    public static void logout() {

        MyApp.isLogin = false;
        MyApp.token = "";
        MyApp.setUserInfo(null);
        SPUtils.getInstance().put("token", "");
        SPUtils.getInstance().put("user", "");
        SPUtils.getInstance().put("head", "");

    }
}
