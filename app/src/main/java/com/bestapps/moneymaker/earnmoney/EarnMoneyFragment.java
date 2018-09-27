package com.bestapps.moneymaker.earnmoney;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.earnings.AddEarningFragment;
import com.bestapps.moneymaker.register.RegisterFragment;

public class EarnMoneyFragment extends Fragment {
    private Button registerButton;
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_earn_money_layout, container, false);
        registerButton = view.findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_placeholder, new RegisterFragment());
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
