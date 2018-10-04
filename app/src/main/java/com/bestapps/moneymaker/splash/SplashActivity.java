package com.bestapps.moneymaker.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.bestapps.moneymaker.MainActivity;
import com.bestapps.moneymaker.R;
import com.bestapps.moneymaker.db.DatabaseData;
import com.bestapps.moneymaker.db.DatabaseHandler;

public class SplashActivity extends AppCompatActivity {
    private DatabaseHandler databaseHandler;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadData();
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
            if (hoursPassed > 17) {
                databaseHandler.updateProfileSetActive("ACTIVE");
                DatabaseData.setProfile(databaseHandler.findProfile());
            }
        }
    }
}
