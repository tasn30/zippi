package com.example.nayeem.zippi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    DataBaseEventPlanners dbep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventplannersList();
        /**
         *Setup the DrawerLayout and NavigationView
         */

        //showing();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                              @Override
                                                              public boolean onNavigationItemSelected(MenuItem menuItem) {
                                                                  mDrawerLayout.closeDrawers();


                                                                  if (menuItem.getItemId() == R.id.nav_about) {
                                                                      startActivity(new Intent(MainActivity.this, AboutFragment.class));

                                                                  }

                                                                  if (menuItem.getItemId() == R.id.nav_homez) {
                                                                      startActivity(new Intent(MainActivity.this, MainActivity.class));
                                                                  }
                                                                  if (menuItem.getItemId() == R.id.nav_sign) {
                                                                      startActivity(new Intent(MainActivity.this, EventplannerLogin.class));

                                                                  }
                                                                  if (menuItem.getItemId() == R.id.nav_feedback) {
                                                                      startActivity(new Intent(MainActivity.this, feedback.class));

                                                                  }
                                                                  if (menuItem.getItemId() == R.id.nav_help) {
                                                                      startActivity(new Intent(MainActivity.this, help.class));

                                                                  }
                                                                  if (menuItem.getItemId() == R.id.nav_eventsign) {
                                                                      startActivity(new Intent(MainActivity.this, eventsign.class));

                                                                  }
                                                                  return false;
                                                              }

                                                          }

        );


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }

    public void eventplannersList() {
        dbep = new DataBaseEventPlanners(getApplicationContext());
        String planner_phone = "9885523555";
        planner_phone = "9346934700";

        if(dbep.matchplanner(planner_phone) != 1) {
            EventPlannerModel planners = new EventPlannerModel();
            Bitmap bitimage = BitmapFactory.decodeResource(getResources(), R.drawable.skybirds);
            planners.bitmap = bitimage;
            planners.name ="Hari Krishna";
            planners.organisationname = "KUSHI EVENTS and SKYBIRDS EVENTS";
            planners.pwd = "skybirds";
            planners.email = "kushievents@gmail.com";
            planners.phone_number = planner_phone;
            planners.address ="Address: B-226, AFOCHS Society,  Sainikpuri , Opposite to Bank Of India, Hyderabad ,India";
            planners.events ="5";
            dbep.addeventplannerDetail(planners);
        }
        if (dbep.matchplanner(planner_phone) != 1) {
            EventPlannerModel planners = new EventPlannerModel();
            Bitmap bitimage = BitmapFactory.decodeResource(getResources(), R.drawable.stylemantra);
            planners.bitmap = bitimage;
            planners.name ="JOITA SEKHAR";
            planners.organisationname = "STYLE MANTRA";
            planners.pwd = "stylemantra";
            planners.email = "thestylemantra@gmail.com";
            planners.phone_number = planner_phone;
            planners.address ="plot A1 30-284/151,Officer’s Colony, Sainathpuram Rd,Dr A.S.Rao Nagar, ECIL-62";
            planners.events ="5";
            dbep.addeventplannerDetail(planners);
        }



        planner_phone = "9618235745";

        if(dbep.matchplanner(planner_phone) != 1) {
            EventPlannerModel planners = new EventPlannerModel();
            Bitmap bitimage = BitmapFactory.decodeResource(getResources(), R.drawable.mapleevents);
            planners.bitmap = bitimage;
            planners.name ="Suhas";
            planners.organisationname = "MAPLE EVENTS";
            planners.pwd = "maple";
            planners.email = "jonatham@mapleevents.in";
            planners.phone_number = planner_phone;
            planners.address ="5-65/1, Shanti Nagar, Hydershakote , suncity, Hyderabad, India";
            planners.events ="5";
            dbep.addeventplannerDetail(planners);
            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

        }
       /* planner_phone = "9676526691";

        if(dbep.matchplanner(planner_phone) != 1) {
            EventPlannerModel planners = new EventPlannerModel();
            Bitmap bitimage = BitmapFactory.decodeResource(getResources(), R.drawable.frames);
            planners.bitmap = bitimage;
            planners.name ="Avinash";
            planners.organisationname = "FRAMES EVENTS";
            planners.pwd = "frames";
            planners.email = "cnc.ak267@gmail.com";
            planners.phone_number = planner_phone;
            planners.address ="Madhapur , Hyderabad, India";
            planners.events ="5";
            dbep.addeventplannerDetail(planners);
            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

        }

        planner_phone = "9247327445";

        if(dbep.matchplanner(planner_phone) != 1) {
            EventPlannerModel planners = new EventPlannerModel();
            Bitmap bitimage = BitmapFactory.decodeResource(getResources(), R.drawable.lakshmievents);
            planners.bitmap = bitimage;
            planners.name ="MADHU";
            planners.organisationname = "LAKSHMI EVENTS";
            planners.pwd = "lakshmi";
            planners.email = "lakshmievents4u@gmail.com";
            planners.phone_number = planner_phone;
            planners.address ="Lakdikapool,Hyderabad, India";
            planners.events ="5";
            dbep.addeventplannerDetail(planners);
            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

        }*/
        return;

    }
}