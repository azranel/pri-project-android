package api;

import java.util.List;

import models.Dish;
import models.Order;
import models.OrderWithFood;
import models.Restaurant;
import models.RestaurantSet;
import models.User;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by bartoszlecki on 8/31/15.
 */
public interface RestaurantoAPI {
    @FormUrlEncoded
    @POST("/users/sign_in.json")
    void signIn(@Field("user[email]") String login, @Field("user[password]") String password, Callback<User> callback);

    @GET("/api/restaurants.json")
    void fetchRestaurants(Callback<List<Restaurant>> callback);

    @GET("/api/dishes")
    void fetchDishesForRestaurant(@Query("restaurant_id") int restaurantId, Callback<List<Dish>> callback);

    @GET("/api/sets")
    void fetchSetsForRestaurant(@Query("restaurant_id") int restaurantId, Callback<List<RestaurantSet>> callback);

    @GET("/api/restaurants/{id}/orders.json")
    void fetchOrdersForRestaurant(@Path("id") int restaurantId, Callback<List<Order>> callback);

    @GET("/api/orders/{id}")
    void fetchOrder(@Path("id") int orderId, Callback<OrderWithFood> callback);

    @POST("/api/orders")
    void sendOrderToKitchen(@Body Order order,
                            Callback<Response> callback);
}
