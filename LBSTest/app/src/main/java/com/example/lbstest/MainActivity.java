package com.example.lbstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public LocationClient mLocationClient;
    private TextView postionTextView;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化百度SDK
        setupBaiDuMapSDK();
        setContentView(R.layout.activity_main);
        postionTextView = findViewById(R.id.position_text_view);
        mapView = findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        requestPermissionList();
    }
    // 初始化SDK
    private void setupBaiDuMapSDK() {
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
    }

    // 请求权限
    private void requestPermissionList() {
        List<String> permissionList = new ArrayList<>();
        int granted = PackageManager.PERMISSION_GRANTED;

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != granted)
        {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != granted)
        {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != granted)
        {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }else {
            requestLocation();
        }
    }

    // 权限请求回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 1) {
            return;
        }

        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "权限缺少", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        requestLocation();
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        // 5s更新一次
        option.setScanSpan(5000);
        // 默认切换定位方式
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        // 需要地址
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPostion = new StringBuilder();
                    currentPostion.append("纬度:").append(bdLocation.getLatitude()).append("\n");
                    currentPostion.append("经度:").append(bdLocation.getLongitude()).append("\n");
                    currentPostion.append("定位方式: ").append("\n");
                    if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                        currentPostion.append("GPS").append("\n");
                    }else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                        currentPostion.append("网络").append("\n");
                    }

                    currentPostion.append("国家:").append(bdLocation.getCountry()).append("\n");
                    currentPostion.append("省:").append(bdLocation.getProvince()).append("\n");
                    currentPostion.append("市:").append(bdLocation.getCity()).append("\n");
                    currentPostion.append("区:").append(bdLocation.getDistrict()).append("\n");
                    currentPostion.append("街道:").append(bdLocation.getStreet()).append("\n");

                    postionTextView.setText(currentPostion);

                    navigateTo(bdLocation);
                }
            });
        }
    }
    private void navigateTo(final BDLocation bdLocation) {
        if (!isFirstLocate) {
            return;
        }
        isFirstLocate = false;
        LatLng latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
        baiduMap.animateMapStatus(mapStatusUpdate);
        mapStatusUpdate = MapStatusUpdateFactory.zoomTo(16f);
        baiduMap.animateMapStatus(mapStatusUpdate);

        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.latitude(bdLocation.getLatitude());
        builder.longitude(bdLocation.getLongitude());
        MyLocationData locationData = builder.build();
        baiduMap.setMyLocationData(locationData);
    }
}
