package api;

import com.restauranto.restaurantoapp.R;

import models.User;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import utils.Utils;

/**
 * Created by bartoszlecki on 9/5/15.
 */
public class RestaurantoAPIBuilder {



    public RestaurantoAPI getClient() {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Utils.HOST)
                .build()
                .create(RestaurantoAPI.class);
    }

    public RestaurantoAPI getClientWithUser(final User loggedInUser) {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Utils.HOST)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("X-User-Email", loggedInUser.getEmail());
                        request.addHeader("X-User-Token", loggedInUser.getAuthenticationToken());
                    }
                }).build()
                .create(RestaurantoAPI.class);
    }
}
