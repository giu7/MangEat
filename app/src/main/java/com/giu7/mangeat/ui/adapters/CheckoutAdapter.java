package com.giu7.mangeat.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.giu7.mangeat.R;
import com.giu7.mangeat.datamodels.Food;

import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter {

    LayoutInflater inflater;
    ArrayList<Food> data;

    public CheckoutAdapter(Context context, ArrayList<Food> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.item_checkout, viewGroup, false);
        return new CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CheckoutViewHolder vh = (CheckoutViewHolder)viewHolder;
        Food item = data.get(position);

        vh.nome.setText(item.getNome());
        vh.prezzo.setText(String.valueOf(item.getPrezzo()));
        vh.quantita.setText(String.valueOf(item.getQuantita()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CheckoutViewHolder extends RecyclerView.ViewHolder {
        TextView quantita;
        TextView nome;
        TextView prezzo;
        ImageButton cancella;

        public CheckoutViewHolder(View itemView) {
            super(itemView);

            quantita=itemView.findViewById(R.id.checkout_quantita);
            nome=itemView.findViewById(R.id.checkout_nome_tv);
            prezzo=itemView.findViewById(R.id.checkout_prezzo_tv);
            cancella=itemView.findViewById(R.id.checkout_cancel_btn);

            cancella.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}
