package com.spaceapps.localisse.common;

import android.util.Log;
import com.spaceapps.localisse.model.PositionResponse;
import com.spaceapps.localisse.model.Usuario;
import com.spaceapps.localisse.model.usuarios;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Jacob on 4/2/2014.
 */
public class LocalisseAPI {
    //private static final String API_URL = "http://ghomam.es/nasa/";
    private static final String API_URL = "http://walk.interactive-tales.com";
    private final String TAG = this.getClass().getSimpleName();

    public RestAdapter getAdapter() {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        return restAdapter;
    }

    public interface ghomamAPI {
        //@GET("/location")
        //usuarios getAstronauts();

        @GET("/exlocation.json")
        List<Usuario> getAstronauts();

        @GET("/users/{user}/{location}")
        PositionResponse setUserPosition(   @Path("user") String user,
                                            @Path("location") String location);
    }

    public List<Usuario> getAstronauts() {
        ghomamAPI users = getAdapter().create(ghomamAPI.class);
        Log.e(Utils.LOG_TAG, "Buscando astronautas" );
        List<Usuario> result = users.getAstronauts();
        Log.e(Utils.LOG_TAG, "Fin de la consulta" );
        for (Usuario demo : result) {
            Log.d(TAG, "ASTRONAUTA ENCONTRADO" );
        }
        Log.e(Utils.LOG_TAG, "Fin de tratamiento" );
        return result;
    }
    /*public usuarios getAstronauts() {
        ghomamAPI users = getAdapter().create(ghomamAPI.class);

        usuarios result = users.getAstronauts();
        for (Usuario demo : result.usuarios) {
            Log.d(TAG, "ASTRONAUTA ENCONTRADO" );
        }
        return result;
    }*/

    public PositionResponse setUserPosition(String userID, String location) {
        ghomamAPI users = getAdapter().create(ghomamAPI.class);

        PositionResponse result = users.setUserPosition(userID, location);
        Log.d(TAG, "ASTRONAUTA " + userID + " SET TO ZONE " + location + " / RESULT " + result.status );
        return result;
    }
}
