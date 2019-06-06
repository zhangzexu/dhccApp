package com.dhcc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.Conts.RequestCode;
import com.dhcc.Conts.ResultCode;
import com.dhcc.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textToLogin ;
    private TabHost tabHost;

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
        // 设置标签1的标题为“标签1”，且布局为LinearLayout1，须和layout中的布局文件tab1.xml同，下同理
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("标签1")
                .setContent(R.id.LinearLayout1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("标签2")
                .setContent(R.id.LinearLayout2));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("标签3")
                .setContent(R.id.LinearLayout3));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("标签4")
                .setContent(R.id.LinearLayout4));
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            // tabId是newTabSpec参数设置的tab页名，并不是layout里面的标识符id
            public void onTabChanged(String tabId) {
                if (tabId.equals("tab1")) {   //第一个标签

                    Toast.makeText(MainActivity.this, "点击标签页一", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab2")) {   //第二个标签
                    Toast.makeText(MainActivity.this, "点击标签页二", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab3")) {   //第三个标签
                    Toast.makeText(MainActivity.this, "点击标签页三", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab4")) {   //第三个标签
                    Toast.makeText(MainActivity.this, "点击标签页四", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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
