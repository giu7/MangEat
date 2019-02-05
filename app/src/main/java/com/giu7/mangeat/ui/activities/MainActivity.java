package com.giu7.mangeat.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.giu7.mangeat.R;
import com.giu7.mangeat.Utils;
import com.giu7.mangeat.datamodels.Restaurant;
import com.giu7.mangeat.ui.adapters.RestaurantAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList;

    SharedPreferences prefs;


    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("Mc Donald's", "Via Tiburtina 10", 5.00f, R.drawable.mcdonalds));
        arrayList.add(new Restaurant("Burger King", "Via Sandro Sandri 100", 3.00f, R.drawable.burgerking));
        arrayList.add(new Restaurant("KFC", "Via Italia 7", 10.00f, R.drawable.kfc));

        return arrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RestaurantAdapter(this, getData());

        prefs = getSharedPreferences(Utils.PACKAGE_NAME,MODE_PRIVATE);
        if (prefs != null) {
            adapter.setGridMode(prefs.getBoolean("viewMode",true));
        }

        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = prefs.edit();
        preferencesEditor.putBoolean("viewMode", adapter.isGridMode());
        preferencesEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.view_mode){
            setLayoutManager();
            return true;
        }
        if (item.getItemId()==R.id.login_menu){
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }
        else if (item.getItemId()==R.id.checkout_menu){
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void setLayoutManager(){
        layoutManager = new LinearLayoutManager(this);
        adapter.setGridMode(!adapter.isGridMode());
        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
    }
}
