package com.bestapps.moneymaker.earnmoney;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.home.HomeFragment;
import com.bestapps.moneymaker.model.Label;

public class CategoryFragment extends Fragment {
    private TextView descriptionTextView;
    private ImageView imageView;
    private TextView titleTextView;
    private FragmentManager fragmentManager;

    public CategoryFragment() {
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
        findViewsById(view);
        Bundle bundle = this.getArguments();
        String label = "";
        if (bundle != null) {
             label = bundle.getString("label", "NULL");
        }
        setUpViews(label, view);
        handleOnBackPressed(view);

        return view;
    }

    private void findViewsById(View view) {
        titleTextView = view.findViewById(R.id.title);
        descriptionTextView = view.findViewById(R.id.description_text_view);
        imageView = view.findViewById(R.id.image_view);
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

    private void setUpViews(String label, View views) {
        if (label.equals(Label.CRYPTOCURRENCY)) {
            titleTextView.setText(Label.CRYPTOCURRENCY);
            imageView.setImageResource(R.drawable.crypto);
            descriptionTextView.setText("Description");
        }
        if (label.equals(Label.WEBSITES)) {
            titleTextView.setText(Label.WEBSITES);
            imageView.setImageResource(R.drawable.websites);
            descriptionTextView.setText("Description");
        }
        if (label.equals(Label.SURVEY)){
            titleTextView.setText(Label.SURVEY);
            imageView.setImageResource(R.drawable.survey);
            descriptionTextView.setText("Description");
        }
        if (label.equals(Label.SOCIAL_MEDIA)) {
            titleTextView.setText(Label.SOCIAL_MEDIA);
            imageView.setImageResource(R.drawable.social_media);
            descriptionTextView.setText("Description");
        }
        if (label.equals(Label.APPS)) {
            titleTextView.setText(Label.APPS);
            imageView.setImageResource(R.drawable.apps);
            descriptionTextView.setText("Description");
        }
        if (label.equals(Label.DEVELOP)) {
            titleTextView.setText(Label.DEVELOP);
            imageView.setImageResource(R.drawable.develop);
            descriptionTextView.setText("Description");
        }
        if (label.equals(Label.EMAIL_MARKETING)) {
            titleTextView.setText(Label.EMAIL_MARKETING);
            imageView.setImageResource(R.drawable.email);
            descriptionTextView.setText("Description");
        }
        if (label.equals(Label.BlOG)) {
            titleTextView.setText(Label.BlOG);
            imageView.setImageResource(R.drawable.blog);
            descriptionTextView.setText("Description");
        }
        if (label.equals(Label.PHOTOGRAPHY)) {
            titleTextView.setText(Label.PHOTOGRAPHY);
            imageView.setImageResource(R.drawable.photography);
            descriptionTextView.setText("Description");
        }
    }

}
