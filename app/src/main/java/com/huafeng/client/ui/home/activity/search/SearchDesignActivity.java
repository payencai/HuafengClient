package com.huafeng.client.ui.home.activity.search;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.design.DesignDetailActivity;
import com.huafeng.client.ui.home.adapter.ModelDesignAdapter;
import com.huafeng.client.ui.home.model.ModelDesign;
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

public class SearchDesignActivity extends BaseActivity {
    @BindView(R.id.rv_design)
    RecyclerView rv_design;
    ModelDesignAdapter mModelDesignAdapter;
    List<ModelDesign> mModelDesigns;
    int page = 1;
    boolean isLoadMore = false;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.et_name)
    EditText et_name;
    String patternMakingNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_design);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    private void initView() {

        mModelDesigns = new ArrayList<>();
        mModelDesignAdapter = new ModelDesignAdapter(R.layout.item_design_list, mModelDesigns);
        mModelDesignAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ModelDesign modelDesign= (ModelDesign) adapter.getItem(position);
                Intent intent=new Intent(SearchDesignActivity.this, DesignDetailActivity.class);
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
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rv_design.setLayoutManager(new LinearLayoutManager(this));
        rv_design.setAdapter(mModelDesignAdapter);
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });

        et_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {

                    patternMakingNo = et_name.getText().toString().trim();

                    if (TextUtils.isEmpty(patternMakingNo)) {
                        Toast.makeText(SearchDesignActivity.this, "请输入搜索关键字", Toast.LENGTH_SHORT).show();

                        return false;
                    }
                    refresh();
                    // 搜索功能主体

                    return true;
                }
                return false;
            }
        });
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 if(s.toString().length()==0){
                     patternMakingNo="";
                     refresh();
                 }
            }
        });

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
        if(!TextUtils.isEmpty(patternMakingNo)){
                httpParams.put("searchCondition",patternMakingNo);
        }
        NetUtils.getInstance().get(MyApp.token, Api.Pattern.getList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
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
                            mModelDesigns.add(modelDesign);
                            modelDesigns.add(modelDesign);
                        }
                        if(isLoadMore){
                            isLoadMore=false;
                            if(data.length()>0){
                                mModelDesignAdapter.addData(modelDesigns);
                                mModelDesignAdapter.loadMoreComplete();
                            }else{
                                mModelDesignAdapter.loadMoreEnd(true);
                            }
                        }else{
                            mModelDesignAdapter.setNewData(mModelDesigns);
                        }
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
