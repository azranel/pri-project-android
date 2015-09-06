package com.restauranto.restaurantoapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.restauranto.restaurantoapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModePickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_pick);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mode_pick, menu);
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

    @OnClick(R.id.ModePickActivity_buttons_waiter)
    public void pickWaiterMode(View view) {
        Log.v("RESTAURANTO", "Picked up waiter mode...");

    }

    @OnClick(R.id.ModePickActivity_buttons_kitchen)
    public void pickKitchenMode(View view) {
        Log.v("RESTAURANTO", "Picked up kitchen mode...");

    }

}
