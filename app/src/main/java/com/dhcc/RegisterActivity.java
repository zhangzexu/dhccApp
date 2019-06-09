package com.dhcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dhcc.BaseUtil.StringUtil;
import com.dhcc.DbUtil.UserDBHelper;
import com.dhcc.Entity.UserInfoEntity;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_name,et_phone,et_bank_card,et_password;
    private UserDBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_bank_card = findViewById(R.id.et_bank_card);
        et_password = findViewById(R.id.et_password);

        findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _name = et_name.getText().toString();
                String _phone = et_phone.getText().toString();
                String _bank_card = et_bank_card.getText().toString();
                String _password = et_password.getText().toString();
                if(StringUtil.isEmpty(_name)){
                    Toast.makeText(RegisterActivity.this,"客户姓名不能为空",Toast.LENGTH_LONG).show();
                }
                if(StringUtil.isEmpty(_bank_card)){
                    Toast.makeText(RegisterActivity.this,"卡号不能为空",Toast.LENGTH_LONG).show();
                }
                List<UserInfoEntity> userInfoEntities = mHelper.queryByName(_name);
                for (UserInfoEntity us:userInfoEntities) {
                    if(us.get_bank_card().equals(_bank_card)){
                        if(us.get_phone().endsWith(_phone)){
                            if(StringUtil.isEmpty(_password)){
                                Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(RegisterActivity.this,"注册成功请登录",Toast.LENGTH_LONG).show();
                                us.set_password(_password);
                                mHelper.update(us);
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                        }else {
                            Toast.makeText(RegisterActivity.this,"预留手机号不对",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this,"卡号不正确",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHelper = UserDBHelper.getInstance(this,2);
        mHelper.openWriteLink();
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.set_bank_card("6222621070001740360");
        userInfoEntity.set_phone("15809591715");
        userInfoEntity.set_name("张泽旭");
        mHelper.insert(userInfoEntity);
        userInfoEntity = new UserInfoEntity();
        userInfoEntity.set_bank_card("6222621070001740361");
        userInfoEntity.set_phone("15809591716");
        userInfoEntity.set_name("张泽旭");
        mHelper.insert(userInfoEntity);
        userInfoEntity = new UserInfoEntity();
        userInfoEntity.set_bank_card("6222621070001740362");
        userInfoEntity.set_phone("15809591717");
        userInfoEntity.set_name("张泽旭");
        mHelper.insert(userInfoEntity);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHelper.close();
    }
}
