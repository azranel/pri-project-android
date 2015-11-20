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
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

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

        Observable.zip(
                api.fetchDishesForRestaurant(Restaurant.pickedRestaurant.getId()),
                api.fetchSetsForRestaurant(Restaurant.pickedRestaurant.getId()),
                new Func2<List<Dish>, List<RestaurantSet>, Object>() {
            @Override
            public Object call(List<Dish> dishes, List<RestaurantSet> restaurantSets) {
                adapter.addFragment(DishesFragment.newInstance(dishes));
                adapter.addFragment(RestaurantSetsFragment.newInstance(restaurantSets));
                return null;
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                Log.v("RESTAURANTO", "Done fetching, setting adapter...");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RESTAURANTO", "Shit is not working");
                e.printStackTrace();
            }

            @Override
            public void onNext(Object o) {
                viewPager.setAdapter(adapter);
            }
        });
    }
}
