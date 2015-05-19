package com.yy.diyidemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {

    private EditText usrName;
    private EditText usrKey;
    private Button btn_login;
    private Boolean LOGIN_STATUES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LOGIN_STATUES = Login();
                if (LOGIN_STATUES) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        usrName = (EditText)findViewById(R.id.usrName);
        usrKey = (EditText)findViewById(R.id.usrKey);
        btn_login = (Button)findViewById(R.id.btn_login);

    }

    private Boolean Login() {
        //TODO login
        return true;
    }
}
