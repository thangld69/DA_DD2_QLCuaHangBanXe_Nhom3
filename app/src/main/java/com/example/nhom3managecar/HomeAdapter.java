package com.example.nhom3managecar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom3managecar.data_models.ModelCar;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HomeAdapter extends FirebaseRecyclerAdapter<ModelCar, MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public HomeAdapter(@NonNull FirebaseRecyclerOptions<ModelCar> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MainAdapter.myViewHolder holder, int position, @NonNull ModelCar model) {

        holder.homema.setText(model.getMaXe());
        holder.hometen.setText(model.getTenXe());
        Glide.with(holder.imageHome.getContext()).load(model.getTurl()).placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.imageHome);
    }

    @NonNull
    @Override
    public MainAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,parent,false);
        return new MainAdapter.myViewHolder(view);
    }
}
