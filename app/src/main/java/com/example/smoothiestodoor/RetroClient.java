package com.example.smoothiestodoor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static RetroClient instance;
    private Foodinterface foodinterface;

    public static synchronized RetroClient getInstance() {
        if (instance == null) {
            instance = new RetroClient();
        }
        return instance;
    }

    private RetroClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demo8023047.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        foodinterface = retrofit.create(Foodinterface.class);

    }

    public Foodinterface getInterface() {
        return foodinterface;
    }

}
