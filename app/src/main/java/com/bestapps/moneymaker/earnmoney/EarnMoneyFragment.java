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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseData;
import com.bestapps.moneymaker.home.HomeFragment;
import com.bestapps.moneymaker.model.Group;
import com.bestapps.moneymaker.model.Label;
import com.bestapps.moneymaker.model.Profile;
import com.bestapps.moneymaker.register.RegisterFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EarnMoneyFragment extends Fragment {
    private static final String message = "Come back here in a couple of hours to get access.\n" +
            " Usually activation takes up to 24h \n\n\n\n\n";
    private Button registerButton;
    private TextView textView;
    private ExpandableListView listView;
    private LinearLayout photographyLinearLayout;
    private LinearLayout socialMediaLinearLayout;
    private LinearLayout websitesLinearLayout;
    private LinearLayout surveyLinearLayout;
    private LinearLayout appsLinearLayout;
    private LinearLayout blogLinearLayout;
    private LinearLayout emailLinearLayout;
    private LinearLayout developLinearLayout;
    private LinearLayout cryptocurrencyLinearLayout;
    private LinearLayout masterLinearLayout;

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
        View view = inflater.inflate(R.layout.fragment_today_earnings, container, false);
        findViews(view);
        handleOnBackPressed(view);

        registerButton = view.findViewById(R.id.register_button);
        textView = view.findViewById(R.id.activate_profile_text_view);

        Profile profile = DatabaseData.getProfile();
        masterLinearLayout.setVisibility(View.INVISIBLE);
        if (profile.getStatus() == null) {
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
            if (profile.getStatus().equals("ACTIVATING")) {
                List<String> inspirationalQuotes =
                        DatabaseData.getInspirationalQuotes();
                Collections.shuffle(inspirationalQuotes);
                textView.setText(message + inspirationalQuotes.get(0));
                registerButton.setVisibility(View.INVISIBLE);
            } else {
                textView.setVisibility(View.INVISIBLE);
                registerButton.setVisibility(View.INVISIBLE);
                masterLinearLayout.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    private void findViews(View view) {
        photographyLinearLayout = view.findViewById(R.id.photography);
        socialMediaLinearLayout = view.findViewById(R.id.social_media);
        websitesLinearLayout = view.findViewById(R.id.websites);
        surveyLinearLayout = view.findViewById(R.id.survey);
        appsLinearLayout = view.findViewById(R.id.apps);
        blogLinearLayout = view.findViewById(R.id.blog);
        emailLinearLayout = view.findViewById(R.id.email_marketing);
        developLinearLayout = view.findViewById(R.id.develop);
        cryptocurrencyLinearLayout = view.findViewById(R.id.cryptocurrency);
        masterLinearLayout = view.findViewById(R.id.master_linear_layout);
        setUpClickListeners();
    }

    private void setUpClickListeners() {
        List<LinearLayout> linearLayoutList = buildLinearLayoutList();
        for (LinearLayout linearLayout: linearLayoutList) {
            String label = getLabelByLinearLayout(linearLayout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CategoryFragment fragment = new CategoryFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("label", label);
                    fragment.setArguments(bundle);
                    fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction =
                            fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_placeholder, fragment);
                    fragmentTransaction.commit();
                }
            });
        }
    }

    private List<LinearLayout> buildLinearLayoutList() {
        List<LinearLayout> linearLayoutList = new ArrayList<>();
        linearLayoutList.add(appsLinearLayout);
        linearLayoutList.add(blogLinearLayout);
        linearLayoutList.add(cryptocurrencyLinearLayout);
        linearLayoutList.add(developLinearLayout);
        linearLayoutList.add(emailLinearLayout);
        linearLayoutList.add(photographyLinearLayout);
        linearLayoutList.add(socialMediaLinearLayout);
        linearLayoutList.add(surveyLinearLayout);
        linearLayoutList.add(websitesLinearLayout);
        return linearLayoutList;
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

    private String getLabelByLinearLayout(LinearLayout linearLayout) {
        String label = "";
        if (linearLayout.equals(appsLinearLayout)) {
            label = Label.APPS;
        }
        if (linearLayout.equals(blogLinearLayout)) {
            label = Label.BlOG;
        }
        if (linearLayout.equals(cryptocurrencyLinearLayout)) {
            label = Label.CRYPTOCURRENCY;
        }
        if (linearLayout.equals(developLinearLayout)) {
            label = Label.DEVELOP;
        }
        if (linearLayout.equals(emailLinearLayout)) {
            label = Label.EMAIL_MARKETING;
        }
        if (linearLayout.equals(photographyLinearLayout)) {
            label = Label.PHOTOGRAPHY;
        }
        if (linearLayout.equals(socialMediaLinearLayout)) {
            label = Label.SOCIAL_MEDIA;
        }
        if (linearLayout.equals(surveyLinearLayout)) {
            label = Label.SURVEY;
        }
        if (linearLayout.equals(websitesLinearLayout)) {
            label = Label.WEBSITES;
        }
        return label;
    }
}
