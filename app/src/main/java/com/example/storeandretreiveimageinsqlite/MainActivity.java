package com.example.storeandretreiveimageinsqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button save,pick,show;
    private static final int GALLARY_REQ_CODE = 1;
    Uri uri;
    Bitmap bitmap;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imgview);
        save = findViewById(R.id.btnSave);
        show = findViewById(R.id.btnNext);
        pick = findViewById(R.id.btnPick);

        helper = new DBHelper(this);

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeImage();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ShowImagesActivity.class));
            }
        });
    }

    private void chooseImage(){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,GALLARY_REQ_CODE);
        save.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == GALLARY_REQ_CODE){
                try {
                    uri=data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);

                    imageView.setImageBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    private void storeImage(){
        try {
            if (imageView.getDrawable()!=null && bitmap!=null){
                long id = helper.storeImage(new model(bitmap));
                if (id == -1){
                    Toast.makeText(this, "failed..", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Pick image", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}