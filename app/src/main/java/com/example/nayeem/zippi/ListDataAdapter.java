package com.example.nayeem.zippi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DIVYA on 2/12/2016.
 */
public class ListDataAdapter extends ArrayAdapter
{
    private Context context;
    public String orgname;
    List l=new ArrayList();
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
    }
    static class LayoutHandler
    {
        TextView organisationname;
        ImageView logo;

    }
    @Override
    public void add(Object obj)
    {
        super.add(obj);
        l.add(obj);

    }
    @Override
    public  int getCount()
    {
        return l.size();
    }
    @Override
    public Object getItem(int position)
    {
        return l.get(position);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View row=convertView;
        LayoutHandler lh;

        if(row==null)
        {
            LayoutInflater li=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=li.inflate(R.layout.eachrow,parent,false);
            lh=new LayoutHandler();
            lh.organisationname=(TextView)row.findViewById(R.id.organisationname);
            lh.logo=(ImageView)row.findViewById(R.id.logo);
            row.setTag(lh);
        }
        else
        {
            lh=(LayoutHandler)row.getTag();
        }
        EventPlannerModel dp=(EventPlannerModel)this.getItem(position);
        orgname=dp.getOrganisationname();
        lh.organisationname.setText(dp.getOrganisationname());
        lh.logo.setImageBitmap(dp.getBitmap());
        row.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AboutEventPlanners.class);

                //  Log.e("DAta basE OPERatioNS",orgname);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Key",orgname);
                context.startActivity(intent);
            }
        });

        return row;
    }



}
