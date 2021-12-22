package com.example.nhom3managecar;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom3managecar.data_models.model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter_DsHangXuat extends FirebaseRecyclerAdapter<model, myadapter_DsHangXuat.myviewholder> {

    public myadapter_DsHangXuat(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    private Button btnChonNgay, btnChonNgayHetBh,btnXuat;
    private EditText edtNgayXuat, edtNgayHenBh, edtQuantity;
    private int mYear, mMonth, mDay;
    private TextView txtSoLuongSP;
    private Button btnDecs, btnInc;
    private int soLuongSP;
    View views;


    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
                edtQuantity = (EditText) view.findViewById(R.id.edtQuantity);
                Button btnXuatFile = view.findViewById(R.id.btnXuatFile);


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
                Calendar calendar = Calendar.getInstance();
                String filePath1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString();
                File filePath = new File(filePath1,tenKh.getText().toString()+"_"+calendar.getTimeInMillis()+ ".xls");
                btnXuatFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
                        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Thông Tin Khách Hàng");
                        hssfSheet.createRow(0);
                        hssfSheet.getRow(0).createCell(0).setCellValue("Tên Khách Hàng");
                        hssfSheet.getRow(0).createCell(1).setCellValue("SDT");
                        hssfSheet.getRow(0).createCell(2).setCellValue("Mã Hàng");
                        hssfSheet.getRow(0).createCell(3).setCellValue("Tên Hàng");
                        hssfSheet.getRow(0).createCell(4).setCellValue("Số Lượng");
                        hssfSheet.getRow(0).createCell(5).setCellValue("Thành Tiền");
                        hssfSheet.getRow(0).createCell(6).setCellValue("Ngày Xuất Hàng");
                        hssfSheet.getRow(0).createCell(7).setCellValue("Ngày Hết Bảo Hành");
                        hssfSheet.createRow(1);
                        hssfSheet.getRow(1).createCell(0).setCellValue(tenKh.getText().toString());
                        hssfSheet.getRow(1).createCell(1).setCellValue(sdt.getText().toString());
                        hssfSheet.getRow(1).createCell(2).setCellValue(maXe.getText().toString());
                        hssfSheet.getRow(1).createCell(3).setCellValue(tenXe.getText().toString());
                        hssfSheet.getRow(1).createCell(4).setCellValue(soLuong.getText().toString());
                        hssfSheet.getRow(1).createCell(5).setCellValue(giaBan.getText().toString());
                        hssfSheet.getRow(1).createCell(6).setCellValue(ngayXuat.getText().toString());
                        hssfSheet.getRow(1).createCell(7).setCellValue(ngayHetBh.getText().toString());

                        try {
                            if (!filePath.exists()){
                                filePath.createNewFile();
                            }

                            FileOutputStream fileOutputStream= new FileOutputStream(filePath);
                            hssfWorkbook.write(fileOutputStream);

                            if (fileOutputStream!=null){
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        androidx.appcompat.app.AlertDialog.Builder alert = new androidx.appcompat.app.AlertDialog.Builder(v.getContext());
                        alert.setTitle("Thông Báo");
                        alert.setMessage("Xuất File thành công\n" +"File được lưu tại:" +filePath1.toString()).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                    }
                });
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
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.dsTenKh.getContext())
                        .setContentHolder(new ViewHolder(R.layout.cap_nhat_ds_hang_xuat))
                        .setExpanded(true,1700)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText tenKh = view.findViewById(R.id.edtTenKhachHang);
                EditText sdt = view.findViewById(R.id.edtSdt);
                EditText maXe = view.findViewById(R.id.edtMaHang);
                EditText tenXe = view.findViewById(R.id.edtTenHang);
                EditText nhomHang = view.findViewById(R.id.edtNhomHang);
                EditText soLuong = view.findViewById(R.id.edtQuantity);
                EditText giaBan = view.findViewById(R.id.edtDonGia);
                EditText ngayXuat = view.findViewById(R.id.edtNgayXuat);
                EditText ngayHetBh = view.findViewById(R.id.edtNgayHetBh);
                btnChonNgayHetBh = view.findViewById(R.id.btnChonNgayHetBh);
                btnChonNgay = (Button) view.findViewById(R.id.btnChonNgayXuat);
                edtQuantity = (EditText) view.findViewById(R.id.edtQuantity);
                btnDecs = view.findViewById(R.id.btnDecs);
                btnInc = view.findViewById(R.id.btnInc);


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
                String giaNe = giaBan.getText().toString();
                int gia = Integer.parseInt(giaNe);
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
                btnChonNgay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get Current Date
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        edtNgayXuat.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }

                });
                btnChonNgayHetBh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get Current Date
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        edtNgayHenBh.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }

                });
                btnDecs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (Integer.valueOf(edtQuantity.getText().toString()) > 1) {
                            int newNum = Integer.valueOf(edtQuantity.getText().toString()) - 1;
                            edtQuantity.setText(String.valueOf(newNum));
                            int iNum2 = Integer.valueOf(giaBan.getText().toString());
                            int iTong = Integer.valueOf(iNum2-gia);
                            giaBan.setText(String.valueOf(iTong));
                        }
                    }

                });

                btnInc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (Integer.valueOf(edtQuantity.getText().toString()) > 0) {
                            int newNum = Integer.valueOf(edtQuantity.getText().toString()) + 1;
                            edtQuantity.setText(String.valueOf(newNum));
                            int iTong = Integer.valueOf(gia * newNum);
                            giaBan.setText(String.valueOf(iTong));
                        }
                    }

                });



//                btnUpdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//
//                        Map<String,Object> map  = new HashMap<>();
//
//                        map.put("maXe",maXe.getText().toString());
//                        map.put("tenXe",tenXe.getText().toString());
//                        map.put("nhomXe",nhomHang.getText().toString());
//                        map.put("giaBan",giaBan.getText().toString());
//                        map.put("giaVon",giaVon.getText().toString());
//                        map.put("tonKho",tonKho.getText().toString());
//                        //map.put("turl",imageUrl.getText().toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("car")
//                                .child(getRef(position).getKey()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(holder.maxe.getContext(), "Update Success", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//                                    }
//                                }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(holder.maxe.getContext(), "Update Failed", Toast.LENGTH_SHORT).show();
//                                dialogPlus.dismiss();
//                            }
//                        });
//                    }
//                });
                //

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

