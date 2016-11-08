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
public class ReceiptItem {

    public String itemID;
    public String receiptId;
    public String name;
    public double price;

    public ReceiptItem() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public ReceiptItem(String itemID, String receiptId, String name, double price) {
        this.itemID = itemID;
        this.receiptId = receiptId;
        this.price = price;
        this.name=name;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
      //  result.put("itemID", itemID);
      //  result.put("receiptId", receiptId);
        result.put("price", price);
        result.put("name",name);
        return result;
    }
    // [END post_to_map]
}
