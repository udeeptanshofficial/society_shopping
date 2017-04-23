package com.example.rusty.society_shopping;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rusty on 4/23/2017.
 */

public class ShoplistAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> shopname;
    ArrayList<String> shopaddress;
    LayoutInflater layoutInflater;

    public ShoplistAdapter(Activity context, ArrayList<String> shopname, ArrayList<String> shopaddress)
    {
        this.context = context;
        this.shopname = shopname;
        this.shopaddress = shopaddress;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.shopname.size();
    }

    @Override
    public Object getItem(int position) {
        return this.shopname.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.shops,null);
        TextView shop = (TextView) convertView.findViewById(R.id.shopname);
        TextView shopaddres = (TextView) convertView.findViewById(R.id.shopaddress);

        shop.setText(shopname.get(position));
        shopaddres.setText(shopaddress.get(position));

        return convertView;
    }
}
