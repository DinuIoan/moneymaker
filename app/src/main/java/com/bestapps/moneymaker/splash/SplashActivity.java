package com.bestapps.moneymaker.splash;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import com.bestapps.moneymaker.MainActivity;
import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseData;
import com.bestapps.moneymaker.db.DatabaseHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SplashActivity extends AppCompatActivity {
    private DatabaseHandler databaseHandler;
    private static Resources resources;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        resources = getApplicationContext().getResources();
        loadData();
        initializeInspirationalQuotes();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }

    private void loadData() {
        databaseHandler = new DatabaseHandler(getApplicationContext());
        DatabaseData.initializeData(databaseHandler);
        int oneHourInMillis = 60 * 60 * 1000;
        long lastTimePlayed = DatabaseData.getProfile().getDate();
        if (!(lastTimePlayed == 0)) {
            long currentTime = System.currentTimeMillis();
            int hoursPassed = (int) ((currentTime - lastTimePlayed) / oneHourInMillis);
            if (!(hoursPassed > 17)) {
                databaseHandler.updateProfileSetActive("ACTIVE");
                DatabaseData.setProfile(databaseHandler.findProfile());
            }
        }
    }

    private void initializeInspirationalQuotes() {
        InputStream inputStream = resources.openRawResource(R.raw.inspirational_quotes);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("/")) {
                    DatabaseData.getInspirationalQuotes().add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
