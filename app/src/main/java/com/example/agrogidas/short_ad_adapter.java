package com.example.agrogidas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agrogidas.users.Kombainai;

import java.util.List;

public class short_ad_adapter extends RecyclerView.Adapter<short_ad_adapter.ViewHolder>{

    Context context;
    List<Kombainai> list;

    public short_ad_adapter(Context context, List<Kombainai> list)
    {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_short_ad,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getNuotrauka()).into(holder.imageView);
        holder.name.setText(String.valueOf(list.get(position).getMarke()));
        holder.price.setText(String.valueOf(list.get(position).getKaina()));
        holder.year.setText(String.valueOf(list.get(position).getMetai()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,short_ad.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,price,year;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.nav_img);
            name = itemView.findViewById(R.id.nav_name);
            price = itemView.findViewById(R.id.nav_price);
            year = itemView.findViewById(R.id.nav_year);
        }
    }
}
