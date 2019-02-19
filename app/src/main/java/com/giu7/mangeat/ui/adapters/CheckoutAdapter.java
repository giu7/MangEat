package com.giu7.mangeat.ui.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.giu7.mangeat.R;
import com.giu7.mangeat.datamodels.Food;

import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter {

    public interface onRemovedRowListener{
        void onChange (float price);
        boolean onCheckMinOrder (float price);
    }

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Food> data;
    private onRemovedRowListener onRemovedRowListener;


    public CheckoutAdapter(Context context, ArrayList<Food> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public CheckoutAdapter.onRemovedRowListener getOnRemovedRowListener() {
        return onRemovedRowListener;
    }

    public void setOnRemovedRowListener(CheckoutAdapter.onRemovedRowListener onRemovedRowListener) {
        this.onRemovedRowListener = onRemovedRowListener;
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
        vh.prezzo.setText(String.valueOf(item.getPrezzo()*item.getQuantita()));
        vh.quantita.setText(String.valueOf(item.getQuantita()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CheckoutViewHolder extends RecyclerView.ViewHolder {
        public TextView quantita, nome, prezzo;
        public ImageButton cancella;

        public CheckoutViewHolder(View itemView) {
            super(itemView);

            quantita=itemView.findViewById(R.id.checkout_quantita);
            nome=itemView.findViewById(R.id.checkout_nome_tv);
            prezzo=itemView.findViewById(R.id.checkout_prezzo_tv);
            cancella=itemView.findViewById(R.id.checkout_cancel_btn);

            cancella.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Food food = data.get(getAdapterPosition());
                    final float price = food.getPrezzo()* food.getQuantita();
                    if (!onRemovedRowListener.onCheckMinOrder(price)){
                        AlertDialog.Builder alertMinOrder = new AlertDialog.Builder(context);
                        alertMinOrder.setMessage(R.string.sotto_min_ordine);
                        alertMinOrder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                        alertMinOrder.create().show();
                    }
                    else{
                        AlertDialog.Builder alertDelete = new AlertDialog.Builder(context);
                        alertDelete.setMessage(R.string.cancella_dialog);
                        alertDelete.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                data.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                onRemovedRowListener.onChange(price);
                            }
                        });
                        alertDelete.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                        alertDelete.create().show();
                    }
                }
            });
        }
    }
}
