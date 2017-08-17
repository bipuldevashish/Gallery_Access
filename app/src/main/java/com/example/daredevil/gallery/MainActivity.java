package com.example.daredevil.gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static final int gal_picker = 50;
    private ImageView imgp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgp = (ImageView) findViewById(R.id.image);
    }

    public void gallery(View view){

        Intent pici = new Intent(Intent.ACTION_PICK);
        File pic_direc=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pic_dire_path= pic_direc.getPath();
        Uri da =Uri.parse(pic_dire_path);

        pici.setDataAndType(da,"image/*");

        startActivityForResult(pici,gal_picker);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK){
            if (requestCode == gal_picker){

                Uri Imguri =data.getData();

                InputStream Inputstream;

                try {
                    Inputstream = getContentResolver().openInputStream(Imguri);

                    imgp.setImageBitmap(BitmapFactory.decodeStream(Inputstream));
                } catch (FileNotFoundException i){
                    i.printStackTrace();
                    Toast.makeText(this, "Unable to load the image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
