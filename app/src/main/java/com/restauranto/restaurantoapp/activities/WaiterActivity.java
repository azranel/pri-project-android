package com.restauranto.restaurantoapp.activities;

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

import com.restauranto.restaurantoapp.R;
import com.restauranto.restaurantoapp.fragments.DishesFragment;
import com.restauranto.restaurantoapp.fragments.OrderFragment;

import java.util.LinkedList;
import java.util.List;

import api.RestaurantoAPIBuilder;
import butterknife.Bind;
import butterknife.ButterKnife;
import models.Dish;
import models.Restaurant;
import models.RestaurantSet;
import models.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import services.FetchDishesForRestaurantService;

public class WaiterActivity extends FragmentActivity {
    @Bind(R.id.WaiterAcitivty_viewPager) ViewPager viewPager;
    MyPagerAdapter adapterViewPager;
    private DishesFragment orderFragment;


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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_waiter, menu);
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
            return 2;
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
