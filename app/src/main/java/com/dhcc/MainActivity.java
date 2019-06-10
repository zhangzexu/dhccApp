package com.dhcc;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.adapater.ImagePagerAdapater;
import com.dhcc.conts.RequestCode;
import com.dhcc.conts.ResultCode;
import com.dhcc.ui.login.LoginActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textToLogin ;
    private TabHost tabHost;
    private ViewPager vpager_one;
    private ViewPager vpager_two;
    private ArrayList<Integer> imageIds;
    private ImagePagerAdapater mAdapter;
    private ArrayList<Integer> imageIds1;
    private ImagePagerAdapater mAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        
        NavigationView nav_view = findViewById(R.id.nav_view);
        View app_bar_main = findViewById(R.id.app_bar_main);
        tabHost = app_bar_main.findViewById(R.id.content_main);
        tabHost.setup();
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        layoutInflater.inflate(R.layout.tab1, tabHost.getTabContentView());
        layoutInflater.inflate(R.layout.tab2, tabHost.getTabContentView());
        layoutInflater.inflate(R.layout.tab3, tabHost.getTabContentView());
        layoutInflater.inflate(R.layout.tab4, tabHost.getTabContentView());
        layoutInflater.inflate(R.layout.tab5, tabHost.getTabContentView());

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

        View view1 = inflater.inflate(R.layout.home_item_tabbar, null);
        TextView txt1 = view1.findViewById(R.id.home_item_tv);
        ImageView iv1 = view1.findViewById(R.id.home_item_iv);
        iv1.setImageResource(R.drawable.home_item_tabbar_selector);
        txt1.setText("首页");

        View view2 = inflater.inflate(R.layout.home_item_tabbar, null);
        TextView txt2 = view2.findViewById(R.id.home_item_tv);
        ImageView iv2 = view2.findViewById(R.id.home_item_iv);
        iv2.setImageResource(R.drawable.home_item_tabbar_selector3);
        txt2.setText("信用卡");

        View view3 = inflater.inflate(R.layout.home_item_tabbar, null);
        TextView txt3 = view3.findViewById(R.id.home_item_tv);
        ImageView iv3= view3.findViewById(R.id.home_item_iv);
        iv3.setImageResource(R.drawable.home_item_tabbar_selector1);
        txt3.setText("投资理财");

        View view4 = inflater.inflate(R.layout.home_item_tabbar, null);
        TextView txt4 = view4.findViewById(R.id.home_item_tv);
        ImageView iv4 = view4.findViewById(R.id.home_item_iv);
        iv4.setImageResource(R.drawable.home_item_tabbar_selector4);
        txt4.setText("贷款");

        View view5 = inflater.inflate(R.layout.home_item_tabbar, null);
        TextView txt5 = view5.findViewById(R.id.home_item_tv);
        ImageView iv5 = view5.findViewById(R.id.home_item_iv);
        iv5.setImageResource(R.drawable.home_item_tabbar_selector2);
        txt5.setText("悦享生活");
        // 设置标签1的标题为“标签1”，且布局为LinearLayout1，须和layout中的布局文件tab1.xml同，下同理
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(view1)
                .setContent(R.id.LinearLayout1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(view2)
                .setContent(R.id.LinearLayout2));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(view3)
                .setContent(R.id.LinearLayout3));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(view4)
                .setContent(R.id.LinearLayout4));
        tabHost.addTab(tabHost.newTabSpec("tab5").setIndicator(view5)
                .setContent(R.id.LinearLayout5));
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            // tabId是newTabSpec参数设置的tab页名，并不是layout里面的标识符id
            public void onTabChanged(String tabId) {
//                if (tabId.equals("tab1")) {   //第一个标签
//
//                    Toast.makeText(MainActivity.this, "点击标签页一", Toast.LENGTH_SHORT).show();
//                }
//                if (tabId.equals("tab2")) {   //第二个标签
//                    Toast.makeText(MainActivity.this, "点击标签页二", Toast.LENGTH_SHORT).show();
//                }
//                if (tabId.equals("tab3")) {   //第三个标签
//                    Toast.makeText(MainActivity.this, "点击标签页三", Toast.LENGTH_SHORT).show();
//                }
//                if (tabId.equals("tab4")) {   //第三个标签
//                    Toast.makeText(MainActivity.this, "点击标签页四", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        vpager_one = tabHost.findViewById(R.id.LinearLayout1).findViewById(R.id.vp_content);
        vpager_two = tabHost.findViewById(R.id.LinearLayout2).findViewById(R.id.vp_content2);
        imageIds = new ArrayList<>();
        imageIds.add(R.mipmap.l20_ar_creditcard_banner);
        imageIds.add(R.mipmap.ad_quickloan_2);
        imageIds.add(R.mipmap.l20_ar_creditcard_banner);

        imageIds1 = new ArrayList<>();
        imageIds1.add(R.mipmap.ad_quickloan_1);
        imageIds1.add(R.mipmap.ad_quickloan_2);
        imageIds1.add(R.mipmap.advertisement1);
        mAdapter = new ImagePagerAdapater(this,imageIds);
        mAdapter1 = new ImagePagerAdapater(this,imageIds1);
        vpager_one.setAdapter(mAdapter);
        vpager_two.setAdapter(mAdapter1);

        View nav_header_main = nav_view.getHeaderView(0);
        textToLogin = nav_header_main.findViewById(R.id.textToLogin);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        textToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void startLogin(){
        Intent intent = new Intent(MainActivity.this, LoginItemActivity.class);
        startActivityForResult(intent, RequestCode.LOGIN_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RequestCode.LOGIN_CODE){
            if(resultCode== ResultCode.LOGIN_SUCCESS){
                Log.i("test", "onActivityResult: 测试");
            }
        }
    }

}
