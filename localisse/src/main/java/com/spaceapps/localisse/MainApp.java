package com.spaceapps.localisse;

/**
 * Created by Jacob on 4/7/2014.
 */

import android.app.Application;
import com.spaceapps.localisse.model.Zone;
import java.util.ArrayList;

/**
 * Created by Jacob on 11/25/13.
 */
public class MainApp extends Application {
    public final static String client = "il3";
    private static MainApp singleton;
    ArrayList<Zone> zones;

    public static MainApp getInstance() {
        return singleton;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        singleton = this;
        zones = new ArrayList<Zone>();
    }

    public ArrayList<Zone> getZones() {
        return zones;
    }
}
