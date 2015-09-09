package com.restauranto.restaurantoapp.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.restauranto.restaurantoapp.R;

import adapters.DishesAdapter;
import adapters.RestaurantSetsAdapter;
import api.RestaurantoAPIBuilder;
import butterknife.Bind;
import butterknife.ButterKnife;
import models.Order;
import models.OrderWithFood;
import models.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrderDetailActivity extends AppCompatActivity {

    @Bind(R.id.OrderDetailActivity_dishesListView) ListView dishesListView;
    @Bind(R.id.OrderDetailActivity_setsListView) ListView setsListView;
    private RestaurantSetsAdapter setsAdapter;
    private DishesAdapter dishesAdapter;

    public static final String ORDER_KEY = "ORDER KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        getFoodForListViews();
    }

    private void getFoodForListViews() {
        final Context context = this;
        int orderId = getOrderFromIntent();
        new RestaurantoAPIBuilder()
                .getClientWithUser(User.loggedInUser)
                .fetchOrder(orderId, new Callback<OrderWithFood>() {
                    @Override
                    public void success(OrderWithFood orderWithFood, Response response) {
                        Log.v("RESTAURANTO", "Fetched order!");
                        setsAdapter = new RestaurantSetsAdapter(context, orderWithFood.getRestaurantSets());
                        dishesAdapter = new DishesAdapter(context, orderWithFood.getDishes());
                        dishesListView.setAdapter(dishesAdapter);
                        setsListView.setAdapter(setsAdapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v("RESTAURANTO", "Failed to fetch order!");
                        Log.e("RESTAURANTO", error.getMessage());
                    }
                });
    }

    private int getOrderFromIntent() {
        return getIntent().getIntExtra(ORDER_KEY, -1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_detail, menu);
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
