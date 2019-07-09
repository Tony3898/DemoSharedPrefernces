package com.android.tony.demosharedpreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText username, password;
    ArrayList<String> usernamelist,passwordlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void demoSharedPreferences(View view) {


        if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {

            /*sharedPreferences.edit().putString("username", username.getText().toString()).apply();
            sharedPreferences.edit().putString("password", password.getText().toString()).apply();*/

            usernamelist = new ArrayList<>();
            passwordlist = new ArrayList<>();

            usernamelist.add(username.getText().toString());
            passwordlist.add(password.getText().toString());

            Log.i("log", "demoSharedPreferences: " + usernamelist);
            Log.i("log", "demoSharedPreferences: " + passwordlist);

            try {
                sharedPreferences.edit().putString("username",ObjectSerializer.serialize(usernamelist)).apply();
                sharedPreferences.edit().putString("password",ObjectSerializer.serialize(passwordlist)).apply();
                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Username and Password cannot be empty", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detailsmenuitem:
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
