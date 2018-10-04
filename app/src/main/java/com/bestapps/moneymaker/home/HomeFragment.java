package com.bestapps.moneymaker.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseData;
import com.bestapps.moneymaker.db.DatabaseHandler;
import com.bestapps.moneymaker.model.Earning;
import com.bestapps.moneymaker.model.Label;
import com.bestapps.moneymaker.model.LabelEarnings;
import com.bestapps.moneymaker.recyclerview.HomeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentManager fragmentManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<LabelEarnings> labelEarnings;
    private TextView totalEarningsTextView;

    private DatabaseHandler databaseHandler;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        databaseHandler = new DatabaseHandler(getContext());

        totalEarningsTextView = view.findViewById(R.id.total_earnings);
        mRecyclerView = view.findViewById(R.id.home_recycler_view);

        handleOnBackPressed(view);
        populateArrayList();
        setUpTodayTitle();
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new HomeRecyclerViewAdapter(labelEarnings);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private void setUpTodayTitle() {
        List<Earning> earnings = DatabaseData.getEarnings();
        double totalEarnings = 0;
        for (Earning earning: earnings) {
            totalEarnings += earning.getAmount();
        }
        totalEarningsTextView.setText(totalEarnings + "$");
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

    private void populateArrayList() {
        labelEarnings = new ArrayList<>();
        int i = 0;
        for (String label: DatabaseData.getLabels()) {
            LabelEarnings labelEarning = new LabelEarnings();
            labelEarnings.add(labelEarning);

            List<Earning> earningsByLabel = databaseHandler.findEarningByLabel(label);
            double totalEarning = 0;
            for (Earning earning: earningsByLabel) {
                totalEarning += earning.getAmount();
            }

            labelEarnings.get(i).setIconId(Label.getIconId(label));
            labelEarnings.get(i).setType(label);
            labelEarnings.get(i).setTotalEarning("" + totalEarning + " $");
            i++;
        }
    }
}
