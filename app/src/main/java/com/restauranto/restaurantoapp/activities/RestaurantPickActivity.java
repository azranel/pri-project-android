package com.restauranto.restaurantoapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.restauranto.restaurantoapp.R;

import java.util.List;

import adapters.RestaurantAdapter;
import api.RestaurantoAPIBuilder;
import butterknife.Bind;
import butterknife.ButterKnife;
import models.Restaurant;
import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import services.FetchRestaurantService;

public class RestaurantPickActivity extends AppCompatActivity {

    @Bind(R.id.RestaurantActivity_list) ListView restaurantsListView;
    private RestaurantAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_pick);
        ButterKnife.bind(this);
        fetchRestaurantsAndAddThemToAdapter();
    }

    private void fetchRestaurantsAndAddThemToAdapter() {
        final Context context = this;
        Log.v("RESTAURANTO", "Fetching resturants...");
        new RestaurantoAPIBuilder()
                .getClientWithUser(User.loggedInUser)
                .fetchRestaurants(new Callback<List<Restaurant>>() {
                    @Override
                    public void success(List<Restaurant> restaurantList, Response response) {
                        Log.v("RESTAURANTO", "Success fetching " + String.valueOf(restaurantList.size()) + " restaurants");
                        restaurantAdapter = new RestaurantAdapter(restaurantList, context);
                        restaurantsListView.setAdapter(restaurantAdapter);
                        restaurantsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Restaurant restaurant = (Restaurant) restaurantAdapter.getItem(position);
                                Restaurant.pickedRestaurant = restaurant;
                                Intent changeToPickUpModeActivity = new Intent(context, ModePickActivity.class);
                                startActivity(changeToPickUpModeActivity);
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("RESTAURANTO", "Failed to fetch restaurants");
                        Log.e("RESTAURANTO", error.getMessage());
                        error.printStackTrace();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant_pick, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
