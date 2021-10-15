package com.example.app_order_food.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_order_food.Interface.ItemClickListener;
import com.example.app_order_food.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;
    public void setItemClickListener (ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public MenuViewHolder (View itemView){
        super(itemView);
        txtMenuName = (TextView)itemView.findViewById(R.id.menu_name);
        imageView = (ImageView) itemView.findViewById(R.id.menu_image);


        itemView.setOnClickListener(this);

    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
