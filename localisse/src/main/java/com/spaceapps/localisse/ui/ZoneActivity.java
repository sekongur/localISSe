package com.spaceapps.localisse.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

        ArrayList<Application> apps = new ArrayList<Application>();
        apps.add(new Application(
                new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                "Facebook", "This is facebook", R.drawable.ic_launcher));
        apps.add(new Application(
                new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                "Facebook", "This is facebook", R.drawable.ic_launcher));
        apps.add(new Application(
                new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                "Facebook", "This is facebook", R.drawable.ic_launcher));
        apps.add(new Application(
                new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root")),
                "Facebook", "This is facebook", R.drawable.ic_launcher));
        AppAdapter appAdapter = new AppAdapter(apps);
        appList.setAdapter(appAdapter);
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
