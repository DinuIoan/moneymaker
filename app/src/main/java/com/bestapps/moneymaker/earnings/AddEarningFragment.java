package com.bestapps.moneymaker.earnings;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseHandler;
import com.bestapps.moneymaker.model.Earning;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddEarningFragment extends Fragment {
    private Button addButton;
    private EditText descriptionTextView;
    private EditText amountTextView;
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

        view.findViewById(R.id.scroll_view_add_earnings)
                .setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                                Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                        return false;
                    }
                });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                View view = getActivity().getCurrentFocus();
                if (view == null) {
                    view = new View(getActivity());
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                addEarning();
            }
        });
        return view;
    }

    private void addEarning() {
        if (!validate()) {
            onAddFailed();
            return;
        }

        Earning earning = new Earning(0L, descriptionTextView.getText().toString(),
                Long.parseLong(amountTextView.getText().toString()), parseDate());

        DatabaseHandler databaseHandler = new DatabaseHandler(getActivity().getApplicationContext());
        databaseHandler.addEarnings(earning);
        changeFragment();
    }

    private void onAddFailed() {
        Toast.makeText(getContext(), "Add failed", Toast.LENGTH_LONG).show();

        addButton.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;
        if (amountTextView.getText().toString().isEmpty()) {
            amountTextView.setError("enter amount");
            valid = false;
        } else {
            amountTextView.setError(null);
        }
        if (descriptionTextView.getText().toString().isEmpty()) {
            descriptionTextView.setError("enter description");
            valid = false;
        } else {
            descriptionTextView.setError(null);
        }

        return valid;
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
