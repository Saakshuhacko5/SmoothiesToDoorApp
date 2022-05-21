package com.example.smoothiestodoor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smoothiestodoor.food.recepie.FoodDataModel;

import java.util.List;

public class RetroAdapterClass extends RecyclerView.Adapter<RetroAdapterClass.MyViewHolder>{
    private Context context;
    private List<FoodDataModel> arrlist;
    private static final String TAG = "Adapter";
    public RetroAdapterClass(Context context, List<FoodDataModel> list) {
        this.context = context;
        this.arrlist = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.food_item,viewGroup,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        //using glidewindow to display the image
        Glide.with(context)
                .load(arrlist.get(position).getImg())
                .into(myViewHolder.img);

        myViewHolder.food_name.setText(arrlist.get(position).getFood_name());
        myViewHolder.rating.setText("Rating : " + arrlist.get(position).getRating());
        myViewHolder.price.setText("Price : " + arrlist.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return arrlist.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView img;
        TextView food_name;
        TextView price;
        TextView rating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img       = itemView.findViewById(R.id.imageView);
            food_name = itemView.findViewById(R.id.food_name);
            price     = itemView.findViewById(R.id.food_price);
            rating    = itemView.findViewById(R.id.food_rating);


        }
    }
}
