package com.example.vegan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNV;
    String email;
    Menu menu;

    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeActivity home;
    private MapActivity map;
    private TypeActivity type;
    private UserActivity user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        email=intent.getExtras().getString("userID");

        /*바텀 네비게이션 */
        mBottomNV = findViewById(R.id.nav_view);
        menu=mBottomNV.getMenu();
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        menuItem.setIcon(R.drawable.home_select);
                        menu.findItem(R.id.map).setIcon(R.drawable.map_unselect);
                        menu.findItem(R.id.type).setIcon(R.drawable.type_unselect);
                        menu.findItem(R.id.user).setIcon(R.drawable.user_unselect);
                        setFrag(0);
                        break;

                    case R.id.map:
                        menuItem.setIcon(R.drawable.map_select);
                        menu.findItem(R.id.home).setIcon(R.drawable.home_unselect);
                        menu.findItem(R.id.type).setIcon(R.drawable.type_unselect);
                        menu.findItem(R.id.user).setIcon(R.drawable.user_unselect);
                        setFrag(1);
                        break;

                    case R.id.type:
                        menuItem.setIcon(R.drawable.type_select);
                        menu.findItem(R.id.home).setIcon(R.drawable.home_unselect);
                        menu.findItem(R.id.map).setIcon(R.drawable.map_unselect);
                        menu.findItem(R.id.user).setIcon(R.drawable.user_unselect);
                        setFrag(2);
                        break;

                    case R.id.user:
                        menuItem.setIcon(R.drawable.user_select);
                        menu.findItem(R.id.home).setIcon(R.drawable.home_unselect);
                        menu.findItem(R.id.map).setIcon(R.drawable.map_unselect);
                        menu.findItem(R.id.type).setIcon(R.drawable.type_unselect);
                        setFrag(3);
                        break;
                }
                return true;
            }
        });
        home=new HomeActivity();
        map = new MapActivity();
        type = new TypeActivity();
        user = new UserActivity();

        mBottomNV.setSelectedItemId(R.id.home);
    }
    // 프레그먼트 교체
    private void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();
        switch (n)
        {
            case 0: //home 버튼이 눌렸을 때
                ft.replace(R.id.content_layout,home);
                ft.commit();
                break;

            case 1: //map 버튼이 눌렸을 때
                ft.replace(R.id.content_layout,map);
                ft.commit();
                break;

            case 2: //type 버튼이 눌렸을 때
                ft.replace(R.id.content_layout,type);
                ft.commit();
                break;

            case 3: //user 버튼이 눌렸을 때
                ft.replace(R.id.content_layout,user);
                ft.commit();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}