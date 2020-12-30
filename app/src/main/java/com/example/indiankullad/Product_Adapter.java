package com.example.indiankullad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.ViewHolder>{

    View view;
    Context context;
    List<Model> mdata;

    public Product_Adapter(Context context, List<Model> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_product__adapter,parent,false);
        ViewHolder viewHolder  = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.price.setText(""+mdata.get(position).getPrice());
       holder.des.setText(""+mdata.get(position).getDescription());
       holder.k_code.setText(""+mdata.get(position).getKullad_code());
       holder.k_name.setText(""+mdata.get(position).getKullad_name());
       //holder.img.setImageResource(""+mdata.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

     ImageView img;
     TextView k_name,k_code,des,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            k_name = itemView.findViewById(R.id.kullad_name);
            k_code = itemView.findViewById(R.id.kullad_code);
            img = itemView.findViewById(R.id.kullad_img);
            price = itemView.findViewById(R.id.price2);   
        }
    }
}