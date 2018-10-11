package com.bestapps.moneymaker.earnings;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseData;
import com.bestapps.moneymaker.db.DatabaseHandler;
import com.bestapps.moneymaker.home.HomeFragment;
import com.bestapps.moneymaker.model.Earning;
import com.bestapps.moneymaker.recyclerview.TodayRecyclerViewAdapter;

import java.util.List;

public class TodayEarningsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton addEarningButton;
    private FragmentManager fragmentManager;
    private TextView totalAmountTextView;


    public TodayEarningsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_today_earning, container, false);
        addEarningButton = view.findViewById(R.id.fab);
        totalAmountTextView = view.findViewById(R.id.today_amount);
        handleOnBackPressed(view);
        loadEarnings();
        if (DatabaseData.getEarnings() != null) {
            mRecyclerView = view.findViewById(R.id.today_recycler_view);

            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new TodayRecyclerViewAdapter(DatabaseData.getEarnings());
            mRecyclerView.setAdapter(mAdapter);

            setTotalAmount(DatabaseData.getEarnings());
        }

        addEarningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_placeholder, new AddEarningFragment());
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void setTotalAmount(List<Earning> earnings) {
        Double max = 0.0;
        for (Earning earning: earnings) {
            max += earning.getAmount();
        }
        totalAmountTextView.setText("$" + max);
    }

    private void loadEarnings() {
        DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
        List<Earning> earningList = databaseHandler.findAllEarnings();
        DatabaseData.setEarnings(earningList);
    }

    private void handleOnBackPressed(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    onBackPressed();
                    return true;
                }
                return false;
            }
        });
    }


    public void onBackPressed() {
        fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, new HomeFragment());
        fragmentTransaction.commit();
    }
}
