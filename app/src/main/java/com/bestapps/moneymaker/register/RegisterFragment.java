package com.bestapps.moneymaker.register;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
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
import android.widget.Toast;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseData;
import com.bestapps.moneymaker.db.DatabaseHandler;
import com.bestapps.moneymaker.earnmoney.EarnMoneyFragment;
import com.bestapps.moneymaker.model.Earning;
import com.bestapps.moneymaker.model.Profile;
import com.bestapps.moneymaker.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.INPUT_METHOD_SERVICE;

public class RegisterFragment extends Fragment {
    private Button signUpButton;
    private EditText nameText;
    private EditText passwordText;
    private EditText emailText;
    private EditText locationText;
    private Spinner genderSpinner;

    private String name;
    private String email;
    private String password;
    private long date;
    private String status = "ACTIVATING";
    private String location;
    private String gender;
    boolean isEdit = false;
    private DatabaseHandler databaseHandler;
    private FragmentManager fragmentManager;
    private Profile editProfile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        handleOnBackPressed(view);
        signUpButton = view.findViewById(R.id.btn_signup);
        nameText = view.findViewById(R.id.input_name);
        emailText = view.findViewById(R.id.input_email);
        passwordText = view.findViewById(R.id.input_password);
        locationText = view.findViewById(R.id.input_location);
        genderSpinner = view.findViewById(R.id.input_gender_spinner);
        setUpSpinner();
        databaseHandler = new DatabaseHandler(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if (bundle.getString("edit", "").equals("true")) {
                isEdit = true;
            }
        }
        if (isEdit) {
            signUpButton.setText("Update account");
            editProfile = databaseHandler.findProfile();
            setUpFieldsForEdit(editProfile);
        }

        view.findViewById(R.id.scroll_view_register)
                .setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                                Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                        return false;
                    }
                });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(INPUT_METHOD_SERVICE);
                view = getActivity().getCurrentFocus();
                if (view == null) {
                    view = new View(getActivity());
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                signup();
            }
        });
        return view;
    }

    private void setUpFieldsForEdit(Profile editProfile) {
        nameText.setText(editProfile.getName());
        passwordText.setText(editProfile.getPassword());
        if (editProfile.getGender().equals("Male")) {
            genderSpinner.setSelection(0);
        } else {
            genderSpinner.setSelection(1);
        }
        emailText.setText(editProfile.getEmail());
        locationText.setText(editProfile.getLocation());
    }

    public void signup() {
        Log.d(TAG, "Signup");
        name = nameText.getText().toString();
        email = emailText.getText().toString();
        password = passwordText.getText().toString();
        date =  System.currentTimeMillis();
        location = locationText.getText().toString();

        if (!isEdit && !validate()) {
            onSignupFailed();
            return;
        }
        if (isEdit) {
            updateProfile();
        } else {
            databaseHandler.addProfile(new Profile(null, name, password, email, date, status, location, gender));
            DatabaseData.setProfile(databaseHandler.findProfile());
            signUpButton.setEnabled(false);
        }
        changeFragment();

    }

    private void updateProfile() {
        Profile updatedProfile = new Profile();
        if (name.isEmpty()) {
            updatedProfile.setName(editProfile.getName());
        } else {
            updatedProfile.setName(name);
        }
        if (email.isEmpty()) {
            updatedProfile.setEmail(editProfile.getEmail());
        } else {
            updatedProfile.setEmail(email);
        }
        if (password.isEmpty()) {
            updatedProfile.setPassword(editProfile.getPassword());
        } else {
            updatedProfile.setPassword(password);
        }
        if (location.isEmpty()) {
            updatedProfile.setLocation(editProfile.getLocation());
        } else {
            updatedProfile.setLocation(location);
        }
        updatedProfile.setGender(gender);
        updatedProfile.setStatus(editProfile.getStatus());
        updatedProfile.setDate(editProfile.getDate());

        databaseHandler.updateProfile(updatedProfile);
        DatabaseData.setProfile(databaseHandler.findProfile());
    }

    public void onSignupFailed() {
        Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();

        signUpButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (location.isEmpty() || location.length() < 2) {
            locationText.setError("at least 2 characters");
            valid = false;
        } else {
            locationText.setError(null);
        }

        return valid;
    }

    private void changeFragment() {
        fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        Fragment fragment;
        if (isEdit) {
            fragment = new ProfileFragment();
        } else {
            fragment = new EarnMoneyFragment();
        }
        fragmentTransaction.replace(R.id.fragment_placeholder,
                fragment);
        fragmentTransaction.commit();
    }

    private void handleOnBackPressed(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    changeFragment();
                    return true;
                }
                return false;
            }
        });
    }

    private void setUpSpinner() {
        List<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, genders);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                gender = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
