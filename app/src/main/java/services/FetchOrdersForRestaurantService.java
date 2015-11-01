package services;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import adapters.OrdersAdapter;
import adapters.RestaurantAdapter;
import api.RestaurantoAPI;
import api.RestaurantoAPIBuilder;
import models.Order;
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
public class FetchOrdersForRestaurantService {
    private final Restaurant restaurant;
    private final Context context;
    private OrdersAdapter ordersAdapter;
    private ListView ordersListView;

    public FetchOrdersForRestaurantService(Restaurant restaurant, Context context, OrdersAdapter ordersAdapter, ListView ordersListView) {
        this.restaurant = Restaurant.pickedRestaurant;
        this.context = context;
        this.ordersAdapter = ordersAdapter;
        this.ordersListView = ordersListView;
    }


    public void call() {
        RestaurantoAPI api = new RestaurantoAPIBuilder()
                .getClientWithUser(User.loggedInUser);

        Observable<List<Order>> orders = api.fetchOrdersForRestaurant(restaurant.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        orders.subscribe(new Observer<List<Order>>() {
            @Override
            public void onCompleted() {
                Log.v("RESTAURANTO", "Fetched orders successfully!");

            }

            @Override
            public void onError(Throwable e) {
                Log.e("RESTAURANTO", "Failed to fetch orders for restaurant");
                Log.e("RESTAURANTO", e.getMessage());
            }

            @Override
            public void onNext(List<Order> orders) {
                ordersAdapter = new OrdersAdapter(context, orders);
                ordersListView.setAdapter(ordersAdapter);
                //TODO: Set onClickListener to ordersListView
            }
        });
    }
}
