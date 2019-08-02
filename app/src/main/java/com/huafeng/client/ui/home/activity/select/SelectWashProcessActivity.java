package com.huafeng.client.ui.home.activity.select;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.design.MoreWashAdapter;
import com.huafeng.client.ui.home.model.WashProcess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectWashProcessActivity extends BaseActivity {
    MoreWashAdapter mWashProcessAdapter;
    ArrayList<WashProcess> mWashProcesses;
    ArrayList<WashProcess> mSelectWash;
    @BindView(R.id.lv_first)
    ListView lv_first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_wash_process);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {

        findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i <mWashProcesses.size() ; i++) {
                    if(mWashProcesses.get(i).isSelect()){
                        mSelectWash.add(mWashProcesses.get(i));
                    }
                }
                if(mSelectWash.size()==0){
                    ToastUtils.showShort("请选择至少一个洗水工艺");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("data", mSelectWash);
                setResult(0, intent);
                finish();
            }
        });
        mSelectWash=new ArrayList<>();
        mWashProcesses=new ArrayList<>();
        mWashProcessAdapter=new MoreWashAdapter(this,mWashProcesses);
        lv_first.setAdapter(mWashProcessAdapter);
        lv_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean isSelect = mWashProcesses.get(position).isSelect();
                if (isSelect) {
                    mWashProcesses.get(position).setSelect(false);
                } else {
                    mWashProcesses.get(position).setSelect(true);
                }
                mWashProcessAdapter.notifyDataSetChanged();

            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getFirstCategory();
    }
    private void getFirstCategory() {

        NetUtils.getInstance().get(MyApp.token, Api.Stage.getWashingProcess, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            WashProcess firstType=new Gson().fromJson(item.toString(),WashProcess.class);
                            mWashProcesses.add(firstType);
                        }
                        mWashProcessAdapter.notifyDataSetChanged();
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
