package com.giu7.mangeat.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.giu7.mangeat.R;
import com.giu7.mangeat.datamodels.Food;
import com.giu7.mangeat.datamodels.Restaurant;
import com.giu7.mangeat.services.RestController;
import com.giu7.mangeat.ui.adapters.MenuAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements MenuAdapter.onQuantityChangedListener, Response.Listener<String>, Response.ErrorListener{

    public static final String RESTAURANT_ID_KEY = "RESTAURANT_ID_KEY";
    private static final String TAG = ShopActivity.class.getSimpleName();
    private static final String PRODUCTS_ARRAY_NAME = "products";

    private RecyclerView menuRV;
    private RecyclerView.LayoutManager layoutManager;
    private MenuAdapter adapter;
    private ArrayList<Food> arrayList;

    private TextView nome, indirizzo, minOrder, totaleTv;
    private ImageView logo, map;
    private Button checkout;
    private ProgressBar progressBar;

    private Restaurant restaurant;
    private float totale = 0;

    private RestController restController;
    private String restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        //restaurant=getRestaurant();
        //restaurant.setProdotti(getData());

        nome=findViewById(R.id.shop_nome_tv);
        indirizzo=findViewById(R.id.shop_address_tv);
        checkout=findViewById(R.id.shop_checkout_btn);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopActivity.this, CheckoutActivity.class));
            }
        });
        progressBar=findViewById(R.id.shop_progress_bar);
        logo=findViewById(R.id.shop_img_iv);
        map=findViewById(R.id.shop_map_iv);
        minOrder=findViewById(R.id.shop_min_order_tv);
        totaleTv=findViewById(R.id.shop_totale_tv);

        menuRV = findViewById(R.id.menu_rv);
        layoutManager = new LinearLayoutManager(this);
        //adapter = new MenuAdapter(this, restaurant.getProdotti());
        adapter = new MenuAdapter(this);
        adapter.setOnQuantityChangedListener(this);

        menuRV.setLayoutManager(layoutManager);
        menuRV.setAdapter(adapter);

        restaurantId = getIntent().getStringExtra(ShopActivity.RESTAURANT_ID_KEY);
        restController = new RestController(this);
        restController.getRequest(Restaurant.ENDPOINT.concat(restaurantId), this, this);
    }

    private void bindData(){
        progressBar.setMax((int)(restaurant.getMinOrdine()*100));
        nome.setText(restaurant.getNome());
        indirizzo.setText(restaurant.getIndirizzo());
        Glide.with(this).load(restaurant.getImg()).into(logo);
        minOrder.setText(String.valueOf(restaurant.getMinOrdine()));
        adapter.setData(restaurant.getProdotti());
    }

    /*private Restaurant getRestaurant(){
        return new Restaurant("Pizzeria", "Via da qui", 10.00f, "http://www.lamescolanza.com/wp-content/uploads/2017/01/McDonalds.png");
    }

    private ArrayList<Food> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Food("Hamburger",5.00f,0));
        arrayList.add(new Food("Chicken Burger",3.00f,0));
        arrayList.add(new Food("Cheeseburger",10.00f,0));
        arrayList.add(new Food("Panino 4",5.00f,0));
        arrayList.add(new Food("Chicken Burger",3.00f,0));
        arrayList.add(new Food("Cheeseburger",10.00f,0));
        arrayList.add(new Food("Hamburger",5.00f,0));
        arrayList.add(new Food("Chicken Burger",3.00f,0));
        arrayList.add(new Food("Cheeseburger",10.00f,0));

        return arrayList;
    }*/

    private void updateProgress(int progress){
        progressBar.setProgress(progress);
    }

    private void updateTotal(float item){
        totale+=item;
        totaleTv.setText(String.valueOf(totale));
    }

    private void enableCheckout(){
        checkout.setEnabled(totale>=restaurant.getMinOrdine());
    }

    @Override
    public void onChange(float price) {
        updateTotal(price);
        updateProgress((int)(totale*100));
        enableCheckout();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG,error.getMessage());
        Toast.makeText(this,error.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {
        try{
            JSONObject jsonObject = new JSONObject(response);
            restaurant = new Restaurant(jsonObject);
            JSONArray jsonProds = jsonObject.getJSONArray(PRODUCTS_ARRAY_NAME);
            ArrayList<Food> prodotti = new ArrayList<>();

            for (int i = 0; i<jsonProds.length(); i++)
                prodotti.add(new Food(jsonProds.getJSONObject(i)));

            restaurant.setProdotti(prodotti);
            bindData();

        } catch (JSONException e){
            Log.e(TAG,e.getMessage());
        }
    }
}
