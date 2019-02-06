package com.giu7.mangeat.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.giu7.mangeat.R;
import com.giu7.mangeat.Utils;
import com.giu7.mangeat.datamodels.Food;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Food> data;

    public MenuAdapter(Context context, ArrayList<Food> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
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
        vh.menuPrezzoTv.append(String.valueOf(item.getPrezzo()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        public TextView menuNomeTv;
        public TextView menuPrezzoTv;
        public TextView numeroTv;
        public Button minusBtn;
        public Button plusBtn;


        public MenuViewHolder(View itemView) {
            super(itemView);

            menuNomeTv=itemView.findViewById(R.id.menu_nome_tv);
            menuPrezzoTv=itemView.findViewById(R.id.menu_prezzo_tv);
            numeroTv=itemView.findViewById(R.id.numero_tv);
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
            int n = Integer.parseInt((String)numeroTv.getText());
            if (n==Utils.MAX_PANINI-1)
                plusBtn.setEnabled(false);
            if (n==Utils.MIN_PANINI)
                minusBtn.setEnabled(true);
            numeroTv.setText(String.valueOf(n+1));
        }

        private void minusOne(){
            int n = Integer.parseInt((String)numeroTv.getText());
            if (n== Utils.MIN_PANINI+1)
                minusBtn.setEnabled(false);
            else if (n>Utils.MIN_PANINI+1)
                minusBtn.setEnabled(true);
            if (n==Utils.MAX_PANINI)
                plusBtn.setEnabled(true);

            numeroTv.setText(String.valueOf(n-1));
        }
    }
}
