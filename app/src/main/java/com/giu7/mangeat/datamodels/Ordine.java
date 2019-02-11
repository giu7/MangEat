package com.giu7.mangeat.datamodels;

import java.util.ArrayList;

public class Ordine {
    private Restaurant restaurant;
    private ArrayList<Food> foods;
    private float totale;

    public Ordine(Restaurant restaurant, ArrayList<Food> foods, float totale) {
        this.restaurant = restaurant;
        this.foods = foods;
        this.totale = totale;
    }

    public Ordine(Restaurant restaurant, ArrayList<Food> foods) {
        this.restaurant = restaurant;
        this.foods = foods;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
}
