package com.restauranto.restaurantoapp.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        new FetchRestaurantService(this, restaurantAdapter)
                .addListView(restaurantsListView)
                .call();
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
