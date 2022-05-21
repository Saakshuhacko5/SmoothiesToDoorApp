package com.example.smoothiestodoor;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class FragmentAdapter extends FragmentPagerAdapter {

    private Context context;
    int total;

    public FragmentAdapter(Context context, @NonNull FragmentManager fm, int total) {
        super(fm);
        this.context = context;
        this.total   = total;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Ingredientsfragment first = new Ingredientsfragment();
                return first;
            case 1:
                Preparationfragment second = new Preparationfragment();
                return second;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return total;
    }


}