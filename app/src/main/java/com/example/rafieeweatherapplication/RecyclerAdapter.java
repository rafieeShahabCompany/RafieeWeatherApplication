package com.example.rafieeweatherapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
   java.util.List<String> mylist;
    public RecyclerAdapter(List<String> list){
        mylist=list;

    }


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item,parent,false);
        RecyclerHolder holder = new RecyclerHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        String name = mylist.get(position);
        holder.txtCityName.setText(name);

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder{
        TextView txtCityName;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtCityName = itemView.findViewById(R.id.txtCityName);
        }
    }
}
