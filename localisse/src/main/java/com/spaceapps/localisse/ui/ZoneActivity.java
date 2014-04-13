package com.spaceapps.localisse.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.spaceapps.localisse.MainApp;
import com.spaceapps.localisse.R;
import com.spaceapps.localisse.adapters.AppAdapter;
import com.spaceapps.localisse.model.Application;
import com.spaceapps.localisse.model.Zone;

import java.util.ArrayList;


public class ZoneActivity extends Activity {
    ListView appList;
    MainApp main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zone_layout);

        appList = (ListView)findViewById(R.id.app_list);
    }

    @Override
    public void onResume() {
        super.onResume();
        main = MainApp.getInstance();

        Intent intent = getIntent();
        int zoneid = intent.getIntExtra("zoneid", -1);
        Log.d("ZONEACTIVITY", "ID DE LA ZONA: " + zoneid);

        if (zoneid == 0) {
            ((LinearLayout) this.findViewById(R.id.layoutbg)).setBackgroundResource(R.drawable.zona1);
            ArrayList<Application> apps = new ArrayList<Application>();
            apps.add(new Application(
                    new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                    "Music", "Play music", R.drawable.musica));

            apps.add(new Application(
                    new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                    "Notes", "Take notes", R.drawable.bloc));

            apps.add(new Application(
                    new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                    "Mail", "Access to your mail", R.drawable.mail));

            apps.add(new Application(
                    new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                    "Chat", "Talk with others", R.drawable.chat));
            AppAdapter appAdapter = new AppAdapter(apps);
            appList.setAdapter(appAdapter);
        }
        if (zoneid == 1) {
            ((LinearLayout) this.findViewById(R.id.layoutbg)).setBackgroundResource(R.drawable.zona2);

            ArrayList<Application> apps = new ArrayList<Application>();
            apps.add(new Application(
                    new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                    "Control Panel", "Control Panel", R.drawable.control));

            apps.add(new Application(
                    new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                    "Compass", "Orientate yourself", R.drawable.brujula));

            AppAdapter appAdapter = new AppAdapter(apps);
            appList.setAdapter(appAdapter);
        }
        if (zoneid == 2) {
            ((LinearLayout) this.findViewById(R.id.layoutbg)).setBackgroundResource(R.drawable.zona3);

            ArrayList<Application> apps = new ArrayList<Application>();
            apps.add(new Application(
                    new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                    "Lab", "Laboratory data", R.drawable.lab));

            apps.add(new Application(
                    new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                    "Data", "View log files", R.drawable.archivo));

            AppAdapter appAdapter = new AppAdapter(apps);
            appList.setAdapter(appAdapter);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
