package com.expenseanddiet.app.expensediet.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Tao on 9/11/2016.
 */

public class NutrientChartFragment extends NutrientChartListFragment {

    private static final String TAG = "NutrientChartFragment";

    // fields
    String yearMonth="";

    public NutrientChartFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get parameters
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            yearMonth = bundle.getString("yearMonth");
        }

    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {

        return databaseReference.child("user-monthlynutrients")
                .child(getUid()).orderByChild("yearMonth").equalTo(yearMonth);



    }
}

