package com.restauranto.restaurantoapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.restauranto.restaurantoapp.R;
import com.restauranto.restaurantoapp.activities.WaiterActivity;

import java.io.Serializable;
import java.util.List;

import adapters.DishesAdapter;
import models.Dish;
import models.Order;

public class DishesFragment extends Fragment {

    private static final String DISHES_COLLECTION = "DISHES COLLECTION";

    private List<Dish> dishes;
    private DishesAdapter dishesAdapter;

    public static DishesFragment newInstance(List<Dish> dishes) {
        DishesFragment fragment = new DishesFragment();
        Bundle args = new Bundle();
        args.putSerializable(DISHES_COLLECTION, (Serializable) dishes);
        fragment.setArguments(args);
        return fragment;
    }

    public DishesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            dishes = (List<Dish>) getArguments().getSerializable(DISHES_COLLECTION);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dishes_fragment, container, false);
        final ListView dishesListView = (ListView) view.findViewById(R.id.DishesFragment_dishesListView);
        final Context context = getActivity();
        dishesAdapter = new DishesAdapter(context, dishes);
        dishesListView.setAdapter(dishesAdapter);

        dishesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dish selectedDish = (Dish) dishesAdapter.getItem(position);
                ((WaiterActivity) context).getOrderFragment().getDishes().add(selectedDish);
                ((WaiterActivity) context).getOrderFragment().getDishesAdapter().notifyDataSetChanged();
            }
        });
        return view;
    }
}
