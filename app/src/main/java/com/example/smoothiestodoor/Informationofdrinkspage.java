package com.example.smoothiestodoor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

public class Informationofdrinkspage extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    String st;
    FragmentAdapter fragmentAdapter;
    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informationofdrinkspage);
        imageView1 = findViewById(R.id.imgof);
        if (getIntent().hasExtra("byteArray")) {
            Bitmap bitmap = BitmapFactory.decodeByteArray
                    (getIntent().getByteArrayExtra("byteArray")
                            , 0, getIntent().getByteArrayExtra
                                    ("byteArray").length);
            imageView1.setImageBitmap(bitmap);
            st = getIntent().getStringExtra("fruitname");
        }



        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("INGREDIENTS"));
        tabLayout.addTab(tabLayout.newTab().setText("PREPARATION"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        fragmentAdapter = new FragmentAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(fragmentAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }

    public void openscreen(View v) {
        Intent intent = new Intent(this, Selectionofingredients.class);
        intent.putExtra("fruitname",st);
        startActivity(intent);
    }


//    public void getIncomingIntent()
//    {
//        Log.d(TAG,"get intent");
//        if(getIntent().hasExtra("image_url"))
//        {
//            Log.d(TAG,"get intent:found extras");
//            String url = getIntent().getStringExtra("image_url");
//        }
//    }
//
//    private void setImage(String imageurl  , String imagename)
//    {
//        Log.d(TAG,"setting an image");
//        ImageView imageView = findViewById(R.id.imgof);
//        Glide.with(this)
//                .asBitmap()
//                .load(imageurl)
//                .into(imageView);
//
//
//    }


}