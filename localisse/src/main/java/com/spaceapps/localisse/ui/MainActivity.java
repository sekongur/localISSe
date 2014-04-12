package com.spaceapps.localisse.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.spaceapps.localisse.MainApp;
import com.spaceapps.localisse.R;
import com.spaceapps.localisse.model.Zone;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    MainApp main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
        main = MainApp.getInstance();
        main.getZones().add(new Zone(this, 0, 0, 400, 400));
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

    public void addZone (int beginX, int beginY, int sizeX, int sizeY) {
        Zone newZone = new Zone(getApplicationContext(), beginX, beginY, sizeX, sizeY);
        main.getZones().add(newZone);
        ((RelativeLayout)findViewById(R.id.map_content)).addView(newZone.getView());
    }



}
