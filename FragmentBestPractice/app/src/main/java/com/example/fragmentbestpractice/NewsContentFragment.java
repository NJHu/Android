package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsContentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_content_fragment, container, false);
        return view;
    }
    public void refresh(String newsTitle, String newsContent) {
        View view = getView();
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView titleTextView = view.findViewById(R.id.news_title);
        TextView contentTextView = view.findViewById(R.id.news_content);
        titleTextView.setText(newsTitle);
        contentTextView.setText(newsContent);
    }
}
