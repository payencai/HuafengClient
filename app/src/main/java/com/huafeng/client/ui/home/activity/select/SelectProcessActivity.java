package com.huafeng.client.ui.home.activity.select;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.sample.ChoosedProcessAdapter;
import com.huafeng.client.ui.home.activity.sample.SelectProcess;
import com.huafeng.client.ui.home.activity.sample.SelectSampleProcessAdapter;
import com.huafeng.client.view.MyGridView;
import com.huafeng.client.view.MyListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectProcessActivity extends BaseActivity {
    List<SelectProcess> selectProcessList;
    @BindView(R.id.lv_process)
    MyListView lv_process;
    @BindView(R.id.gv_select)
    MyGridView gv_select;
    SelectSampleProcessAdapter selectSampleProcessAdapter;
    ChoosedProcessAdapter choosedProcessAdapter;
    ArrayList<SelectProcess.ProcessListBean> selectProcess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_process);
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
        findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectProcess.size()==0){
                    ToastUtils.showShort("请至少选择一个流程");
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra("data",selectProcess);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        selectProcess=new ArrayList<>();
        selectProcessList = new ArrayList<>();
        choosedProcessAdapter=new ChoosedProcessAdapter(this,selectProcess);
        choosedProcessAdapter.setOnChildSelectListener(new ChoosedProcessAdapter.OnChildSelectListener() {
            @Override
            public void onSelect(int pos) {
                selectProcess.remove(pos);
                choosedProcessAdapter.notifyDataSetChanged();
            }
        });
        selectSampleProcessAdapter=new SelectSampleProcessAdapter(this,selectProcessList);
        selectSampleProcessAdapter.setOnChildSelectListener(new SelectSampleProcessAdapter.OnChildSelectListener() {
            @Override
            public void onSelect(int parent, int child) {
                SelectProcess.ProcessListBean processListBean=selectProcessList.get(parent).getProcessList().get(child);
                processListBean.setProcessName(selectProcessList.get(parent).getName());
                selectProcess.add(processListBean);
                choosedProcessAdapter.notifyDataSetChanged();
            }
        });
        lv_process.setAdapter(selectSampleProcessAdapter);
        gv_select.setAdapter(choosedProcessAdapter);
        getProcess();
    }

    private void getProcess() {
        NetUtils.getInstance().get(MyApp.token, Api.Stage.getStageHierarchy, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            SelectProcess firstType = new Gson().fromJson(item.toString(), SelectProcess.class);
                            selectProcessList.add(firstType);
                        }
                        selectSampleProcessAdapter.notifyDataSetChanged();

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
