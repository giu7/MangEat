package com.giu7.mangeat.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.giu7.mangeat.R;
import com.giu7.mangeat.datamodels.Food;
import com.giu7.mangeat.datamodels.Restaurant;
import com.giu7.mangeat.ui.adapters.MenuAdapter;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements MenuAdapter.onQuantityChangedListener{

    private RecyclerView menuRV;
    private RecyclerView.LayoutManager layoutManager;
    private MenuAdapter adapter;
    private ArrayList<Food> arrayList;

    private TextView nome;
    private TextView indirizzo;
    private Button checkout;
    private ProgressBar progressBar;
    private ImageView logo;
    private ImageView map;
    private TextView minOrder;
    private TextView totaleTv;

    private Restaurant restaurant;
    private float totale = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        restaurant=getRestaurant();
        restaurant.setProdotti(getData());

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
        progressBar.setMax((int)(restaurant.getMinOrdine()*100));
        logo=findViewById(R.id.shop_img_iv);
        map=findViewById(R.id.shop_map_iv);
        minOrder=findViewById(R.id.shop_min_order_tv);
        totaleTv=findViewById(R.id.shop_totale_tv);

        menuRV = findViewById(R.id.menu_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MenuAdapter(this, restaurant.getProdotti());
        adapter.setOnQuantityChangedListener(this);

        menuRV.setLayoutManager(layoutManager);
        menuRV.setAdapter(adapter);


        nome.setText(restaurant.getNome());
        indirizzo.setText(restaurant.getIndirizzo());
        Glide.with(this).load(restaurant.getImg()).into(logo);
        minOrder.setText(String.valueOf(restaurant.getMinOrdine()));
    }

    private Restaurant getRestaurant(){
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
    }

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
}
