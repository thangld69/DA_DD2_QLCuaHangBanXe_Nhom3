package com.example.nhom3managecar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom3managecar.HomeAdapter;
import com.example.nhom3managecar.KhoAdapter;
import com.example.nhom3managecar.MainAdapter;
import com.example.nhom3managecar.R;
import com.example.nhom3managecar.data_models.ModelCar;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class TrangChuFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    HomeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.trang_chu_layout, container, false);
        try {
            recyclerView = view.findViewById(R.id.rvHome);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            FirebaseRecyclerOptions<ModelCar> options =
                    new FirebaseRecyclerOptions.Builder<ModelCar>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("car"), ModelCar.class)
                            .build();

            adapter = new HomeAdapter(options);
            recyclerView.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(getContext(), "clm"+e, Toast.LENGTH_SHORT).show();
        }

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
