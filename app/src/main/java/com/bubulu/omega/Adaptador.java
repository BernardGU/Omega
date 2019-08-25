package com.bubulu.omega;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<Dog> listItems;

    public Adaptador(Context context, ArrayList<Dog> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dog Item = (Dog) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item, null);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvBreed = (TextView) convertView.findViewById(R.id.tvBreed);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.layout);

        tvName.setText(Item.getName());
        tvBreed.setText(Item.getBreed());
        tvDescription.setText(Item.getDescription());
        if(Item.getEncontrado()) {
            layout.setBackgroundColor(Color.parseColor("#006633"));
        } else {
            layout.setBackgroundColor(Color.parseColor("#990033"));
        }

        return convertView;
    }
}
