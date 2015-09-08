package com.restauranto.restaurantoapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.restauranto.restaurantoapp.R;

import java.io.Serializable;
import java.util.List;

import adapters.DishesAdapter;
import adapters.RestaurantSetsAdapter;
import models.Dish;
import models.RestaurantSet;

public class RestaurantSetsFragment extends Fragment {

    private static final String SETS_COLLECTION = "SETS COLLECTION";

    private List<RestaurantSet> restaurantSets;
    private RestaurantSetsAdapter restaurantSetAdapter;

    public static RestaurantSetsFragment newInstance(List<RestaurantSet> restaurantSets) {
        RestaurantSetsFragment fragment = new RestaurantSetsFragment();
        Bundle args = new Bundle();
        args.putSerializable(SETS_COLLECTION, (Serializable) restaurantSets);
        fragment.setArguments(args);
        return fragment;
    }

    public RestaurantSetsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            restaurantSets = (List<RestaurantSet>) getArguments().getSerializable(SETS_COLLECTION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.restaurant_sets_fragment, container, false);
        final ListView dishesListView = (ListView) view.findViewById(R.id.RestaurantSetsFragment_setsListView);
        final Context context = getActivity();
        restaurantSetAdapter = new RestaurantSetsAdapter(context, restaurantSets);
        dishesListView.setAdapter(restaurantSetAdapter);
        return view;
    }
}
