package com.example.essam.hospitalscover.View;



import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.essam.hospitalscover.ModelView.MyMACAdress;
import com.example.essam.hospitalscover.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    void subscribeToTopic(String mac) {
        FirebaseMessaging.getInstance().subscribeToTopic(mac);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subscribeToTopic(MyMACAdress.getMacAddr());
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(MainActivity.this, HomePage.class);
            MainActivity.this.startActivity(mainIntent);
            MainActivity.this.finish();
        }, SPLASH_DISPLAY_LENGTH);

    }

}
