package com.example.smoothiestodoor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewRecyclerViewFragment} factory method to
 * create an instance of this fragment.
 */
public class NewRecyclerViewFragment extends Fragment implements BackPressedListener,ItemAdapter.ItemOnclicklistener {

    public static AlertDialog alertDialog;
    RecyclerView recyclerView;
    List<Model> itemlist;
    public static BackPressedListener backPressedListener;

    public NewRecyclerViewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_recycler_view, container, false);
        initData();
        initrecyclerview(view);
        return view;
    }


    @Override
    public void onPause() {
        backPressedListener = null;
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        backPressedListener = this;
        if ( alertDialog!=null && alertDialog.isShowing() ){
            alertDialog.dismiss();
        }
    }

    @Override
    public void onBackpressed() {
        Intent intent  = new Intent(getActivity(),Signinpage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


    private void initrecyclerview(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemAdapter adapter = new ItemAdapter(itemlist, (ItemAdapter.ItemOnclicklistener) this);
        recyclerView.setAdapter(adapter);
    }

    private List<Model> initData() {
        itemlist = new ArrayList<>();
        itemlist.add(new Model(R.drawable.smoothies));
        itemlist.add(new Model(R.drawable.greens));
        itemlist.add(new Model(R.drawable.peaches));
        return itemlist;
    }


    @Override
    public void onitemclick(Model model) {

        Intent intent = new Intent(getActivity(), Informationofdrinkspage.class);
        if (model.getImage() == R.drawable.smoothies) {
            Bitmap bitmap = BitmapFactory.decodeResource
                    (getResources(), R.drawable.sixthsmoothie); // your bitmap
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
            intent.putExtra("byteArray", bs.toByteArray());
            intent.putExtra("fruitname", "Red Fruit");
        } else if (model.getImage() == R.drawable.greens) {
            Bitmap bitmap = BitmapFactory.decodeResource
                    (getResources(), R.drawable.seventhsmoothie); // your bitmap
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
            intent.putExtra("byteArray", bs.toByteArray());
            intent.putExtra("fruitname", "Green Fruit");

        } else if (model.getImage() == R.drawable.peaches) {
            Bitmap bitmap = BitmapFactory.decodeResource
                    (getResources(), R.drawable.eighthsmoothie); // your bitmap
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
            intent.putExtra("byteArray", bs.toByteArray());
            intent.putExtra("fruitname", "Peach Fruit");
        }

        startActivity(intent);
    }
}

