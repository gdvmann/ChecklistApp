package com.example.dev.proj3gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dev on 3/6/2016.
 */
public class ItemsArrayAdapter extends ArrayAdapter<String> {

    Context mContext;
    ArrayList<String> mArrayList;

    public ItemsArrayAdapter(Context context, ArrayList<String> arrayList){
        super(context,R.layout.items_list ,arrayList);

        mContext = context;
        mArrayList = arrayList;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.items_list, parent, false);

            holder = new ViewHolder();
            holder.itemName = (TextView) convertView.findViewById(R.id.item_text);
            convertView.setTag(holder);

        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }


        holder.itemName.setText(mArrayList.get(position));

        return convertView;
    }

    private static class ViewHolder {

        TextView itemName;
    }
}
