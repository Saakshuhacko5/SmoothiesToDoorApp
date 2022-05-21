package com.example.smoothiestodoor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smoothiestodoor.food.recepie.FoodDataModel;
import com.example.smoothiestodoor.food.recepie.FoodModel;
//import com.example.smoothiestodoor.myapiretrofit.retroclient.RetroClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAPIActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myapiactivity);
        recyclerView = findViewById(R.id.recyclerview);
        callApi();
    }

    private void callApi() {
         Call<FoodModel> call = RetroClient.getInstance().getInterface().getFood();

        call.enqueue(new Callback<FoodModel>() {
            @Override
            public void onResponse(Call<FoodModel> call, Response<FoodModel> response)
            {

                FoodModel data = response.body();
                if(response != null && response.isSuccessful())
                {
                    putDatainRecyclerView(data.getData().getFoodModel());
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "An error occured", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<FoodModel> call, Throwable throwable) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MyAPIActivity.this);
                builder.setMessage("An error Has occured ")
                        .setCancelable(false)
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                System.exit(i);

                            }
                        })
                        .setNegativeButton("Wait", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    private void putDatainRecyclerView(List<FoodDataModel> list)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RetroAdapterClass retroadapterClass = new RetroAdapterClass(this,list);
        recyclerView.setAdapter(retroadapterClass);
    }

}
