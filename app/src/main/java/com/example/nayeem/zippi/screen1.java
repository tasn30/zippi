package com.example.nayeem.zippi;

/**
 * Created by NAyeem on 2/13/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class screen1 extends Activity {
    // Splash screen timer
    private static int SCREEN1_TIME_OUT = 2000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(screen1.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SCREEN1_TIME_OUT);
    }

}