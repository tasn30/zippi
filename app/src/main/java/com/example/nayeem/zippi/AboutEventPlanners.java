package com.example.nayeem.zippi;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by DIVYA on 2/25/2016.
 */
public class AboutEventPlanners extends Activity{
    DataBaseEventPlanners udb;
    //SQLiteDatabase db;
    EventPlannerModel epm;

    ImageView logo;
    TextView organisationname, name, phone, email, address,  events;
    String orgname;
    String phn;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abouteventplanners);
        udb=new DataBaseEventPlanners(getApplicationContext());
        epm=new EventPlannerModel();
        Intent intent=getIntent();
        orgname = intent.getStringExtra("Key");
        organisationname = (TextView) findViewById(R.id.organisationname);

        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        address = (TextView) findViewById(R.id.address);
        events = (TextView) findViewById(R.id.events);
        logo=(ImageView)findViewById(R.id.logo);
        epm=udb.getplanner(orgname);
        showing();

                }
    public void showing()
    {
        //db=udb.getReadableDatabase();

        organisationname.setText(orgname);
        name.setText(epm.getName());
        email.setText(epm.getEmail());
        phone.setText(epm.getPhone_number());
        address.setText(epm.getAddress());
        events.setText(epm.getEvents());
        phn=epm.getPhone_number().toString();
        logo.setImageBitmap(epm.getBitmap());

    }

    public void calling(View v) {


        String num = epm.getPhone_number();
        if (num.matches(""))
            Toast.makeText(AboutEventPlanners.this, "Invalid Number", Toast.LENGTH_SHORT).show();
        else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + num));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);
        }
    }

    public void messaging(View v)
    {
        Intent callIntent = new Intent(AboutEventPlanners.this,Messaging.class);
        callIntent.putExtra("phone",phn);
        startActivity(callIntent);
    }



}
