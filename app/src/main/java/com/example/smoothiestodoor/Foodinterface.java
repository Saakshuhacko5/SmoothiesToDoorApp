package com.example.smoothiestodoor;

import com.example.smoothiestodoor.food.recepie.FoodModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Foodinterface {

    @GET("foodrecipes")
    Call<FoodModel> getFood();

}
