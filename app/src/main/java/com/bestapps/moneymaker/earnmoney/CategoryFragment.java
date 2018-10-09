package com.bestapps.moneymaker.earnmoney;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.home.HomeFragment;

public class CategoryFragment extends Fragment {
    private FragmentManager fragmentManager;

    public CategoryFragment() {
    }

    public CategoryFragment(String me) {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        handleOnBackPressed(view);

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
        fragmentTransaction.replace(R.id.fragment_placeholder, new EarnMoneyFragment());
        fragmentTransaction.commit();

    }

}
