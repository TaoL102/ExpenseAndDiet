package com.expenseanddiet.app.expensediet.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Tao on 7/11/2016.
 */

public class ReceiptItemFragment extends ReceiptItemListFragment {

    private String receiptId;

    public ReceiptItemFragment() {


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get parameters
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            receiptId = bundle.getString("receiptId");
        }
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("receipt-items").child(receiptId);
    }
}