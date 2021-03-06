package com.bestapps.moneymaker.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bestapps.moneymaker.model.Earning;
import com.bestapps.moneymaker.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "earnMoneyDB";
    private static final int DATABASE_VERSION = 1;
    private static final String ID = "id";

    private static final String EARNINGS_TABLE = "earnings";
    private static final String EARNINGS_LABEL = "label";
    private static final String EARNINGS_AMOUNT = "amount";
    private static final String EARNINGS_DATE = "date";

    private static final String PROFILE_TABLE = "profile";
    private static final String PROFILE_NAME = "name";
    private static final String PROFILE_PASSWORD = "password";
    private static final String PROFILE_EMAIL = "email";
    private static final String PROFILE_DATE = "date";
    private static final String PROFILE_STATUS = "status";
    private static final String PROFILE_LOCATION = "location";
    private static final String PROFILE_GENDER = "gender";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EARNINGS_TABLE = "create table " + EARNINGS_TABLE+
                " ( "
                + ID + " integer primary key autoincrement, "
                + EARNINGS_LABEL + " text, "
                + EARNINGS_AMOUNT + "integer, "
                + EARNINGS_DATE + "text " +
                " )" ;
        String CREATE_PROFILE_TABLE = "create table " + PROFILE_TABLE +
                " ( "
                + ID + " integer primary key autoincrement, "
                + PROFILE_NAME + " text, "
                + PROFILE_PASSWORD + " text, "
                + PROFILE_EMAIL + " text, "
                + PROFILE_DATE + " integer, "
                + PROFILE_STATUS + " text, "
                + PROFILE_LOCATION + " text, "
                + PROFILE_GENDER + " text " +
                " ) ";
        db.execSQL(CREATE_EARNINGS_TABLE);
        db.execSQL(CREATE_PROFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + EARNINGS_TABLE);
        db.execSQL("drop table if exists " + PROFILE_TABLE);
        onCreate(db);
    }

    // ADD
    public void addEarnings(Earning earning) {
        SQLiteDatabase database = getWritableDatabase();
        String ADD_EARNINGS = "insert into " + EARNINGS_TABLE +
                " values(null, '"
                + earning.getLabel() + "', '"
                + earning.getAmount() + "', '"
                + earning.getDate() + "')";
        database.execSQL(ADD_EARNINGS);
        database.close();
    }

    public void addProfile(Profile profile) {
        SQLiteDatabase database = getWritableDatabase();
        String ADD_PROFILE = "insert into " + PROFILE_TABLE +
                " values(0, '" +
                profile.getName() + "', '" +
                profile.getPassword() + "', '" +
                profile.getEmail() +  "', '" +
                profile.getDate() + "', '" +
                profile.getStatus() + "', '" +
                profile.getLocation() + "', '" +
                profile.getGender() + "') ";
        database.execSQL(ADD_PROFILE);
        database.close();
    }

    // FIND
    public List<Earning> findAllEarnings() {
        SQLiteDatabase database = getReadableDatabase();
        String FIND_ALL_EARNINGS = "select * from " + EARNINGS_TABLE;
        Cursor cursor = database.rawQuery(FIND_ALL_EARNINGS, null);
        List<Earning> earnings = new ArrayList<>();

        while(cursor.moveToNext()) {
            Earning earning = new Earning(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3));
            earnings.add(earning);
        }
        cursor.close();
        database.close();
        return earnings;
    }

    public Profile findProfile() {
        SQLiteDatabase database = getReadableDatabase();
        String FIND_ALL_PROFILES = "select * from " + PROFILE_TABLE;
        Cursor cursor = database.rawQuery(FIND_ALL_PROFILES, null );
        Profile profile = new Profile();
        if (cursor.moveToFirst()) {
             profile = new Profile(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getLong(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
        }

        cursor.close();
        database.close();
        return profile;
    }

    public List<Earning> findEarningByLabel(String label) {
        SQLiteDatabase database = getReadableDatabase();
        String FIND_ALL_EARNINGS = "select * from " + EARNINGS_TABLE +
                " where " + EARNINGS_LABEL + " = '" + label + "'";
        Cursor cursor = database.rawQuery(FIND_ALL_EARNINGS, null);
        List<Earning> earnings = new ArrayList<>();

        while(cursor.moveToNext()) {
            Earning earning = new Earning(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3));
            earnings.add(earning);
        }
        cursor.close();
        database.close();
        return earnings;
    }

    //UPDATE
    public void updateProfileSetActive(String isActive) {
        SQLiteDatabase database = getWritableDatabase();
        String UPDATE_PROFILE_SET_ACTIVE = "update " + PROFILE_TABLE +
                " set " +
                PROFILE_STATUS + " = '" + isActive +
                "' where " + ID + " = " + 0 ;
        database.execSQL(UPDATE_PROFILE_SET_ACTIVE);
        database.close();
    }

    public void updateProfile(Profile profile) {
        SQLiteDatabase database = getWritableDatabase();
        String UPDATE_PROFILE = "update " + PROFILE_TABLE +
                " set "
                + PROFILE_NAME + " = '" + profile.getName() + "', "
                + PROFILE_PASSWORD + " = '" + profile.getPassword() + "', "
                + PROFILE_EMAIL + " = '" + profile.getEmail() + "', "
                + PROFILE_DATE + " = " + profile.getDate() + ", "
                + PROFILE_STATUS + " = '" + profile.getStatus() + "', "
                + PROFILE_LOCATION + " = '" + profile.getLocation() + "', "
                + PROFILE_GENDER + " = '" + profile.getGender() +
                "' where " + ID + " = " + 0 ;
        database.execSQL(UPDATE_PROFILE);
        database.close();
    }
}
