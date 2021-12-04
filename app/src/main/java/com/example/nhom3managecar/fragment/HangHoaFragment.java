package com.example.nhom3managecar.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nhom3managecar.ForgotPassword;
import com.example.nhom3managecar.R;
import com.example.nhom3managecar.ThemSanPhamActivity;

public class HangHoaFragment extends Fragment {
    ImageButton btnThemSp;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.hang_hoa_layout,container,false);
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
    btnThemSp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), ThemSanPhamActivity.class);
            startActivity(intent);
        }
    });
    }

    private void setControl() {
    btnThemSp = view.findViewById(R.id.btnThemSp);
    }
}
