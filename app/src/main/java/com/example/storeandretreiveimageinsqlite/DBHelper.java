package com.example.storeandretreiveimageinsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "images_db";

    private ByteArrayOutputStream byteArrayOutputStream;

    private byte[] imageBytes;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE image(id INTEGER PRIMARY KEY AUTOINCREMENT,imageCol BLOB);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS image");
        onCreate(db);
    }

    public long storeImage(model m){
        SQLiteDatabase db =this.getWritableDatabase();
        Bitmap imageBitmap = m.getImage();

        byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        imageBytes = byteArrayOutputStream.toByteArray();
        ContentValues values =new ContentValues();
        values.put("imageCol",imageBytes);

        long id = db.insert("image",null,values);
        db.close();

        return id;

    }

    public ArrayList<model> fetchImages(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<model> modelArrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM image",null);
        while (cursor.moveToNext()){
            byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow("imageCol"));
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);

            modelArrayList.add(new model(bitmap));
        }
        return modelArrayList;
    }
}
