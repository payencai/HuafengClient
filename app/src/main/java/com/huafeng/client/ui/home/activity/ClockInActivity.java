package com.huafeng.client.ui.home.activity;

import android.app.Dialog;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.tools.DistanceUtil;
import com.huafeng.client.tools.TimeUtil;
import com.huafeng.client.ui.home.model.MyLocation;
import com.huafeng.client.ui.home.model.Record;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClockInActivity extends BaseActivity implements AMap.OnMyLocationChangeListener {
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_time)
    TextView tv_time;
    MyLocationStyle myLocationStyle;
    AMap mAMap;
    MyLocation mMyLocation;
    int status=0;
    Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_in);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        mMapView.onCreate(savedInstanceState);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_clock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status==1){
                    checkRecord();
                }else{
                    ToastUtils.showShort("当前不符合打卡条件");
                }
            }
        });

        initView();
    }


    private void initView() {
        mAMap = mMapView.getMap();
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(5000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        mAMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        mAMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        mAMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        mAMap.setOnMyLocationChangeListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        Thread.sleep(1000);
                        EventBus.getDefault().post(new MsgEvent(601));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while (true);
            }
        }).start();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void update(MsgEvent msgEvent){
        if(msgEvent.getmMsg()==601){
            long sysTime = System.currentTimeMillis();//获取系统时间
            CharSequence sysTimeStr = DateFormat.format("hh:mm:ss", sysTime);//时间显示格式
            tv_time.setText(sysTimeStr); //更新时间
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        EventBus.getDefault().unregister(this);

    }
    private void clock() {
        Map<String, Object> params = new HashMap<>();
        params.put("address",MyApp.getaMapLocation().getAddress());
        params.put("lat",MyApp.getaMapLocation().getLatitude()+"");
        params.put("lng",MyApp.getaMapLocation().getLongitude()+"");
        String json=new Gson().toJson(params);
        NetUtils.getInstance().post( Api.Attendance.clockIn, json,MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                Log.e("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("打卡成功");
                    } else {
                        ToastUtils.showShort(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                ToastUtils.showShort(error);
            }
        });
    }
    private void checkRecord(){
         if(record.getAttendanceRecord()==null){
             ToastUtils.showShort("还没有设置考勤班次，不能打卡");
             return;
         }
        if (record.getAttendanceRecord().getStatus() == 1) {
            ToastUtils.showShort("今天无需打卡");
            return;//今天不用打卡
        }
        if (!TextUtils.isEmpty(record.getAttendanceRecord().getGmtFirstTime()) && !TextUtils.isEmpty(record.getAttendanceRecord().getGmtSecondTime())){
            ToastUtils.showShort("你今日已经打了2次卡了");
            return;//已经打了2次
        }

        long startTime = TimeUtil.getTime(record.getAttendanceShiftScheduleBak().getStartTime());
        long endTime = TimeUtil.getTime(record.getAttendanceShiftScheduleBak().getEndTime());
        long nowTime = TimeUtil.getNow();

        if(nowTime>endTime&&TextUtils.isEmpty(record.getAttendanceRecord().getGmtFirstTime())){
            ToastUtils.showShort("已经过了打卡时间");
            return;//已经打了2次
        }

        if (TextUtils.isEmpty(record.getAttendanceRecord().getGmtFirstTime())) {
            //迟到
            if (nowTime > startTime) {
                //ToastUtils.showShort("你已经迟到了，是否打卡");
                showDialog("你已经迟到了，是否打卡?");
                return;
            }
        } else {
            //早退
            if(nowTime<startTime){
                ToastUtils.showShort("还未到打第二次卡的时间");
                return;
            }
            if (nowTime < endTime) {
               // ToastUtils.showShort("现在是早退，是否打卡");
                showDialog("现在是早退，是否打卡?");
                return;
            }
        }
        double realdistance = DistanceUtil.calDistance(mMyLocation.getLatitude(), mMyLocation.getLongitude());
        if (realdistance >mMyLocation.getDistance()) {
            showDialog("超出打卡范围,是否打卡？");
            return;
        }
        clock();
    }

    private void showDialog(String title) {
        final Dialog dialog = new Dialog(this, R.style.dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_clock, null);

        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

        TextView tv_submit = (TextView) view.findViewById(R.id.tv_confirm);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                dialog.dismiss();
                clock();

            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_title.setText(title);
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = (int) (display.getWidth() * 0.7);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

    }

    private void getRecord() {
        NetUtils.getInstance().get(MyApp.token, Api.Attendance.getMyAttendanceRecordToday, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        LogUtils.e(response);
                        JSONObject data = jsonObject.getJSONObject("data");
                        if (data != null) {
                            record = new Gson().fromJson(data.toString(), Record.class);
                        }
                    } else {
                        String msg = jsonObject.getString("message");
                        ToastUtils.showShort(msg);

                    }
                } catch (JSONException e) {
                    LogUtils.e(e.getMessage());
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }
    private void getLocation() {

        NetUtils.getInstance().get(MyApp.token, Api.Attendance.getLocation, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        LogUtils.e(response);
                        JSONObject data = jsonObject.getJSONObject("data");
                        if(data!=null){
                            mMyLocation = new Gson().fromJson(data.toString(), MyLocation.class);
                            tv_name.setText("符合打卡条件");
                            status=1;
                            getRecord();
                            //double realdistance = DistanceUtil.calDistance(mMyLocation.getLatitude(), mMyLocation.getLongitude());
//                            if (realdistance <= mMyLocation.getDistance()) {
//                                tv_name.setText("符合打卡条件");
//                                status=1;
//                                getRecord();
//                            }else{
//                                status=0;
//                                tv_name.setText("不符合打卡条件");
//                            }
                        }else{
                            status=0;
                            tv_name.setText("不符合打卡条件");

                        }
                    } else {
                        status=0;
                        tv_name.setText("不符合打卡条件");
                        String msg = jsonObject.getString("message");
                        ToastUtils.showShort(msg);
                    }
                } catch (JSONException e) {
                    LogUtils.e(e.getMessage());
                    status=0;
                    tv_name.setText("不符合打卡条件");
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMyLocationChange(Location location) {
        getLocation();
        Log.e("locate", location.getLatitude() + "");
    }
}
