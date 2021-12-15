package com.example.nhom3managecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class XuatHangActivity extends AppCompatActivity {
    EditText maXe, tenXe, nhomHang, giaBan, edtTenKh, edtSdt;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private Intent intent;
    private Button btnChonNgay, btnChonNgayHetBh,btnXuat;
    private EditText edtNgayXuat, edtNgayHenBh, edtQuantity;
    private int mYear, mMonth, mDay;
    private TextView txtSoLuongSP;
    private Button btnDecs, btnInc;
    private int soLuongSP;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuat_hang_layout1);
        edtTenKh = findViewById(R.id.edtTenKhachHang);
        edtSdt = findViewById(R.id.edtSdt);
        edtQuantity = (EditText) findViewById(R.id.edtQuantity);
        btnXuat = (Button) findViewById(R.id.btnXuat);
        btnDecs = (Button) findViewById(R.id.btnDecs);
        btnInc = (Button) findViewById(R.id.btnInc);
        edtNgayXuat = (EditText) findViewById(R.id.edtNgayXuat);
        edtNgayHenBh = (EditText) findViewById(R.id.edtNgayHetBh);
        btnChonNgay = (Button) findViewById(R.id.btnChonNgayXuat);
        btnChonNgayHetBh = (Button) findViewById(R.id.btnChonNgayHetBh);
        maXe = findViewById(R.id.edtMaHang);
        tenXe = findViewById(R.id.edtTenHang);
        nhomHang = findViewById(R.id.edtNhomHang);
        giaBan = findViewById(R.id.edtDonGia);
//        EditText giaVon = findViewById(R.id.txtUpdateGiaVon);
//        EditText tonKho = findViewById(R.id.txtUpdateTonKho);
        giaBan.setText(getIntent().getStringExtra("giaBan"));
        String giaNe = giaBan.getText().toString();
        int gia = Integer.parseInt(giaNe);
        //Event
        btnXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference reference = database.getReference("hang_xuat");
                Map<String,Object> map  = new HashMap<>();
                map.put("tenKh",edtTenKh.getText().toString());
                map.put("sdt",edtSdt.getText().toString());
                map.put("maXe",maXe.getText().toString());
                map.put("tenXe",tenXe.getText().toString());
                map.put("nhomXe",nhomHang.getText().toString());
                map.put("soLuong",edtQuantity.getText().toString());
                map.put("giaBan",giaBan.getText().toString());
                map.put("ngayXuat", edtNgayXuat.getText().toString());
                map.put("ngayHetBh", edtNgayHenBh.getText().toString());
                //map.put("turl",imageUrl.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("hang_xuat").push()
                        .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(XuatHangActivity.this);
                        alert.setMessage("Xuất hàng thành công").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //progressDialog.dismiss();
                                clear();
                                edtTenKh.requestFocus();
                            }
                        }).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(XuatHangActivity.this);
                        alert.setMessage("xuât hàng thất bại").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                progressDialog.dismiss();
//                                clear();
//                                maXe.requestFocus();
                            }
                        }).show();
                    }
                });

            }
        });
        btnDecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.valueOf(edtQuantity.getText().toString()) > 1) {
                    int newNum = Integer.valueOf(edtQuantity.getText().toString()) - 1;
                    edtQuantity.setText(String.valueOf(newNum));
                    int iNum2 = Integer.valueOf(giaBan.getText().toString());
                    int iTong = Integer.valueOf(iNum2-gia);
                    giaBan.setText(String.valueOf(iTong));
                }
            }

        });

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.valueOf(edtQuantity.getText().toString()) > 0) {
                    int newNum = Integer.valueOf(edtQuantity.getText().toString()) + 1;
                    edtQuantity.setText(String.valueOf(newNum));
                    int iTong = Integer.valueOf(gia * newNum);
                    giaBan.setText(String.valueOf(iTong));
                }
            }

        });

        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edtNgayXuat.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

        });
        btnChonNgayHetBh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edtNgayHenBh.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

        });
    }
    private void clear() {
        edtTenKh.setText("");
        giaBan.setText("");
        edtSdt.setText("");
        edtNgayHenBh.setText("");
        edtNgayXuat.setText("");
    }


    @Override
    protected void onResume() {


        maXe.setText(getIntent().getStringExtra("maXe"));
        tenXe.setText(getIntent().getStringExtra("tenXe"));
        nhomHang.setText(getIntent().getStringExtra("nhomHang"));
//        giaBan.setText(getIntent().getStringExtra("giaBan"));
//        giaVon.setText(getIntent().getStringExtra("giaVon"));
//        tonKho.setText(getIntent().getStringExtra("tonKho"));
//        tonKho.setText(getIntent().getStringExtra("tonKho"));
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(XuatHangActivity.this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}