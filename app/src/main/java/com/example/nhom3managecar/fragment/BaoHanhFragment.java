package com.example.nhom3managecar.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom3managecar.MainAdapter;
import com.example.nhom3managecar.R;
import com.example.nhom3managecar.data_models.ModelCar;
import com.example.nhom3managecar.data_models.model;
import com.example.nhom3managecar.myadapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BaoHanhFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    //MainAdapter adapter;
    EditText search;
    myadapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bao_hanh_layout,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        search = view.findViewById(R.id.searchCar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("hang_xuat"), model.class)
                        .build();

        adapter=new myadapter(options);
        recyclerView.setAdapter(adapter);
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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("hang_xuat").orderByChild("sdt").startAt(str).endAt(str + "~"), model.class)
                        .build();
        adapter = new myadapter(options);
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
}
