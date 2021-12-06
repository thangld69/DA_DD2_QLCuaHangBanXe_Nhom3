package com.example.nhom3managecar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom3managecar.data_models.ModelCar;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainCarActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter adapter;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<ModelCar> options =
                new FirebaseRecyclerOptions.Builder<ModelCar>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("car"), ModelCar.class)
                        .build();

        adapter = new MainAdapter(options);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView  view = (SearchView)menuItem.getActionView();

        view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                txtSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void txtSearch(String str){
        FirebaseRecyclerOptions<ModelCar> options =
                new FirebaseRecyclerOptions.Builder<ModelCar>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("car").orderByChild("tenXe").startAt(str).endAt(str + "~"), ModelCar.class)
                        .build();
        adapter = new MainAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}