package com.bestapps.moneymaker.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseHandler;
import com.bestapps.moneymaker.model.Profile;
import com.bestapps.moneymaker.register.RegisterFragment;

public class ProfileFragment extends Fragment {
    private TextView name;
    private TextView location;
    private TextView email;
    private TextView status;
    private TextView gender;
    private TextView pleaseRegister;
    private ImageView editButton;

    private DatabaseHandler databaseHandler;
    private Profile profile;
    private FragmentManager fragmentManager;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        databaseHandler = new DatabaseHandler(getContext());
        name = view.findViewById(R.id.profile_name);
        location = view.findViewById(R.id.location);
        email = view.findViewById(R.id.email);
        status = view.findViewById(R.id.status);
        gender = view.findViewById(R.id.gender);
        pleaseRegister = view.findViewById(R.id.please_register);
        editButton = view.findViewById(R.id.edit);

        profile = databaseHandler.findProfile();
        if (profile.getId() == null) {
            name.setText("-");
            location.setText("-");
            email.setText("-");
            status.setText("-");
            gender.setText("-");
            editButton.setImageResource(android.R.drawable.ic_input_add);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction =
                            fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_placeholder, new RegisterFragment());
                    fragmentTransaction.commit();
                }
            });
            pleaseRegister.setVisibility(View.VISIBLE);
        } else {
            pleaseRegister.setVisibility(View.INVISIBLE);
            editButton.setImageResource(android.R.drawable.ic_menu_edit);

            name.setText(profile.getName());
            location.setText(profile.getLocation());
            email.setText(profile.getEmail());
            status.setText(profile.getStatus());
            gender.setText(profile.getGender());
        }
        return view;
    }
}
