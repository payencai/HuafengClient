package com.huafeng.client.ui.home.activity.buy;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
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
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.model.Supplier;
import com.huafeng.client.view.MathUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyDetailSubmitActivity extends BaseActivity {

    @BindView(R.id.tv_reqest)
    TextView tv_reqest;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_order)
    TextView tv_order;
    @BindView(R.id.tv_produce)
    TextView tv_produce;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_pay)
    TextView tv_pay;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    @BindView(R.id.lv_ask)
    RecyclerView lv_ask;
    int id;
    BuyOrderDetail askDetail;
    BuySubmitAdapter buyTableAdapter;
    List<BuyOrderDetail.NoteListBean.SupplierListBean> supplierListBeans;
    List<Supplier> suppliers;
    BuyOrderSubmit buyOrderSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_detail_submit);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);

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

    int select = 0;
    int add = 0;

    private void submit() {
        String json = new Gson().toJson(buyOrderSubmit);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Purchase.submitBuyOrder, json, MyApp.token, new OnMessageReceived() {
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
    }

    private void showAddDialog(int pos) {
        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < suppliers.size(); i++) {
            list.add(suppliers.get(i).getName());
        }
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
                supplierListBeans.get(pos).setShowAdd(false);
                BuyOrderDetail.NoteListBean.SupplierListBean supplierListBean = new BuyOrderDetail.NoteListBean.SupplierListBean();
                supplierListBean.setId(suppliers.get(add).getId());
                supplierListBean.setSupplierName(suppliers.get(add).getName());
                supplierListBean.setNoteId(supplierListBeans.get(pos).getNoteId());
                supplierListBean.setShowDel(true);
                supplierListBean.setShowAdd(true);
                int position = pos + 1;
                supplierListBeans.add(position, supplierListBean);
                buyTableAdapter.setNewData(supplierListBeans);
            }
        });


        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                add = index;
            }
        });
        // 设置原始数据
        loopView.setItems(list);
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

    private void showSelectDialog(int pos, TextView tv_name) {
        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < suppliers.size(); i++) {
            list.add(suppliers.get(i).getName());
        }
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
                supplierListBeans.get(pos).setId(suppliers.get(select).getId());
                tv_name.setText(suppliers.get(select).getName());
            }
        });


        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                select = index;
            }
        });
        // 设置原始数据
        loopView.setItems(list);
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

    private void setData() {
        List<BuyOrderSubmit.NoteListBean> noteListBeans = new ArrayList<>();
        List<BuyOrderSubmit.NoteSupplierListBean> noteSupplierListBeans = new ArrayList<>();
        for (int i = 0; i < askDetail.getNoteList().size(); i++) {
            BuyOrderSubmit.NoteListBean noteListBean = new BuyOrderSubmit.NoteListBean();
            int nodeId = askDetail.getNoteList().get(i).getId();
            noteListBean.setId(nodeId);
            for (int j = 0; j < supplierListBeans.size(); j++) {
                if (supplierListBeans.get(j).getNoteId() == nodeId) {
                    String cloth = supplierListBeans.get(j).getClothQuantity();
                    if (!TextUtils.isEmpty(cloth)) {
                        noteListBean.setClothQuantity(cloth);
                        break;
                    }

                }
            }
            noteListBeans.add(noteListBean);
        }
        for (int j = 0; j < supplierListBeans.size(); j++) {
            BuyOrderSubmit.NoteSupplierListBean noteSupplierListBean = new BuyOrderSubmit.NoteSupplierListBean();
            noteSupplierListBean.setNoteId(supplierListBeans.get(j).getNoteId());
            noteSupplierListBean.setSupplierId(supplierListBeans.get(j).getId());
            noteSupplierListBean.setQuantity(supplierListBeans.get(j).getQuantity());
            noteSupplierListBean.setUnitPrice(supplierListBeans.get(j).getUnitPrice());

            noteSupplierListBeans.add(noteSupplierListBean);
        }
        buyOrderSubmit = new BuyOrderSubmit();
        buyOrderSubmit.setGroupId(id);
        buyOrderSubmit.setRemarks("");
        buyOrderSubmit.setNoteList(noteListBeans);
        buyOrderSubmit.setNoteSupplierList(noteSupplierListBeans);
    }

    private boolean checkInput() {
        boolean isOk = true;
        for (int i = 0; i < buyOrderSubmit.getNoteSupplierList().size(); i++) {
            if (buyOrderSubmit.getNoteSupplierList().get(i).getUnitPrice() == null) {
                ToastUtils.showLong("价格不能为空");
                isOk = false;
                break;
            }
            if (buyOrderSubmit.getNoteSupplierList().get(i).getQuantity() == null) {
                ToastUtils.showLong("数量不能为空");
                isOk = false;
                break;
            }
        }
        if (!isOk) {
            return isOk;
        }
        for (int i = 0; i < buyOrderSubmit.getNoteList().size(); i++) {
            String cloth = buyOrderSubmit.getNoteList().get(i).getClothQuantity();
            if (TextUtils.isEmpty(cloth)) {
                isOk = false;
                ToastUtils.showLong("请输入码数");
                break;
            } else {
                List<String> cloths = StringUtils.StringToArrayList(cloth, ",");
                for (int j = 0; j < cloths.size(); j++) {
                    if (!MathUtils.isNumber(cloths.get(j))) {
                        ToastUtils.showLong("输入的码数不符合格式");
                        isOk = false;
                        break;
                    }
                }
                if (!isOk) {
                    break;
                }
            }
        }
        if (!isOk) {
            return isOk;
        }
        double clothTotal = calTotal(StringUtils.StringToArrayList(supplierListBeans.get(0).getClothQuantity(), ","));
        double buyTotal = 0;

        int oldId = supplierListBeans.get(0).getNoteId();
        for (int i = 0; i < supplierListBeans.size(); i++) {
            BuyOrderDetail.NoteListBean.SupplierListBean supplierListBean = supplierListBeans.get(i);
            int newNoteId = supplierListBean.getNoteId();
            int len = supplierListBeans.size() - 1;
            LogUtils.e(clothTotal + "");
            if (oldId == newNoteId) {
                buyTotal = buyTotal + supplierListBean.getQuantity().doubleValue();
            } else {
                if (clothTotal != buyTotal && buyTotal > 0) {
                    isOk = false;
                    ToastUtils.showLong("码数之和要等于采购数量之和");
                    break;
                }
                oldId = newNoteId;
                buyTotal = supplierListBean.getQuantity().doubleValue();
                clothTotal = calTotal(StringUtils.StringToArrayList(supplierListBean.getClothQuantity(), ","));

            }
            if (i == len) {  //如果是最后一个
                if (clothTotal != buyTotal && buyTotal > 0) {
                    ToastUtils.showLong("码数之和要等于采购数量之和");
                    isOk = false;
                    break;
                }
            }

        }
        return isOk;
    }

    private double calTotal(ArrayList<String> cloths) {
        double total = 0;
        for (int i = 0; i < cloths.size(); i++) {
            LogUtils.e(cloths.get(i));
            if (MathUtils.isNumber(cloths.get(i))) {
                total = total + Double.parseDouble(cloths.get(i));
            }
        }
        return total;
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                setData();
                if (checkInput())
                    submit();
            }
        });
        suppliers = new ArrayList<>();
        supplierListBeans = new ArrayList<>();
        buyTableAdapter = new BuySubmitAdapter(supplierListBeans);
        buyTableAdapter.setOnEditChangeListener(new BuySubmitAdapter.OnEditChangeListener() {
            @Override
            public void onEdit(int type, int pos, String price, TextView tvTotal) {
                if (!TextUtils.isEmpty(price)) {
                    if (type == 1) {
                        supplierListBeans.get(pos).setClothQuantity(price);
                    } else if (type == 2) {
                        if (MathUtils.isNumber(price)) {
                            supplierListBeans.get(pos).setQuantity(Double.valueOf(price));
                            if (supplierListBeans.get(pos).getUnitPrice() != null) {
                                tvTotal.setText("￥" + Double.valueOf(price) * supplierListBeans.get(pos).getUnitPrice());
                            } else {
                                tvTotal.setText("￥-");
                            }
                        } else {
                            tvTotal.setText("￥-");
                        }

                    } else {
                        if (MathUtils.isNumber(price)) {
                            supplierListBeans.get(pos).setUnitPrice(Double.valueOf(price));
                            if (supplierListBeans.get(pos).getQuantity() != null) {
                                tvTotal.setText("￥" + Double.valueOf(price) * supplierListBeans.get(pos).getQuantity());
                            } else {
                                tvTotal.setText("￥-");
                            }
                        } else {
                            tvTotal.setText("￥-");
                        }
                    }
                } else {
                    if (type > 1) {
                        tvTotal.setText("￥-");
                    }
                }


            }

            @Override
            public void onSelect(int pos, TextView view) {
                showSelectDialog(pos, view);
            }

            @Override
            public void onDelete(int pos) {
                int position = pos - 1;
                supplierListBeans.remove(pos);
                supplierListBeans.get(position).setShowAdd(true);
                buyTableAdapter.setNewData(supplierListBeans);
            }

            @Override
            public void onAdd(int pos) {
                showAddDialog(pos);
            }
        });
        lv_ask.setLayoutManager(new

                LinearLayoutManager(this));
        lv_ask.setAdapter(buyTableAdapter);

        getSupplier();

    }

    private void getSupplier() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("type", 1);
        NetUtils.getInstance().get(MyApp.token, Api.Supplier.getListForSelect, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            Supplier supplier = new Gson().fromJson(item.toString(), Supplier.class);
                            suppliers.add(supplier);
                        }
                        getDetail();
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


    @TargetApi(Build.VERSION_CODES.N)
    private void initData() {
        tv_reqest.setText(askDetail.getRequisitionNumber());
        tv_name.setText(askDetail.getRequisitionCreateByName());
        tv_number.setText(askDetail.getSampleNo());
        tv_order.setText(askDetail.getOrderNumber());
        tv_produce.setText(askDetail.getProductionOrderNumber());
        tv_time.setText(askDetail.getGmtCreate());
        tv_remark.setText(askDetail.getRemarks());
        if (askDetail.getPayMethod() == 1) {
            tv_pay.setText("采购人垫付");
        } else {
            tv_pay.setText("公司支付");
        }
        for (int i = 0; i < askDetail.getNoteList().size(); i++) {
            BuyOrderDetail.NoteListBean noteListBean = askDetail.getNoteList().get(i);
            if (noteListBean.getSupplierList().size() > 0) {
                for (int j = 0; j < noteListBean.getSupplierList().size(); j++) {
                    BuyOrderDetail.NoteListBean.SupplierListBean supplierListBean = noteListBean.getSupplierList().get(j);
                    supplierListBean.setMaterialId(noteListBean.getMaterialId());
                    if (j == 0) {
                        supplierListBean.setHeader(true);
                        supplierListBean.setClothQuantity(noteListBean.getClothQuantity());
                        supplierListBean.setMaterialName(noteListBean.getMaterialName());
                        supplierListBean.setNeedQuantity(noteListBean.getNeedQuantity());
                    } else {
                        supplierListBean.setShowDel(true);
                    }
                    if (j == (noteListBean.getSupplierList().size() - 1)) {
                        supplierListBean.setShowAdd(true);
                    }
                    supplierListBeans.add(supplierListBean);
                }
            } else {
                BuyOrderDetail.NoteListBean.SupplierListBean supplierListBean = new BuyOrderDetail.NoteListBean.SupplierListBean();
                supplierListBean.setSupplierName(suppliers.get(0).getName());
                supplierListBean.setId(suppliers.get(0).getId());
                supplierListBean.setNoteId(askDetail.getNoteList().get(i).getId());
                supplierListBean.setHeader(true);
                supplierListBean.setClothQuantity(noteListBean.getClothQuantity());
                supplierListBean.setMaterialId(noteListBean.getMaterialId());
                supplierListBean.setMaterialName(noteListBean.getMaterialName());
                supplierListBean.setNeedQuantity(noteListBean.getNeedQuantity());
                supplierListBean.setShowAdd(true);
                supplierListBeans.add(supplierListBean);
            }

        }
        String ids = supplierListBeans.stream().map(BuyOrderDetail.NoteListBean.SupplierListBean::getMaterialId).map(String::valueOf).collect(Collectors.joining(","));
        getPrice(ids);

        //Log.e("courseIds", ids);
    }

    private void getPrice(String ids) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("materialIds", ids);
        NetUtils.getInstance().get(MyApp.token, Api.Inventory.getMaterialPriceBatch, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        List<String> stringList = StringUtils.StringToArrayList(ids, ",");
                        for (int i = 0; i < stringList.size(); i++) {
                            Double price = data.getDouble(stringList.get(i));
                            supplierListBeans.get(i).setUnitPrice(price);
                        }
                        buyTableAdapter.setNewData(supplierListBeans);
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

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("groupId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Purchase.getBuyDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        askDetail = new Gson().fromJson(data.toString(), BuyOrderDetail.class);
                        initData();
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
