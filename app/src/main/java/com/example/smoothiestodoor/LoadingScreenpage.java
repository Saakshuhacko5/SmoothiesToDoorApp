package com.example.smoothiestodoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.io.ByteArrayOutputStream;

public class LoadingScreenpage extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        String st = ConfirmOrderpage.valuefetch();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.loadingscreenpage);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progressAnimation();
    }
    public void progressAnimation()
    {
        ProgressBarAnimation panim = new ProgressBarAnimation(this,progressBar,0f,100f);
        panim.setDuration(4000);
        progressBar.setAnimation(panim);
    }
    }
//    Intent intent=null;
//        if(st=="Red Fruit")
//        {
//            Bitmap _bitmap = BitmapFactory.decodeResource
//                    (getResources(), R.drawable.redfruits);; // your bitmap
//            ByteArrayOutputStream _bs = new ByteArrayOutputStream();
//            _bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
//            intent.putExtra("byteArray", _bs.toByteArray());
//            startActivity(intent);
//        }
//        else if(st=="Green Fruit")
//        {
//            Bitmap _bitmap =BitmapFactory.decodeResource
//                    (getResources(), R.drawable.greenfruits);; // your bitmap
//            ByteArrayOutputStream _bs = new ByteArrayOutputStream();
//            _bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
//            intent.putExtra("byteArray", _bs.toByteArray());
//            startActivity(intent);
//        }
//        else
//        {
//            Bitmap _bitmap =BitmapFactory.decodeResource
//                    (getResources(), R.drawable.peachfruits);; // your bitmap
//            ByteArrayOutputStream _bs = new ByteArrayOutputStream();
//            _bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
//            intent.putExtra("byteArray", _bs.toByteArray());
//            startActivity(intent);
//        }