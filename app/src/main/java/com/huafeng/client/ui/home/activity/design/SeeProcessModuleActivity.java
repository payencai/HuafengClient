package com.huafeng.client.ui.home.activity.design;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CollectionsUtil;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.sample.ProcesModel;
import com.huafeng.client.ui.home.activity.sample.SelectProcess;
import com.huafeng.client.ui.home.activity.sample.SelectSampleProcessAdapter;
import com.huafeng.client.view.MyListView;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeeProcessModuleActivity extends BaseActivity {
    ModuleSubmit moduleSubmit;
    List<SelectProcess> selectProcessList;
    @BindView(R.id.lv_process)
    MyListView lv_process;
    @BindView(R.id.rv_process)
    RecyclerView rv_process;
    ProcesModel procesModel;
    SelectSampleProcessAdapter selectSampleProcessAdapter;
    int id;
    List<ProcesModel.ProcessListBean> processListBeans;
    ProcessModelAdapter processModelAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_process2);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id=getIntent().getIntExtra("id",0);
        moduleSubmit=new ModuleSubmit();
        moduleSubmit.setId(id);
        initView();
    }
   private void update(){
        List<ModuleSubmit.ProcessListBean> processListBeanList=new ArrayList<>();
       for (int i = 0; i <processListBeans.size() ; i++) {
           ModuleSubmit.ProcessListBean process=new ModuleSubmit.ProcessListBean();
           process.setProcessId(processListBeans.get(i).getProcessId());
           process.setIsOutsourcing(processListBeans.get(i).getIsOutsourcing());
           process.setPrice(processListBeans.get(i).getPrice());
           processListBeanList.add(process);
       }
       moduleSubmit.setProcessList(processListBeanList);
       String json=new Gson().toJson(moduleSubmit);
       NetUtils.getInstance().post(Api.Sample.editProcessModule, json, MyApp.token, new OnMessageReceived() {
           @Override
           public void onSuccess(String response) {
               try {
                   JSONObject jsonObject=new JSONObject(response);
                   int code=jsonObject.getInt("resultCode");
                   if(code==0){
                       finish();
                   }else{
                       LogUtils.e(response);
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
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(processListBeans.size()==0){
                    ToastUtils.showLong("模板不能为空！");
                    return;

                }
                update();

            }
        });
        processListBeans=new ArrayList<>();
        selectProcessList = new ArrayList<>();
        processModelAdapter=new ProcessModelAdapter(processListBeans);
        processModelAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ProcesModel.ProcessListBean processListBean= (ProcesModel.ProcessListBean) adapter.getItem(position);

                switch (view.getId()){
                    case R.id.tv_up:
                        if(position==0){
                            return;
                        }else{
                            int uppos=position-1;
                            CollectionsUtil.swap1(processListBeans,position,uppos);
                            processModelAdapter.setNewData(processListBeans);
                        }
                        break;
                    case R.id.tv_down:
                        int len=processListBeans.size()-1;
                        if(position==len){
                            return;
                        }else{
                            int downpos=position+1;
                            CollectionsUtil.swap1(processListBeans,position,downpos);
                            processModelAdapter.setNewData(processListBeans);
                        }

                        break;
                    case R.id.tv_delete:
                        processListBeans.remove(position);
                        processModelAdapter.setNewData(processListBeans);
                        break;
                }
            }
        });
        rv_process.setLayoutManager(new LinearLayoutManager(this));
        rv_process.setAdapter(processModelAdapter);
        selectSampleProcessAdapter=new SelectSampleProcessAdapter(this,selectProcessList);
        selectSampleProcessAdapter.setOnChildSelectListener(new SelectSampleProcessAdapter.OnChildSelectListener() {
            @Override
            public void onSelect(int parent, int child) {
                SelectProcess.ProcessListBean processListBean=selectProcessList.get(parent).getProcessList().get(child);
                processListBean.setProcessName(selectProcessList.get(parent).getName());
                ProcesModel.ProcessListBean process=new ProcesModel.ProcessListBean();
                process.setId(processListBean.getId());
                process.setStageName(processListBean.getName());
                process.setProcessName(processListBean.getProcessName());
                process.setIsOutsourcing(processListBean.getIsOutsourcing());
                process.setPrice(processListBean.getPrice());
                process.setProcessId(processListBean.getId());
                processListBeans.add(process);
                processModelAdapter.setNewData(processListBeans);
            }
        });
        lv_process.setAdapter(selectSampleProcessAdapter);
        getProcess();
        getModel();
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

    private void getModel() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("moduleId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getProcessModule, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        procesModel = new Gson().fromJson(data.toString(), ProcesModel.class);
                        processListBeans.addAll(procesModel.getProcessList());
                        processModelAdapter.setNewData(processListBeans);
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
}
