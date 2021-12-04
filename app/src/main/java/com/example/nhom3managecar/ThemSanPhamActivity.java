package com.example.nhom3managecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ThemSanPhamActivity extends AppCompatActivity {
    private ImageButton imageButtonBackSpace;
    private Button btnChooseFromGallery, btnOpenCamera, btnSave;
    private ImageView imgAccChange;
    private int PICK_IMAGE = 123;
    private int CAMERA_IMAGE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_san_pham_layout);
        setControl();
        setEvent();
    }

    private void setEvent() {
        imageButtonBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnChooseFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PICK_IMAGE = 1;
                Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CAMERA_IMAGE = 2;
                Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, CAMERA_IMAGE);
            }
        });
    }

    private void setControl() {
        imgAccChange = (ImageView) findViewById(R.id.imgAccChange);
        imageButtonBackSpace = (ImageButton) findViewById(R.id.icon_backspace_them_hang_hoa);
        btnChooseFromGallery = (Button) findViewById(R.id.btnChooseFromGallery);
        btnOpenCamera = (Button) findViewById(R.id.btnOpenCamera);
        btnSave = (Button) findViewById(R.id.btnSave);
    }
}