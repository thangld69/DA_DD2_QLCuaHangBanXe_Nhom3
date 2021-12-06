package com.example.nhom3managecar;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
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


public class KhoAdapter extends FirebaseRecyclerAdapter<ModelCar,MainAdapter.myViewHolder> {
    Button btnUpdateKho;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public KhoAdapter(@NonNull FirebaseRecyclerOptions<ModelCar> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MainAdapter.myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull ModelCar model) {
        holder.maKho.setText(model.getMaXe());
        holder.soLuongkho.setText(model.getTonKho());

        Glide.with(holder.imageKho.getContext()).load(model.getTurl()).placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.imageKho);

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imageKho.getContext())
                        .setContentHolder(new ViewHolder(R.layout.cap_nhat_kho))
                        .setExpanded(true,600)
                        .create();
                View view = dialogPlus.getHolderView();
                EditText tonKho = view.findViewById(R.id.txtUpdateKhoMaXe);

                tonKho.setText(model.getTonKho());

                btnUpdateKho = view.findViewById(R.id.btnUpdateKho);
                dialogPlus.show();
                btnUpdateKho.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map  = new HashMap<>();

                        map.put("tonKho",tonKho.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("car")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });
            }
        });
    }
    @NonNull
    @Override
    public MainAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kho_item,parent,false);

        return new MainAdapter.myViewHolder(view);
    }

}
