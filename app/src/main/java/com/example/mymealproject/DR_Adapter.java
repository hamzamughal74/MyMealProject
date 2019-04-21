package com.example.mymealproject;
import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DR_Adapter extends RecyclerView.Adapter<DR_Adapter.DRViewHolder> {

    private Context mCtx;
    private List<String> mRestaurantList;

    public DR_Adapter(Context mCtx) {
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public DR_Adapter.DRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.restaurant_card, parent, false);
        return new DRViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DR_Adapter.DRViewHolder holder, int position) {
        holder.textViewAge.setText("test");
        holder.textViewDep.setText("test");
        holder.textViewDoc_Id.setText("ID");
        holder.textViewName.setText("Muneeb");

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DRViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewDep, textViewAge, textViewDoc_Id;

        public DRViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDep = itemView.findViewById(R.id.text_dep);
            textViewAge = itemView.findViewById(R.id.text_view_age);
            textViewDoc_Id = itemView.findViewById(R.id.text_view_id);
        }
    }
}