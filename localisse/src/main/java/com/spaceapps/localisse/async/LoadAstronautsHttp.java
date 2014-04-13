package com.spaceapps.localisse.async;

import android.os.AsyncTask;
import android.util.Log;

import com.spaceapps.localisse.common.Utils;
import com.spaceapps.localisse.ui.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class LoadAstronautsHttp extends AsyncTask<String, String, String> {
    private MainActivity activity;
    private String responseString;

    public LoadAstronautsHttp(MainActivity activity) {
        this.activity = activity;
//        progress = new ProgressDialog(activity);
    }

    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        //String responseString = null;
        try {
            Log.e(Utils.LOG_TAG, "respuesta recibida");

            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..
        Log.e(Utils.LOG_TAG, "onPostExecute");
        activity.onAstronautsReady(responseString);

    }
}