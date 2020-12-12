package com.bulut.garson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BasketAdapter extends BaseAdapter {

    private static ArrayList<Product> searchArrayList;
    public ViewHolder holder;
    private LayoutInflater mInflater;

    public BasketAdapter(Context context, ArrayList<Product> products) {
        searchArrayList = products;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return searchArrayList.size();
    }

    public Product getItem(int position) {
        return searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.basket_lv, null);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.price = (TextView) convertView.findViewById(R.id.price);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(searchArrayList.get(position).getName());
        holder.price.setText(searchArrayList.get(position).getPriceString());

        return convertView;
    }

    public static class ViewHolder {
        TextView name;
        TextView price;
    }

    public String getName() {
        return (String) holder.name.getText();
    }

}