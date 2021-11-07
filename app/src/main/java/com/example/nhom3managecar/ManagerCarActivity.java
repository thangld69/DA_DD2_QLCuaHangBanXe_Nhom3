package com.example.nhom3managecar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class ManagerCarActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE = 101;
    private ImageView imageAddCar;
    private EditText maCar,tenCar,loaiCar,giaCar,soLuongCar;
    private TextView load;
    private Button btnAddCar;

    Uri imageUri;
    boolean isImageAdd = false;
    DatabaseReference ref;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_car_activity);

        imageAddCar  = findViewById(R.id.imageAddCar);
        maCar = findViewById(R.id.edtMaXe);
        tenCar = findViewById(R.id.edtTenXe);
        loaiCar = findViewById(R.id.edtLoaiXe);
        giaCar = findViewById(R.id.edtGiaXe);
        soLuongCar = findViewById(R.id.edtSoLuong);
        load = findViewById(R.id.load);

        btnAddCar = findViewById(R.id.btnAddCar);

        load.setVisibility(View.GONE);

        ref = FirebaseDatabase.getInstance().getReference().child("Car");
        storageReference = FirebaseStorage.getInstance().getReference().child("ImageCar");

        imageAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });
        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String maXe = maCar.getText().toString();
                final String tenXe = tenCar.getText().toString();
                final String loaiXe = loaiCar.getText().toString();
                final String giaXe = giaCar.getText().toString();
                final String soLuongXe = soLuongCar.getText().toString();
                if(isImageAdd!= false &&maXe!=null && tenXe!=null && loaiXe!=null && giaXe!=null && soLuongXe !=null){
                    upLoadImage(maXe,tenXe,loaiXe,giaXe,soLuongXe);
                }
            }
        });

    }

    private void upLoadImage(final String maXe,String tenXe,String loaiXe,String giaXe,String soLuongXe) {
        load.setVisibility(View.VISIBLE);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        final String key = ref.push().getKey();
        storageReference.child(key + "jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.child(key + "jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("maXe",maXe);
                        hashMap.put("tenXe",tenXe);
                        hashMap.put("loaiXe",loaiXe);
                        hashMap.put("giaXe",giaXe);
                        hashMap.put("soLuong",soLuongXe);
                        hashMap.put("imageUrl",uri.toString());

                        ref.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Data Successfully Uploaded!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),ListCarActivity.class));
                            }
                        });
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = (snapshot.getBytesTransferred()*100)/snapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded "+(int)progress+"%");
                progressDialog.dismiss();
                load.setText(progress + " %");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_IMAGE && data != null){
            imageUri = data.getData();
            isImageAdd = true;
            imageAddCar.setImageURI(imageUri);
        }
    }
}