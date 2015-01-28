package com.spaceapps.localisse.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spaceapps.localisse.MainApp;
import com.spaceapps.localisse.R;
import com.spaceapps.localisse.model.Application;

import java.util.ArrayList;

/**
 * Created by JMliras on 12/04/2014.
 */
public class AppAdapter extends BaseAdapter {
    ArrayList<Application> appList;

    public AppAdapter(ArrayList<Application> apps) {
        //TODO:
        appList = apps;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflator.inflate(R.layout.app_item, null);
        }

        ((TextView)view.findViewById(R.id.nametext)).setText(appList.get(position).name);
        ((TextView)view.findViewById(R.id.desctext)).setText(appList.get(position).description);
        ((ImageView)view.findViewById(R.id.appimage)).setImageResource(appList.get(position).appIcon);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appList.get(position).intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(appList.get(position).intent);
            }
        });
        return view;
    }
}
