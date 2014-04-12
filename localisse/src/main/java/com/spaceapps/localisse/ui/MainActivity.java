package com.spaceapps.localisse.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;
import com.spaceapps.localisse.MainApp;
import com.spaceapps.localisse.R;
import com.spaceapps.localisse.async.LoadAstronauts;
import com.spaceapps.localisse.common.LocalisseAPI;
import com.spaceapps.localisse.common.Utils;
import com.spaceapps.localisse.model.Astronaut;
import com.spaceapps.localisse.model.Zone;
import com.spaceapps.localisse.model.usuarios;

import java.util.Collection;
import java.util.List;


public class MainActivity extends Activity implements IBeaconConsumer {
    MainApp main;

    public static final double RANGE_ZONE = 2.0;

    protected PowerManager.WakeLock mWakeLock;

    //private IBeaconProtocol ibp;
    private IBeaconManager iBeaconManager = IBeaconManager.getInstanceForApplication(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iBeaconManager.bind(this);

        main = MainApp.getInstance();

        // ADD ZONES HERE
        main.getZones().add(addZone(130, 250, 200, 90));
        main.getZones().add(addZone(350, 250, 200, 90));
        main.getZones().add(addZone(740, 250, 150, 90));

        // ASYNC LOAD ASTRONAUTS
        LoadAstronauts load = new LoadAstronauts(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            load.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            load.execute();
        }

        //Keep the screen ON
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        this.mWakeLock.acquire();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mWakeLock.release();
        iBeaconManager.unBind(this);
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

    public void onAstronautsReady(usuarios astronauts) {
//        List<Astronaut> astronauts = api.getAstronauts();
//
//        zone1.addAstronaut();
//        zone1.addAstronaut();
//        zone1.addAstronaut();
//
//
//        zone2.addAstronaut();
    }

    public Zone addZone (int beginX, int beginY, int sizeX, int sizeY) {
        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(
                        sizeX,
                        sizeY);

        lp.setMargins(beginX, beginY, 0, 0);

        Zone newZone = new Zone(getApplicationContext(), beginX, beginY, sizeX, sizeY);
        main.getZones().add(newZone);
        ((RelativeLayout)findViewById(R.id.map_content)).addView(newZone.getView(), lp);
        newZone.getView().bringToFront();

        return newZone;
    }


    @Override
    public void onIBeaconServiceConnect() {
        iBeaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region) {
                if (iBeacons.size() > 0) {
                    openNearestZone(iBeacons);
                    Log.e(Utils.LOG_TAG, "The first iBeacon I see is about " + iBeacons.iterator().next().getAccuracy() + " meters away.");
                }
            }
        });

        try {
            iBeaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {   }


        /*iBeaconManager.setMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.e(Utils.LOG_TAG, "I just saw an iBeacon for the firt time!");
            }

            @Override
            public void didExitRegion(Region region) {
                Log.e(Utils.LOG_TAG, "I no longer see an iBeacon");
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.e(Utils.LOG_TAG, "I have just switched from seeing/not seeing iBeacons: "+state+" "+region.getMinor()+" "+region.toString());
            }
        });*/

        /*try {
            iBeaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
        } catch (RemoteException e) {   }*/
    }

    public void openNearestZone(Collection<IBeacon> iBeacons){
        //Open the zone for the nearest beacon if is <= to the RANGE
        if (iBeacons.iterator().next().getAccuracy() <= RANGE_ZONE){
            Intent i=new Intent(this, ZoneActivity.class);
            int zoneid = iBeacons.iterator().next().getMinor();
            i.putExtra("zoneid", zoneid);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.d("ZONEAOBJECT", "SEND ACTIVITY ZONE WITH ID: " + zoneid);

            Toast.makeText(getApplicationContext(), "Abriendo zona " + zoneid,
                    Toast.LENGTH_LONG).show();

            this.startActivity(i);
        }
    }

}
