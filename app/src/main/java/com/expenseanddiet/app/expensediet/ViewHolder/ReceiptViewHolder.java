package com.expenseanddiet.app.expensediet.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.expenseanddiet.app.expensediet.Models.Receipt;
import com.expenseanddiet.app.expensediet.R;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class ReceiptViewHolder extends RecyclerView.ViewHolder {

    public TextView dateView;
    public TextView totalPriceView;
    public ImageView vendorView;


    public ReceiptViewHolder(View itemView) {
        super(itemView);

        dateView = (TextView) itemView.findViewById(R.id.receipt_date);
        totalPriceView = (TextView) itemView.findViewById(R.id.receipt_total_price);
        vendorView = (ImageView) itemView.findViewById(R.id.receipt_vendor);

    }

    public void bindToPost(Receipt receipt, View.OnClickListener starClickListener) {
        dateView.setText(new SimpleDateFormat("dd MM yyyy", Locale.getDefault()).format(receipt.time));
        totalPriceView.setText(Double.toString(receipt.totalPrice));
        /*vendorView.setOnClickListener(starClickListener);*/
    }
}