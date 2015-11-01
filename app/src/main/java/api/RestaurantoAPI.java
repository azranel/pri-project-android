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
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by bartoszlecki on 8/31/15.
 */
public interface RestaurantoAPI {
    @FormUrlEncoded
    @POST("/users/sign_in.json")
    Observable<User> signIn(@Field("user[email]") String login, @Field("user[password]") String password);

    @GET("/api/restaurants.json")
    Observable<List<Restaurant>> fetchRestaurants();

    @GET("/api/dishes")
    Observable<List<Dish>> fetchDishesForRestaurant(@Query("restaurant_id") int restaurantId);

    @GET("/api/sets")
    Observable<List<RestaurantSet>> fetchSetsForRestaurant(@Query("restaurant_id") int restaurantId);

    @GET("/api/restaurants/{id}/orders.json")
    Observable<List<Order>> fetchOrdersForRestaurant(@Path("id") int restaurantId);

    @GET("/api/orders/{id}")
    Observable<OrderWithFood> fetchOrder(@Path("id") int orderId);

    @PUT("/api/orders/{id}/next_step")
    Observable<Response> moveOrderToNextStep(@Path("id") int orderId);

    @POST("/api/orders")
    Observable<Response> sendOrderToKitchen(@Body Order order);
}
