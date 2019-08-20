package com.example.recyclerviewtest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;
    private static final String TAG = "FruitAdapter";

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImageView;
        TextView fruitNameTextView;
        ViewHolder(View view) {
            super(view);
            fruitImageView = view.findViewById(R.id.fruit_image);
            fruitNameTextView = view.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        Log.d(TAG, "onCreateViewHolder: ");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(view.getContext(), "you clicked view \n" + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.fruitImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(view.getContext(), "you clicked image \n" + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return mFruitList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        super.onBindViewHolder(holder, position, payloads);
        Fruit fruit = mFruitList.get(position);
        holder.fruitImageView.setImageResource(fruit.getImage());
        holder.fruitNameTextView.setText(fruit.getName());
        Log.d(TAG, "onBindViewHolder: ");
    }
}
