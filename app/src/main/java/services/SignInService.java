package services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.restauranto.restaurantoapp.R;
import com.restauranto.restaurantoapp.activities.RestaurantPickActivity;

import api.RestaurantoAPI;
import api.RestaurantoAPIBuilder;
import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        final Observable<User> user = apiHandler
                .signIn(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        user.subscribe(new Observer<User>() {
            @Override
            public void onCompleted() {
                Log.v("RESTAURANTO", "SUCCESS");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RESTAURANTO", "FAIL");
                Log.e("RESTAURANTO", e.getMessage());
                Toast.makeText(context, "Zły login lub hasło", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(User user) {
                User.loggedInUser = user;
                Intent intent = new Intent(context, RestaurantPickActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
