package services;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import adapters.OrdersAdapter;
import adapters.RestaurantAdapter;
import api.RestaurantoAPIBuilder;
import models.Order;
import models.Restaurant;
import models.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
        new RestaurantoAPIBuilder()
                .getClientWithUser(User.loggedInUser)
                .fetchOrdersForRestaurant(restaurant.getId(), new Callback<List<Order>>() {
                    @Override
                    public void success(List<Order> orders, Response response) {
                        Log.v("RESTAURANTO", "Fetched orders successfully!");
                        ordersAdapter = new OrdersAdapter(context, orders);
                        ordersListView.setAdapter(ordersAdapter);
                        //TODO: Set onClickListener to ordersListView
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("RESTAURANTO", "Failed to fetch orders for restaurant");
                        Log.e("RESTAURANTO", error.getMessage());
                    }
                });
    }
}
