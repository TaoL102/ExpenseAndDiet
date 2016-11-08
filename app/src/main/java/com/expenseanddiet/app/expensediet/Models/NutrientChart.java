package com.expenseanddiet.app.expensediet.Models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tao on 9/11/2016.
 */
@IgnoreExtraProperties
public class NutrientChart {

    public String yearMonth;
    public double proteinTotal;
    public double fatTotal;
    public double carbsTotal;

    public NutrientChart() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    // Constructor
    public NutrientChart(String yearMonth,
                         double proteinTotal,
                         double fatTotal,
                         double carbsTotal) {

        this.yearMonth = yearMonth;
        this.proteinTotal = proteinTotal;
        this.fatTotal = fatTotal;
        this.carbsTotal = carbsTotal;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("yearMonth", yearMonth);
        result.put("proteinTotal", proteinTotal);
        result.put("fatTotal", fatTotal);
        result.put("carbsTotal", carbsTotal);
        return result;
    }
    // [END post_to_map]
}
