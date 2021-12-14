package com.example.nhom3managecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nhom3managecar.data_models.ModelCar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CapNhatHangActivity extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private ImageView imgHinhXe;
    private Button btnChooseFromGallery, btnOpenCamera, btnUpdate;
    private int PICK_IMAGE = 123, galleryChoose = 0;
    private int CAMERA_IMAGE = 123, cameraChoose = 0;
    private ImageButton imageButtonBackSpace;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cap_nhat_hang_layout);
        ModelCar model = new ModelCar();
        imgHinhXe = (ImageView) findViewById(R.id.imgHinhXe);
        btnChooseFromGallery = (Button) findViewById(R.id.btnChooseFromGallery);
        btnOpenCamera = (Button) findViewById(R.id.btnOpenCamera);
        btnUpdate = findViewById(R.id.btnUpdate);
        imageButtonBackSpace = findViewById(R.id.icon_backspace_cap_nhat_sp);
        EditText maXe = findViewById(R.id.txtUpdateMaXe);
        EditText tenXe = findViewById(R.id.txtUpdateTenXe);
        EditText nhomHang = findViewById(R.id.txtUpdateNhomHang);
        EditText giaBan = findViewById(R.id.txtUpdateGiaBan);
        EditText giaVon = findViewById(R.id.txtUpdateGiaVon);
        EditText tonKho = findViewById(R.id.txtUpdateTonKho);
        imageButtonBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        maXe.setText(getIntent().getStringExtra("maXe"));
        tenXe.setText(getIntent().getStringExtra("tenXe"));
        nhomHang.setText(getIntent().getStringExtra("nhomHang"));
        giaBan.setText(getIntent().getStringExtra("giaBan"));
        giaVon.setText(getIntent().getStringExtra("giaVon"));
        tonKho.setText(getIntent().getStringExtra("tonKho"));
        tonKho.setText(getIntent().getStringExtra("tonKho"));
        //imgHinhXe.setImageResource(getIntent().getStringExtra("turl"));
//        Glide.with(imgHinhXe.getContext()).load(model.getTurl()).placeholder(R.drawable.common_google_signin_btn_icon_dark)
//                .circleCrop()
//                .error(R.drawable.common_google_signin_btn_icon_dark)
//                .into(imgHinhXe);



    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(),MainCarActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}