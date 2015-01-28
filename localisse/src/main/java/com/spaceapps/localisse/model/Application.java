package com.spaceapps.localisse.model;

import android.content.Intent;

/**
 * Created by JMliras on 12/04/2014.
 */
public class Application {
    public Intent intent;
    public String name;
    public String description;
    public int appIcon;

    public Application(Intent intent, String name, String description, int appIcon) {
        this.intent = intent;
        this.name = name;
        this.description = description;
        this.appIcon = appIcon;
    }
}
