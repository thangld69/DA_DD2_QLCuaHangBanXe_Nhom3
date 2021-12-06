package com.example.nhom3managecar.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.nhom3managecar.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NhapHangFragment extends Fragment {
    private View view;
    EditText maXe, tenXe, nhomXe, giaBan, giaVon, tonKho, imageUrl;
    ProgressDialog progressDialog;
    Button btnAdd;
    private String eMaXe = "", eTenXe = "", eNhomXe = "", eGiaBan = "", eGiaVon = "", eTonKho = "", eImageUrl = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.nhap_hang_layout, container, false);


        maXe = view.findViewById(R.id.txtAddMaXe);
        tenXe = view.findViewById(R.id.txtAddTenXe);
        nhomXe = view.findViewById(R.id.txtAddNhomXe);
        giaBan = view.findViewById(R.id.txtAddGiaBan);
        giaVon = view.findViewById(R.id.txtAddGiaVon);
        tonKho = view.findViewById(R.id.txtAddTonKho);
        imageUrl = view.findViewById(R.id.txtAddImage);
        btnAdd = view.findViewById(R.id.btnAdd);
        progressDialog = new ProgressDialog(getContext());
        //
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("maXe", maXe.getText().toString());
                map.put("tenXe", tenXe.getText().toString());
                map.put("nhomXe", nhomXe.getText().toString());
                map.put("giaBan", giaBan.getText().toString());
                map.put("giaVon", giaVon.getText().toString());
                map.put("tonKho", tonKho.getText().toString());
                map.put("turl", imageUrl.getText().toString());
                eMaXe = maXe.getText().toString();
                eTenXe = tenXe.getText().toString();
                eNhomXe = nhomXe.getText().toString();
                eGiaBan = giaBan.getText().toString();
                eGiaVon = giaVon.getText().toString();
                eTonKho = tonKho.getText().toString();
                eImageUrl = imageUrl.getText().toString();
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
                } else if (eImageUrl.isEmpty()) {
                    imageUrl.setError("Bạn chưa nhập đường dẫn ảnh");
                } else {
                    progressDialog.setMessage("Đang thêm hàng vui lòng đợi...");
                    progressDialog.setTitle("Thông Báo");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
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
                            Toast.makeText(getActivity(), "Insert Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

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
        imageUrl.setText("");
    }
}
