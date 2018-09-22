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
        // set the content view for your splash screen you defined in an xml file
        setContentView(R.layout.activity_splash);
        // perform other stuff you need to do
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
        if (DatabaseData.getEarnings() == null) {
            DatabaseData.initializeData(databaseHandler);
        }
    }
}