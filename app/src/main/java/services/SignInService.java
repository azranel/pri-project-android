package services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.restauranto.restaurantoapp.R;
import com.restauranto.restaurantoapp.activities.RestaurantPickActivity;

import api.RestaurantoAPI;
import api.RestaurantoAPIBuilder;
import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bartoszlecki on 8/31/15.
 */
public class SignInService {
    private String login;
    private String password;
    private Context context;

    public SignInService(Context context, String login, String password) {
        this.login = login;
        this.password = password;
        this.context = context;
    }

    public void call() {
        RestaurantoAPI apiHandler = new RestaurantoAPIBuilder().getClient();

        apiHandler.signIn(login, password, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Log.v("RESTAURANTO", "SUCCESS");
                User.loggedInUser = user;
                Intent intent = new Intent(context, RestaurantPickActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RESTAURANTO", "FAIL");
                Log.e("RESTAURANTO", error.getMessage());
            }
        });

    }
}
