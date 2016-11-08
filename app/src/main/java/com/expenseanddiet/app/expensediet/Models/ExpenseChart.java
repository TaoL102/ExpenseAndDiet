package com.expenseanddiet.app.expensediet.Models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tao on 9/10/2016.
 */

// [START post_class]
@IgnoreExtraProperties
public class ExpenseChart {

    public String yearMonth;
    public double dairyTotal;
    public double snacksAndSweetsTotal;
    public double meatAndPoultryTotal;
    public double grainsTotal;
    public double fruitsAndFruitJuiceTotal;
    public double vegetablesTotal;
    public double beveragesTotal;
    public double alcoholTotal;

    public ExpenseChart() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    // Constructor
    public ExpenseChart(String yearMonth,
                   double dairyTotal,
                   double snacksAndSweetsTotal,
                   double meatAndPoultryTotal,
                   double grainsTotal,
                   double fruitsAndFruitJuiceTotal,
                   double vegetablesTotal,
                   double beveragesTotal,
                   double alcoholTotal) {

        this.yearMonth = yearMonth;
        this.dairyTotal =dairyTotal ;
        this.snacksAndSweetsTotal = snacksAndSweetsTotal;
        this.meatAndPoultryTotal = meatAndPoultryTotal;
        this.grainsTotal = grainsTotal;
        this.fruitsAndFruitJuiceTotal = fruitsAndFruitJuiceTotal;
        this.vegetablesTotal = vegetablesTotal;
        this.beveragesTotal = beveragesTotal;
        this.alcoholTotal = alcoholTotal;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("yearMonth", yearMonth);
        result.put("dairyTotal", dairyTotal);
        result.put("snacksAndSweetsTotal", snacksAndSweetsTotal);
        result.put("meatAndPoultryTotal", meatAndPoultryTotal);
        result.put("grainsTotal", grainsTotal);
        result.put("fruitsAndFruitJuiceTotal", fruitsAndFruitJuiceTotal);
        result.put("vegetablesTotal", vegetablesTotal);
        result.put("beveragesTotal", beveragesTotal);
        result.put("alcoholTotal", alcoholTotal);
        return result;
    }
    // [END post_to_map]
}
