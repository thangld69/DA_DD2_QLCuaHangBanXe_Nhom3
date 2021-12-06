package com.example.nhom3managecar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom3managecar.KhoAdapter;
import com.example.nhom3managecar.data_models.ModelCar;
import com.example.nhom3managecar.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class KiemKhoFragment extends Fragment {
    private View view;
    RecyclerView recyclerView;
    KhoAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_kho, container, false);

        recyclerView = view.findViewById(R.id.rvKho);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<ModelCar> options =
                new FirebaseRecyclerOptions.Builder<ModelCar>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("car"), ModelCar.class)
                        .build();

        adapter = new KhoAdapter(options);
        recyclerView.setAdapter(adapter);
        return view;

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
