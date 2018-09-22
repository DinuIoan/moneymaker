package com.bestapps.moneymaker.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bestapps.moneymaker.model.Earning;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "earnMoneyDB";
    private static final int DATABASE_VERSION = 1;
    private static final String ID = "id";

    private static final String EARNINGS_TABLE = "earnings";
    private static final String EARNINGS_DESCRIPTION = "description";
    private static final String EARNINGS_AMOUNT = "amount";
    private static final String EARNINGS_DATE = "date";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EARNINGS_TABLE = "create table " + EARNINGS_TABLE+
                " ( "
                + ID + " integer primary key , "
                + EARNINGS_DESCRIPTION + " text, "
                + EARNINGS_AMOUNT + "integer, "
                + EARNINGS_DATE + "integer " +
                " )" ;
        db.execSQL(CREATE_EARNINGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + EARNINGS_TABLE);
        onCreate(db);
    }

    public void addEarnings(Earning earning) {
        SQLiteDatabase database = getWritableDatabase();
        String ADD_EARNINGS = "insert into " + EARNINGS_TABLE +
                "values(null, '"
                + earning.getDescription() + "', '"
                + earning.getAmount() + "', '"
                + earning.getDate() + "')";
        database.execSQL(ADD_EARNINGS);
        database.close();
    }

    public List<Earning> findAllEarnings() {
        SQLiteDatabase database = getReadableDatabase();
        String FIND_ALL_EARNINGS = "select * from " + EARNINGS_TABLE;
        Cursor cursor = database.rawQuery(FIND_ALL_EARNINGS, null);
        List<Earning> earnings = new ArrayList<>();

        while(cursor.moveToNext()) {
            Earning earning = new Earning(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getLong(3));
            earnings.add(earning);
        }
        cursor.close();
        database.close();
        return earnings;
    }
}
