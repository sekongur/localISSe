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
 * Created by JMliras on 12/04/2014.
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
        astronautsLayout.setBackgroundResource(android.R.color.transparent);

        astronautsLayout.bringToFront();
    }

    public void addAstronaut(Astronaut astronaut) {
        if (astronautsLayout != null) {
            astronautsInZone.add(astronaut);
            if (astronaut.getId().equals("2dc2e73f8d2f41ff") || astronaut.getId().equals("5ef447d071199ba")) {
                LinearLayout.LayoutParams lp =
                        new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                ImageView astronautView = new ImageView(ctx);
                astronautView.setLayoutParams(lp);
                // COULD BE DIFFERENT DEPENDS ON ID TO PERSONALIZE
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

}
