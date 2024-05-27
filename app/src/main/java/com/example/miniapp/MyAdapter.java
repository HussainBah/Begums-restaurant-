package com.example.miniapp;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;
    ArrayList<Menu> menuArrayList;

//    public MyAdapter(ArrayList<Menu> menuArrayList) {
//        this.menuArrayList = menuArrayList;
//    }
    public MyAdapter(HomeActivity homeActivity, ArrayList<Menu> menuArrayList) {
        this.context = homeActivity;
        this.menuArrayList = menuArrayList;
    }

    @NonNull
    @Override
    //public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //parent.getContext(); if not using context obj
        View v = LayoutInflater.from(context).inflate(R.layout.menu_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Menu menu = menuArrayList.get(position);
        holder.menuName.setText(menu.getName());
        Glide.with(context).load(menu.getImage()).into(holder.menuImage);



        holder.menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.menuImage.getContext(),MenuDetail.class);
                intent.putExtra("uname",menu.getName());
                intent.putExtra("uimage",menu.getImage());
                intent.putExtra("udes",menu.getDescription());
                intent.putExtra("uprice", menu.getPrice());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.menuImage.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView menuName;
        ImageView menuImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            menuName = itemView.findViewById(R.id.menu_name);
            menuImage = itemView.findViewById(R.id.menu_image);

        }
    }
}
