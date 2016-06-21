package com.example.nayeem.zippi;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EventplannerLogin extends AppCompatActivity {

    Bitmap bitimage;
    EditText organisationname, name, phone, email, address, password, events;
    private static int RESULT_LOAD_IMG = 1;
    public ImageView b1;
    DataBaseEventPlanners dbep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginformofeventplanner);

        b1 = (ImageView) findViewById(R.id.logo);



    }

    public void gotogallery(View v) {
        startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), RESULT_LOAD_IMG);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == -1 && data != null) {
                String[] filePathColumn = new String[]{"_data"};
                Cursor cursor = getContentResolver().query(data.getData(), filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();
                bitimage = BitmapFactory.decodeFile(filePath);
                b1.setImageBitmap(bitimage);


            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }


    public void submit(View view) {

        organisationname = (EditText) findViewById(R.id.organisationname);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        events = (EditText) findViewById(R.id.events);
        dbep = new DataBaseEventPlanners(getApplicationContext());
        String planner_name = name.getText().toString();
        String planner_organisationname = name.getText().toString();
        String planner_email = email.getText().toString();
        String planner_password = password.getText().toString();
        String planner_phone = phone.getText().toString();
        String planner_address = address.getText().toString();
        String planner_events = events.getText().toString();

        if (planner_password.matches("") ||
                planner_name.matches("") ||
                planner_organisationname.matches("") ||
                planner_phone.matches("") ||
                planner_email.matches("") ||
                planner_address.matches("") ||
                planner_events.matches(""))
            Toast.makeText(EventplannerLogin.this, "Enter All THE Details", Toast.LENGTH_SHORT).show();


        else {


           /* if (planner_phone.length() >= 10 && (planner_password.length() <= 3 || planner_name.length() <= 3))
                Toast.makeText(MainActivity.this, "number of Characters entered in password and name should be atleast 4!", Toast.LENGTH_SHORT).show();


            else if (planner_phone.length() < 10 && (planner_password.length() <= 3 || planner_name.length() <= 3))
                Toast.makeText(MainActivity.this, "number of Characters entered in password and name should be atleast 4 or entered an invalid number", Toast.LENGTH_SHORT).show();


            else if (planner_phone.length() < 10 && (planner_password.length() > 3 || planner_name.length() > 3))
                Toast.makeText(MainActivity.this, "entered an invalid number", Toast.LENGTH_SHORT).show();

            else {
                  int i;
                i = dbep.matchplanner(planner_phone);
                if (i == 1)
                    Toast.makeText(MainActivity.this, "Already Registered as a Event Planner!", Toast.LENGTH_SHORT).show();
                else {*/
                    EventPlannerModel planners = new EventPlannerModel();
                    planners.bitmap = bitimage;
                    planners.name = planner_name;
                    planners.organisationname = planner_organisationname;
                    planners.pwd = planner_password;
                    planners.email = planner_email;
                    planners.phone_number = planner_phone;
                    planners.address = planner_address;
                    planners.events = planner_events;
                   dbep.addeventplannerDetail(planners);
                    Toast.makeText(EventplannerLogin.this, "Registered Successfully", Toast.LENGTH_SHORT).show();


                }
startActivity(new Intent(EventplannerLogin.this,MainActivity.class));

            }
      //  }
    //}




}