package com.huafeng.client.ui.home.activity.order;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.huafeng.client.ui.home.activity.select.SelectOrderClothesActivity;
import com.huafeng.client.ui.home.adapter.OrderInputSizeAdapter;
import com.huafeng.client.ui.home.adapter.SelectClothesSizeAdapter;
import com.huafeng.client.ui.home.model.Employee;
import com.huafeng.client.view.MyGridView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.lzy.okgo.model.HttpParams;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateOrderActivity extends BaseActivity implements OnDateSetListener {
    OrderDetail orderDetail;
    int id;
    OrderInputSizeAdapter orderInputSizeAdapter;
    @BindView(R.id.gv_size)
    MyGridView gv_size;
    @BindView(R.id.gv_clothes)
    MyGridView gv_clothes;
    @BindView(R.id.gv_use)
    MyGridView gv_use;
    @BindView(R.id.tv_cloth)
    TextView tv_cloth;
    @BindView(R.id.tv_single)
    TextView tv_single;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_total)
    TextView tv_total;
    @BindView(R.id.tv_month)
    TextView tv_month;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_employ)
    TextView tv_employ;
    @BindView(R.id.rl_employ)
    RelativeLayout rl_employ;
    @BindView(R.id.rv_process)
    RecyclerView rv_process;
    OrderClothes orderClothes;
    Inventory inventory;
    SelectClothesSizeAdapter selectClothesSizeAdapter;
    OrderClothesInputAdapter orderClothesInputAdapter;
    List<ClothesSize> clothesSizes;
    List<ClothesUse> clothesUses;
    List<OrderSection> orderSections;
    List<OrderDetail.OrderSampleVoBean.SizeListBean> sizeListBeans;
    OrderSectionAdapter orderSectionAdapter;
    TimePickerDialog mDialogYearMonth;
    TimePickerDialog mDialogYearMonthDay;
    String month;
    String estimatedTimeOfFinishment;
    double quantity;
    OrderSubmit orderSubmit;

    SimpleDateFormat sf_time = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sf_month = new SimpleDateFormat("yyyy-MM");
    List<Employee> employees;
    ArrayList<String> employList = new ArrayList<>();
    Employee employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        orderSubmit = new OrderSubmit();
        id = getIntent().getIntExtra("id", 0);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            orderClothes = (OrderClothes) data.getSerializableExtra("data");
            tv_cloth.setText(orderClothes.getRawMaterialName());
            clothesSizes.clear();
            clothesUses.clear();
            getInventory();
        }
    }

    private void initView() {
        Calendar calendar = Calendar.getInstance();
        long two = 30 * 1000 * 60 * 60 * 24L;
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 60);
        mDialogYearMonth = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setCyclic(false)
                .setTitleStringId("选择月份")
                .setMinMillseconds(System.currentTimeMillis())
                .setMaxMillseconds(System.currentTimeMillis() + two)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.color_orange))
                .setCallBack(this)
                .build();

        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("预期时间")
                .setMinMillseconds(System.currentTimeMillis())
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.color_orange))
                .setCallBack(this)
                .build();
        sizeListBeans = new ArrayList<>();
        orderSections = new ArrayList<>();
        employees = new ArrayList<>();
        clothesSizes = new ArrayList<>();
        clothesUses = new ArrayList<>();
        orderClothesInputAdapter = new OrderClothesInputAdapter(this, clothesUses);
        orderClothesInputAdapter.setOnItemSelectListener(new OrderClothesInputAdapter.OnItemSelectListener() {
            @Override
            public void calcloth() {
                double total = 0;
                for (int i = 0; i < clothesUses.size(); i++) {
                    if (!TextUtils.isEmpty(clothesUses.get(i).getInput())) {
                        total = total + Double.parseDouble(clothesUses.get(i).getInput());
                    }
                }
                if (quantity > 0 && total > 0) {
                    double value = total / quantity;
                    tv_single.setText(formatDouble(value) + "");
                }

                tv_total.setText(total + "");
            }
        });
        selectClothesSizeAdapter = new SelectClothesSizeAdapter(this, clothesSizes);
        gv_clothes.setAdapter(selectClothesSizeAdapter);
        gv_use.setAdapter(orderClothesInputAdapter);
        gv_clothes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (clothesSizes.get(position).isCheck()) {
                    clothesSizes.get(position).setCheck(false);
                    selectClothesSizeAdapter.notifyDataSetChanged();
                    for (int i = 0; i < clothesUses.size(); i++) {
                        if (position == clothesUses.get(i).getId()) {
                            clothesUses.remove(i);
                            break;
                        }
                    }
                    orderClothesInputAdapter.notifyDataSetChanged();
                } else {
                    clothesSizes.get(position).setCheck(true);
                    ClothesUse clothesUse = new ClothesUse();
                    clothesUse.setInput(clothesSizes.get(position).getName());
                    clothesUse.setName(clothesSizes.get(position).getName());
                    clothesUse.setId(position);
                    clothesUses.add(clothesUse);
                    orderClothesInputAdapter.notifyDataSetChanged();
                    selectClothesSizeAdapter.notifyDataSetChanged();
                }
            }
        });
        getDetail();
    }

    public String formatDouble(double s) {
        DecimalFormat fmt = new DecimalFormat("##0.0");
        return fmt.format(s);
    }

    private void getProcessInput() {
        List<OrderSubmit.ProductProcessListBean> productProcessListBeans = new ArrayList<>();
        for (int i = 0; i < orderSections.size(); i++) {
            OrderSection orderSection = orderSections.get(i);
            OrderDetail.OrderSampleVoBean.ProductProcessListBean processListBean = orderSection.t;
            if (processListBean != null) {
                OrderSubmit.ProductProcessListBean productProcessListBean = new OrderSubmit.ProductProcessListBean();
                productProcessListBean.setIsOutsourcing(processListBean.getIsOutsourcing());
                productProcessListBean.setProcessId(processListBean.getProcessId());
                productProcessListBeans.add(productProcessListBean);
            }
        }
        productProcessListBeans.remove(0);
        orderSubmit.setProductProcessList(productProcessListBeans);

    }

    private void getClothesInput() {
        OrderSubmit.ProductionOrderMaterialConsumeAccountParamBean paramBean = new OrderSubmit.ProductionOrderMaterialConsumeAccountParamBean();
        paramBean.setOrderSampleMaterialId(orderClothes.getId());
        String param = "";
        for (int i = 0; i < clothesUses.size(); i++) {
            if (!TextUtils.isEmpty(clothesUses.get(i).getInput())) {
                String value = clothesUses.get(i).getName() + ":" + clothesUses.get(i).getInput();
                param = param + "," + value;
            }
        }
        if (!TextUtils.isEmpty(param)) {
            paramBean.setClothConsume(param.substring(1));
        }
        orderSubmit.setProductionOrderMaterialConsumeAccountParam(paramBean);
    }

    private void getSizeInput() {
        List<OrderSubmit.SizeQuantityListBean> sizeQuantityListBeans = new ArrayList<>();
        for (int i = 0; i < sizeListBeans.size(); i++) {
            OrderDetail.OrderSampleVoBean.SizeListBean sizeListBean = sizeListBeans.get(i);
            if (!TextUtils.isEmpty(sizeListBean.getInput())) {
                OrderSubmit.SizeQuantityListBean sizeQuantityListBean = new OrderSubmit.SizeQuantityListBean();
                sizeQuantityListBean.setQuantity(Double.parseDouble(sizeListBean.getInput()));
                sizeQuantityListBean.setSizeId(sizeListBean.getSizeId());
                sizeQuantityListBeans.add(sizeQuantityListBean);
            }
        }
        orderSubmit.setSizeQuantityList(sizeQuantityListBeans);
    }

    private boolean checkInput() {
        boolean isOk = true;
        if (TextUtils.isEmpty(month)) {
            isOk = false;
            ToastUtils.showShort("请选择月份");
            return isOk;
        }
        if (TextUtils.isEmpty(estimatedTimeOfFinishment)) {
            isOk = false;
            ToastUtils.showShort("请选择预期时间");
            return isOk;
        }
        if (orderClothes == null) {
            isOk = false;
            ToastUtils.showShort("请选择布料");
            return isOk;
        }
        if (inventory==null||TextUtils.isEmpty(inventory.getClothQuantity())) {
            isOk = false;
            ToastUtils.showShort("该布料暂无库存，无法创建");
            return isOk;
        }
        for (int i = 0; i < sizeListBeans.size(); i++) {
            OrderDetail.OrderSampleVoBean.SizeListBean sizeListBean = sizeListBeans.get(i);
            if (TextUtils.isEmpty(sizeListBean.getInput())) {
                isOk = false;
                ToastUtils.showShort("裁片有空未输入！");
                break;
            }
        }
        if(orderDetail.getOrderSampleVo().getProductProcessList().size()>1){
            if(employee==null){
                isOk=false;
                ToastUtils.showShort("请选择负责人");
                return isOk;
            }
        }
        if (clothesUses.size() == 0) {
            isOk = false;
            ToastUtils.showShort("至少选择一个布料码数");
            return isOk;
        }
        for (int i = 0; i < clothesUses.size(); i++) {
            if (TextUtils.isEmpty(clothesUses.get(i).getInput())) {
                isOk = false;
                ToastUtils.showShort("布料码数有空未输入！");
                break;
            }
        }
        return isOk;
    }

    private void submit() {
        String json = new Gson().toJson(orderSubmit);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Production.create, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("创建成功");
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
                            Employee emp = new Gson().fromJson(item.toString(), Employee.class);
                            if(i==0){
                                employee=emp;
                            }
                            employees.add(emp);
                            employList.add(emp.getName());
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
                tv_employ.setText(employList.get(loopView.getSelectedItem()));
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

    @OnClick({R.id.rl_clothes, R.id.back, R.id.rl_month, R.id.rl_time, R.id.tv_add, R.id.rl_employ})
    void OnClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.rl_employ:
                showEmployDialog();
                break;
            case R.id.tv_add:
                if (checkInput()) {
                    orderSubmit.setQuantity(quantity);
                    orderSubmit.setOrderId(orderDetail.getOrders().getId());
                    orderSubmit.setMonth(month);
                    orderSubmit.setNextflowPrincipalId(employee.getId());
                    orderSubmit.setEstimatedTimeOfFinishment(estimatedTimeOfFinishment);
                    getProcessInput();
                    getSizeInput();
                    getClothesInput();
                    submit();
                }
                break;
            case R.id.rl_time:
                mDialogYearMonthDay.show(getSupportFragmentManager(), "b");
                break;
            case R.id.rl_clothes:
                Intent intent = new Intent(CreateOrderActivity.this, SelectOrderClothesActivity.class);
                intent.putExtra("id", orderDetail.getOrders().getId());
                startActivityForResult(intent, 1);
                break;
            case R.id.rl_month:
                mDialogYearMonth.show(getSupportFragmentManager(), "a");
                break;
            case R.id.back:
                finish();
                break;

        }
    }

    private void getInventory() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("materialId", orderClothes.getRawMaterialId());
        NetUtils.getInstance().get(MyApp.token, Api.Inventory.getRaw, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        inventory = new Gson().fromJson(data.toString(), Inventory.class);
                        String clothQuantity = inventory.getClothQuantity();
                        if (!TextUtils.isEmpty(clothQuantity)) {
                            String[] clothes = clothQuantity.split(",");
                            for (int i = 0; i < clothes.length; i++) {
                                double num= Double.parseDouble(clothes[i]);
                                ClothesSize clothesSize = new ClothesSize();
                                clothesSize.setName(clothes[i]);
                                if(num>0){
                                    clothesSizes.add(clothesSize);
                                }
                            }
                            selectClothesSizeAdapter.notifyDataSetChanged();
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

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Order.getDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        orderDetail = new Gson().fromJson(data.toString(), OrderDetail.class);
                        initData();
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
        sizeListBeans.addAll(orderDetail.getOrderSampleVo().getSizeList());
        orderInputSizeAdapter = new OrderInputSizeAdapter(this, sizeListBeans);
        orderInputSizeAdapter.setOnItemSelectListener(new OrderInputSizeAdapter.OnItemSelectListener() {
            @Override
            public void cal() {
                double total = 0;
                for (int i = 0; i < sizeListBeans.size(); i++) {
                    OrderDetail.OrderSampleVoBean.SizeListBean sizeListBean = sizeListBeans.get(i);
                    if (!TextUtils.isEmpty(sizeListBean.getInput())) {
                        total = total + Double.parseDouble(sizeListBean.getInput());
                    }
                }
                quantity = total;
                tv_num.setText(total + "");
            }
        });
        gv_size.setAdapter(orderInputSizeAdapter);
        orderSubmit.setOrderId(orderDetail.getOrders().getId());
        OrderDetail.OrderSampleVoBean.ProductProcessListBean old = orderDetail.getOrderSampleVo().getProductProcessList().get(0);
        orderSections.add(new OrderSection(true, old.getStageName()));
        old.setHeader(true);
        orderSections.add(new OrderSection(old));
        for (int i = 1; i < orderDetail.getOrderSampleVo().getProductProcessList().size(); i++) {
            OrderDetail.OrderSampleVoBean.ProductProcessListBean current = orderDetail.getOrderSampleVo().getProductProcessList().get(i);
            if (old.getStageId() != current.getStageId()) {
                current.setHeader(true);
                orderSections.add(new OrderSection(true, current.getStageName()));
                orderSections.add(new OrderSection(current));
                old = current;
            } else {
                orderSections.add(new OrderSection(current));
            }
        }
        orderSectionAdapter = new OrderSectionAdapter(orderSections);
        orderSectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        orderSectionAdapter.setOnItemSelectListener(new OrderSectionAdapter.OnItemSelectListener() {
            @Override
            public void onSelect(int pos, ImageView imageView) {
                OrderDetail.OrderSampleVoBean.ProductProcessListBean productProcessListBean = orderSections.get(pos).t;
                if (productProcessListBean.isCheck()) {
                    productProcessListBean.setCheck(false);
                    orderSections.get(pos).t.setIsOutsourcing(0);
                } else {
                    orderSections.get(pos).t.setIsOutsourcing(1);
                    productProcessListBean.setCheck(true);
                }
                orderSectionAdapter.setNewData(orderSections);

            }
        });
        rv_process.setLayoutManager(new LinearLayoutManager(this));
        rv_process.setHasFixedSize(true);
        rv_process.setNestedScrollingEnabled(false);
        rv_process.setAdapter(orderSectionAdapter);
        if (orderDetail.getOrderSampleVo().getProductProcessList().size() > 1)
            getEmploy(orderDetail.getOrderSampleVo().getProductProcessList().get(1).getStageId());
        else {
             rl_employ.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

        String tag = timePickerView.getTag();
        if ("a".equals(tag)) {
            month = getMonth(millseconds);
            tv_month.setText(month);
        } else {
            estimatedTimeOfFinishment = getTime(millseconds);
            tv_time.setText(estimatedTimeOfFinishment);
        }
    }

    public String getTime(long time) {
        Date d = new Date(time);
        return sf_time.format(d);
    }

    public String getMonth(long time) {
        Date d = new Date(time);
        return sf_month.format(d);
    }
}
