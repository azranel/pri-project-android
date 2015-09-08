package services;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.restauranto.restaurantoapp.activities.WaiterActivity;
import com.restauranto.restaurantoapp.fragments.DishesFragment;
import com.restauranto.restaurantoapp.fragments.RestaurantSetsFragment;

import java.util.List;

import api.RestaurantoAPI;
import api.RestaurantoAPIBuilder;
import models.Dish;
import models.Restaurant;
import models.RestaurantSet;
import models.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bartoszlecki on 9/8/15.
 */
public class FetchDishesForRestaurantService {
    private ViewPager viewPager;
    private WaiterActivity.MyPagerAdapter adapter;

    public FetchDishesForRestaurantService(ViewPager viewPager, WaiterActivity.MyPagerAdapter adapter) {
        this.viewPager = viewPager;
        this.adapter = adapter;
    }

    public void call() {
        RestaurantoAPI api = new RestaurantoAPIBuilder()
                .getClientWithUser(User.loggedInUser);

        fetchDishes(api);
        fetchRestaurantSets(api);
        createBlankOrder(api);
    }

    private void fetchDishes(RestaurantoAPI api) {
        api.fetchDishesForRestaurant(Restaurant.pickedRestaurant.getId(), new Callback<List<Dish>>() {
            @Override
            public void success(List<Dish> dishes, Response response) {
                adapter.addFragment(DishesFragment.newInstance(dishes));
                Log.v("RESTAURANTO", "Dishes count: " + String.valueOf(dishes.size()));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RESTAURANTO", "Failed to fetch dishes...");
                Log.e("RESTAURANTO", error.getMessage());
            }
        });
    }

    private void fetchRestaurantSets(RestaurantoAPI api) {
        api.fetchSetsForRestaurant(Restaurant.pickedRestaurant.getId(),
                new Callback<List<RestaurantSet>>() {
            @Override
            public void success(List<RestaurantSet> restaurantSets, Response response) {
                adapter.addFragment(RestaurantSetsFragment.newInstance(restaurantSets));
                viewPager.setAdapter(adapter);
                Log.v("RESTAURANTO", "Restaurant sets count: " + String.valueOf(restaurantSets.size()));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RESTAURANTO", "Failed to fetch restaurant sets...");
                Log.e("RESTAURANTO", error.getMessage());
            }
        });
    }

    private void createBlankOrder(RestaurantoAPI api) {
//        adapter.addFragment(OrderFragment.newInstance());
    }
}
