package com.giu7.mangeat.datamodels;

import org.json.JSONException;
import org.json.JSONObject;

public class Food {
    private String nome;
    private float prezzo;
    private int quantita;
    private String id;

    public Food(String nome, float prezzo, int quantita) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public Food(String nome, float prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public Food (JSONObject jsonFood) throws JSONException{
        nome = jsonFood.getString("name");
        prezzo = (float)jsonFood.getDouble("price");
        quantita = 0;
        id = jsonFood.getString("id");
    }

    public int getQuantita() {
        return quantita;
    }

    public void plusQuantita(){
        if (quantita==10)
            return;
        quantita++;
    }

    public void minusQuantita(){
        if (quantita==0)
            return;
        quantita--;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
}
