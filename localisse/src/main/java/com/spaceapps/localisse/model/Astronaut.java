package com.spaceapps.localisse.model;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.spaceapps.localisse.R;

/**
 * Created by Jacob on 4/12/2014.
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
        //createView();
    }

    public String getId(){
        return id;
    }

    /*private void createView() {
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        ImageView astronautView = new ImageView(ctx);
        astronautView.setLayoutParams(lp);
        // COULD BE DIFFERENT DEPENDS ON ID TO PERSONALIZE
        astronautView.setImageResource(R.drawable.astronaut);
    }*/

    /*public ImageView getView() {
        return astronautView;
    }*/

}
