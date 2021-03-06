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
import android.widget.Toast;

import com.restauranto.restaurantoapp.R;

import java.io.Serializable;
import java.util.List;

import adapters.OrdersAdapter;
import api.RestaurantoAPIBuilder;
import butterknife.Bind;
import butterknife.ButterKnife;
import models.Order;
import models.Restaurant;
import models.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class KitchenActivity extends AppCompatActivity {

    @Bind(R.id.KitchenActivity_ordersList) ListView ordersListView;
    private OrdersAdapter ordersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        ButterKnife.bind(this);
        getOrdersAndBindToAdapter();
        final Context context = this;
        ordersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order order = (Order) ordersAdapter.getItem(position);
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra(OrderDetailActivity.ORDER_KEY, order.getId());
                startActivity(intent);
            }
        });
        ordersListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Order order = (Order) ordersAdapter.getItem(position);
                Log.v("RESTAURANTO", "Sending order from kitchen to client...");
                new RestaurantoAPIBuilder().getClientWithUser(User.loggedInUser)
                        .moveOrderToNextStep(order.getId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Response>() {
                            @Override
                            public void onCompleted() {
                                Log.v("RESTAURANTO", "Done!");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("RESTAURANTO", "Failed to next step... :(");
                                Log.e("RESTAURANTO", e.getMessage());
                            }

                            @Override
                            public void onNext(Response response) {
                                ordersAdapter.removeOrderFromList(order);
                                Toast.makeText(context, "Zamówienie wykonane!", Toast.LENGTH_SHORT).show();
                            }
                        });
                return true;
            }
        });
    }

    private void getOrdersAndBindToAdapter() {
        final Context context = this;
        new RestaurantoAPIBuilder()
                .getClientWithUser(User.loggedInUser)
                .fetchOrdersForRestaurant(Restaurant.pickedRestaurant.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Order>>() {
                    @Override
                    public void onCompleted() {
                        Log.v("RESTAURANTO", "Fetched orders!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("RESTAURANTO", "Failed to fetch orders :(");
                        Log.e("RESTAURANTO", e.getMessage());
                    }

                    @Override
                    public void onNext(List<Order> orders) {
                        ordersAdapter = new OrdersAdapter(context, orders);
                        ordersListView.setAdapter(ordersAdapter);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kitchen, menu);
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
