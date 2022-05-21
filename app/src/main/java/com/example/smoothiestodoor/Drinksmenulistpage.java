package com.example.smoothiestodoor;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.smoothiestodoor.databinding.DrinksmenulistpageBinding;
import com.google.android.material.navigation.NavigationView;

public class Drinksmenulistpage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    DrinksmenulistpageBinding drinksmenulistpageBinding;
    TextView editText1;
    String useremail;
    String flags;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drinksmenulistpage);

        drinksmenulistpageBinding = DrinksmenulistpageBinding.inflate(getLayoutInflater());
        View view = drinksmenulistpageBinding.getRoot();
        setContentView(view);

        setSupportActionBar(drinksmenulistpageBinding.toolbar);
        flags = getIntent().getStringExtra("flag");

        sharedPreferences = getApplicationContext().getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
        useremail = sharedPreferences.getString("email", "");
        View header = drinksmenulistpageBinding.navview.getHeaderView(0);
        editText1 = header.findViewById(R.id.user);
        editText1.setText(useremail);
        Intent i = getIntent();
        drinksmenulistpageBinding.navview.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drinksmenulistpageBinding.drawlayout,drinksmenulistpageBinding.toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drinksmenulistpageBinding.drawlayout.addDrawerListener(toggle);
         toggle.syncState();

        if(flags!=null && flags.equals("1"))
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                    new Finalorderedpagefragment()).commit();
            drinksmenulistpageBinding.navview.setCheckedItem(R.id.nav_orders);
        }
        else
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                    new NewRecyclerViewFragment()).commit();
            drinksmenulistpageBinding.navview.setCheckedItem(R.id.nav_drinks);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_orders:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                        new Finalorderedpagefragment()).commit();
                break;
            case R.id.nav_drinks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                        new NewRecyclerViewFragment()).commit();
                break;
            case R.id.nav_retro:
                Intent intent1 = new Intent(Drinksmenulistpage.this,MyAPIActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent  = new Intent(Drinksmenulistpage.this,Signinpage.class);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("hasLoggedin",false);
                                editor.commit();
//
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
        drinksmenulistpageBinding.drawlayout.closeDrawer(GravityCompat.START);
        return true;
    }


//    @Override
    public void onBackPressed()
    {
//        if(drinksmenulistpageBinding.drawlayout.isDrawerOpen(GravityCompat.START))
//        {
//            drinksmenulistpageBinding.drawlayout.closeDrawer(GravityCompat.START);
//        }
//        else
//        {
//            super.onBackPressed();
//        }
        if(NewRecyclerViewFragment.backPressedListener!=null)
        {
            NewRecyclerViewFragment.backPressedListener.onBackpressed();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("hasLoggedin",true);
            editor.commit();
            Intent intent  = new Intent(Drinksmenulistpage.this,Signinpage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( NewRecyclerViewFragment.alertDialog!=null && NewRecyclerViewFragment.alertDialog.isShowing() ){
            NewRecyclerViewFragment.alertDialog.dismiss();
        }
    }
}
