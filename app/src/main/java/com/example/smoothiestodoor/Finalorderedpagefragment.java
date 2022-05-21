package com.example.smoothiestodoor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.LuhnChecksumValidator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class Finalorderedpagefragment extends Fragment {
    ViewPager viewPager;
    String useremail;
    Finalorderfragmentadapter finalorderfragmentadapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_message_finalorderedpage,
                container, false);
        viewPager = view.findViewById(R.id.viewpagers);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        new fetchorderdetails().execute();
    }


    class fetchorderdetails extends AsyncTask<String, Void, List<OrderEntity>> {
        OrdersDao ordersDao;

        @Override
        protected void onPreExecute() {
            UserDatabase userDatabase = UserDatabase.getUserDatabase(getContext());
            ordersDao = userDatabase.OrdersDao();
            super.onPreExecute();
            Log.d("a string of pre execute", "");
        }

        @Override
        protected List<OrderEntity> doInBackground(String... strings) {
            List<OrderEntity> list = new ArrayList<>();
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
            if(!(sharedPreferences.contains("Myuserpref")))
            {
                useremail = sharedPreferences.getString("email", "");
                 list = ordersDao.getAll(useremail);
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<OrderEntity> list) {
            Log.d("a of post execute", "");
            if (list != null && list.size() > 0 ) {
                finalorderfragmentadapter = new Finalorderfragmentadapter(getChildFragmentManager(), list);
                viewPager.setAdapter(finalorderfragmentadapter);
            } else {
                Toast.makeText(getContext(), "List is null", Toast.LENGTH_SHORT).show();
            }

        }
    }
}










































//    TextView noofservings, finalprice;
//    ImageView imageView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.finalordereddrinkspage);
//        noofservings = findViewById(R.id.noofservings);
//        String st = ConfirmOrderpage.valuefetch();
//        imageView = findViewById(R.id.ordersfruits);
//        finalprice = findViewById(R.id.finalprice);
//        if(st.equals ("Red Fruit"))
//        {
//            imageView.setImageResource(R.drawable.redfruits);
//        }
//        else if(st.equals("Green Fruit") )
//        {
//            imageView.setImageResource(R.drawable.greenfruits);
//        }
//        else if(st.equals("Peach Fruit"))
//        {
//            imageView.setImageResource(R.drawable.peachfruits);
//        }
//        int n = Selectionofingredients.setTexts();
//        noofservings.setText(Integer.toString(n));
//        int v = 50 * n;
//        String s = v + "  USD";
//        finalprice.setText(s);
//
