package com.huafeng.client.ui.home.activity.origin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.view.MathUtils;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckStorageActivity extends AppCompatActivity {
    ChecDataAdapter checDataAdapter;
    List<ChecData> checDataList;
    @BindView(R.id.rv_check)
    RecyclerView rv_check;
    @BindView(R.id.et_des)
    EditText et_des;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_title)
    TextView tv_title;
    int id;
    int type=0;
    int total=0;
    String url;
    CheckEntry checkEntry=new CheckEntry();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_storage);
        ButterKnife.bind(this);
        type=getIntent().getIntExtra("type",0);
        id=getIntent().getIntExtra("id",0);
        initView();
    }
    @OnClick({R.id.iv_back, R.id.tv_submit})
    void onClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                if (checkInput()) {
                    submit();
                }
                break;

        }
    }

    private void submit() {
        checkEntry.setQuantity(total);
        String json = new Gson().toJson(checkEntry);
        LogUtils.e(json);
        NetUtils.getInstance().post(url, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("提交成功");
                        finish();
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


    private void initView() {
        initSizeInfo();
    }

    private int calTotal(){
        int count=0;
        for (int i = 0; i <checDataAdapter.getData().size() ; i++) {
            if(checDataAdapter.getData().get(i).getQuantity()!=null&& MathUtils.isInteger(checDataAdapter.getData().get(i).getQuantity()+"")){
                int value=checDataAdapter.getData().get(i).getQuantity().intValue();
                count=count+value;
            }
        }
        total=count;
        return count;
    }
    private void initSizeInfo(){
        if(type==1){
            url=Api.Inventory.finishcheck;
            tv_title.setText("盘点");
        }else{
            url=Api.Inventory.finishentry;
            tv_title.setText("入库");
        }
        checDataList=new ArrayList<>();
        checDataAdapter=new ChecDataAdapter(checDataList);
        rv_check.setLayoutManager(new LinearLayoutManager(this));
        rv_check.setAdapter(checDataAdapter);
        checDataAdapter.setOnEditListener(new ChecDataAdapter.OnEditListener() {
            @Override
            public void onEdit(String value) {
                tv_num.setText(calTotal()+"");
            }
        });
        getSizeList();
    }
    private void getSizeList() {
        HttpParams httpParams=new HttpParams();
        httpParams.put("sampleId",id);
        NetUtils.getInstance().get(MyApp.token, Api.Inventory.getSizeList,httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            ChecData checData=new Gson().fromJson(item.toString(),ChecData.class);
                            checDataList.add(checData);
                        }
                        checDataAdapter.setNewData(checDataList);
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
    private boolean checkInput(){
        boolean isOk=true;
        checkEntry.setRemarks(et_des.getEditableText().toString());
        checkEntry.setQuantity(total);
        checkEntry.setSampleId(id);
        List<CheckEntry.SizeListBean> sizeListBeans=new ArrayList<>();
        for (int i = 0; i <checDataAdapter.getData().size() ; i++) {
            CheckEntry.SizeListBean sizeListBean=new CheckEntry.SizeListBean();
            sizeListBean.setQuantity(checDataAdapter.getData().get(i).getQuantity());
            sizeListBean.setSizeId(checDataAdapter.getData().get(i).getId());
            sizeListBeans.add(sizeListBean);
        }
        checkEntry.setSizeList(sizeListBeans);
        return isOk;
    }
}
