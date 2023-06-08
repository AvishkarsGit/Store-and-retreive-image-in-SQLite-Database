package com.example.storeandretreiveimageinsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowImagesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<model> arrayList;
    private DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        helper = new DBHelper(this);

        myAdapter = new MyAdapter(getApplicationContext(),getAll());

        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<model> getAll() {

        arrayList = new ArrayList<>();
        arrayList = helper.fetchImages();
        return arrayList;
    }
}