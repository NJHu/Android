package com.example.listfiewtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class FruitAdapt extends ArrayAdapter<Fruit> {
    private static final String TAG = "FruitAdapt";
    private int resourceId;
    FruitAdapt(Context context, int itemViewId, List<Fruit> objects) {
        super(context, itemViewId, objects);
        resourceId = itemViewId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);
//        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//        ImageView fruitImageView = view.findViewById(R.id.fruit_image);
//        TextView textView = view.findViewById(R.id.fruit_name);
//        fruitImageView.setImageResource(fruit.getImageId());
//        textView.setText(fruit.getName());

        View view = null;
        if (convertView != null) {
            view = convertView;
            Log.d(TAG, "getView: convertView != null");
        }else  {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.fruitNameTextView = view.findViewById(R.id.fruit_name);
            viewHolder.fruitImageView = view.findViewById(R.id.fruit_image);
            view.setTag(viewHolder);
            Log.d(TAG, "getView: convertView == null");
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.fruitImageView.setImageResource(fruit.getImageId());
        viewHolder.fruitNameTextView.setText(fruit.getName());

        return view;
    }
    class ViewHolder {
        ImageView fruitImageView;
        TextView fruitNameTextView;
    }
}
