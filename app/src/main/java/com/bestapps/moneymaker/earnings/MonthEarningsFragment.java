package com.bestapps.moneymaker.earnings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseData;
import com.bestapps.moneymaker.model.Earning;
import com.bestapps.moneymaker.recyclerview.TodayRecyclerViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonthEarningsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private android.support.v7.widget.RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FragmentManager fragmentManager;

    public MonthEarningsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_month_earnings, container, false);
        if (DatabaseData.getEarnings() != null ) {
            mRecyclerView = view.findViewById(R.id.today_recycler_view);

            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            List<Earning> earnings = filterEarningsByMonth();
            mAdapter = new TodayRecyclerViewAdapter(earnings);
            mRecyclerView.setAdapter(mAdapter);
        }
        return view;
    }

    private List<Earning> filterEarningsByMonth() {
        List<Earning> allEarnings = DatabaseData.getEarnings();
        List<Earning> earnings = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for (Earning earning: allEarnings) {
            Date date = null;
            try {
                date = format.parse(earning.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) {
                Date currentDate = new Date();
                int currentMonth = currentDate.getMonth();
                if (currentMonth == date.getMonth()) {
                    earnings.add(earning);
                }
            }
        }
        return earnings;
    }
}
