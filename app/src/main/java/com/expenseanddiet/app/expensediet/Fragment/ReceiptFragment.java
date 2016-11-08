package com.expenseanddiet.app.expensediet.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Tao on 9/10/2016.
 */
public class ReceiptFragment extends ReceiptListFragment {

    private static final String TAG = "ReceiptFragment";

    // fields
    long startDate=0;
    long endDate=0;

    public ReceiptFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get parameters
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            startDate = bundle.getLong("startDate");
            endDate= bundle.getLong("endDate");
        }
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        Log.d(TAG,startDate+","+endDate);
        Query query=databaseReference.child("user-receipts")
                .child(getUid())
                .orderByChild("time")
                .startAt(startDate)
                .endAt(endDate);

        return query;


    }
}

