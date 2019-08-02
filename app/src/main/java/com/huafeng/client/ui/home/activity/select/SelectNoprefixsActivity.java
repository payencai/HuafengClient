package com.huafeng.client.ui.home.activity.select;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.adapter.SelectPrefixsAdapter;
import com.huafeng.client.ui.home.model.NoPrefixs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectNoprefixsActivity extends BaseActivity {


    SelectPrefixsAdapter mWashProcessAdapter;
    List<NoPrefixs> mWashProcesses;
    @BindView(R.id.lv_first)
    ListView lv_first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_noprefixs);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showAdd();
            }
        });
        mWashProcesses = new ArrayList<>();
        mWashProcessAdapter = new SelectPrefixsAdapter(this, mWashProcesses);
        lv_first.setAdapter(mWashProcessAdapter);
        lv_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoPrefixs firstType = mWashProcesses.get(position);
                Intent intent = new Intent();
                intent.putExtra("data", firstType);
                setResult(0, intent);
                finish();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getHead();
    }

    private void getHead() {

        NetUtils.getInstance().get(MyApp.token, Api.Pattern.getNoPrefixs, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            NoPrefixs firstType = new Gson().fromJson(item.toString(), NoPrefixs.class);
                            mWashProcesses.add(firstType);
                        }
                        mWashProcessAdapter.notifyDataSetChanged();
                    } else {
                        String msg = jsonObject.getString("message");
                        ToastUtils.showShort(msg);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void addHead(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        String json = new Gson().toJson(params);
        LogUtils.e( MyApp.token);
        NetUtils.getInstance().post(Api.Pattern.addNoPrefixe, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        mWashProcesses.clear();
                        getHead();
                    } else {
                        String msg = jsonObject.getString("message");
                        ToastUtils.showShort(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }
    private void showAdd() {
        final Dialog dialog = new Dialog(this, R.style.dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_modelhead, null);

        ImageView ivdel = view.findViewById(R.id.iv_del);
        EditText etContent = view.findViewById(R.id.et_content);
        TextView tv_submit =  view.findViewById(R.id.tv_confirm);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etContent.getEditableText().toString();
                if (TextUtils.isEmpty(name)) {
                    return;
                }
                addHead(name);
                dialog.dismiss();
            }
        });
        ivdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = (int) (display.getWidth() * 0.8);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

    }
}
