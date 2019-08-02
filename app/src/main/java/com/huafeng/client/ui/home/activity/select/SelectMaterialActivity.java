package com.huafeng.client.ui.home.activity.select;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.adapter.RawMatrerialAdapter;
import com.huafeng.client.ui.home.model.RawMatrerial;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectMaterialActivity extends BaseActivity {
    @BindView(R.id.lv_raw)
    ListView lv_raw;
    @BindView(R.id.tv_new)
    TextView tv_new;
    @BindView(R.id.et_search)
    EditText et_search;
    RawMatrerialAdapter rawMatrerialAdapter;
    List<RawMatrerial> mRawMatrerials;
    int id;
    int flag = 0;
    String keyWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_material);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        flag = getIntent().getIntExtra("flag", 0);
        if (flag == 1) {
            tv_new.setVisibility(View.GONE);
        }
        initView();
    }

    private void showRawDialog() {
        final Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_updatenick, null);
        final EditText et_season = (EditText) view.findViewById(R.id.et_season);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

        TextView tv_submit = (TextView) view.findViewById(R.id.tv_submit);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String name = et_season.getEditableText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showShort("输入不能为空！");
                    return;
                }
                RawMatrerial rawMatrerial = new RawMatrerial();
                rawMatrerial.setId(-1);
                rawMatrerial.setName(name);
                Intent intent = new Intent();
                intent.putExtra("data", rawMatrerial);
                setResult(0, intent);
                finish();

            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

    }
    @OnClick({R.id.tv_new,R.id.back})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.tv_new:
                showRawDialog();
                break;
        }
    }
    private void initView() {
        mRawMatrerials = new ArrayList<>();
        rawMatrerialAdapter = new RawMatrerialAdapter(this, mRawMatrerials);
        lv_raw.setAdapter(rawMatrerialAdapter);
        lv_raw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RawMatrerial rawMatrerial = mRawMatrerials.get(position);
                Intent intent = new Intent();
                intent.putExtra("data", rawMatrerial);
                setResult(0, intent);
                finish();
            }
        });
        getMatrerial(id);

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    keyWord = et_search.getEditableText().toString();
                    mRawMatrerials.clear();
                    getMatrerial(id);
                    return true;
                }
                return false;
            }
        });
    }

    private void getMatrerial(int id) {
        final HttpParams params = new HttpParams();
        params.put("categoryId2", id);
        if(!TextUtils.isEmpty(keyWord))
           params.put("name",keyWord);
        NetUtils.getInstance().get(MyApp.token, Api.Matrerial.getRawMaterials, params, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            RawMatrerial rawMatrerial = new Gson().fromJson(item.toString(), RawMatrerial.class);
                            mRawMatrerials.add(rawMatrerial);
                        }
                        rawMatrerialAdapter.notifyDataSetChanged();
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
}
