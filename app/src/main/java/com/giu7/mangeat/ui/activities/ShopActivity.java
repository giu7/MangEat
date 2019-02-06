package com.giu7.mangeat.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.giu7.mangeat.R;
import com.giu7.mangeat.datamodels.Food;
import com.giu7.mangeat.ui.adapters.MenuAdapter;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    RecyclerView menuRV;
    RecyclerView.LayoutManager layoutManager;
    MenuAdapter adapter;
    ArrayList<Food> arrayList;

    private ArrayList<Food> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Food("Hamburger",5.00f));
        arrayList.add(new Food("Chicken Burger",3.00f));
        arrayList.add(new Food("Cheeseburger",10.00f));
        arrayList.add(new Food("Hamburger",5.00f));
        arrayList.add(new Food("Chicken Burger",3.00f));
        arrayList.add(new Food("Cheeseburger",10.00f));
        arrayList.add(new Food("Hamburger",5.00f));
        arrayList.add(new Food("Chicken Burger",3.00f));
        arrayList.add(new Food("Cheeseburger",10.00f));

        return arrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        menuRV = findViewById(R.id.menu_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MenuAdapter(this, getData());

        menuRV.setLayoutManager(layoutManager);
        menuRV.setAdapter(adapter);
    }
}
