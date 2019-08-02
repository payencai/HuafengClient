package com.huafeng.client.ui.home.activity.design;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.search.SearchDesignActivity;
import com.huafeng.client.ui.home.adapter.FirstTypeAdapter;
import com.huafeng.client.ui.home.adapter.ModelDesignAdapter;
import com.huafeng.client.ui.home.model.FirstType;
import com.huafeng.client.ui.home.model.ModelDesign;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fj.dropdownmenu.lib.concat.DropdownI;
import fj.dropdownmenu.lib.ion.ViewInject;
import fj.dropdownmenu.lib.ion.ViewUtils;
import fj.dropdownmenu.lib.pojo.DropdownItemObject;
import fj.dropdownmenu.lib.utils.DropdownUtils;
import fj.dropdownmenu.lib.view.DropdownButton;
import fj.dropdownmenu.lib.view.DropdownColumnView;

public class ModelDesignActivity extends BaseActivity implements DropdownI.SingleRow, DropdownI.RandomView {

    int page = 1;
    boolean isLoadMore = false;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.mask)
    View mask;
    @ViewInject(R.id.chooseType)
    @BindView(R.id.chooseType)
    DropdownButton chooseType;
    @ViewInject(R.id.lvType)
    @BindView(R.id.lvType)
    DropdownColumnView lvType;
    @ViewInject(R.id.chooseProgress)
    @BindView(R.id.chooseProgress)
    DropdownButton chooseProgress;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @ViewInject(R.id.lvProgress)
    @BindView(R.id.lvProgress)
    DropdownColumnView lvProgress;
    @BindView(R.id.rv_design)
    RecyclerView rv_design;
    ModelDesignAdapter mModelDesignAdapter;
    List<ModelDesign> mModelDesigns;
    List<DropdownItemObject> mProgressList;
    List<DropdownItemObject> mLeftList;
    List<DropdownItemObject> mRightList;
    List<FirstType> firstTypes;
    List<FirstType> secTypes;
    int status = -2;
    int category1Id = -2;
    int category2Id = -2;
    String firstName;
    String secName;
    FirstTypeAdapter firstTypeAdapter;
    FirstTypeAdapter mSecTypeAdapter;
    List<Integer> statusList;
    KProgressHUD kProgressHUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_design);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        kProgressHUD= KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中")
                .setCancellable(false)
                .show();

        initView();
    }
    private void getAuth() {

        NetUtils.getInstance().get(MyApp.token, Api.Authority.getMyAuthority, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            int authId = data.getInt(i);
                            if(authId==1011||authId==1012){
                                iv_add.setVisibility(View.VISIBLE);
                            }
                        }
                    } else {

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
    @OnClick({R.id.iv_add,R.id.iv_back,R.id.iv_search})
    void onClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.iv_search:
                startActivity(new Intent(ModelDesignActivity.this, SearchDesignActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add:
                startActivityForResult(new Intent(ModelDesignActivity.this, AddModelDesignActivity.class),2);
                break;
        }
    }
    private void refresh(){
        page=1;
        mModelDesigns.clear();
        mModelDesignAdapter.setNewData(mModelDesigns);
        getData();
    }
    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        if (status > -2) {
            httpParams.put("status", status);
        }
        if (category1Id > 0) {
            httpParams.put("category1Id", category1Id);
        }
        if (category2Id > 0) {
            httpParams.put("category2Id", category2Id);
        }
        NetUtils.getInstance().get(MyApp.token, Api.Pattern.getList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
                if(kProgressHUD!=null&&kProgressHUD.isShowing()){
                    kProgressHUD.dismiss();
                }
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        jsonObject=jsonObject.getJSONObject("data");
                        List<ModelDesign> modelDesigns=new ArrayList<>();
                        JSONArray data=jsonObject.getJSONArray("beanList");
                        for (int i = 0; i <data.length() ; i++) {
                            JSONObject item=data.getJSONObject(i);
                            ModelDesign modelDesign=new Gson().fromJson(item.toString(),ModelDesign.class);
                            modelDesigns.add(modelDesign);
                        }
                        mModelDesignAdapter.addData(modelDesigns);
                        if(isLoadMore){
                            isLoadMore=false;
                            if(data.length()>0){
                                mModelDesignAdapter.loadMoreComplete();
                            }else{
                                mModelDesignAdapter.loadMoreEnd(true);
                            }
                        }else{
                            mModelDesignAdapter.loadMoreComplete();
                        }

                    }else{
                        ToastUtils.showShort("你没有该权限！");
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

    //获取任务进度
    private void getWorkProgress() {
        DropdownItemObject typeObj = new DropdownItemObject(-2, "全部", "全部");
        mProgressList.add(typeObj);
        NetUtils.getInstance().get(MyApp.token, Api.Pattern.getFlowStatus, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);

                            DropdownItemObject dropdownItemObject = new DropdownItemObject(item.getInt("id"), item.getString("name"), item.getString("name"));
                            mProgressList.add(dropdownItemObject);
                        }
                        lvProgress.setSingleRow(ModelDesignActivity.this)
                                .setSingleRowList(mProgressList, -2)  //单列数据
                                .setButton(chooseProgress) //按钮
                                .show();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refresh();
    }

    private void initSelectMenu() {
        mProgressList = new ArrayList<>();
        mLeftList = new ArrayList<>();
        mRightList = new ArrayList<>();
        DropdownUtils.init(this, mask);
        ViewUtils.injectViews(this, mask);
        getWorkProgress();
        chooseType.setText("全部");
        lvType.setRandom(this)
                .setRandomView(R.layout.menu_style)
                .setButton(chooseType) //按钮
                .show();
    }

    private void initView() {
        getAuth();
        initSelectMenu();
        mModelDesigns = new ArrayList<>();
        statusList=new ArrayList<>();
        mModelDesignAdapter = new ModelDesignAdapter(R.layout.item_design_list, mModelDesigns);
        mModelDesignAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                ModelDesign modelDesign= (ModelDesign) adapter.getItem(position);
                Intent intent=new Intent(ModelDesignActivity.this, DesignDetailActivity.class);
                intent.putExtra("id",modelDesign.getId());
                startActivityForResult(intent,1);
            }
        });
        mModelDesignAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                isLoadMore=true;
                page++;
                getData();
            }
        },rv_design);
        rv_design.setLayoutManager(new LinearLayoutManager(this));
        rv_design.setAdapter(mModelDesignAdapter);
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        getData();
    }

    @Override
    public void onSingleChanged(DropdownItemObject dropdownItemObject) {
         if(dropdownItemObject.id>-2){
             status=dropdownItemObject.id;
         }else{
             status=-2;
         }
        page=1;
        mModelDesigns.clear();
        mModelDesignAdapter.setNewData(mModelDesigns);
        getData();
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
                page=1;
                mModelDesigns.clear();
                mModelDesignAdapter.setNewData(mModelDesigns);
                if(position>0){
                    firstName=firstTypes.get(position).getName();
                    category1Id=firstTypes.get(position).getId();
                    category2Id=-1;
                    chooseType.setText(firstName);
                    getSecCategory(firstTypes.get(position).getId());
                }else{
                    category1Id=-1;
                    category2Id=-1;
                    secTypes.clear();
                    final FirstType firstType=new FirstType();
                    firstType.setName("全部");
                    chooseType.setText("全部");
                    secTypes.add(firstType);
                    mSecTypeAdapter.notifyDataSetChanged();

                }
                getData();

            }
        });
        lv_right.setAdapter(mSecTypeAdapter);
        lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSecTypeAdapter.setPos(position);
                mSecTypeAdapter.notifyDataSetChanged();
                page=1;
                mModelDesigns.clear();
                mModelDesignAdapter.setNewData(mModelDesigns);
                if(position==0){
                    category2Id=-1;
                    if(category1Id>0)
                       chooseType.setText(firstName);
                    else{
                        chooseType.setText("全部");
                    }
                }else{
                    category2Id=secTypes.get(position).getId();
                    secName=secTypes.get(position).getName();
                    chooseType.setText(firstName+secName);
                }
                getData();

            }
        });
        getFirstCategory();
    }
}
