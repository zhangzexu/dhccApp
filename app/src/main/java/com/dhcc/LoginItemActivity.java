package com.dhcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dhcc.conts.RequestCode;
import com.dhcc.ui.login.LoginActivity;

public class LoginItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_item);
        findViewById(R.id.goto_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginItemActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.goto_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginItemActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
