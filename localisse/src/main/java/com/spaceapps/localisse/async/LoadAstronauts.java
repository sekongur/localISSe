package com.spaceapps.localisse.async;

import android.os.AsyncTask;
import com.spaceapps.localisse.common.LocalisseAPI;
import com.spaceapps.localisse.model.Usuario;
import com.spaceapps.localisse.model.usuarios;
import com.spaceapps.localisse.ui.MainActivity;

import java.util.List;

public class LoadAstronauts extends AsyncTask<Void, Void, Void> {
	private MainActivity activity;
    private List<Usuario> astronauts;

	public LoadAstronauts(MainActivity activity) {
    	this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
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
    }

}
