package com.example.nhom3managecar.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nhom3managecar.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class XuatHangFragment extends Fragment {
    private View view;
    private Button btnChonNgay,btnChonNgayHetBh;
    private EditText edtNgayXuat,edtNgayHenBh;
    private int mYear, mMonth, mDay;
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
        edtNgayXuat = (EditText)view.findViewById(R.id.edtNgayXuat);
        edtNgayHenBh = (EditText)view.findViewById(R.id.edtNgayHetBh);
        btnChonNgay = (Button)view.findViewById(R.id.btnChonNgayXuat);
        btnChonNgayHetBh = (Button)view.findViewById(R.id.btnChonNgayHetBh);
    }
}
