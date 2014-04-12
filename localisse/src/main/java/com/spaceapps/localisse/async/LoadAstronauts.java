package com.spaceapps.localisse.async;

import android.os.AsyncTask;
import com.spaceapps.localisse.common.LocalisseAPI;
import com.spaceapps.localisse.model.usuarios;
import com.spaceapps.localisse.ui.MainActivity;

public class LoadAstronauts extends AsyncTask<Void, Void, Void> {
	private MainActivity activity;
//    private ProgressDialog progress = null;
    private usuarios astronauts;

	public LoadAstronauts(MainActivity activity) {
    	this.activity = activity;
//        progress = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        // This method works in UI Thread
//        progress.setTitle("LOADING ASTRONAUTS");
//        progress.setMessage("Pleaase wait a second..");
//        progress.setCancelable(false);
//        progress.show();
    }

	@Override
	protected Void doInBackground(Void... params) {
        LocalisseAPI api = new LocalisseAPI();
        try {
            astronauts = api.getAstronauts();
        } catch (Exception e) {
            //TODO: Catch Retrofit error trying to access API
            e.printStackTrace();
        }
        return null;
	}

    @Override
    protected void onPostExecute(Void result)
    {
//        if (progress.isShowing()) progress.dismiss();
        activity.onAstronautsReady(astronauts);
    }

}
