package com.example.nayeem.zippi;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Messaging extends AppCompatActivity {
    Button one,two,six;
    EditText five,eight;
    String phn;
    int year_x,month_x,day_x,minute_x,hour_x;
    static final int DIALOG_ID=0, DIALOG_ID1=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        Intent i =getIntent();
        phn=i.getStringExtra("phone") ;
        six = (Button) findViewById(R.id.six);
        five = (EditText) findViewById(R.id.five);
        eight = (EditText) findViewById(R.id.eight);
        eight.setText(phn);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo=eight.getText().toString();
                String message=five.getText().toString();
                if(phoneNo.length()>0&&message.length()>0) {
                    sendMessage(phoneNo, message);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "enter phoneno and message", Toast.LENGTH_LONG).show();
                }

            }
        });


        final Calendar cal= Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();
        showTimePickerDialog();

        Button seven = (Button)findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText three=(EditText)findViewById(R.id.three);
                EditText four=(EditText)findViewById(R.id.four);

                String budg = String.valueOf(three.getText());
                String capacity = String.valueOf(four.getText());
                String message = "the event is on date" + day_x + "/" + month_x + "/" + year_x +"at time" + hour_x + ":" + minute_x + " With budget " + budg + " capacity " + capacity;
                EditText five = (EditText) findViewById(R.id.five);

                five.setText(message);


            }
        });

    }
    private void sendMessage(String phoneNo,String message)
    {

        try {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo,null,message,null,null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "SMS failed.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    public void showDialogOnButtonClick()
    {
        one = (Button)findViewById(R.id.one);
        one.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);

                    }

                }

        );
    }
    public void showTimePickerDialog()
    {
        two = (Button)findViewById(R.id.two);
        two.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View V)
                    {
                        showDialog(DIALOG_ID1);
                    }


                }
        );
    }
    @Override
    protected Dialog onCreateDialog(int id)
    {

        if(id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerlistener, year_x, month_x, day_x);
        }
        else if(id == DIALOG_ID1) {
            return new TimePickerDialog(this, kTimePickerListener, hour_x, minute_x, true);
        }
        else
            return null;
    }
    public TimePickerDialog.OnTimeSetListener kTimePickerListener =
            new TimePickerDialog.OnTimeSetListener(){
                public void onTimeSet(TimePicker view, int hourOfDay,int minute)
                {
                    hour_x = hourOfDay;
                    minute_x = minute;

                    //Toast.makeText(MainActivity.this,hour_x+":"+minute_x, Toast.LENGTH_LONG).show();
                }

            };
    public DatePickerDialog.OnDateSetListener dpickerlistener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x=year;
            month_x=monthOfYear + 1;
            day_x=dayOfMonth;
            //String message1="the event is on "+day_x+"/"+month_x+"/"+year_x;
            //EditText five=(EditText)findViewById(R.id.five);
            //five.setText(message1);
            //Toast.makeText(MainActivity.this,year_x+"/"+month_x+"/"+day_x, Toast.LENGTH_LONG).show();

        }

    };
    //String message = "the event is on date"+day_x+"/"+month_x+"/"+"year_x at time"+hour_x+":"+minute_x;

    //EditText five=(EditText)findViewById(R.id.five);

    //five.setText(message);
    protected void sendSMSMessage() {
        Log.i("Send SMS", "");
        String phoneNo = eight.getText().toString();
        String message = five.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}
