package services;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import adapters.RestaurantAdapter;
import api.RestaurantoAPIBuilder;
import models.Restaurant;
import models.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bartoszlecki on 9/6/15.
 */
public class FetchRestaurantService {
    private final Context context;
    private RestaurantAdapter restaurantAdapter;
    private ListView restaurantsListView;


    public FetchRestaurantService(Context context, RestaurantAdapter adapter) {
        this.context = context;
        this.restaurantAdapter = adapter;
    }

    public FetchRestaurantService addListView(ListView restaurantListView) {
        this.restaurantsListView = restaurantListView;
        return this;
    }

    public void call() {

    }
}
