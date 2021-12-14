package com.example.nhom3managecar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnChooseFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PICK_IMAGE = 1;
                galleryChoose++;//ACTION_GET_CONTENT
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);//ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI
                gallery.setType("image/*");
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CAMERA_IMAGE = 2;
                cameraChoose++;
                Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, CAMERA_IMAGE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (PICK_IMAGE != 123) {

            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
                Uri imageUri = data.getData();
                imgHinhXe.setImageURI(imageUri);
            }
            PICK_IMAGE = 123;
        }
        if (CAMERA_IMAGE != 123) {
            if (requestCode == CAMERA_IMAGE && resultCode == RESULT_OK) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgHinhXe.setImageBitmap(bitmap);
            }
            CAMERA_IMAGE = 123;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(),MainCarActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}