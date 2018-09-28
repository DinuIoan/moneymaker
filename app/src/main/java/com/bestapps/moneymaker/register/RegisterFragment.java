package com.bestapps.moneymaker.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseHandler;
import com.bestapps.moneymaker.model.Profile;

import static android.content.ContentValues.TAG;

public class RegisterFragment extends Fragment {
    private Button signUpButton;
    private EditText nameText;
    private EditText passwordText;
    private EditText emailText;

    private String name;
    private String email;
    private String password;
    private long date;
    private int active = 0;
    private DatabaseHandler databaseHandler;
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        signUpButton = view.findViewById(R.id.btn_signup);
        nameText = view.findViewById(R.id.input_name);
        emailText = view.findViewById(R.id.input_email);
        passwordText = view.findViewById(R.id.input_password);
        databaseHandler = new DatabaseHandler(getContext());

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        return view;
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        databaseHandler.addProfile(new Profile(null, name, password, email, date, active));
        signUpButton.setEnabled(false);

    }

    public void onSignupFailed() {
        Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();

        signUpButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        name = nameText.getText().toString();
        email = emailText.getText().toString();
        password = passwordText.getText().toString();
        date =  System.currentTimeMillis();

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

        return valid;
    }

    private void changeFragment() {
        fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, new RegisterFragment());
        fragmentTransaction.commit();
    }

}
