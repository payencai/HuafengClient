package com.huafeng.client.clientui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.clientui.SelectClientSampleAdapter;
import com.huafeng.client.clientui.SelectSample;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectClientSampleActivity extends AppCompatActivity {


    SelectClientSampleAdapter selectClientSampleAdapter;
    List<SelectSample> selectSamples;
    @BindView(R.id.lv_no)
    ListView lv_first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_client_sample);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        selectSamples=new ArrayList<>();
        selectClientSampleAdapter=new SelectClientSampleAdapter(this,selectSamples);
        lv_first.setAdapter(selectClientSampleAdapter);
        lv_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectSample selectSample=selectSamples.get(position);
                Intent intent = new Intent();
                intent.putExtra("data", selectSample);
                setResult(0, intent);
                finish();
            }
        });
        getFirstCategory();
    }
    private void getFirstCategory() {

        NetUtils.getInstance().get(MyApp.token, Api.Sample.getListForCustmerSelect, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            SelectSample selectSample=new Gson().fromJson(item.toString(), SelectSample.class);
                            selectSamples.add(selectSample);
                        }
                        selectClientSampleAdapter.notifyDataSetChanged();
                    }else {
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
}
