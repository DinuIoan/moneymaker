package com.bestapps.moneymaker.earnings;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseHandler;
import com.bestapps.moneymaker.model.Earning;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddEarningFragment extends Fragment {
    private Button addButton;
    private TextView descriptionTextView;
    private TextView amountTextView;
    private FragmentManager fragmentManager;

    public AddEarningFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_earning, container, false);
        addButton = view.findViewById(R.id.add_button);
        descriptionTextView = view.findViewById(R.id.description_edit_text);
        amountTextView = view.findViewById(R.id.amount_edit_text);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEarning();
            }
        });
        return view;
    }

    private void addEarning() {
        Earning earning = new Earning(0L, descriptionTextView.getText().toString(),
                Long.parseLong(amountTextView.getText().toString()), parseDate());
        InputMethodManager imm = (InputMethodManager)
                getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getActivity().getCurrentFocus();
        if (view == null) {
            view = new View(getActivity());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        DatabaseHandler databaseHandler = new DatabaseHandler(getActivity().getApplicationContext());
        databaseHandler.addEarnings(earning);
        changeFragment();
    }

    private void changeFragment() {
        fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, new TodayEarningsFragment());
        fragmentTransaction.commit();
    }

    private String parseDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }
}
