package com.bestapps.moneymaker.db;

import android.content.Context;

import com.bestapps.moneymaker.model.Earning;
import com.bestapps.moneymaker.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class DatabaseData {
    private static List<Earning> earnings = new ArrayList<>();
    private static List<Profile> profiles = new ArrayList<>();

    public static void initializeData(DatabaseHandler databaseHandler) {
        earnings = databaseHandler.findAllEarnings();
        profiles = databaseHandler.findAllProfiles();
    }

    public static List<Earning> getEarnings() {
        return earnings;
    }

    public static void setEarnings(List<Earning> earnings) {
        DatabaseData.earnings = earnings;
    }

    public static List<Profile> getProfiles() {
        return profiles;
    }

    public static void setProfiles(List<Profile> profiles) {
        DatabaseData.profiles = profiles;
    }
}
