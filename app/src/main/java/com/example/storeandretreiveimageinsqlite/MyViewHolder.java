package com.example.storeandretreiveimageinsqlite;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {


    ImageView display;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        display = itemView.findViewById(R.id.image_display);
    }
}
