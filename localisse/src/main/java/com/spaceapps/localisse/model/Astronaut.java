package com.spaceapps.localisse.model;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.spaceapps.localisse.R;

/**
 * Created by JMliras on 12/04/2014.
 */
public class Astronaut {
    Context ctx;
    String name;
    String id;
    //ImageView astronautView;

    public Astronaut(Context ctx, String name, String id) {
        this.ctx = ctx;
        this.name = name;
        this.id = id;
    }

    public String getId(){
        return id;
    }


}
