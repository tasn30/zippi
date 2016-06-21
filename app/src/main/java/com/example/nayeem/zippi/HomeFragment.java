package com.example.nayeem.zippi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HomeFragment extends Fragment {


    DataBaseEventPlanners udb;
    ListView l;
    SQLiteDatabase db;
    byte[] img1;
    Cursor c;
    ListDataAdapter lda;

    private View myFragmentView;




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        udb = new DataBaseEventPlanners(getActivity().getApplicationContext());
        myFragmentView = inflater.inflate(R.layout.home_layout, container, false);


        l = (ListView) myFragmentView.findViewById(R.id.listView);

             lda = new ListDataAdapter(getActivity(), R.layout.eachrow);
              l.setAdapter(lda);
              db = udb.getReadableDatabase();
        c = udb.getInfo(db);
        if (c.moveToFirst()) {
            do {
                String name, id;
                name = c.getString(0);
                id = c.getString(2);
                img1 = c.getBlob(1);
                Bitmap b1 = BitmapFactory.decodeByteArray(img1, 0, img1.length);
                EventPlannerModel dp = new EventPlannerModel(name, b1, id);
                lda.add(dp);
            } while (c.moveToNext());
            c.close();


        }

        return myFragmentView;
    }



}

