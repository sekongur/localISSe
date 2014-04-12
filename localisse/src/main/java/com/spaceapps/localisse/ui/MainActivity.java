package com.spaceapps.localisse.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.spaceapps.localisse.com.spaceapps.localisse.utils.Utils;
import com.spaceapps.localisse.model.Zone;

import java.util.Collection;


public class MainActivity extends Activity implements IBeaconConsumer {
    MainApp main;

    public static final double RANGE_ZONE = 2.0;

    protected PowerManager.WakeLock mWakeLock;


    //private IBeaconProtocol ibp;
    private IBeaconManager iBeaconManager = IBeaconManager.getInstanceForApplication(this);


    // Configure here your sample UUID
    //public static final byte[] UUID = {(byte)0xEB, (byte)0xEF, (byte)0xD0, (byte)0x83, 0x70, (byte)0xA2, 0x47, (byte)0xC8, (byte)0x98, 0x37, (byte)0xE7, (byte)0xB5, 0x63, 0x4D, (byte)0xF5, 0x24};

    // Configure the UUID, major an minor of your sample easiBeacons
    /*private IBeacon zoneBeacon1 = new IBeacon(UUID, 1, 1);
    private IBeacon zoneBeacon2 = new IBeacon(UUID, 1, 2);
    private IBeacon zoneBeacon3 = new IBeacon(UUID, 1, 3);
*/
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iBeaconManager.bind(this);
  //      ibp = IBeaconProtocol.getInstance(this);
    //    ibp.setListener(this);
/*
        TimerTask searchIbeaconTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        scanBeacons();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(searchIbeaconTask, 1000, 5*1000);*/

        //Keep the screen ON
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        this.mWakeLock.acquire();
    }

    @Override
    public void onResume() {
        super.onResume();
        main = MainApp.getInstance();
        addZone (0, 0, 400, 400);
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
            //scanBeacons();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addZone (int beginX, int beginY, int sizeX, int sizeY) {
        Zone newZone = new Zone(getApplicationContext(), beginX, beginY, sizeX, sizeY);
        main.getZones().add(newZone);
        ((RelativeLayout)findViewById(R.id.map_content)).addView(newZone.getView());
        newZone.getView().bringToFront();
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

            Toast.makeText(getApplicationContext(), "Abriendo zona "+zoneid,
                    Toast.LENGTH_LONG).show();

            this.startActivity(i);
        }
    }

/*
    @Override
    public void enterRegion(IBeacon iBeacon) {
        Log.e("Shop","Enter region: " + "dist:"+iBeacon.getProximity()+" "+iBeacon.toString());
        checkOpenZone(iBeacon);
    }



    @Override
    public void exitRegion(IBeacon iBeacon) {
        Log.e("Shop","Exit region: " + "dist:"+iBeacon.getProximity()+" "+iBeacon.toString());
        checkOpenZone(iBeacon);
    }

    @Override
    public void beaconFound(IBeacon iBeacon) {
        Log.e("Shop","Beacon Found: " + "dist:"+iBeacon.getProximity()+" "+iBeacon.toString());
        checkOpenZone(iBeacon);
    }

    @Override
    public void searchState(int i) {

    }

    @Override
    public void operationError(int i) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_BLUETOOTH_ENABLE){
            if(resultCode == Activity.RESULT_OK){
                scanBeacons();
            }
        }
    }

    private void scanBeacons(){
        // Check Bluetooth every time
        Log.e(Utils.LOG_TAG, "Scanning");

        ibp = IBeaconProtocol.getInstance(this);

        // Filter based on default easiBeacon UUID, remove if not required
        ibp.setScanUUID(UUID);

        if(!IBeaconProtocol.initializeBluetoothAdapter(this)){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_BLUETOOTH_ENABLE );
        }else{
            ibp.setListener(this);
            if(ibp.isScanning())
                ibp.scanIBeacons(false);
            ibp.reset();
            ibp.scanIBeacons(true);
        }
    }*/
}
