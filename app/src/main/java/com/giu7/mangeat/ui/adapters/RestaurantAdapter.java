package com.giu7.mangeat.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giu7.mangeat.R;
import com.giu7.mangeat.datamodels.Restaurant;
import com.giu7.mangeat.ui.activities.ShopActivity;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;
    private Context context;
    private boolean isGridMode;

    public boolean isGridMode(){
        return  isGridMode;
    }

    public void setGridMode(boolean mode){
        isGridMode=mode;
    }

    public RestaurantAdapter(Context context, ArrayList<Restaurant> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layout = isGridMode? R.layout.item_restaurant_grid : R.layout.item_restaurant;
        View view = inflater.inflate(layout, viewGroup, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        RestaurantViewHolder vh = (RestaurantViewHolder)viewHolder;
        Restaurant item = data.get(position);

        vh.restaurantName.setText(item.getNome());
        vh.restaurantAddress.setText(item.getIndirizzo());
        vh.restaurantMinOrder.append(String.valueOf(item.getMinOrdine()));
        vh.restaurantImage.setImageResource(item.getImg());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{

        public TextView restaurantName;
        public TextView restaurantAddress;
        public TextView restaurantMinOrder;
        public ImageView restaurantImage;
        //public Button menuBtn;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            restaurantName=itemView.findViewById(R.id.name_tv);
            restaurantAddress=itemView.findViewById(R.id.address_tv);
            restaurantMinOrder= itemView.findViewById(R.id.min_order_tv);
            restaurantImage=itemView.findViewById(R.id.img_iv);
            /*menuBtn=itemView.findViewById(R.id.menu_btn);
            menuBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ShopActivity.class));
                }
            });*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ShopActivity.class));
                }
            });
        }
    }
}
