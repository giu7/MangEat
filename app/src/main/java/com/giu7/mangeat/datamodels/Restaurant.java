package com.giu7.mangeat.datamodels;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Restaurant {
    private String nome;
    private String indirizzo;
    private float minOrdine;
    private String img;
    private ArrayList<Food> prodotti;

    public ArrayList<Food> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Food> prodotti) {
        this.prodotti = prodotti;
    }

    public Restaurant(String nome, String indirizzo, float minOrdine) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.minOrdine = minOrdine;
        this.prodotti=new ArrayList<>();
    }

    public Restaurant(String nome, String indirizzo, float minOrdine, String img) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.minOrdine = minOrdine;
        this.img = img;
    }

    public Restaurant(JSONObject jsonRestaurant) throws JSONException {
        nome = jsonRestaurant.getString("name");
        indirizzo = jsonRestaurant.getString("address");
        minOrdine = Float.valueOf(jsonRestaurant.getString("min_order"));
        img = jsonRestaurant.getString("image_url");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public float getMinOrdine() {
        return minOrdine;
    }

    public void setMinOrdine(float minOrdine) {
        this.minOrdine = minOrdine;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
