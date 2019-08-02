package com.huafeng.client.ui.home.activity.produce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.origin.ChecData;
import com.huafeng.client.ui.home.activity.origin.ChecDataAdapter;
import com.huafeng.client.view.CursorEditText;
import com.huafeng.client.view.MathUtils;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinishEntryActivity extends AppCompatActivity {
    ChecDataAdapter checDataAdapter;
    List<ChecData> checDataList;
    @BindView(R.id.rv_check)
    RecyclerView rv_check;
    @BindView(R.id.et_item2)
    CursorEditText et_item2;
    @BindView(R.id.et_item3)
    CursorEditText et_item3;
    @BindView(R.id.et_item4)
    CursorEditText et_item4;
    @BindView(R.id.et_item5)
    CursorEditText et_item5;


    int id;
    int total=0;
    FinshEntry checkEntry=new FinshEntry();
    ProduceDetail produceDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_entry);
        ButterKnife.bind(this);

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
    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Production.getProductionOrder, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        produceDetail = new Gson().fromJson(data.toString(), ProduceDetail.class);
                        initData();
                    } else {
                        String msg=jsonObject.getString("message");
                        ToastUtils.showShort(msg);
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                LogUtils.e(error);
            }
        });
    }

    private void initData() {
        for (int i = 0; i <produceDetail.getSizeQuantityList().size() ; i++) {
            if(produceDetail.getSizeQuantityList().get(i).getQuantity()<=0){
                continue;
            }
            ChecData checData=new ChecData();
            checData.setId(produceDetail.getSizeQuantityList().get(i).getSizeId());
            checData.setName(produceDetail.getSizeQuantityList().get(i).getSizeName());
            checData.setQuantity(produceDetail.getSizeQuantityList().get(i).getQuantity());
            checDataList.add(checData);
        }
        checDataAdapter.setNewData(checDataList);
    }

    private void submit() {
        checkEntry.setProductionOrderId(id);
        String json = new Gson().toJson(checkEntry);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Production.entryInventorySample, json, MyApp.token, new OnMessageReceived() {
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
        getDetail();
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

        checDataList=new ArrayList<>();
        checDataAdapter=new ChecDataAdapter(checDataList);
        rv_check.setLayoutManager(new LinearLayoutManager(this));
        rv_check.setAdapter(checDataAdapter);
        checDataAdapter.setOnEditListener(new ChecDataAdapter.OnEditListener() {
            @Override
            public void onEdit(String value) {

            }
        });

    }

    private boolean checkInput(){
        boolean isOk=true;
        if(TextUtils.isEmpty(et_item2.getEditableText().toString().trim())){
            isOk=false;
            ToastUtils.showShort("数量不能为空！");
            return isOk;
        }
        if(TextUtils.isEmpty(et_item3.getEditableText().toString().trim())){
            isOk=false;
            ToastUtils.showShort("数量不能为空！");
            return isOk;
        }
        if(TextUtils.isEmpty(et_item4.getEditableText().toString().trim())){
            isOk=false;
            ToastUtils.showShort("数量不能为空！");
            return isOk;
        }
        if(TextUtils.isEmpty(et_item5.getEditableText().toString().trim())){
            isOk=false;
            ToastUtils.showShort("数量不能为空！");
            return isOk;
        }

        List<FinshEntry.SizeListBean> sizeListBeans=new ArrayList<>();
        for (int i = 0; i <checDataAdapter.getData().size() ; i++) {
            FinshEntry.SizeListBean sizeListBean=new FinshEntry.SizeListBean();
            if(checDataAdapter.getData().get(i).getQuantity()==null){
                isOk=false;
                ToastUtils.showShort("请输入数量！");
                break;
            }
            sizeListBean.setQuantity(checDataAdapter.getData().get(i).getQuantity());
            sizeListBean.setSizeId(checDataAdapter.getData().get(i).getId());
            sizeListBeans.add(sizeListBean);
        }
        checkEntry.setSizeList(sizeListBeans);
        checkEntry.setClothQuantity(Integer.parseInt(et_item5.getEditableText().toString().trim()));
        checkEntry.setSewQuantity(Integer.parseInt(et_item4.getEditableText().toString().trim()));
        checkEntry.setRepairQuantity(Integer.parseInt(et_item3.getEditableText().toString().trim()));
        checkEntry.setWashQuantity(Integer.parseInt(et_item2.getEditableText().toString().trim()));
        return isOk;
    }
}
