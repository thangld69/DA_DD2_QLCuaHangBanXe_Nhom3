package com.example.nhom3managecar.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.nhom3managecar.R;
import com.example.nhom3managecar.data_models.ModelCar;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class NhapHangFragment extends Fragment {

    private View view;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance();

    private ImageView imgHinhXe;
    EditText maXe, tenXe, nhomXe, giaBan, giaVon, tonKho, imageUrl;
    ProgressDialog progressDialog;
    Button btnAdd, btnChooseFromGallery, btnOpenCamera;
    private String eMaXe = "", eTenXe = "", eNhomXe = "", eGiaBan = "", eGiaVon = "", eTonKho = "", eImageUrl = "";
    private int PICK_IMAGE = 123, galleryChoose = 0;
    private int CAMERA_IMAGE = 123, cameraChoose = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.nhap_hang_layout, container, false);
        final StorageReference storageRef = storage.getReferenceFromUrl("gs://nhom3-quanlyxe.appspot.com");

        maXe = view.findViewById(R.id.txtAddMaXe);
        tenXe = view.findViewById(R.id.txtAddTenXe);
        nhomXe = view.findViewById(R.id.txtAddNhomXe);
        giaBan = view.findViewById(R.id.txtAddGiaBan);
        giaVon = view.findViewById(R.id.txtAddGiaVon);
        tonKho = view.findViewById(R.id.txtAddTonKho);
        //imageUrl = view.findViewById(R.id.txtAddImage);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnChooseFromGallery = view.findViewById(R.id.btnChooseFromGallery);
        btnOpenCamera = view.findViewById(R.id.btnOpenCamera);
        imgHinhXe = (ImageView) view.findViewById(R.id.imgHinhXe);
        progressDialog = new ProgressDialog(getContext());
        //
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("maXe", maXe.getText().toString());
//                map.put("tenXe", tenXe.getText().toString());
//                map.put("nhomXe", nhomXe.getText().toString());
//                map.put("giaBan", giaBan.getText().toString());
//                map.put("giaVon", giaVon.getText().toString());
//                map.put("tonKho", tonKho.getText().toString());
//                map.put("turl", photoLink);
                //map.put("turl",imgShop;
                eMaXe = maXe.getText().toString();
                eTenXe = tenXe.getText().toString();
                eNhomXe = nhomXe.getText().toString();
                eGiaBan = giaBan.getText().toString();
                eGiaVon = giaVon.getText().toString();
                eTonKho = tonKho.getText().toString();
                //eImageUrl = imageUrl.getText().toString();
                if (eMaXe.isEmpty()) {
                    maXe.setError("Bạn chưa nhập mã xe");
                } else if (eTenXe.isEmpty()) {
                    tenXe.setError("Bạn chưa nhập tên xe");
                } else if (eNhomXe.isEmpty()) {
                    nhomXe.setError("Bạn chưa nhập nhóm xe");
                } else if (eGiaBan.isEmpty()) {
                    giaBan.setError("Bạn chưa nhập giá bán");
                } else if (eGiaVon.isEmpty()) {
                    giaVon.setError("Bạn chưa nhập giá vốn");
                } else if (eTonKho.isEmpty()) {
                    tonKho.setError("Bạn chưa nhập số lượng tồn kho");
                } else if (cameraChoose == 0 && galleryChoose == 0) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                    alert.setMessage("Bạn chưa chọn hình cho sản phẩm!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                } else {
                    progressDialog.setMessage("Đang thêm hàng vui lòng đợi...");
                    progressDialog.setTitle("Thông Báo");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    String shopImage = databaseReference.push().getKey();
                    Calendar calendar = Calendar.getInstance();
                    final StorageReference mountainsRef = storageRef.child("image" + calendar.getTimeInMillis() + ".png");
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
                            AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                            alert.setMessage("Thêm hình thất bại!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String photoLink = uri.toString();
                                    Log.d("cml", photoLink + "");
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("maXe", maXe.getText().toString());
                                    map.put("tenXe", tenXe.getText().toString());
                                    map.put("nhomXe", nhomXe.getText().toString());
                                    map.put("giaBan", giaBan.getText().toString());
                                    map.put("giaVon", giaVon.getText().toString());
                                    map.put("tonKho", tonKho.getText().toString());
                                    map.put("turl", photoLink);
                                    FirebaseDatabase.getInstance().getReference().child("car").push()
                                            .setValue(map)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                                                    alert.setMessage("Thêm hàng thành công").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            progressDialog.dismiss();
                                                            clear();
                                                            maXe.requestFocus();
                                                        }
                                                    }).show();
                                                }

                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                                            alert.setMessage("Thêm hàng thất bại!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                }
                                            });

                                        }
                                    });
                                }
                            });
//                            Uri downloadUrl = taskSnapshot.getUploadSessionUri();
//                            Log.d("cml",downloadUrl+"");
                        }
                    });


                }

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

        return view;
    }

    private void clear() {
        maXe.setText("");
        tenXe.setText("");
        nhomXe.setText("");
        giaBan.setText("");
        giaVon.setText("");
        tonKho.setText("");
        imgHinhXe.setImageResource(R.mipmap.anh_trang);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

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
}
