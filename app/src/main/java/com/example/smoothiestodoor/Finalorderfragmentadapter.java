package com.example.smoothiestodoor;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class Finalorderfragmentadapter extends FragmentStatePagerAdapter {
    List<OrderEntity> list = new ArrayList<>();
    public Finalorderfragmentadapter( FragmentManager fm,List<OrderEntity> list ) {
        super(fm);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        fragment = FinalorderViewPagerfragment.newInstance(list.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
