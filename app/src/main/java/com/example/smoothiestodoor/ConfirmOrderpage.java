package com.example.smoothiestodoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smoothiestodoor.databinding.ConfirmorderpageBinding;
import com.example.smoothiestodoor.databinding.DiscoversmoothiesBinding;

import java.util.ArrayList;

public class ConfirmOrderpage extends AppCompatActivity {
    ConfirmorderpageBinding confirmorderpageBinding;
    ArrayList<String> display = new ArrayList<String>();
    String fselect = "";
    static String nameofsmoothie = "", useremail = "";
    static int noofservings, totalprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmorderpage);

        confirmorderpageBinding = ConfirmorderpageBinding.inflate(getLayoutInflater());
        View view = confirmorderpageBinding.getRoot();
        setContentView(view);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
        useremail = sharedPreferences.getString("email", "");
        noofservings = Selectionofingredients.setTexts();
        nameofsmoothie = getIntent().getStringExtra("fruitname");
        confirmorderpageBinding.t2.setText(nameofsmoothie + " *  " + noofservings + " Servings");
        totalprice = 50 * noofservings;
        String s = totalprice + "  USD";
        confirmorderpageBinding.totalusdprice.setText(s);
        display = Selectionofingredients.setstring();
        for (String sel : display) {
            fselect = fselect + sel;
        }
        confirmorderpageBinding.t4.setText(fselect);
        confirmorderpageBinding.buttonconfirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveorderdetails();

            }
        });
    }

    //Make it async method task.
    private void saveorderdetails() {

        new Saveorderdetails().execute();
    }


    public static String valuefetch() {
        return nameofsmoothie;
    }


    class Saveorderdetails extends AsyncTask<String, Void, Void>
    {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            Log.d("a string of pre execute","");
        }

        @Override
        protected Void doInBackground(String... strings) {
            UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
            OrdersDao ordersDao = userDatabase.OrdersDao();
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setUseremail(useremail);
            orderEntity.setNameofsmothie(nameofsmoothie);
            orderEntity.setNoofservings(noofservings);
            orderEntity.setTotalprice(totalprice);
            ordersDao.insert(orderEntity);
            Log.d("a string of do execute","");
            return null;

        }

        @Override
        protected void onPostExecute(Void unused) {
            Log.d("a of post execute","");
            openscreen();
        }
        public void openscreen() {
            Intent intent = new Intent(ConfirmOrderpage.this, LoadingScreenpage.class);
            intent.putExtra("fruitname", nameofsmoothie);
            startActivity(intent);
        }
    }
}


































    //    public void select(View v) {
//        boolean check = ((CheckBox) v).isChecked();
//        switch (v.getId()) {
//            case R.id.checkBox1:
//                String st1 = Integer.toString(R.id.textone);
//                if (check) {
//                    display.add(st1);
//                } else {
//                    display.remove(st1);
//                }
//                break;
//            case R.id.checkBox2:
//                String st2 = Integer.toString(R.id.texttwo);
//                if (check) {
//                    display.add(st2);
//                } else {
//                    display.remove(st2);
//                }
//                break;
//            case R.id.checkBox3:
//                String st3 = Integer.toString(R.id.textthree);
//                if (check) {
//                    display.add(st3);
//                } else {
//                    display.remove(st3);
//                }
//                break;
//            case R.id.checkBox4:
//                String st4 = Integer.toString(R.id.textfour);
//                if (check) {
//                    display.add(st4);
//                } else {
//                    display.remove(st4);
//                }
//                break;
//            case R.id.checkBox5:
//                String st5 = Integer.toString(R.id.textfive);
//                if (check) {
//                    display.add(st5);
//                } else {
//                    display.remove(st5);
//                }
//                break;
//            case R.id.checkBox6:
//                String st6 = Integer.toString(R.id.textsix);
//                if (check) {
//                    display.add(st6);
//                } else {
//                    display.remove(st6);
//                }
//                break;
//            default:
//                break;
//        }

