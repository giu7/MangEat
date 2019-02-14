package com.giu7.mangeat.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.giu7.mangeat.R;
import com.giu7.mangeat.Utils;
import com.giu7.mangeat.datamodels.Restaurant;
import com.giu7.mangeat.ui.adapters.RestaurantAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList = new ArrayList<>();

    SharedPreferences prefs;


    /*private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("Mc Donald's", "Via Tiburtina 10", 5.00f, "http://www.lamescolanza.com/wp-content/uploads/2017/01/McDonalds.png"));
        arrayList.add(new Restaurant("Burger King", "Via Sandro Sandri 100", 3.00f, "https://media-cdn.tripadvisor.com/media/photo-s/11/0f/6d/9c/burger-king.jpg"));
        arrayList.add(new Restaurant("KFC", "Via Italia 7", 10.00f, "https://www.hadalsame.com/wp-content/uploads/2018/08/b-163.jpg"));

        return arrayList;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);

        layoutManager = new LinearLayoutManager(this);
        adapter = new RestaurantAdapter(this);

        prefs = getSharedPreferences(Utils.PACKAGE_NAME,MODE_PRIVATE);
        if (prefs != null) {
            adapter.setGridMode(prefs.getBoolean("viewMode",true));
        }

        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://5ba19290ee710f0014dd764c.mockapi.io/api/v1/restaurant";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, // HTTP request method
                url, // Server link
                new Response.Listener<String>() {  // Listener for successful response
                    @Override
                    public void onResponse(String response) {
                        Log.d("mainactivity",response);
                        //Start parsing
                        try {
                            JSONObject responseJson = new JSONObject(response);
                            JSONArray restaurantJsonArray = responseJson.getJSONArray("data");
                            for (int i = 0; i< restaurantJsonArray.length(); i++){
                                Restaurant restaurant = new Restaurant(restaurantJsonArray.getJSONObject(i));
                                arrayList.add(restaurant);
                            }
                            adapter.setData(arrayList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() { // Listener for error response
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("mainactivity",error.getMessage());
                    }
                }
        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
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
        else if (item.getItemId()==R.id.login_menu){
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
