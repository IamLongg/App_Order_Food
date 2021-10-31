package com.example.app_order_food.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.app_order_food.Cart;
import com.example.app_order_food.Database.Database;
import com.example.app_order_food.Interface.ItemClickListener;
import com.example.app_order_food.Model.Order;
import com.example.app_order_food.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Order> listData = new ArrayList<>();
    private Context context;
    Database db;
    public void showData() {
        listData.clear();
        listData.addAll(db.getCarts());
    }

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cart_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+listData.get(position).getQuantity(), Color.RED);
        holder.img_cart_count.setImageDrawable(drawable);

        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(position).getPrice())) * (Integer.parseInt(listData.get(position).getQuantity()));
        holder.txt_price.setText(fmt.format(price));
        Database db = new Database(context);
        holder.cart_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteByID(listData.get(holder.getAdapterPosition()).getProductId());
                Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                //showData();
                listData.clear();
                listData.addAll(db.getCarts());
                ((Cart)context).finish();
                Intent intent = new Intent(context, Cart.class);
                context.startActivity(intent);

            }
        });
        holder.txt_cart_name.setText(listData.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txt_cart_name, txt_price;
        public ImageView img_cart_count,cart_item_delete;
        private ItemClickListener itemClickListener;
        public void setTxt_cart_name(TextView txt_cart_name) {
            this.txt_cart_name = txt_cart_name;
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_cart_name = (TextView) itemView.findViewById(R.id.cart_item_name);
            txt_price = (TextView) itemView.findViewById(R.id.cart_item_Price);
            img_cart_count = (ImageView) itemView.findViewById(R.id.cart_item_count);
            cart_item_delete = (ImageView) itemView.findViewById(R.id.cart_item_delete);
    }
    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
    }
}
