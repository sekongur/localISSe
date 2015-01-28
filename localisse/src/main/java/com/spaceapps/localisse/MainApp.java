package com.spaceapps.localisse;

/**
 * Created by JMliras on 12/04/2014.
 */

import android.app.Application;
import com.spaceapps.localisse.model.Zone;
import java.util.ArrayList;

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
