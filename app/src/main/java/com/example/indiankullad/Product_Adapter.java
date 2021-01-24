package com.example.indiankullad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.ViewHolder>{

    View v;
    Context context;
    List<Model> mydata;

    //    String str [] = {"Accept Payments","Shop on Paytm","Book Bus Tickets",
//            "My orders","My Passbook","Choose Language"};
//    String name[]= {"Show/Share QR or Share Payment link","Electronics,Fashion,Groceries & more","Avail Amazing Payment Cashback","Shopping ,Movie Tickets, Utility Bills","Wallets, Paytm payment Bank Balance",
//            "Use Paytm in your preferred language"};
//    Integer img[]={R.drawable.logo,
//            R.drawable.logo,R.drawable.logo,
//            R.drawable.logo,R.drawable.logo,
//            R.drawable.logo};
    // Context context;
    public Product_Adapter(Context context, List<Model> mydata) {
        this.context = context;
        this.mydata = mydata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_product__adapter,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.k_name.setText(""+mydata.get(position).getKullad_name());
        holder.k_code.setText(""+mydata.get(position).getKullad_code());
        holder.price.setText(""+mydata.get(position).getPrice());
       holder.des.setText(""+mydata.get(position).getDescription());



//
        //            holder.k_name.setText(""+mydata.get(position).getKullad_name());
        //   holder.k_code.setText(""+mydata.get(position).getKullad_code());
//        holder.price.setText(""+mydata.get(position).getPrice());
//        holder.des.setText(""+mydata.get(position).getDescription());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context,Item_infoo.class);
//                    context.startActivity(intent);

                Intent i = new Intent(v.getContext(),Item_infoo.class);
                i.putExtra("condition","profil");
                i.putExtra("kullad_name",mydata.get(position).getKullad_name());
                i.putExtra("kullad_code",mydata.get(position).getKullad_code());
                i.putExtra("price",mydata.get(position).getPrice());
                i.putExtra("box_capacity",mydata.get(position).getBox_capacity());
                i.putExtra("description",mydata.get(position).getDescription());
                i.putExtra("image",mydata.get(position).getImage());
                //  i.putExtra("cfeepaid",mydata.)
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                }
//
//     Picasso.with(context)
//                    .load(EndPoints.IMAGE_PATH+"/"+mydata.get(position).getImage())
//                    .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//            // To fit image into imageView
//                .fit()
//            // To prevent fade animation
//                .noFade()
//                .into(holder.profile_img);


        });

    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView k_name,k_code,des,price;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            k_name = itemView.findViewById(R.id.type);
            k_code = itemView.findViewById(R.id.code);
            img = itemView.findViewById(R.id.image);
            price = itemView.findViewById(R.id.price2);
            des = itemView.findViewById(R.id.des);
            relativeLayout = itemView.findViewById(R.id.reletive);
        }
    }
}