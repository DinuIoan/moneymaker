package com.bestapps.moneymaker.earnings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.model.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEarningFragment extends Fragment {
    private Button addButton;
    private EditText amountTextView;
    private FragmentManager fragmentManager;
    private Spinner labelSpinner;
    private Button unu;
    private Button doi;
    private Button trei;
    private Button patru;
    private Button cinci;
    private Button sase;
    private Button sapte;
    private Button opt;
    private Button noua;
    private Button zero;
    private Button punct;
    private Button delete;
    private CheckBox photographyCheckBox;
    private CheckBox appsCheckBox;
    private CheckBox developCheckBox;
    private CheckBox emailCheckBox;
    private CheckBox cryptoCheckBox;
    private CheckBox socialMediaCheckBox;
    private CheckBox blogCheckBox;
    private CheckBox surveyCheckBox;
    private CheckBox websitesCheckBox;

    private boolean isPhotography = false;
    private boolean isSocialMedia = false;
    private boolean isWebsites = false;
    private boolean isApps = false;
    private boolean isDevelop = false;
    private boolean isSurvey = false;
    private boolean isEmail = false;
    private boolean isCrypto = false;
    private boolean isBlog = false;
    private String label = "";
    private String amount = "";

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
        initiateViews(view);
        setCLickListeners();
        setDefaultCheckBox();
        return view;
    }

    private void setDefaultCheckBox() {
        photographyCheckBox.setChecked(true);
    }

    private void initiateViews(View view) {
        unu = view.findViewById(R.id.unu);
        doi = view.findViewById(R.id.doi);
        trei = view.findViewById(R.id.trei);
        patru = view.findViewById(R.id.patru);
        cinci = view.findViewById(R.id.cinci);
        sase = view.findViewById(R.id.sase);
        sapte = view.findViewById(R.id.sapte);
        opt = view.findViewById(R.id.opt);
        noua = view.findViewById(R.id.noua);
        zero = view.findViewById(R.id.zero);
        punct = view.findViewById(R.id.point);
        delete = view.findViewById(R.id.delete);
        amountTextView = view.findViewById(R.id.amount);
        appsCheckBox = view.findViewById(R.id.apps_checkbox);
        blogCheckBox= view.findViewById(R.id.blog_checkbox);
        cryptoCheckBox= view.findViewById(R.id.crypto_checkbox);
        developCheckBox= view.findViewById(R.id.develop_checkbox);
        emailCheckBox= view.findViewById(R.id.email_checkbox);
        photographyCheckBox= view.findViewById(R.id.photography_checkbox);
        socialMediaCheckBox= view.findViewById(R.id.social_media_checkbox);
        surveyCheckBox= view.findViewById(R.id.survey_checkbox);
        websitesCheckBox= view.findViewById(R.id.websites_checkbox);

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

    private void setCLickListeners() {
        unu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += "1";
                amountTextView.setText(amount);
            }
        });
        doi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += "2";
                amountTextView.setText(amount);
            }
        });
        trei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += "3";
                amountTextView.setText(amount);
            }
        });
        patru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += "4";
                amountTextView.setText(amount);
            }
        });
        cinci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += "5";
                amountTextView.setText(amount);
            }
        });
        sase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += "6";
                amountTextView.setText(amount);
            }
        });
        sapte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += "7";
                amountTextView.setText(amount);
            }
        });
        opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += "8";
                amountTextView.setText(amount);
            }
        });
        noua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += "9";
                amountTextView.setText(amount);
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += "0";
                amountTextView.setText(amount);
            }
        });
        punct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += ".";
                amountTextView.setText(amount);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = deleteLastCharacter(amount);
                amountTextView.setText(amount);
            }
        });
        appsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label = Label.APPS;
                checkBoxSelect(appsCheckBox);
            }
        });
        blogCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label = Label.BlOG;
                checkBoxSelect(blogCheckBox);
            }
        });
        cryptoCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label = Label.CRYPTOCURRENCY;
                checkBoxSelect(cryptoCheckBox);
            }
        });
        developCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label = Label.DEVELOP;
                checkBoxSelect(developCheckBox);
            }
        });
        emailCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label = Label.EMAIL_MARKETING;
                checkBoxSelect(emailCheckBox);
            }
        });
        photographyCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label = Label.PHOTOGRAPHY;
                checkBoxSelect(photographyCheckBox);
            }
        });
        socialMediaCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label = Label.SOCIAL_MEDIA;
                checkBoxSelect(socialMediaCheckBox);
            }
        });
        surveyCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label = Label.SURVEY;
                checkBoxSelect(surveyCheckBox);
            }
        });
        websitesCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label = Label.WEBSITES;
                checkBoxSelect(websitesCheckBox);
            }
        });
    }

    public String deleteLastCharacter(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private void checkBoxSelect(CheckBox checkBox) {
        if (!(checkBox.equals(websitesCheckBox)) && websitesCheckBox.isChecked()) {
            websitesCheckBox.setChecked(false);
        }
        if (!(checkBox.equals(surveyCheckBox)) && surveyCheckBox.isChecked()) {
            surveyCheckBox.setChecked(false);
        }
        if (!(checkBox.equals(socialMediaCheckBox)) && socialMediaCheckBox.isChecked()) {
            socialMediaCheckBox.setChecked(false);
        }
        if (!(checkBox.equals(photographyCheckBox)) && photographyCheckBox.isChecked()) {
            photographyCheckBox.setChecked(false);
        }
        if (!(checkBox.equals(emailCheckBox)) && emailCheckBox.isChecked()) {
            emailCheckBox.setChecked(false);
        }
        if (!(checkBox.equals(developCheckBox)) && developCheckBox.isChecked()) {
            developCheckBox.setChecked(false);
        }
        if (!(checkBox.equals(cryptoCheckBox)) && cryptoCheckBox.isChecked()) {
            cryptoCheckBox.setChecked(false);
        }
        if (!(checkBox.equals(blogCheckBox)) && blogCheckBox.isChecked()) {
            blogCheckBox.setChecked(false);
        }
        if (!(checkBox.equals(appsCheckBox)) && appsCheckBox.isChecked()) {
            appsCheckBox.setChecked(false);
        }
    }
}
