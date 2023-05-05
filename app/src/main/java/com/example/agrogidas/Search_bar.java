package com.example.agrogidas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agrogidas.users.Kombainai;

import java.util.ArrayList;
import java.util.List;
public class Search_bar extends RecyclerView.Adapter<Search_bar.ViewHolder> implements Filterable {

    private Context context;
    private List<Kombainai> list;
    private List<Kombainai> filteredList;

    public Search_bar(Context context, List<Kombainai> list) {
        this.context = context;
        this.list = list;
        this.filteredList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_short_ad, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(filteredList.get(position).getNuotrauka()).into(holder.imageView);
        holder.name.setText(String.valueOf(filteredList.get(position).getMarke()));
        holder.price.setText(String.valueOf(filteredList.get(position).getKaina()) + " €");
        holder.year.setText(String.valueOf(filteredList.get(position).getMetai()) + " m.");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, full_ad.class);
                intent.putExtra("detail", filteredList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchText = constraint.toString().toLowerCase();
                if (searchText.isEmpty()) {
                    filteredList = list;
                } else {
                    List<Kombainai> tempList = new ArrayList<>();
                    for (Kombainai item : list) {
                        if (item.getMarke().toLowerCase().contains(searchText)) {
                            tempList.add(item);
                        }
                    }
                    filteredList = tempList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (List<Kombainai>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, price, year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.nav_img);
            name = itemView.findViewById(R.id.nav_name);
            price = itemView.findViewById(R.id.nav_price);
            year = itemView.findViewById(R.id.nav_year);
        }
    }
}