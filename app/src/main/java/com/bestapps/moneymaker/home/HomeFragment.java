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

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseData;
import com.bestapps.moneymaker.recyclerview.HomeRecyclerViewAdapter;
import com.bestapps.moneymaker.recyclerview.TodayRecyclerViewAdapter;
import com.bestapps.moneymaker.register.RegisterFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentManager fragmentManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> chaptersArrayList;

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
        handleOnBackPressed(view);
        populateArrayList();
        mRecyclerView = view.findViewById(R.id.home_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new HomeRecyclerViewAdapter(chaptersArrayList);
        mRecyclerView.setAdapter(mAdapter);
        return view;
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
        chaptersArrayList = new ArrayList<>();
        chaptersArrayList.add("Photography");
        chaptersArrayList.add("Social media");
        chaptersArrayList.add("Websites");
        chaptersArrayList.add("Survey");
        chaptersArrayList.add("Apps");
        chaptersArrayList.add("Blog");
        chaptersArrayList.add("Email marketing");
        chaptersArrayList.add("Develop");
    }
}
