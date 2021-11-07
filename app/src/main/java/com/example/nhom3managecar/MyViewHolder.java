package com.example.nhom3managecar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView txtTenXe,txtLoaiXe,txtGiaXe;
    View v;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageRecyclerView);
        txtTenXe = itemView.findViewById(R.id.tenXeRecyclerView);
        txtLoaiXe = itemView.findViewById(R.id.loaiXeRecyclerView);
        txtGiaXe = itemView.findViewById(R.id.giaXeRecyclerView);
        v= itemView;

    }
}
