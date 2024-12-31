package com.example.bloomexplorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FlowerAdapter extends BaseAdapter {

    private Context context;
    private List<Flower> flowers;

    public FlowerAdapter(Context context, List<Flower> flowers) {
        this.context = context;
        this.flowers = flowers;
    }

    @Override
    public int getCount() {
        return flowers.size();
    }

    @Override
    public Object getItem(int position) {
        return flowers.get(position);
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
            holder.flowerName = convertView.findViewById(R.id.itemTitle);
            holder.flowerImage = convertView.findViewById(R.id.itemImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Flower flower = flowers.get(position);
        holder.flowerName.setText(flower.getName());
        Picasso.get().load(flower.getImageUrl()).into(holder.flowerImage);

        return convertView;
    }

    private static class ViewHolder {
        TextView flowerName;
        ImageView flowerImage;
    }
}
