package com.example.smoothiestodoor.food.recepie;

import com.google.gson.annotations.SerializedName;

public class FoodModel {
    @SerializedName("result")
    Boolean result = false;

    @SerializedName("data")
    FoodListModel data;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public FoodListModel getData() {
        return data;
    }

    public void setData(FoodListModel data) {
        this.data = data;
    }
}
