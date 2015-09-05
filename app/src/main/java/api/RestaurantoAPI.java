package api;

import java.util.List;

import models.Restaurant;
import models.User;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by bartoszlecki on 8/31/15.
 */
public interface RestaurantoAPI {
    @FormUrlEncoded
    @POST("/users/sign_in.json")
    public void signIn(@Field("user[email]") String login, @Field("user[password]") String password, Callback<User> callback);

    @GET("/api/restaurants.json")
    public void fetchRestaurants(Callback<List<Restaurant>> callback);
}
