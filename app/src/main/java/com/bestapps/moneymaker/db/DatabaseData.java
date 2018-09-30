package com.bestapps.moneymaker.db;

import android.content.Context;

import com.bestapps.moneymaker.model.Earning;
import com.bestapps.moneymaker.model.Label;
import com.bestapps.moneymaker.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class DatabaseData {
    private static List<Earning> earnings;
    private static Profile profile;
    private static List<String> labels;

    public static void initializeData(DatabaseHandler databaseHandler) {
        earnings = databaseHandler.findAllEarnings();
        profile = databaseHandler.findProfile();
        populateArrayList();
    }

    public static List<Earning> getEarnings() {
        return earnings;
    }

    public static void setEarnings(List<Earning> earnings) {
        DatabaseData.earnings = earnings;
    }

    public static Profile getProfile() {
        return profile;
    }

    public static void setProfile(Profile profile) {
        DatabaseData.profile = profile;
    }

    public static List<String> getLabels() {
        return labels;
    }

    private static void populateArrayList() {
        labels = new ArrayList<>();
        labels.add(Label.PHOTOGRAPHY);
        labels.add(Label.SOCIAL_MEDIA);
        labels.add(Label.WEBSITES);
        labels.add(Label.SURVEY);
        labels.add(Label.APPS);
        labels.add(Label.BlOG);
        labels.add(Label.EMAIL_MARKETING);
        labels.add(Label.DEVELOP);
    }
}
