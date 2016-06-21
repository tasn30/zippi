package com.example.nayeem.zippi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by DIVYA on 1/27/2016.
 */
public class DataBaseEventPlanners extends SQLiteOpenHelper {


        // Database Name
        public static String DATABASE_NAME = "YEP";

        // Current version of database
        private static final int DATABASE_VERSION = 1;

        // Name of table
        private static final String TABLE_eventplanners = "eventplanners";


        // All Keys used in table
    public static final String KEY_ROWID = "_id";
    public static final String KEY_LOGO = "logo";
    public static final String KEY_NAME = "name";
    public static final String KEY_ORGANISATIONNAME = "orgname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PWD = "pwd";
    public static final String KEY_PHONENUMBER = "phone_number";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_NUMOFEVENTS = "numofevents";
    private static final String KEY_OTP= "otp";
    private static final String KEY_Rating= "rating";

//Tag Name
    public static String TAG = "DataBase Operations";



    // planners Table Create Query

      private static final String CREATE_TABLE_eventplanners =
              "CREATE TABLE "+ TABLE_eventplanners
                      + "("+ KEY_ROWID+" integer primarykey,"
                      + KEY_PHONENUMBER + " TEXT UNIQUE,"
                      + KEY_NAME + " TEXT DEFAULT NULL,"
                      + KEY_ORGANISATIONNAME + " TEXT DEFAULT NULL,"
                      + KEY_PWD + " TEXT DEFAULT NULL,"
                      + KEY_EMAIL + " TEXT UNIQUE,"
                      + KEY_ADDRESS + " TEXT DEFAULT NULL,"
                      +KEY_NUMOFEVENTS+" TEXT DEFAULT NULL,"
                      +KEY_Rating+" Integer Default 0,"
                      + KEY_OTP + " TEXT DEFAULT NULL,"
                      + KEY_LOGO + " BLOB UNIQUE);";



        public DataBaseEventPlanners(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * This method is called by system if the database is accessed but not yet
         * created.
         */

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_eventplanners); // create planners table
        }

        /**
         * This method is called when any modifications in database are done like
         * version is updated or database schema is changed
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_eventplanners); // drop table if exists
            onCreate(db);
        }
        /**
         *
         * This method is used to add planners detail in planners Table
         */
        public long addeventplannerDetail(EventPlannerModel planner) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
            values.put(KEY_ORGANISATIONNAME, planner.organisationname);
            values.put(KEY_NAME, planner.name);
            values.put(KEY_EMAIL,planner.email);
            values.put(KEY_PWD, planner.pwd);
            values.put(KEY_PHONENUMBER, planner.phone_number);
            values.put(KEY_ADDRESS,planner.address);
             values.put(KEY_NUMOFEVENTS,planner.events);
             values.put(KEY_OTP,planner.otp);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        planner.bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] image = stream.toByteArray();
        values.put(KEY_LOGO, image);
        // insert row in planners table

        long insert1 = db.insert(TABLE_eventplanners, null, values);
        return insert1;
    }

        /**
         * This method is used to update particular planner entry
         *
         * @param planner
         * @return
         */
        public int updateEntry(EventPlannerModel planner) {
            SQLiteDatabase db = this.getWritableDatabase();
            // Creating content values
            ContentValues values = new ContentValues();
            values.put(KEY_ORGANISATIONNAME, planner.organisationname);
            values.put(KEY_NAME, planner.name);
            values.put(KEY_EMAIL,planner.email);
            values.put(KEY_PWD, planner.pwd);
            values.put(KEY_PHONENUMBER, planner.phone_number);
            values.put(KEY_ADDRESS,planner.address);
            values.put(KEY_NUMOFEVENTS, planner.events);
            values.put(KEY_OTP,planner.otp);

            // update row in planners table base on planners.is value
            return db.update(TABLE_eventplanners, values, KEY_PHONENUMBER + " = ?",
                    new String[] { String.valueOf(planner.phone_number) });
        }

        /**
         * Used to delete particular planner entry
         *
         * @param id
         */
    public void deleteEntry(long id) {

        // delete row in planners table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_eventplanners, KEY_PHONENUMBER + " = ?",
                new String[] { String.valueOf(id) });
    }
    public EventPlannerModel getuser(String phn) {
        SQLiteDatabase db = this.getReadableDatabase();
        // SELECT * FROM users WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_eventplanners + " WHERE "+ KEY_PHONENUMBER + " = " + phn;
        Log.d(TAG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        EventPlannerModel eventplanner = new EventPlannerModel();
        eventplanner.pwd = c.getString(c.getColumnIndex(KEY_PWD));
        eventplanner.phone_number = c.getString(c.getColumnIndex(KEY_PHONENUMBER));
        eventplanner.name = c.getString(c.getColumnIndex(KEY_NAME));
        c.close();
        return eventplanner;
    }

        public EventPlannerModel getplanner(String organisationname) {
            SQLiteDatabase db = this.getReadableDatabase();
            // SELECT * FROM planners WHERE id = ?;
            String selectQuery = "SELECT * FROM "+ TABLE_eventplanners+" WHERE "+KEY_ORGANISATIONNAME+" =?";
            Cursor c = db.rawQuery(selectQuery, new String[] {organisationname });
            Log.d(TAG, selectQuery);
            //Cursor c = db.rawQuery(selectQuery, null);
            EventPlannerModel eventplanner = new EventPlannerModel();

            if (c != null) {
                c.moveToFirst();

                eventplanner.name = c.getString(c.getColumnIndex(KEY_NAME));
                byte[] b = c.getBlob(c.getColumnIndex(KEY_LOGO));
                eventplanner.bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                eventplanner.email = c.getString(c.getColumnIndex(KEY_EMAIL));
                eventplanner.phone_number = c.getString(c.getColumnIndex(KEY_PHONENUMBER));
                eventplanner.events = c.getString(c.getColumnIndex(KEY_NUMOFEVENTS));
                eventplanner.address = c.getString(c.getColumnIndex(KEY_ADDRESS));
            }
                c.close();

                return eventplanner;
        }

        public int matchplanner(String phn)
        {
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery;
             selectQuery = "SELECT  * FROM " + TABLE_eventplanners + " WHERE "+ KEY_PHONENUMBER + " =" + phn;
            Cursor cu;
           String[] projections={KEY_PHONENUMBER};
            cu=db.query(TABLE_eventplanners, projections, null, null, null, null, null);
          // Toast.makeText(DataBaseEventPlanners.this,"DataBaseOperations= "+cu.getCount(),Toast.LENGTH_LONG).show();
            int i =0;
            i= cu.getCount();
            cu.close();
            if (i>0) {
                return 1;
            }
            else
                return 0;

        }

        /**
         * Used to get detail of entire database and save in array list of data type
         * plannersModel
         *
         * @return
         */

    public Cursor getInfo(SQLiteDatabase db)
    {
        Cursor cu;
        String[] projections={KEY_ORGANISATIONNAME,KEY_LOGO,KEY_ROWID};
        cu=db.query(TABLE_eventplanners,projections,null,null,null,null,null);
        return cu;
    }


}