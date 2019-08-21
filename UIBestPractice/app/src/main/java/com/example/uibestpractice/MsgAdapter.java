package com.example.uibestpractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLinearLayout;
        LinearLayout rightLinearLayout;
        TextView leftMsgTextView;
        TextView rightMsgTextView;
        ViewHolder(View view) {
            super(view);
            leftLinearLayout = view.findViewById(R.id.left_layout);
            rightLinearLayout = view.findViewById(R.id.right_layout);
            leftMsgTextView = view.findViewById(R.id.left_msg);
            rightMsgTextView = view.findViewById(R.id.right_msg);
        }
    }

    private List<Msg> mMsgList;

    MsgAdapter(List<Msg> msgList) {
        mMsgList = msgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVED) {

            holder.leftLinearLayout.setVisibility(View.VISIBLE);
            holder.rightLinearLayout.setVisibility(View.GONE);
            holder.leftMsgTextView.setText(msg.getContent());

        }else if (msg.getType() == Msg.TYPE_SEND) {
            holder.rightLinearLayout.setVisibility(View.VISIBLE);
            holder.leftLinearLayout.setVisibility(View.GONE);
            holder.rightMsgTextView.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
