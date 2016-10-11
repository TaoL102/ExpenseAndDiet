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
    public long time;

    public Receipt() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Receipt(String uid, String receiptId, double totalPrice, int totalQuantity) {
        this.uid = uid;
        this.receiptId = receiptId;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.time= Calendar.getInstance().getTimeInMillis();
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("receiptId", receiptId);
        result.put("totalPrice", totalPrice);
        result.put("totalQuantity", totalQuantity);
        result.put("time", time);
        return result;
    }
    // [END post_to_map]
}
