package com.example.bloomexplorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StoreAdapter extends BaseAdapter {

    private Context context;
    private List<String> storeNames;
    private String[] storeDescriptions;
    private int[] storeImages;

    public StoreAdapter(Context context, List<String> storeNames, String[] storeDescriptions, int[] storeImages) {
        this.context = context;
        this.storeNames = storeNames;
        this.storeDescriptions = storeDescriptions;
        this.storeImages = storeImages;
    }

    @Override
    public int getCount() {
        return storeNames.size();
    }

    @Override
    public Object getItem(int position) {
        return storeNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.itemTitle);
            holder.description = convertView.findViewById(R.id.itemDescription);
            holder.image = convertView.findViewById(R.id.itemImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Set data
        holder.title.setText(storeNames.get(position));
        holder.description.setText(storeDescriptions[position]);
        holder.image.setImageResource(storeImages[position]);

        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView description;
        ImageView image;
    }
}
