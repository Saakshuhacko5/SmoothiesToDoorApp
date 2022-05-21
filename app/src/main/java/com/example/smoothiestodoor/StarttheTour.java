package com.example.smoothiestodoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.smoothiestodoor.databinding.StartthetourBinding;

public class StarttheTour extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.startthetour);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(Signuppage.PREFS_NAME, Context.MODE_PRIVATE);
                boolean hasloggedin = sharedPreferences.getBoolean("hasLoggedin", false);

                if (hasloggedin) {
                    Intent intent = new Intent(StarttheTour.this, Drinksmenulistpage.class);
                    startActivity(intent);
                    finish();
               }
//                else {
//
//                    Intent intent = new Intent(StarttheTour.this, StarttheTour.class);
//                    startActivity(intent);
//                    finish();
//                }
            }
        }, SPLASH_TIME_OUT);
    }

    public void openscreen(View v) {
        Intent intent = new Intent(this, DiscoverSmoothies.class);
        startActivity(intent);
    }
}