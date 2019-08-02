package com.huafeng.client.ui.home.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.design.AddModelDataActivity;
import com.huafeng.client.ui.home.activity.search.SearchSampleActivity;
import com.huafeng.client.ui.home.adapter.FirstTypeAdapter;
import com.huafeng.client.ui.home.adapter.ModelDataAdapter;
import com.huafeng.client.ui.home.model.FirstType;
import com.huafeng.client.ui.home.model.ModelData;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fj.dropdownmenu.lib.concat.DropdownI;
import fj.dropdownmenu.lib.ion.ViewInject;
import fj.dropdownmenu.lib.ion.ViewUtils;
import fj.dropdownmenu.lib.utils.DropdownUtils;
import fj.dropdownmenu.lib.view.DropdownButton;
import fj.dropdownmenu.lib.view.DropdownColumnView;

public class ModelDataActivity extends BaseActivity implements  DropdownI.RandomView{
    @BindView(R.id.rv_design)
    RecyclerView rv_model;
    ModelDataAdapter mModelDataAdapter;
    List<ModelData> mModelData;
    @BindView(R.id.mask)
    View mask;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @ViewInject(R.id.btn_type)
    @BindView(R.id.btn_type)
    DropdownButton btn_type;
    @ViewInject(R.id.lvType)
    @BindView(R.id.lvType)
    DropdownColumnView lvType;

    FirstTypeAdapter firstTypeAdapter;
    FirstTypeAdapter mSecTypeAdapter;
    int page = 1;
    int category1Id = -2;
    int category2Id = -2;
    String firstName;
    String secName;
    List<FirstType> firstTypes;
    List<FirstType> secTypes;
    boolean isLoadMore = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_data);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        showLoading();
        initView();
    }
    KProgressHUD kProgressHUD;
    private void showLoading(){
        kProgressHUD= KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(1000)
                .setCancellable(true)
                .setDimAmount(0.5f)
                .show();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                kProgressHUD.dismiss();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 500);//3秒后执行TimeTask的run方法
    }
    @OnClick({R.id.iv_back,R.id.iv_add,R.id.iv_search})
    void onClick(View view){
        switch (view.getId()){
            case R.id.iv_search:
                startActivityForResult(new Intent(ModelDataActivity.this, SearchSampleActivity.class),2);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add:
                startActivityForResult(new Intent(ModelDataActivity.this, AddModelDataActivity.class),1);
                break;

        }
    }
    private void initView() {
        DropdownUtils.init(this, mask);
        ViewUtils.injectViews(this, mask);
        btn_type.setText("全部");
        lvType.setRandom(this)
                .setRandomView(R.layout.menu_style)
                .setButton(btn_type) //按钮
                .show();
        mModelData=new ArrayList<>();
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        mModelDataAdapter=new ModelDataAdapter(R.layout.item_model_data,mModelData);
        mModelDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                ModelData modelDesign= (ModelData) adapter.getItem(position);
                Intent intent=new Intent(ModelDataActivity.this, ModelDataDetailActivity.class);
                intent.putExtra("id",modelDesign.getId());
                startActivityForResult(intent,1);
            }
        });
        rv_model.setLayoutManager(new LinearLayoutManager(this));
        rv_model.setAdapter(mModelDataAdapter);
        getData();
    }

    private void refresh() {
        page=1;
        mModelData.clear();
        mModelDataAdapter.setNewData(mModelData);
        getData();
    }
    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        httpParams.put("imageNecessary",1);
        if (category1Id > 0) {
            httpParams.put("category1Id", category1Id);
        }
        if (category2Id > 0) {
            httpParams.put("category2Id", category2Id);
        }
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        jsonObject=jsonObject.getJSONObject("data");
                        List<ModelData> modelDesigns=new ArrayList<>();
                        JSONArray data=jsonObject.getJSONArray("beanList");
                        for (int i = 0; i <data.length() ; i++) {
                            JSONObject item=data.getJSONObject(i);
                            ModelData modelDesign=new Gson().fromJson(item.toString(),ModelData.class);
                            mModelData.add(modelDesign);
                            modelDesigns.add(modelDesign);
                        }
                        if(isLoadMore){
                            isLoadMore=false;
                            if(data.length()>0){
                                mModelDataAdapter.addData(modelDesigns);
                                mModelDataAdapter.loadMoreComplete();
                            }else{
                                mModelDataAdapter.loadMoreEnd(true);
                            }
                        }else{
                            mModelDataAdapter.setNewData(mModelData);
                        }

                    }
                    else{
                        ToastUtils.showShort("你没有该权限");
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
    @Override
    public void onRandom(View view) {
        firstTypes=new ArrayList<>();
        secTypes=new ArrayList<>();
        final FirstType firstType=new FirstType();
        firstType.setName("全部");
        firstTypes.add(firstType);
        secTypes.add(firstType);
        ListView lv_left=view.findViewById(R.id.lv_left);
        ListView lv_right=view.findViewById(R.id.lv_right);
        firstTypeAdapter=new FirstTypeAdapter(this,firstTypes);
        mSecTypeAdapter=new FirstTypeAdapter(this,secTypes);
        lv_left.setAdapter(firstTypeAdapter);
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                firstTypeAdapter.setPos(position);
                firstTypeAdapter.notifyDataSetChanged();
                mSecTypeAdapter.setPos(0);
                mSecTypeAdapter.notifyDataSetChanged();

                if(position>0){
                    firstName=firstTypes.get(position).getName();
                    category1Id=firstTypes.get(position).getId();
                    category2Id=-1;
                    btn_type.setText(firstName);
                    getSecCategory(firstTypes.get(position).getId());
                }else{
                    category1Id=-1;
                    category2Id=-1;
                    secTypes.clear();
                    final FirstType firstType=new FirstType();
                    firstType.setName("全部");
                    btn_type.setText("全部");
                    secTypes.add(firstType);
                    mSecTypeAdapter.notifyDataSetChanged();

                }
                refresh();

            }
        });
        lv_right.setAdapter(mSecTypeAdapter);
        lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSecTypeAdapter.setPos(position);
                mSecTypeAdapter.notifyDataSetChanged();

                if(position==0){
                    category2Id=-1;
                    if(category1Id>0)
                        btn_type.setText(firstName);
                    else{
                        btn_type.setText("全部");
                    }
                }else{
                    category2Id=secTypes.get(position).getId();
                    secName=secTypes.get(position).getName();
                    btn_type.setText(firstName+secName);
                }
                refresh();

            }
        });
        getFirstCategory();
    }
    private void getFirstCategory() {

        NetUtils.getInstance().get(MyApp.token, Api.Product.getFirstCategorys, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            FirstType firstType=new Gson().fromJson(item.toString(),FirstType.class);
                            firstTypes.add(firstType);
                        }
                        firstTypeAdapter.notifyDataSetChanged();
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

    private void getSecCategory(int id) {
        secTypes.clear();
        final FirstType firstType=new FirstType();
        firstType.setName("全部");
        secTypes.add(firstType);
        final HttpParams params=new HttpParams();
        params.put("firstCategoryId",id);
        NetUtils.getInstance().get(MyApp.token, Api.Product.getSecondCategorysByFirst, params,new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            FirstType firstType=new Gson().fromJson(item.toString(),FirstType.class);
                            secTypes.add(firstType);
                        }
                        mSecTypeAdapter.notifyDataSetChanged();
//                        if(data.length()==0){
//                            page=1;
//                            mModelDesigns.clear();
//                            category2Id=-1;
//                            mModelDesignAdapter.setNewData(mModelDesigns);
//                            getData();
//                        }
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
