package com.example.smoothiestodoor.food.recepie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodListModel {
    @SerializedName("food")
    List<FoodDataModel> foodModel;

    public List<FoodDataModel>  getFoodModel() {
        return foodModel;
    }

    public void setFoodModel(List<FoodDataModel>foodModel) {
        this.foodModel = foodModel;
    }
}
