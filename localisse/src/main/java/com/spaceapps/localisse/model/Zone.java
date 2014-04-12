package com.spaceapps.localisse.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.spaceapps.localisse.MainApp;
import com.spaceapps.localisse.R;
import com.spaceapps.localisse.ui.ZoneActivity;

import java.util.ArrayList;

/**
 * Created by Jacob on 4/12/2014.
 */
public class Zone {
    int x, y, sizeX, sizeY;
    LinearLayout astronautsLayout;
    ArrayList<Astronaut> astronautsInZone;
    Context ctx;

    public Zone(Context ctx, int beginX, int beginY, int sizeX, int sizeY) {
        this.x = beginX;
        this.y = beginY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.ctx = ctx;
        astronautsInZone = new ArrayList<Astronaut>();

        createView();
    }

    public LinearLayout getView() {
        return astronautsLayout;
    };

    private void createView() {
        astronautsLayout = new LinearLayout(ctx);
        astronautsLayout.setOrientation(LinearLayout.HORIZONTAL);
//        astronautsLayout.setLayoutParams(lp);
        astronautsLayout.setBackgroundResource(R.color.transparentbackground);
        astronautsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ctx, ZoneActivity.class);
                int zoneid = MainApp.getInstance().getZones().size();
                i.putExtra("zoneid", zoneid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("ZONEAOBJECT", "SEND ATIVITY ZONE WITH ID: " + zoneid);
                ctx.startActivity(i);
            }
        });
        astronautsLayout.bringToFront();
    }

    public void addAstronaut(Astronaut astronaut) {
        if (astronautsLayout != null) {
            astronautsInZone.add(astronaut);
            astronautsLayout.addView(astronaut.getView());
        }
    }

    public void removeAstronaut(Astronaut astronaut) {
        if (astronautsLayout != null) {
            astronautsInZone.remove(astronaut);
            astronautsLayout.removeView(astronaut.getView());
        }
    }
}
