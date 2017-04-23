package com.example.rusty.society_shopping;

import org.json.JSONException;
import org.json.JSONObject;

public class EstimateObject {
    String id, name, qty, price, total;
    int position;

    public EstimateObject(String id, String name, String qty, String price, String total, int position) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.total = total;
        this.position = position;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("Id", id);
            obj.put("Name", name);
            obj.put("Qty", qty);
            obj.put("Price", price);
            obj.put("Total", total);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}