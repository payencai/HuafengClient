package com.huafeng.client.ui.home.activity.select;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.design.SeeProcessModuleActivity;
import com.huafeng.client.ui.home.activity.design.SeeRawModuleActivity;
import com.huafeng.client.ui.home.activity.design.SeeSizeModuleActivity;
import com.huafeng.client.ui.home.activity.sample.BigModel;
import com.huafeng.client.ui.home.activity.sample.SelectBigModelAdapter;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectBigModelActivity extends BaseActivity {
    @BindView(R.id.lv_size)
    ListView lv_size;
    List<BigModel> bigModels;
    SelectBigModelAdapter selectBigModelAdapter;
    int type=1;
    int moduleId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_size_model);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        type=getIntent().getIntExtra("type",0);
        initView();
    }
    @OnClick({R.id.back,R.id.tv_confirm})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.tv_confirm:
                Intent intent=new Intent();
                intent.putExtra("id",moduleId);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    private void initView() {
        bigModels=new ArrayList<>();
        selectBigModelAdapter=new SelectBigModelAdapter(this,bigModels);
        selectBigModelAdapter.setOnSeeLisenter(new SelectBigModelAdapter.OnSeeLisenter() {
            @Override
            public void onSee(int pos) {
                BigModel bigModel =bigModels.get(pos);
                Intent intent;
                if(type==3){
                    intent=new Intent(SelectBigModelActivity.this, SeeProcessModuleActivity.class);
                    intent.putExtra("id",bigModel.getId());
                    startActivity(intent);
                }else if(type==2){
                    intent=new Intent(SelectBigModelActivity.this, SeeRawModuleActivity.class);
                    intent.putExtra("id",bigModel.getId());
                    startActivity(intent);
                }else{
                    intent=new Intent(SelectBigModelActivity.this, SeeSizeModuleActivity.class);
                    intent.putExtra("id",bigModel.getId());
                    startActivity(intent);
                }


            }
        });

        lv_size.setAdapter(selectBigModelAdapter);
        lv_size.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectBigModelAdapter.setPos(position);
                moduleId=bigModels.get(position).getId();
                selectBigModelAdapter.notifyDataSetChanged();
            }
        });
        getModel();
    }
    private void getModel(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("type",type);
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getModels,httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            BigModel bigModel=new Gson().fromJson(item.toString(),BigModel.class);
                            bigModels.add(bigModel);
                        }
                        if(bigModels.size()>0)
                            moduleId=bigModels.get(0).getId();
                        selectBigModelAdapter.notifyDataSetChanged();
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
