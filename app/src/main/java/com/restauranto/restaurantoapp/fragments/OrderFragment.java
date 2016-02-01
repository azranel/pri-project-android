package com.restauranto.restaurantoapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.restauranto.restaurantoapp.R;

import java.io.Serializable;
import java.util.List;

import adapters.DishesAdapter;
import adapters.RestaurantAdapter;
import adapters.RestaurantSetsAdapter;
import models.Dish;
import models.RestaurantSet;

/**
 * Created by bartoszlecki on 9/8/15.
 */
public class OrderFragment extends Fragment {
    private static final String DISHES_COLLECTION = "DISHES COLLECTION";
    private static final String SETS_COLLECTION = "SETS COLLECTION";

    private List<Dish> dishes;
    private List<RestaurantSet> sets;

    public RestaurantSetsAdapter getSetsAdapter() {
        return setsAdapter;
    }

    public DishesAdapter getDishesAdapter() {
        return dishesAdapter;
    }

    private DishesAdapter dishesAdapter;
    private RestaurantSetsAdapter setsAdapter;



    public static OrderFragment newInstance(List<Dish> dishes, List<RestaurantSet> sets) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putSerializable(DISHES_COLLECTION, (Serializable) dishes);
        args.putSerializable(SETS_COLLECTION, (Serializable) sets);
        fragment.setArguments(args);
        return fragment;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
    public List<RestaurantSet> getSets() {
        return sets;
    }

    public OrderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            dishes = (List<Dish>) getArguments().getSerializable(DISHES_COLLECTION);
            sets = (List<RestaurantSet>) getArguments().getSerializable(SETS_COLLECTION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment, container, false);
        final ListView dishesListView = (ListView) view.findViewById(R.id.OrderFragment_dishesListView);
        final ListView setsListView = (ListView) view.findViewById(R.id.OrderFragment_setsListView);
        final Context context = getActivity();
        dishesAdapter = new DishesAdapter(context, dishes);
        setsAdapter = new RestaurantSetsAdapter(context, sets);
        dishesListView.setAdapter(dishesAdapter);
        setsListView.setAdapter(setsAdapter);
        dishesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dish dish = (Dish) dishesAdapter.getItem(position);
                dishes.remove(dish);
                dishesAdapter.notifyDataSetChanged();
            }
        });
        setsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RestaurantSet set = (RestaurantSet) setsAdapter.getItem(position);
                sets.remove(set);
                setsAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    public void clearOrder() {
        dishes.clear();
        sets.clear();
        dishesAdapter.notifyDataSetChanged();
        setsAdapter.notifyDataSetChanged();
    }
}
