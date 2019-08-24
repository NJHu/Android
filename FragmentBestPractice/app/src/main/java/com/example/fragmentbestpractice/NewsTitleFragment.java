package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_fragment, container,false);

        RecyclerView recyclerView = view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        NewsAdapter adapter = new NewsAdapter(NewsTitleFragment.this.getNews());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            News news = new News();
            news.setName("aaa" + i * i);

            Random random = new Random();
            int len = random.nextInt(1000) + 1;
            StringBuilder builder = new StringBuilder();
            while (len > 0) {
                len --;
                builder.append("aaa" + i * i);
            }

            news.setContent(builder.toString());
            newsList.add(news);
        }
        return newsList;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isTwoPane = getActivity().findViewById(R.id.news_content_layout) != null;
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView newsTitleTextView;
            public ViewHolder (View view) {
                super(view);
                newsTitleTextView = view.findViewById(R.id.news_title);
            }
        }

        public NewsAdapter(List<News> mNewsList) {
            this.mNewsList = mNewsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane) {

                        NewsContentFragment newsContentFragment = (NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getName(), news.getContent());

                    }else  {
                        NewsContentActivity.actionStart(getActivity(), news.getName(), news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleTextView.setText(news.getName());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }
}
