package com.example.nayeem.zippi;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by NAyeem on 3/4/2016.
 */
public class eventsign extends Activity {

    Button reg;
    EditText  phone_no, pwd;
    //DatabaseHelper db;
    DataBaseEventPlanners db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventsign);



       // reg = (Button) findViewById(R.id.button);
            phone_no = (EditText) findViewById(R.id.phn);
            pwd= (EditText) findViewById(R.id.pwd);
          //  db = new DatabaseHelper(getApplicationContext());
            db1 = new DataBaseEventPlanners(getApplicationContext());
        }
        public void logging(View v) {
            String user_pwd = pwd.getText().toString();
            String user_phn = phone_no.getText().toString();
            if (user_pwd.matches("") || user_pwd.matches("Password") || user_phn.matches("") || user_phn.matches("PHN"))
                Toast.makeText( eventsign.this, "Enter All THE Details", Toast.LENGTH_SHORT).show();
                //if the user  or event planners enters all the details
            else {
                    int planners = db1.matchplanner(user_phn);
                    //if there exist no event planner with the phn no
                    if (planners == 0)//2
                        Toast.makeText( eventsign.this, "Wrong Credentials! Re-Enter", Toast.LENGTH_SHORT).show();
                    else {//2
                        //if there exist event planner with the phn no
                        EventPlannerModel planner = db1.getuser(user_phn);
                        if (user_pwd.matches(planner.pwd)) //1
                        {//if the phn number matches with the password

                            Intent intent = new Intent( eventsign.this, MainActivity.class);
                            intent.putExtra("key1",planner.name);
                            // db1.close();
                            startActivity(intent);
                        } else //1
                        {//if the phn number and password don't match
                            Toast.makeText( eventsign.this, " Wrong Credentials! Re-Enter", Toast.LENGTH_SHORT).show();

                        }
                    }
                } //users = db.matchuser(user_phn);
                //if


            }

    public void Registereventplanner(View view) {

            Intent intent = new Intent( eventsign.this, EventplannerLogin.class);
            startActivity(intent);
        }
}



