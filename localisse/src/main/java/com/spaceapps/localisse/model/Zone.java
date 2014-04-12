package com.spaceapps.localisse.model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.spaceapps.localisse.MainApp;
import com.spaceapps.localisse.R;
import com.spaceapps.localisse.ui.ZoneActivity;

/**
 * Created by Jacob on 4/12/2014.
 */
public class Zone {
    int x, y, sizeX, sizeY;
    Context ctx;
    View v;

    public Zone(Context ctx, int beginX, int beginY, int sizeX, int sizeY) {
        this.x = beginX;
        this.y = beginY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.ctx = ctx;
        createView();
    }

    public View getView() {
        return v;
    };

    private void createView() {
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        lp.setMargins(x, y, x + sizeX, y + sizeY);
        v = new View(ctx);
        v.setLayoutParams(lp);
        v.setBackgroundResource(R.color.transparentbackground);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ctx, ZoneActivity.class);
                i.putExtra("zoneid", MainApp.getInstance().getZones().indexOf(this));
                ctx.startActivity(i);
            }
        });
    }
}
