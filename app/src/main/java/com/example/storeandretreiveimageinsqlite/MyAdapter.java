package com.example.storeandretreiveimageinsqlite;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    ArrayList<model> arrayList;

    public MyAdapter(Context context, ArrayList<model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.images_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final model m = arrayList.get(position);
        holder.display.setImageBitmap(m.getImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
