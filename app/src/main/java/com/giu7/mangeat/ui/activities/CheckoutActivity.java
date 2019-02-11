package com.giu7.mangeat.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.giu7.mangeat.R;
import com.giu7.mangeat.datamodels.Food;
import com.giu7.mangeat.datamodels.Ordine;
import com.giu7.mangeat.datamodels.Restaurant;
import com.giu7.mangeat.ui.adapters.CheckoutAdapter;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private CheckoutAdapter adapter;

    private TextView nome;
    private TextView indirizzo;
    private Button paga;
    private ImageView logo;
    private TextView minOrder;
    private TextView totaleTv;

    private Ordine ordine;
    private Restaurant restaurant;
    private ArrayList<Food> arrayList;
    private float totale;

    private Restaurant getRestaurant(){
        return new Restaurant("Pizzeria", "Via da qui", 10.00f, R.drawable.pizza);
    }

    private ArrayList<Food> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Food("Hamburger",5.00f,1));
        arrayList.add(new Food("Chicken Burger",3.00f,2));
        arrayList.add(new Food("Cheeseburger",10.00f,3));

        return arrayList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        restaurant=getRestaurant();
        ordine=new Ordine(restaurant,getData(),41f);

        nome = findViewById(R.id.check_nome_tv);
        indirizzo = findViewById(R.id.check_address_tv);
        paga = findViewById(R.id.check_paga_btn);
        minOrder = findViewById(R.id.check_min_order_tv);
        totaleTv = findViewById(R.id.check_totale_tv);
        logo = findViewById(R.id.check_img_iv);

        rv = findViewById(R.id.checkout_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CheckoutAdapter(this, ordine.getFoods());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        nome.setText(restaurant.getNome());
        indirizzo.setText(restaurant.getIndirizzo());
        logo.setImageResource(restaurant.getImg());
        minOrder.setText(String.valueOf(restaurant.getMinOrdine()));
        totaleTv.setText(String.valueOf(ordine.getTotale()));

    }
}
