package com.huafeng.client.ui.home.activity.produce;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
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
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.model.Employee;
import com.huafeng.client.view.CursorEditText;
import com.huafeng.client.view.MathUtils;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NextFlowActivity extends BaseActivity {
    int stageId;
    int id;
    List<Employee> employees = new ArrayList<>();
    ArrayList<String> employList = new ArrayList<>();
    Employee employee;
    @BindView(R.id.et_num)
    CursorEditText et_num;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.rl_man)
    RelativeLayout rl_man;
    @BindView(R.id.rl_num)
    RelativeLayout rl_num;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_flow);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        num = getIntent().getIntExtra("num", 0);
        stageId = getIntent().getIntExtra("stageId", stageId);
        initView();
    }

    @OnClick({R.id.iv_back, R.id.tv_submit, R.id.rl_man})
    void onClick(View view) {
        if (CheckDoubleClick.isFastDoubleClick()) {
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
            case R.id.rl_man:
                showEmployDialog();
                break;
        }
    }

    private void initView() {
        if (num == -1) {
            rl_num.setVisibility(View.GONE);
        }
        if (stageId == -1) {
            rl_man.setVisibility(View.GONE);
        } else {
            getEmploy(stageId);
        }

    }

    private boolean checkInput() {
        boolean isOk = true;
        String count = et_num.getEditableText().toString();
        if (TextUtils.isEmpty(count) && num > 0) {
            isOk = false;
            ToastUtils.showShort("请输入数量");
            return isOk;
        }
        if (num > 0 && Integer.parseInt(count) > num) {
            ToastUtils.showLong("损耗数量不能大于总量");
            isOk = false;
            return isOk;
        }
        if (stageId >= 2) {
            if (employee==null) {
                isOk = false;
                ToastUtils.showShort("请选择负责人");
                return isOk;
            }
        }

        return isOk;
    }

    private void submit() {
        Map<String, Object> params = new HashMap<>();
        String num = et_num.getEditableText().toString();
        if (!TextUtils.isEmpty(num) && MathUtils.isInteger(num)) {
            params.put("lossQuantity", Integer.parseInt(num));
        }

        if (stageId >= 2 && employee != null)
            params.put("nextflowPrincipalId", employee.getId());
        params.put("productionOrderId", id);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Production.nextFlow, json, MyApp.token, new OnMessageReceived() {
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
        LogUtils.e(json);
    }

    private void getEmploy(int status) {

        String url = "";
        if (status == 2) {
            url = Api.Employee.getEmployeesForSewing;
        } else if (status == 3) {
            url = Api.Employee.getEmployeesForWashing;
        } else if (status == 4) {
            url = Api.Employee.getEmployeesForAfterfinish;
        }
        NetUtils.getInstance().get(MyApp.token, url, new OnMessageReceived() {
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
                            Employee Em = new Gson().fromJson(item.toString(), Employee.class);
                            employees.add(Em);
                            employList.add(Em.getName());
                        }

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

    private void showEmployDialog() {
        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_pay_type, null);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        LoopView loopView = (LoopView) view.findViewById(R.id.loopView);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                tv_name.setText(employList.get(loopView.getSelectedItem()));
                employee=employees.get(loopView.getSelectedItem());
            }
        });


        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                employee = employees.get(index);
            }
        });
        // 设置原始数据
        loopView.setItems(employList);
        loopView.setNotLoop();
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
    }
}
