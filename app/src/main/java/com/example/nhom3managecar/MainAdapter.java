package com.example.nhom3managecar;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom3managecar.data_models.ModelCar;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class MainAdapter extends FirebaseRecyclerAdapter<ModelCar,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private int PICK_IMAGE = 123, galleryChoose = 0;
    private int CAMERA_IMAGE = 123, cameraChoose = 0;
    private ImageView imgHinhXe;
    private Button btnChooseFromGallery, btnOpenCamera;

    public MainAdapter(@NonNull FirebaseRecyclerOptions<ModelCar> options) {
        super(options);
    }
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView")final int position, @NonNull ModelCar model) {

        holder.maxe.setText(model.getMaXe());
        holder.tenxe.setText(model.getTenXe());
        holder.gia.setText(model.getGiaBan());
        holder.tonkho.setText(model.getTonKho());

        Glide.with(holder.imgCar.getContext()).load(model.getTurl()).placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.imgCar);

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgCar.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_chi_tiet))
                        .setExpanded(true,1700)
                        .create();
                View view = dialogPlus.getHolderView();
                EditText maXe = view.findViewById(R.id.txtCTMaXe);
                EditText tenXe = view.findViewById(R.id.txtCTTenXe);
                EditText nhomHang = view.findViewById(R.id.txtCTNhomXe);
                EditText giaBan = view.findViewById(R.id.txtCTGiaBan);
                EditText giaVon = view.findViewById(R.id.txtCTGiaVon);
                EditText tonKho = view.findViewById(R.id.txtCTTonKho);
                ImageView imageCT = view.findViewById(R.id.imageCTCar);

                maXe.setText(model.getMaXe());
                tenXe.setText(model.getTenXe());
                nhomHang.setText(model.getNhomXe());
                giaBan.setText(model.getGiaBan());
                giaVon.setText(model.getGiaVon());
                tonKho.setText(model.getTonKho());
                Glide.with(imageCT.getContext()).load(model.getTurl()).placeholder(R.drawable.common_google_signin_btn_icon_dark)
                        .circleCrop()
                        .error(R.drawable.common_google_signin_btn_icon_dark)
                        .into(imageCT);

                dialogPlus.show();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String maXe = model.getMaXe();
//                String tenXe = model.getTenXe();
//                String nhomHang = model.getNhomXe();
//                String giaBan = model.getGiaBan();
//                String giaVon = model.getGiaVon();
//                String tonKho = model.getTonKho();
//                String turl = model.getTurl();
//
//                Intent intent = new Intent(v.getContext(),CapNhatHangActivity.class);
//                intent.putExtra("maXe",maXe);
//                intent.putExtra("tenXe",tenXe);
//                intent.putExtra("nhomHang",nhomHang);
//                intent.putExtra("giaBan",giaBan);
//                intent.putExtra("giaVon",giaVon);
//                intent.putExtra("tonKho",tonKho);
//                intent.putExtra("turl",turl);
//
//                v.getContext().startActivity(intent);
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgCar.getContext())
                        .setContentHolder(new ViewHolder(R.layout.cap_nhat_car))
                        .setExpanded(true,1700)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText maXe = view.findViewById(R.id.txtUpdateMaXe);
                EditText tenXe = view.findViewById(R.id.txtUpdateTenXe);
                EditText nhomHang = view.findViewById(R.id.txtUpdateNhomHang);
                EditText giaBan = view.findViewById(R.id.txtUpdateGiaBan);
                EditText giaVon = view.findViewById(R.id.txtUpdateGiaVon);
                EditText tonKho = view.findViewById(R.id.txtUpdateTonKho);
                EditText imageUrl  = view.findViewById(R.id.txtUpdateImage);
                Button btnUpdate = view.findViewById(R.id.btnUpdate);
                Button btnChooseFromGallery = view.findViewById(R.id.btnChooseFromGallery);
                Button btnOpenCamera = view.findViewById(R.id.btnOpenCamera);
                ImageView imageEdt = view.findViewById(R.id.imgHinhXe);

                ///


                //imgHinhXe = (ImageView) v.findViewById(R.id.imgHinhXe);
//                btnChooseFromGallery = (Button) v.findViewById(R.id.btnChooseFromGallery);
//                btnOpenCamera = (Button) v.findViewById(R.id.btnOpenCamera);

                maXe.setText(model.getMaXe());
                tenXe.setText(model.getTenXe());
                nhomHang.setText(model.getNhomXe());
                giaBan.setText(model.getGiaBan());
                giaVon.setText(model.getGiaVon());
                tonKho.setText(model.getTonKho());
                //imageUrl.setText(model.getTurl());
                Glide.with(imageEdt.getContext()).load(model.getTurl()).placeholder(R.drawable.common_google_signin_btn_icon_dark)
                        .circleCrop()
                        .error(R.drawable.common_google_signin_btn_icon_dark)
                        .into(imageEdt);

                dialogPlus.show();
//                btnChooseFromGallery.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        String clms;
////                        Log.d("tet","clm");
//                        PICK_IMAGE = 1;
//                        galleryChoose++;
//                        Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                        v.getContext().startActivity(gallery);
//                    }
//                });
//                btnOpenCamera.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        CAMERA_IMAGE = 2;
//                        cameraChoose++;
//                        Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                        v.getContext().startActivity(camera);
//
////                        PICK_IMAGE = 1;
////                        galleryChoose++;
////                        Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
////                        v.getContext().startActivity(gallery);
//                    }
//                });



                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        Map<String,Object> map  = new HashMap<>();

                        map.put("maXe",maXe.getText().toString());
                        map.put("tenXe",tenXe.getText().toString());
                        map.put("nhomXe",nhomHang.getText().toString());
                        map.put("giaBan",giaBan.getText().toString());
                        map.put("giaVon",giaVon.getText().toString());
                        map.put("tonKho",tonKho.getText().toString());
                        //map.put("turl",imageUrl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("car")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.maxe.getContext(), "Update Success", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.maxe.getContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });
                //

            }


        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.maxe.getContext());
                builder.setTitle("Bạn có muốn xóa sản phẩm này không?");
                builder.setIcon(R.drawable.ic_question_answer);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("car").child(getRef(position).getKey())
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
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);

        return new myViewHolder(view);
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgCar,imageKho,imageHome;
        TextView maxe,tenxe,gia,tonkho,maKho,soLuongkho,homema,hometen;
        Button btnEdit,btnDelete,btnUpdateKho;
        View v;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCar = itemView.findViewById(R.id.imageCar);
            maxe = itemView.findViewById(R.id.txtMaXe);
            tenxe = itemView.findViewById(R.id.txtTenXe);
            gia = itemView.findViewById(R.id.txtGiaXe);
            tonkho = itemView.findViewById(R.id.txtTonKho);
            imageKho = itemView.findViewById(R.id.imageKhoXe);
            maKho = itemView.findViewById(R.id.txtKhoMaXe);
            soLuongkho = itemView.findViewById(R.id.txtSoLuongKho);
            homema = itemView.findViewById(R.id.txtHomeMa);
            hometen = itemView.findViewById(R.id.txtHomeTen);
            imageHome = itemView.findViewById(R.id.imageHome);


            v =itemView;

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdateKho = itemView.findViewById(R.id.btnUpdateKho);

        }

    }



}
