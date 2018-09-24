package com.bestapps.moneymaker.db;

import android.content.Context;

import com.bestapps.moneymaker.model.Earning;

import java.util.ArrayList;
import java.util.List;

public class DatabaseData {
    private static List<Earning> earnings = new ArrayList<>();

    public static void initializeData(DatabaseHandler databaseHandler) {
        earnings = databaseHandler.findAllEarnings();
    }

    public static List<Earning> getEarnings() {
        return earnings;
    }

    public static void setEarnings(List<Earning> earnings) {
        DatabaseData.earnings = earnings;
    }
}
