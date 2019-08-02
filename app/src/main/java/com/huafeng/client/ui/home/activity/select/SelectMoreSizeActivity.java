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
import com.huafeng.client.ui.home.adapter.MoreSizeInfoAdapter;
import com.huafeng.client.ui.home.model.SizeType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectMoreSizeActivity extends BaseActivity {
    @BindView(R.id.lv_sec)
    ListView lv_size;
    List<SizeType> mSizeTypes;
    ArrayList<SizeType> mSelectSizes;
    MoreSizeInfoAdapter moreSizeInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_more_size);
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
                for (int i = 0; i <mSizeTypes.size() ; i++) {
                    if(mSizeTypes.get(i).isSelect()){
                        mSelectSizes.add(mSizeTypes.get(i));
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
            }
        });
        mSelectSizes = new ArrayList<>();
        mSizeTypes = new ArrayList<>();
        moreSizeInfoAdapter = new MoreSizeInfoAdapter(this, mSizeTypes);
        lv_size.setAdapter(moreSizeInfoAdapter);
        lv_size.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean isSelect = mSizeTypes.get(position).isSelect();
                if (isSelect) {
                    mSizeTypes.get(position).setSelect(false);
                } else {
                    mSizeTypes.get(position).setSelect(true);
                }
                moreSizeInfoAdapter.notifyDataSetChanged();
            }
        });
        getSize();
    }

    private void getSize() {

        NetUtils.getInstance().get(MyApp.token, Api.Size.getSizeType, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            SizeType firstType = new Gson().fromJson(item.toString(), SizeType.class);
                            mSizeTypes.add(firstType);
                        }
                        moreSizeInfoAdapter.notifyDataSetChanged();
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
