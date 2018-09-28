package com.bestapps.moneymaker.db;

import android.content.Context;

import com.bestapps.moneymaker.model.Earning;
import com.bestapps.moneymaker.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class DatabaseData {
    private static List<Earning> earnings;
    private static Profile profile;

    public static void initializeData(DatabaseHandler databaseHandler) {
        earnings = databaseHandler.findAllEarnings();
        profile = databaseHandler.findProfile();
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
}
