package com.spaceapps.localisse.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spaceapps.localisse.MainApp;
import com.spaceapps.localisse.R;
import com.spaceapps.localisse.common.Utils;
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
        astronautsLayout.setBackgroundResource(android.R.color.transparent);
        /*astronautsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ctx, ZoneActivity.class);
                int zoneid = MainApp.getInstance().getZones().size();
                i.putExtra("zoneid", zoneid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("ZONEAOBJECT", "SEND ATIVITY ZONE WITH ID: " + zoneid);
                ctx.startActivity(i);
            }
        });*/
        astronautsLayout.bringToFront();
    }

    public void addAstronaut(Astronaut astronaut) {
        if (astronautsLayout != null) {
            astronautsInZone.add(astronaut);
            //TextView tv=new TextView(ctx);
            //tv.setText("1");
            //astronautsLayout.addView(tv);
            //astronautsLayout.addView(astronaut.getView());
            if (astronaut.getId().equals("2dc2e73f8d2f41ff")/* || astronaut.getId().equals("5ef447d071199ba")*/) {
                LinearLayout.LayoutParams lp =
                        new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                ImageView astronautView = new ImageView(ctx);
                astronautView.setLayoutParams(lp);
                // COULD BE DIFFERENT DEPENDS ON ID TO PERSONALIZE
                /*if (astronaut.getId().equals("0"))
                    astronautView.setImageResource(R.drawable.astro1);
                if (astronaut.getId().equals("156745121565"))
                    astronautView.setImageResource(R.drawable.astro2);
                if (astronaut.getId().equals("15674515"))
                    astronautView.setImageResource(R.drawable.astro3);*/
                if (astronaut.getId().equals("2dc2e73f8d2f41ff"))
                    astronautView.setImageResource(R.drawable.astro1);
                if (astronaut.getId().equals("5ef447d071199ba"))
                    astronautView.setImageResource(R.drawable.astro2);

                astronautsLayout.addView(astronautView);
            }


        }
    }

    public void clear(){
        astronautsLayout.removeAllViews();
    }

    /*public void removeAstronaut(Astronaut astronaut) {
        if (astronautsLayout != null) {
            astronautsInZone.remove(astronaut);
            astronautsLayout.removeView(astronaut.getView());
        }
    }*/
}
