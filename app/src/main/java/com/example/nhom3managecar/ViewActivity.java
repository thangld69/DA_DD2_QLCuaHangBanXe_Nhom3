package com.example.nhom3managecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ViewActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView txtViewMa,txtViewTen,txtViewLoai,txtViewGia,txtViewSoLuong;
    private Button btnDelete;
    DatabaseReference ref,dataRef;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        imageView = findViewById(R.id.imageViewCar);
        txtViewMa = findViewById(R.id.txtViewMaXe);
        txtViewTen = findViewById(R.id.txtViewTenXe);
        txtViewLoai = findViewById(R.id.txtViewLoaiXe);
        txtViewGia = findViewById(R.id.txtViewGiaXe);
        txtViewSoLuong = findViewById(R.id.txtViewSoLuong);
        btnDelete = findViewById(R.id.deleteCar);

        ref = FirebaseDatabase.getInstance().getReference().child("Car");

        String CarKey = getIntent().getStringExtra("CarKey");

        dataRef = FirebaseDatabase.getInstance().getReference().child("Car").child(CarKey);
        storageReference = FirebaseStorage.getInstance().getReference().child("ImageCar").child(CarKey+" jpg");

        ref.child(CarKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String carMa = snapshot.child("maXe").getValue().toString();
                    String carTen = snapshot.child("tenXe").getValue().toString();
                    String carLoai = snapshot.child("loaiXe").getValue().toString();
                    String carGia = snapshot.child("giaXe").getValue().toString();
                    String carSoLuong = snapshot.child("soLuong").getValue().toString();
                    String carImage = snapshot.child("imageUrl").getValue().toString();

                    Picasso.get().load(carImage).into(imageView);
                    txtViewMa.setText(carMa);
                    txtViewTen.setText(carTen);
                    txtViewLoai.setText(carLoai);
                    txtViewGia.setText(carGia);
                    txtViewSoLuong.setText(carSoLuong);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                startActivity(new Intent(getApplicationContext(),ListCarActivity.class));
                            }
                        });
                    }
                });
            }
        });
    }
}