package com.bestapps.moneymaker.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.model.Earning;

import java.util.List;

public class HomeRecyclerViewAdapter
        extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHolder>  {
    private List<String> categoryList;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView category;

        public MyViewHolder(LinearLayout v) {
            super(v);
            category = v.findViewById(R.id.category);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HomeRecyclerViewAdapter(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HomeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_recycler_view_list_item, parent, false);

        HomeRecyclerViewAdapter.MyViewHolder vh = new HomeRecyclerViewAdapter.MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(HomeRecyclerViewAdapter.MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.category.setText(categoryList.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}