package com.giu7.mangeat.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.giu7.mangeat.R;
import com.giu7.mangeat.Utils;
import com.giu7.mangeat.datamodels.Food;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter {

    public interface onQuantityChangedListener{
        void onChange(float price);
    }

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Food> data;
    private onQuantityChangedListener onQuantityChangedListener;

    /*public MenuAdapter(Context context, ArrayList<Food> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }*/

    public MenuAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context=context;
        data = new ArrayList<>();
    }

    public void setData(ArrayList<Food> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public MenuAdapter.onQuantityChangedListener getOnQuantityChangedListener() {
        return onQuantityChangedListener;
    }

    public void setOnQuantityChangedListener(MenuAdapter.onQuantityChangedListener onQuantityChangedListener) {
        this.onQuantityChangedListener = onQuantityChangedListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.item_menu, viewGroup, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MenuViewHolder vh = (MenuViewHolder)viewHolder;
        Food item=data.get(position);

        vh.menuNomeTv.setText(item.getNome());
        vh.menuPrezzoTv.setText(String.valueOf(item.getPrezzo()));
        vh.quantitaTv.setText(String.valueOf(item.getQuantita()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        public TextView menuNomeTv;
        public TextView menuPrezzoTv;
        public TextView quantitaTv;
        public Button minusBtn;
        public Button plusBtn;


        public MenuViewHolder(View itemView) {
            super(itemView);

            menuNomeTv=itemView.findViewById(R.id.menu_nome_tv);
            menuPrezzoTv=itemView.findViewById(R.id.menu_prezzo_tv);
            quantitaTv =itemView.findViewById(R.id.numero_tv);
            minusBtn=itemView.findViewById(R.id.minus_btn);
            plusBtn=itemView.findViewById(R.id.plus_btn);

            plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plusOne();
                }
            });

            minusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    minusOne();
                }
            });
        }

        private void plusOne(){
            Food food = data.get(getAdapterPosition());
            int n = food.getQuantita();
            if (n==Utils.MAX_PANINI) return;
            food.plusQuantita();
            notifyItemChanged(getAdapterPosition());
            onQuantityChangedListener.onChange(food.getPrezzo());
        }

        private void minusOne(){
            Food food = data.get(getAdapterPosition());
            int n = food.getQuantita();
            if (n==0) return;
            food.minusQuantita();
            notifyItemChanged(getAdapterPosition());
            onQuantityChangedListener.onChange(food.getPrezzo()*(-1));
        }
    }
}
