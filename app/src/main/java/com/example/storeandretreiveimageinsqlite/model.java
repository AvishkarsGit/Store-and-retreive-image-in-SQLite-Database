package com.example.storeandretreiveimageinsqlite;

import android.graphics.Bitmap;

public class model {
    Bitmap image;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public model(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
