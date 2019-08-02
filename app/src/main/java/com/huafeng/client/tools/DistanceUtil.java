package com.huafeng.client.tools;

import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;
import com.huafeng.client.MyApp;

public class DistanceUtil {


    public static float calDistance(String lat, String lng) {
        LatLng latLng2 = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        if(MyApp.getaMapLocation()!=null){
            LatLng latLng = new LatLng(MyApp.getaMapLocation().getLatitude(), MyApp.getaMapLocation().getLongitude());
            float distance = AMapUtils.calculateLineDistance(latLng, latLng2);
            return distance;
        }
        return 0;
    }
}
