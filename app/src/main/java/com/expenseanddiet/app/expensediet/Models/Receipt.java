package com.expenseanddiet.app.expensediet.Models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tao on 9/10/2016.
 */

// [START post_class]
@IgnoreExtraProperties
public class Receipt {

    public String uid;
    public String receiptId;
    public double totalPrice;
    public int totalQuantity;
    public double dairyTotal;
    public double snacksAndSweetsTotal;
    public double meatAndPoultryTotal;
    public double grainsTotal;
    public double fruitsAndFruitJuiceTotal;
    public double vegetablesTotal;
    public double beveragesTotal;
    public double alcoholTotal;
    public double proteinTotal;
    public double fatTotal;
    public double carbsTotal;
    public long time;

    public Receipt() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    // Constructor
    public Receipt(String uid,
                   String receiptId,
                   double totalPrice,
                   int totalQuantity,
                   double dairyTotal,
                   double snacksAndSweetsTotal,
                   double meatAndPoultryTotal,
                   double grainsTotal,
                   double fruitsAndFruitJuiceTotal,
                   double vegetablesTotal,
                   double beveragesTotal,
                   double alcoholTotal,
                   double proteinTotal,
                   double fatTotal,
                   double carbsTotal) {

        this.uid = uid;
        this.receiptId = receiptId;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.dairyTotal =dairyTotal ;
        this.snacksAndSweetsTotal = snacksAndSweetsTotal;
        this.meatAndPoultryTotal = meatAndPoultryTotal;
        this.grainsTotal = grainsTotal;
        this.fruitsAndFruitJuiceTotal = fruitsAndFruitJuiceTotal;
        this.vegetablesTotal = vegetablesTotal;
        this.beveragesTotal = beveragesTotal;
        this.alcoholTotal = alcoholTotal;
        this.proteinTotal = proteinTotal;
        this.fatTotal = fatTotal;
        this.carbsTotal = carbsTotal;
        this.time= Calendar.getInstance().getTimeInMillis();
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
      //  result.put("uid", uid);
       // result.put("receiptId", receiptId);
        result.put("totalPrice", totalPrice);
        result.put("totalQuantity", totalQuantity);
        result.put("time", time);
        result.put("dairyTotal", dairyTotal);
        result.put("snacksAndSweetsTotal", snacksAndSweetsTotal);
        result.put("meatAndPoultryTotal", meatAndPoultryTotal);
        result.put("grainsTotal", grainsTotal);
        result.put("fruitsAndFruitJuiceTotal", fruitsAndFruitJuiceTotal);
        result.put("vegetablesTotal", vegetablesTotal);
        result.put("beveragesTotal", beveragesTotal);
        result.put("alcoholTotal", alcoholTotal);
        result.put("proteinTotal", proteinTotal);
        result.put("fatTotal", fatTotal);
        result.put("carbsTotal", carbsTotal);
        return result;
    }
    // [END post_to_map]
}
