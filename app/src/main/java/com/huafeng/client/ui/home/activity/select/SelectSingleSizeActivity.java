package com.huafeng.client.ui.home.activity.select;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.adapter.FirstSizeAdapter;
import com.huafeng.client.ui.home.adapter.MoreSizeAdapter;
import com.huafeng.client.ui.home.model.FirstSize;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectSingleSizeActivity extends BaseActivity {
    @BindView(R.id.lv_first)
    ListView lv_first;
    @BindView(R.id.lv_sec)
    ListView lv_sec;
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;
    FirstSizeAdapter mFristAdapter;
    MoreSizeAdapter mSecSizeAdapter;
    List<FirstSize> mFirstSizes;
    ArrayList<FirstSize.SizesBean> mSizesBeans;
    ArrayList<FirstSize.SizesBean> mSelectSizes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_single_size);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                for (int i = 0; i <mSizesBeans.size() ; i++) {
                    if(mSizesBeans.get(i).isSelect()){
                        mSelectSizes.add(mSizesBeans.get(i));
                    }
                }
                if(mSelectSizes.size()==0){
                    ToastUtils.showShort("请至少选择一个尺寸");
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra("data",mSelectSizes);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.iv_back:
                if (lv_sec.getVisibility() == View.VISIBLE) {
                    lv_sec.setVisibility(View.GONE);
                    lv_first.setVisibility(View.VISIBLE);
                    tv_confirm.setVisibility(View.GONE);
                } else {
                    finish();
                }
                break;
        }
    }

    private void initView() {
        mSelectSizes=new ArrayList<>();
        mFirstSizes = new ArrayList<>();
        mSizesBeans = new ArrayList<>();
        mFristAdapter = new FirstSizeAdapter(this, mFirstSizes);
        mSecSizeAdapter = new MoreSizeAdapter(this, mSizesBeans);
        lv_first.setAdapter(mFristAdapter);
        lv_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_confirm.setVisibility(View.VISIBLE);
                FirstSize firstSize = mFirstSizes.get(position);
                lv_first.setVisibility(View.GONE);
                lv_sec.setVisibility(View.VISIBLE);
                mSizesBeans.clear();
                mSizesBeans.addAll(firstSize.getSizes());
                mSecSizeAdapter.notifyDataSetChanged();
            }
        });
        lv_sec.setAdapter(mSecSizeAdapter);
        lv_sec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean isSelect = mSizesBeans.get(position).isSelect();
                if (isSelect) {
                    mSizesBeans.get(position).setSelect(false);
                } else {
                    mSizesBeans.get(position).setSelect(true);
                }
                mSecSizeAdapter.notifyDataSetChanged();

            }
        });
        getSize();
    }

    private void getSize() {

        NetUtils.getInstance().get(MyApp.token, Api.Size.getHierarchy, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            FirstSize firstType = new Gson().fromJson(item.toString(), FirstSize.class);
                            mFirstSizes.add(firstType);
                        }
                        mFristAdapter.notifyDataSetChanged();
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
