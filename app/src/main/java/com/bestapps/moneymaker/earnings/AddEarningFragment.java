package com.bestapps.moneymaker.earnings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseData;
import com.bestapps.moneymaker.db.DatabaseHandler;
import com.bestapps.moneymaker.model.Earning;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddEarningFragment extends Fragment {
    private Button addButton;
    private EditText amountTextView;
    private FragmentManager fragmentManager;
    private Spinner labelSpinner;

    private String label;

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
        amountTextView = view.findViewById(R.id.amount_edit_text);
        amountTextView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        labelSpinner = (Spinner) view.findViewById(R.id.label_spinner);
        setUpSpinner();

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

    private void setUpSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, DatabaseData.getLabels());
        labelSpinner.setAdapter(adapter);
        labelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                label = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void addEarning() {
        if (!validate()) {
            onAddFailed();
            return;
        }

        Earning earning = new Earning(0L, label,
                Double.parseDouble(amountTextView.getText().toString()), parseDate());

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
        if (label.isEmpty()) {
            valid = false;
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
