package com.bestapps.moneymaker.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.model.Earning;

import java.util.List;

public class TodayRecyclerViewAdapter
        extends RecyclerView.Adapter<TodayRecyclerViewAdapter.MyViewHolder>  {
//    private List<Earning> earningList;
    private String[] test = {"First description",  "Second description",
        "Second description", "Second description", "Second description",
        "Second description", "Second description", "Second description",
        "Third description", "Third description", "Third description"};
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView description;
        public MyViewHolder(LinearLayout v) {
            super(v);
            description = v.findViewById(R.id.description);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TodayRecyclerViewAdapter(List<Earning> earningList) {
        earningList = earningList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TodayRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.today_earning_list_row, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.description.setText(test[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return test.length;
    }
}
