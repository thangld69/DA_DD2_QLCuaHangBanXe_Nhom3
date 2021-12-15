package com.example.nhom3managecar;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom3managecar.data_models.model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model, myadapter.myviewholder> {
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
        holder.tenKh.setText("Tên KH:" + model.getTenKh());
        holder.sdt.setText("Sdt:" + model.getSdt());
        holder.tenXe.setText("Tên xe:" + model.getTenXe());
        holder.ngayMua.setText("Ngày mua:" + model.getNgayXuat());
        holder.ngayHetBh.setText("Ngày Hết Bh:" + model.getNgayHetBh());
        //Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder {
        //CircleImageView img;
        TextView tenKh, sdt, tenXe, ngayMua, ngayHetBh;
        View v;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            //img=(CircleImageView)itemView.findViewById(R.id.img1);
            tenKh = (TextView) itemView.findViewById(R.id.nametext);
            sdt = (TextView) itemView.findViewById(R.id.coursetext);
            tenXe = (TextView) itemView.findViewById(R.id.emailtext);
            ngayMua = (TextView) itemView.findViewById(R.id.tvNgayMua);
            ngayHetBh = (TextView) itemView.findViewById(R.id.tvNgayHetBh);
            v = itemView;
        }
    }
}

