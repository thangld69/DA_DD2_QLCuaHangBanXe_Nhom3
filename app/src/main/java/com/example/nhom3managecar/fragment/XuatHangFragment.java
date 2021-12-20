package com.example.nhom3managecar.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom3managecar.CapNhatHangActivity;
import com.example.nhom3managecar.MainAdapter;
import com.example.nhom3managecar.R;
import com.example.nhom3managecar.data_models.ModelCar;
import com.example.nhom3managecar.data_models.XuatHang_Model;
import com.example.nhom3managecar.data_models.model;
import com.example.nhom3managecar.myadapter;
import com.example.nhom3managecar.myadapter_DsHangXuat;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class XuatHangFragment extends Fragment implements ValueEventListener{

    View view;
    RecyclerView recyclerView;
    myadapter_DsHangXuat adapter;
    EditText search;
    TextView txtDoanhThu;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("hang_xuat");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.xuat_hang_layout,container,false);
        databaseReference.addValueEventListener(this);
        recyclerView = view.findViewById(R.id.recycler_view);
        search = view.findViewById(R.id.searchCar);
        txtDoanhThu = view.findViewById(R.id.txtDoanhThu);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //txtDoanhThu.setText("giaBanNe");

        txtSearch("");
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString()!=null){
                    txtSearch(s.toString());
                }else {
                    txtSearch("");
                }
            }
        });

        return view;
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("hang_xuat").orderByChild("tenXe").startAt(str).endAt(str + "~"), model.class)
                        .build();
        adapter = new myadapter_DsHangXuat(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        Iterable<DataSnapshot> nodeChild = snapshot.getChildren();
        XuatHang_Model xuatHang_model = null;
        for(DataSnapshot dataSnapshot1 : nodeChild){
            //Log.d("kt",dataSnapshot1.toString());
             xuatHang_model = dataSnapshot1.getValue(XuatHang_Model.class);
            //List<String> td = (ArrayList<String>) dataSnapshot1.getValue();

        //Log.d("ktne",xuatHang_model.getGiaBan());

            String tong = xuatHang_model.getGiaBan()+xuatHang_model.getGiaBan();
        //int doanhThu = Integer.parseInt(tong);
//        txtDoanhThu.setText(tong);
        }
        txtDoanhThu.setText(xuatHang_model.getGiaBan());
        
//        XuatHang_Model xuatHang_model = snapshot.getValue(XuatHang_Model.class);
//        //Log.d("ktne",xuatHang_model.getGiaBan().toString());
//        txtDoanhThu.setText(xuatHang_model.getGiaBan().toString());
//        //txtDoanhThu.setText(snapshot.getValue().toString());

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
