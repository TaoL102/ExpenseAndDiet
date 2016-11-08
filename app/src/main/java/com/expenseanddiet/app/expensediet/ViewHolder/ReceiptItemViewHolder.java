package com.expenseanddiet.app.expensediet.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.expenseanddiet.app.expensediet.Models.Item;
import com.expenseanddiet.app.expensediet.R;

/**
 * Created by Tao on 7/11/2016.
 */

public class ReceiptItemViewHolder extends RecyclerView.ViewHolder {

    public TextView itemNameView;
    public TextView itemPriceView;
    public ImageView vendorView;

    // TODO: 20/10/2016  ALL have to be modified.

    public ReceiptItemViewHolder(View itemView) {
        super(itemView);

        itemNameView = (TextView) itemView.findViewById(R.id.item_name);
        itemPriceView = (TextView) itemView.findViewById(R.id.item_price);
        vendorView = (ImageView) itemView.findViewById(R.id.receipt_vendor);

    }

    public void bindToPost(Item item, View.OnClickListener starClickListener) {
        itemNameView.setText(item.name);
        itemPriceView.setText(Double.toString(item.price));
        /*vendorView.setOnClickListener(starClickListener);*/
    }
}