package com.example.nhom3managecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nhom3managecar.fragment.XuatHangFragment;

public class XemDoanhThuActivity extends AppCompatActivity {

    TextView txtXemDoanhThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_doanh_thu_layout);
        txtXemDoanhThu = findViewById(R.id.txtDoanhThuTong);
        Intent intent = getIntent();
        String value1 = intent.getStringExtra("tongDoanhThu");
        txtXemDoanhThu.setText(value1 + " VND");

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(XemDoanhThuActivity.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}