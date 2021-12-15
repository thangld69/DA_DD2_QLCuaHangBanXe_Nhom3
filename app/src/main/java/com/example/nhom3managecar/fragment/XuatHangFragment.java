package com.example.nhom3managecar.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.nhom3managecar.CapNhatHangActivity;
import com.example.nhom3managecar.R;
import com.example.nhom3managecar.data_models.ModelCar;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class XuatHangFragment extends Fragment {
    private View view;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private  Intent intent ;
    private Button btnChonNgay,btnChonNgayHetBh;
    EditText maXe, tenXe, nhomHang, giaBan, giaVon, tonKho;
    private EditText edtNgayXuat,edtNgayHenBh,edtQuantity;
    private int mYear, mMonth, mDay;
    private TextView txtSoLuongSP;
    private Button btnDecs, btnInc;
    private int soLuongSP;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.xuat_hang_layout,container,false);
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        btnDecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(soLuongSP > 0){
                    if(Integer.valueOf(edtQuantity.getText().toString()) > 1){
                        int newNum = Integer.valueOf(edtQuantity.getText().toString()) - 1;
                        edtQuantity.setText(String.valueOf(newNum));
                    }
                }
            }
        });
        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(soLuongSP > 0){
                    if(Integer.valueOf(edtQuantity.getText().toString()) < soLuongSP){
                        int newNum = Integer.valueOf(edtQuantity.getText().toString()) + 1;
                        edtQuantity.setText(String.valueOf(newNum));
                    }
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

    private void setControl() {
        btnDecs = (Button) view.findViewById(R.id.btnDecs);
        btnInc = (Button) view.findViewById(R.id.btnInc);
        edtNgayXuat = (EditText)view.findViewById(R.id.edtNgayXuat);
        edtNgayHenBh = (EditText)view.findViewById(R.id.edtNgayHetBh);
        btnChonNgay = (Button)view.findViewById(R.id.btnChonNgayXuat);
        btnChonNgayHetBh = (Button)view.findViewById(R.id.btnChonNgayHetBh);
    }
}
