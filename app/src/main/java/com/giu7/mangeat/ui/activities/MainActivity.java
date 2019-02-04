package com.giu7.mangeat.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.giu7.mangeat.R;
import com.giu7.mangeat.datamodels.Restaurant;
import com.giu7.mangeat.ui.adapters.RestaurantAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList;

    /*private ArrayList<String> getData(){
        arrayList = new ArrayList<>();
        arrayList.add("Mc Donald's");
        arrayList.add("Burger King");
        arrayList.add("KFC");

        return arrayList;
    }*/

    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("Mc Donald's", "Via Tiburtina 10", 5.00f));
        arrayList.add(new Restaurant("Burger King", "Via Sandro Sandri 100", 3.00f));
        arrayList.add(new Restaurant("KFC", "Via Italia 7", 10.00f));

        return arrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RestaurantAdapter(this, getData());

        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
