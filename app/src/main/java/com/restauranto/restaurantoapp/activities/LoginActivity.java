package com.restauranto.restaurantoapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.restauranto.restaurantoapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import services.SignInService;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.LoginActivity_loginInput) EditText loginInput;
    @Bind(R.id.LoginActivity_passwordInput) EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void signInToRestauranto(View view) {
        Log.v("RESTAURANTO", "lol");
        new SignInService(
                this,
                loginInput.getText().toString(),
                passwordInput.getText().toString()
        ).call();
    }
}
