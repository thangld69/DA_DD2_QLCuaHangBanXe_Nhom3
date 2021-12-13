package com.example.nhom3managecar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CapNhapHangHoaActivity extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private ImageView imgHinhXe;
    private Button btnChooseFromGallery, btnOpenCamera, btnUpdate;
    private int PICK_IMAGE = 123, galleryChoose = 0;
    private int CAMERA_IMAGE = 123, cameraChoose = 0;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    //cai nay cua MainAdapter,chỗ btnEdit
   // Intent intent = new Intent(v.getContext(),CapNhapHangHoaActivity.class);
//                v.getContext().startActivity(intent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cap_nhap_hang_hoa_layout);
        final StorageReference storageRef = storage.getReferenceFromUrl("gs://nhom3-quanlyxe.appspot.com");
        imgHinhXe = (ImageView) findViewById(R.id.imgHinhXe);
        btnChooseFromGallery = (Button) findViewById(R.id.btnChooseFromGallery);
        btnOpenCamera = (Button) findViewById(R.id.btnOpenCamera);
        btnUpdate = findViewById(R.id.btnUpdate);
        EditText maXe = findViewById(R.id.txtUpdateMaXe);
        EditText tenXe = findViewById(R.id.txtUpdateTenXe);
        EditText nhomXe = findViewById(R.id.txtUpdateNhomHang);
        EditText giaBan = findViewById(R.id.txtUpdateGiaBan);
        EditText giaVon = findViewById(R.id.txtUpdateGiaVon);
        EditText tonKho = findViewById(R.id.txtUpdateTonKho);
        //EditText imageUrl = findViewById(R.id.txtUpdateImage);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cap nhat hinh len strong
                String shopImage = databaseReference.push().getKey();
                Calendar calendar = Calendar.getInstance();
                final StorageReference mountainsRef = storageRef.child("imageUpdate" + calendar.getTimeInMillis() + ".png");
                imgHinhXe.setDrawingCacheEnabled(true);
                imgHinhXe.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imgHinhXe.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();
                final UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
//                alert.setMessage("Thêm hình thất bại!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //progressDialog.dismiss();
//                    }
//
//                });
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String photoLink = uri.toString();
                                Map<String, Object> map = new HashMap<>();
                                map.put("maXe", maXe.getText().toString());
                                map.put("tenXe", tenXe.getText().toString());
                                map.put("nhomXe", nhomXe.getText().toString());
                                map.put("giaBan", giaBan.getText().toString());
                                map.put("giaVon", giaVon.getText().toString());
                                map.put("tonKho", tonKho.getText().toString());
                                map.put("turl", photoLink);
                                //

                                FirebaseDatabase.getInstance().getReference().child("car").updateChildren(map)//.child(getRef(position).getKey()).updateChildren(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                                //Toast.makeText(maxe.getContext(), "Update Success", Toast.LENGTH_SHORT).show();
                                                //dialogPlus.dismiss();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //Toast.makeText(holder.maxe.getContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                                        //dialogPlus.dismiss();
                                    }
                                });
                            }
                        });
                    }
                });

            }
        });


        btnChooseFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PICK_IMAGE = 1;
                galleryChoose++;
                Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
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

    @SuppressLint("MissingSuperCall")
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


    }

}