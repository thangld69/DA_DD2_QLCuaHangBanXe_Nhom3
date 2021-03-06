package com.example.nhom3managecar;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.view.Window;


import com.example.nhom3managecar.data_models.Permission;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nhom3managecar.fragment.BaoHanhFragment;
import com.example.nhom3managecar.fragment.HangHoaFragment;
import com.example.nhom3managecar.fragment.KiemKhoFragment;
import com.example.nhom3managecar.fragment.LienHeFragment;
import com.example.nhom3managecar.fragment.NhapHangFragment;
import com.example.nhom3managecar.fragment.TrangChuFragment;
import com.example.nhom3managecar.fragment.XuatHangFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    ProgressDialog progressDialog;


    //gan man hinh mac dinh la main_layout
    private int mCurrentFragment = Permission.FRAGMENT_TRANGCHU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(getString(R.string.app_name1));
        //setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open_nav, R.string.drawer_close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //bat su kien click tai nav
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new TrangChuFragment());
        navigationView.getMenu().findItem(R.id.nav_trangChu).setChecked(true);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_trangChu) {
            if (mCurrentFragment != Permission.FRAGMENT_TRANGCHU) {
                replaceFragment(new TrangChuFragment());
                mCurrentFragment = Permission.FRAGMENT_TRANGCHU;
            }
        } else if (id == R.id.nav_hangHoa) {
            if (mCurrentFragment != Permission.FRAGMENT_HANGHOA) {
                replaceFragment(new HangHoaFragment());
                mCurrentFragment = Permission.FRAGMENT_HANGHOA;
            }
        } else if (id == R.id.nav_kiemKho) {
            if (mCurrentFragment != Permission.FRAGMENT_KIEMKHO) {
                replaceFragment(new KiemKhoFragment());
                mCurrentFragment = Permission.FRAGMENT_KIEMKHO;
            }

        } else if (id == R.id.nav_nhapHang) {
            if (mCurrentFragment != Permission.FRAGMENT_NHAPHANG) {
                replaceFragment(new NhapHangFragment());
                mCurrentFragment = Permission.FRAGMENT_NHAPHANG;
            }

        } else if (id == R.id.nav_xuatHang) {
            if (mCurrentFragment != Permission.FRAGMENT_XUATHANG) {
                replaceFragment(new XuatHangFragment());
                mCurrentFragment = Permission.FRAGMENT_XUATHANG;
            }

        } else if (id == R.id.nav_baoHanh) {
            if (mCurrentFragment != Permission.FRAGMENT_BAOHANH) {
                replaceFragment(new BaoHanhFragment());
                mCurrentFragment = Permission.FRAGMENT_BAOHANH;
            }

        } else if (id == R.id.nav_lienHe) {
            if (mCurrentFragment != Permission.FRAGMENT_LIENHE) {
                replaceFragment(new LienHeFragment());
                mCurrentFragment = Permission.FRAGMENT_LIENHE;
            }

        } else if (id == R.id.nav_dangXuat) {
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));

        } else if (id == R.id.nav_thoat) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.ThemeOverlay);//android.R.style.Theme_DeviceDefault
            builder.setTitle("B???n c?? mu???n tho??t kh???i ???ng d???ng");
            builder.setMessage("Vui l??ng x??c nh???n l???a ch???n");
            builder.setIcon(R.drawable.ic_question_answer);
            builder.setPositiveButton("C??", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                    System.exit(0);
                }
            });
            builder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //xu ly nut back tren dt
    @Override
    public void onBackPressed() {
        //neu drawer da dong thi thoat app
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            progressDialog.dismiss();
        } else {

//            progressDialog.dismiss();
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
//            super.onBackPressed();
        }
    }

    //xu ly fragmet
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //relaceFragment v??o
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}