package com.huafeng.client.ui.home.activity.client;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasicFragment extends Fragment {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_addr1)
    TextView tv_addr1;
    @BindView(R.id.tv_addr2)
    TextView tv_addr2;
    @BindView(R.id.tv_man)
    TextView tv_man;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    @BindView(R.id.iv_head)
    ImageView ivhead;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_edit)
    TextView tv_edit;
    int id;
    ClientDetail clientDetail;

    public BasicFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic, container, false);
        ButterKnife.bind(this, view);
        id = getArguments().getInt("id");
        initView();
        return view;
    }

    private void initView() {
        getClientDetail();
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),EditClientActivity.class);
                intent.putExtra("data",clientDetail);
                startActivityForResult(intent,2);
            }
        });
    }
    @OnClick({R.id.rl_phone, R.id.rl_addr})
    void OnClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rl_phone:
                intent=new Intent(getContext(),ClientManaPhoneActivity.class);
                intent.putExtra("id",0);
                intent.putExtra("phone",tv_phone.getText().toString());
                startActivityForResult(intent,1);
                break;
            case R.id.rl_addr:
                intent=new Intent(getContext(),ClientManaAddressActivity.class);
                intent.putExtra("id",0);
                intent.putExtra("address",tv_addr2.getText().toString());
                startActivityForResult(intent,2);
                break;

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getClientDetail();
    }

    private void getClientDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Client.getDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        clientDetail = new Gson().fromJson(data.toString(), ClientDetail.class);
                        initData();
                    }else{
                        ToastUtils.showShort("你没有权限");
                        getActivity().finish();
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

    private void initData() {
        tv_city.setText(clientDetail.getClientRecord().getProvince()+clientDetail.getClientRecord().getCity());
        tv_name.setText(clientDetail.getClientRecord().getName());
        tv_addr1.setText(clientDetail.getClientRecord().getContactAddress());
        tv_addr2.setText(clientDetail.getClientRecord().getReceivingAddress());
        tv_phone.setText(clientDetail.getClientRecord().getContactNumber());
        tv_man.setText(clientDetail.getClientRecord().getPrincipal());
        tv_time.setText(clientDetail.getClientRecord().getGmtCreate());
        tv_remark.setText(clientDetail.getClientRecord().getRemarks());
        if (!TextUtils.isEmpty(clientDetail.getClientRecord().getImageUrl()))
            Glide.with(this).load(clientDetail.getClientRecord().getImageUrl()).apply(RequestOptions.circleCropTransform()).into(ivhead);
        else{
            Glide.with(this).load("http://img2.3png.com/09541447ec671987f5e015b924384b89cf5d.png").apply(RequestOptions.circleCropTransform()).into(ivhead);
        }
    }

    public static BasicFragment newInstance(int id) {
        BasicFragment basicFragment = new BasicFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        basicFragment.setArguments(bundle);
        return basicFragment;
    }
}
