package com.example.smoothiestodoor;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FinalorderViewPagerfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinalorderViewPagerfragment extends Fragment {
    TextView noofservings, finalprice;
    ImageView imageView;
    ViewPager viewPager;
    Finalorderfragmentadapter finalorderfragmentadapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private OrderEntity order;


    public FinalorderViewPagerfragment(OrderEntity orderEntity) {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment FinalorderViewPagerfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FinalorderViewPagerfragment newInstance(OrderEntity orders) {
        FinalorderViewPagerfragment fragment = new FinalorderViewPagerfragment(orders);
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,orders);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            order = (OrderEntity) getArguments().getSerializable(ARG_PARAM1);
        }
    }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            View view = inflater.inflate(R.layout.fragment_finalorder_view_pagerfragment, container, false);
            noofservings = view.findViewById(R.id.noofservings);
            String st = order.getNameofsmothie();
            viewPager = view.findViewById(R.id.viewpagers);
            imageView = view.findViewById(R.id.orderfruits);
            finalprice = view.findViewById(R.id.finalprice);
            if (st.equals("Red Fruit")) {
                imageView.setImageResource(R.drawable.redfruits);
            } else if (st.equals("Green Fruit")) {
                imageView.setImageResource(R.drawable.greenfruits);
            } else if (st.equals("Peach Fruit")) {
                imageView.setImageResource(R.drawable.peachfruits);
            }
            int n = order.getNoofservings();
            noofservings.setText(Integer.toString(n));
            int price = order.getTotalprice();
            finalprice.setText(Integer.toString(price));
            return view;
    }
}