package com.bestapps.moneymaker.model;

import com.bestapps.moneymaker.R;

public class Label {
    public static final String PHOTOGRAPHY = "Photography";
    public static final String SOCIAL_MEDIA = "Social media";
    public static final String WEBSITES = "Websites";
    public static final String SURVEY = "Survey";
    public static final String APPS = "Apps";
    public static final String BlOG = "Blog";
    public static final String EMAIL_MARKETING = "Email_marketing";
    public static final String DEVELOP = "Develop";
    public static final String CRYPTOCURRENCY = "Cryptocurrency";


    public static int addIconId(String label) {
        if (label.equals(Label.PHOTOGRAPHY)) {
            return R.drawable.photography;
        }
        if (label.equals(Label.APPS)) {
            return R.drawable.apps;
        }
        if (label.equals(Label.BlOG)) {
            return R.drawable.blog;
        }
        if (label.equals(Label.DEVELOP)) {
            return R.drawable.develop;
        }
        if (label.equals(Label.EMAIL_MARKETING)) {
            return R.drawable.email;
        }
        if (label.equals(Label.SOCIAL_MEDIA)) {
            return R.drawable.social_media;
        }
        if (label.equals(Label.SURVEY)) {
            return R.drawable.survey;
        }
        if (label.equals(Label.WEBSITES)) {
            return R.drawable.websites;
        }
        if (label.equals(Label.CRYPTOCURRENCY)) {
            return R.drawable.crypto;
        }
        return 0;
    }
}
