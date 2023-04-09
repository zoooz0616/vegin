package com.example.vegan;

import android.Manifest;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends Fragment implements OnMapReadyCallback {
    private GoogleMap map;
    //Mapview mapview= null;
    MapView mapView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        try {
//            mapView = (MapView) inflater.inflate(R.layout.activity_map, container, false);
//        } catch (InflateException e) {}


//        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.activity_map, container, false);

        mapView = v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
//        SupportMapFragment mapFragment = v.findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop () {
        super.onStop();
        mapView.onStop();

    }

    @Override
    public void onSaveInstanceState (@Nullable Bundle outState){
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mapView!=null){
            ViewGroup parent = (ViewGroup)mapView.getParent();
            if(parent!=null){
                parent.removeView(mapView);
            }
        }

        mapView.onLowMemory();
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        String email="ttt";

        map = googleMap;
        LatLng seoul = new LatLng(37.551626, 126.990549);
        map.moveCamera(CameraUpdateFactory.newLatLng(seoul)); //초기 위치
        map.animateCamera(CameraUpdateFactory.zoomTo(12)); //줌

        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5738835,126.9831643)).title("발우공양"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.6223075, 127.0730142)).title("502 세컨즈카페"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5420084,126.9608797)).title("MTL (효창)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5581399,126.9344327)).title("Mr Lee (미스터 리)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5473679,127.0412102)).title("Ndd"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5259511,127.0379025)).title("WHAT A CHEF (왓어쉐프)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5917368,127.0154717)).title("가미꼬마김밥"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5246084,126.9268244)).title("강가 (여의도)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.4689661,126.9383802)).title("강릉형제장칼국수"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5533338,126.8510294)).title("공간녹음"));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.4784231,126.9822603)).title("깔리"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5886541,127.060779)).title("꽃갈피"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.6205661,126.9172305)).title("나무선인장"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5258712,126.8708145)).title("닥터로빈 (목동)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.4806318,126.8805702)).title("스윗밸런스 (가산더블유센터)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5052205,127.0252342)).title("개성집"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5670256,126.9898479)).title("그랑블루"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5589929,126.8288187)).title("그리너 샐러드 (강서구)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5062821,127.0900849)).title("나위"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5358798,127.0920732)).title("날일달월"));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.538851,127.124463)).title("죠샌드위치 (현대백화점 천호)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5541168,126.929946)).title("드렁큰비건"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.512403,126.9272129)).title("홀썸"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5456108,126.9846067)).title("해방식당"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5576104,126.9083316)).title("해콩(망원)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5414092,127.0550499)).title("헤이보울"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5064288,127.1002813)).title("피피샐러드"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5410053,127.0491878)).title("플랜브이"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5699642,126.9312486)).title("포포브레드"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5618917,126.9211936)).title("키다리아저씨"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5190769,127.0247078)).title("칙피스"));







//        Response.Listener<String> responseListener = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    ArrayList<MapItem> maplist = new ArrayList<>();
//
//                    JSONObject jsonResponse = new JSONObject(response);
//                    JSONArray jsonArray = jsonResponse.getJSONArray("map");
//                    boolean success = jsonResponse.getBoolean("success");
//
//                    Log.d("rrrr","성공");
//
//                    if (success) {
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            MapItem article = new MapItem();
//                            JSONObject item = jsonArray.getJSONObject(i);
//
//                            article.setName(item.getString("name"));
//                            article.setlatitude(Double.parseDouble(item.getString("latitude")));
//                            article.setlongtitude(Double.parseDouble(item.getString("lontitude")));
//
//                            maplist.add(article);
//                            googleMap.addMarker(new MarkerOptions().position(new LatLng(article.getlatitude(),article.getlatitude())).title(article.getName()));
//
//
//                        }
//                    } else{// 회원가입이 안된다면
//                            Toast.makeText(getApplicationContext(), "실패함", Toast.LENGTH_SHORT).show();
//                            Log.i("rrrr","실패");
//                            return;
//                    }
//                }catch(Exception e){
//                        e.printStackTrace();
//                }
//            }
//        };
//
//        MapRequest mapRequest = new MapRequest(email, responseListener);
//        RequestQueue queue = Volley.newRequestQueue(MapActivity.this);
//        queue.add(mapRequest);
    }
}