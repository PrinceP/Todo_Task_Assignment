package com.iiitd.prince.assignmentfour;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Prince on 31-10-2016.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoHolder>
{
    private List<TodoTask> feedItemList;
    private Context mContext;

    public TodoAdapter(Context context, List<TodoTask> feedItemList){
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TodoHolder viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        viewHolder = new TodoHolder(layoutView, feedItemList);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(TodoHolder holder, int position) {
        holder.categoryTitle.setText(feedItemList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }





}

