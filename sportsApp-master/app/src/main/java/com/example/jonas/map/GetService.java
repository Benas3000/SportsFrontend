package com.example.jonas.map;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class GetService extends AsyncTask<Void, Void, JSONObject> {

    protected JSONObject doInBackground(Void... voids) {
        Log.d("GetService started: ", " Works ");
        try {
            return getPlaces();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getPlaces() throws IOException, JSONException {
        //TODO: change ip to your own
        URL url = new URL("http://10.0.2.2:8080");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String answer = "";
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            urlConnection.getInputStream()));
            //readStream(in);
            String inputLine;

            while ((inputLine = in.readLine()) != null){
                answer += inputLine;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return new JSONObject(answer);
    }
}
