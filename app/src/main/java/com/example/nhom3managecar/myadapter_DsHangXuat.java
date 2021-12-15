package com.example.nhom3managecar;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom3managecar.data_models.model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter_DsHangXuat extends FirebaseRecyclerAdapter<model, myadapter_DsHangXuat.myviewholder> {

    public myadapter_DsHangXuat(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {

        //ds hang xuat
        holder.dsTenKh.setText(model.getTenKh());
        holder.dsSdt.setText(model.getSdt());
        holder.dsTenXe.setText(model.getTenXe());
        holder.dsSoLuong.setText(model.getSoLuong());
        //Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.dsTenKh.getContext())
                        .setContentHolder(new ViewHolder(R.layout.chi_tiet_ds_hang_xuat))
                        .setExpanded(true,1700)
                        .create();
                View view = dialogPlus.getHolderView();
                EditText tenKh = view.findViewById(R.id.edtTenKhachHangCT);
                EditText sdt = view.findViewById(R.id.edtSdt);
                EditText maXe = view.findViewById(R.id.edtMaHang);
                EditText tenXe = view.findViewById(R.id.edtTenHang);
                EditText nhomHang = view.findViewById(R.id.edtNhomHangCT);
                EditText soLuong = view.findViewById(R.id.edtSoLuong);
                EditText giaBan = view.findViewById(R.id.edtDonGia);
                EditText ngayXuat = view.findViewById(R.id.edtNgayXuat);
                EditText ngayHetBh = view.findViewById(R.id.edtNgayHetBh);

                tenKh.setText(model.getTenKh());
                sdt.setText(model.getSdt());
                maXe.setText(model.getMaXe());
                tenXe.setText(model.getTenXe());
                nhomHang.setText(model.getNhomHang());
                soLuong.setText(model.getSoLuong());
                giaBan.setText(model.getGiaBan());
                ngayXuat.setText(model.getNgayXuat());
                ngayHetBh.setText(model.getNgayHetBh());

                dialogPlus.show();
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.dsSdt.getContext());
                builder.setTitle("Bạn có muốn xóa sản phẩm này không?");
                builder.setIcon(R.drawable.ic_question_answer);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("hang_xuat").child(getRef(position).getKey())
                                .removeValue();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ds_hang_xuat_item, parent, false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder {
        //CircleImageView img;
        Button btnEdit,btnDelete,btnUpdateKho;
        TextView tenKh, sdt, tenXe, ngayMua, ngayHetBh,dsTenKh,dsSdt,dsTenXe,dsSoLuong;
        View v;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
//            //img=(CircleImageView)itemView.findViewById(R.id.img1);
//            tenKh = (TextView) itemView.findViewById(R.id.nametext);
//            sdt = (TextView) itemView.findViewById(R.id.coursetext);
//            tenXe = (TextView) itemView.findViewById(R.id.emailtext);
//            ngayMua = (TextView) itemView.findViewById(R.id.tvNgayMua);
//            ngayHetBh = (TextView) itemView.findViewById(R.id.tvNgayHetBh);
            dsTenKh = (TextView) itemView.findViewById(R.id.tvTenKh);
            dsSdt = (TextView) itemView.findViewById(R.id.tvSdt);
            dsTenXe = (TextView) itemView.findViewById(R.id.tvTenXe);
            dsSoLuong = (TextView) itemView.findViewById(R.id.tvSoLuong);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            v = itemView;
        }
    }
}

