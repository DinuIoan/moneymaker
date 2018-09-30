package com.bestapps.moneymaker.earnmoney;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseData;
import com.bestapps.moneymaker.home.HomeFragment;
import com.bestapps.moneymaker.model.Label;
import com.bestapps.moneymaker.model.Profile;
import com.bestapps.moneymaker.register.RegisterFragment;

import java.util.ArrayList;

public class EarnMoneyFragment extends Fragment {
    private static final String message = "Come back here in a couple of hours to get access.\n" +
            " Usually activation takes up to 24h";
    private Button registerButton;
    private TextView textView;
    private ExpandableListView listView;

    private FragmentManager fragmentManager;
    private ArrayList<String> chaptersArrayList = new ArrayList<>();
    SparseArray<Group> groups = new SparseArray<>();

    public EarnMoneyFragment() {
    }

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
        textView = view.findViewById(R.id.activate_profile_text_view);
        handleOnBackPressed(view);

        Profile profile = DatabaseData.getProfile();

        if (profile == null) {
            textView.setVisibility(View.VISIBLE);

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
        } else {
            if (profile.getActive().equals("ACTIVATING")) {
                textView.setText(message);
                registerButton.setVisibility(View.INVISIBLE);
            } else {
                textView.setVisibility(View.INVISIBLE);
                registerButton.setVisibility(View.INVISIBLE);
                listView = view.findViewById(R.id.listview);
                populateArrayList();
                createData();
                MyExpandableListAdapter adapter = new MyExpandableListAdapter(getActivity(), groups);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        view.animate().setDuration(2000).alpha(0)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        chaptersArrayList.remove(item);
                                        adapter.notifyDataSetChanged();
                                        view.setAlpha(1);
                                    }
                                });
                    }

                });
            }
        }
        return view;
    }

    private void createData() {
        for (int j = 0; j < chaptersArrayList.size(); j++) {
            Group group = new Group(chaptersArrayList.get(j));
            for (int i = 0; i < 1; i++) {
                group.children.add("Sub Item" + i);
            }
            groups.append(j, group);
        }
    }

    private void populateArrayList() {
        for (String label: DatabaseData.getLabels()) {
            chaptersArrayList.add(label);
        }
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
}
