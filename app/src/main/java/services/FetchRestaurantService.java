package services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.restauranto.restaurantoapp.activities.ModePickActivity;

import java.util.List;

import adapters.RestaurantAdapter;
import api.RestaurantoAPI;
import api.RestaurantoAPIBuilder;
import models.Restaurant;
import models.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        RestaurantoAPI api = new RestaurantoAPIBuilder()
                .getClientWithUser(User.loggedInUser);

        Observable<List<Restaurant>> restaurants = api
                .fetchRestaurants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        restaurants.subscribe(new Observer<List<Restaurant>>() {
            @Override
            public void onCompleted() {
                Log.v("RESTAURANTO", "Success fetching restaurants");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RESTAURANTO", "Failed to fetch restaurants");
                Log.e("RESTAURANTO", e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Restaurant> restaurants) {
                restaurantAdapter = new RestaurantAdapter(restaurants, context);
                restaurantsListView.setAdapter(restaurantAdapter);
                restaurantsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Restaurant restaurant = (Restaurant) restaurantAdapter.getItem(position);
                    Restaurant.pickedRestaurant = restaurant;
                    Intent changeToPickUpModeActivity = new Intent(context, ModePickActivity.class);
                    context.startActivity(changeToPickUpModeActivity);
                    }
                });
            }
        });
    }
}
