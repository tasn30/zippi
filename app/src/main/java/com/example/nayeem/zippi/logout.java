package com.example.nayeem.zippi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by NAyeem on 3/4/2016.
 */
public class logout extends Activity {
    // Splash screen timer
    private static int LOGOUT_TIME_OUT = 2000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(logout.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, LOGOUT_TIME_OUT);
    }

}