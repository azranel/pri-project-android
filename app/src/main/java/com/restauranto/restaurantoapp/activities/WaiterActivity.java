package com.restauranto.restaurantoapp.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.restauranto.restaurantoapp.R;
import com.restauranto.restaurantoapp.fragments.DishesFragment;
import com.restauranto.restaurantoapp.fragments.OrderFragment;

import java.util.LinkedList;
import java.util.List;

import api.RestaurantoAPIBuilder;
import butterknife.Bind;
import butterknife.ButterKnife;
import models.Dish;
import models.Order;
import models.Restaurant;
import models.RestaurantSet;
import models.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.FetchDishesForRestaurantService;

public class WaiterActivity extends FragmentActivity {
        @Bind(R.id.WaiterAcitivty_viewPager) ViewPager viewPager;
        MyPagerAdapter adapterViewPager;
        private OrderFragment orderFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);
        ButterKnife.bind(this);
        setupAdapter();
    }

    private void setupAdapter() {
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        new FetchDishesForRestaurantService(viewPager, adapterViewPager).call();

        orderFragment = OrderFragment.newInstance(new LinkedList<Dish>(), new LinkedList<RestaurantSet>());

        adapterViewPager.addFragment(orderFragment);
        adapterViewPager.notifyDataSetChanged();
    }

    public OrderFragment getOrderFragment() { return orderFragment; }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_waiter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sentOrderToKitchen(View view) {
        Log.v("RESTAURANTO", "Sending order...");
        int[] dishIds = new int[orderFragment.getDishes().size()];
        int[] setIds = new int[orderFragment.getSets().size()];
        for(int i = 0; i < orderFragment.getDishes().size(); i++) {
            dishIds[i] = orderFragment.getDishes().get(i).getId();
        }
        for(int j = 0; j < orderFragment.getSets().size(); j++) {
            setIds[j] = orderFragment.getSets().get(j).getId();
        }
        orderFragment.clearOrder();
        final Context context = this;

        Order order = new Order(Restaurant.pickedRestaurant.getId(), dishIds, setIds);
        new RestaurantoAPIBuilder()
                .getClientWithUser(User.loggedInUser)
                .sendOrderToKitchen(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onCompleted() {
                        Log.v("RESTAURANTO", "Order sent...");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("RESTAURANTO", "FAIL! You wont eat!");
                        Log.e("RESTAURANTO", e.getMessage());
                        Toast.makeText(context,
                                "Coś się nie udało w trakcie wysyłania zamówienia",
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onNext(Response response) {
                        Toast.makeText(context, "Wysłano zamówienie", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            fragmentList = new LinkedList<>();
        }

        public void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return 3;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
}
