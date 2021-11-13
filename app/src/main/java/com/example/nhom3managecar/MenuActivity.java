package com.example.nhom3managecar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    private Button btnLogin,btnRegister;
    private TextView txtQuenMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register_activity);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        txtQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Quên mật khẩu tài khoản!", Toast.LENGTH_SHORT).show(); //hiện thông báo chuyển trang
                Intent intent = new Intent(MenuActivity.this,ForgotPassword.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

    }

    private void setControl() {
        btnLogin = findViewById(R.id.btnMenuLogin);
        btnRegister = findViewById(R.id.btnMenuRegister);
        txtQuenMK = findViewById(R.id.txtQuenMK);
    }
}
