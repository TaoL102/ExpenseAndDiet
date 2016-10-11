package com.expenseanddiet.app.expensediet.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Tao on 9/10/2016.
 */
public class ReceiptFragment extends ReceiptListFragment {

    public ReceiptFragment() {


    }



    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("user-receipts")
                .child(getUid());
    }
}

