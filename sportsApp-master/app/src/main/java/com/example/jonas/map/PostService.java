package com.example.jonas.map;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PostService extends AsyncTask<String, String, String> {

    protected String doInBackground(String... params) {
        Log.d("First param: ", params[0]);
        try {
            post(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void post(String latlng) throws IOException {
        //TODO: change ip to your own
        URL url = new URL("http://10.0.2.2:8080");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        String urlParameters = "latlng=" + latlng;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
        
    }
}
