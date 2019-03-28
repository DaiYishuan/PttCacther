package com.yi.pttcactcher;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HttpPostAsyncTask extends AsyncTask<String,Void,Void> {
    @Override
    protected Void doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestMethod("GET");

            int statusCode = urlConnection.getResponseCode();

            if (statusCode==200){
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                // TODO: 2019/3/24 return bitmap
            }
            else {
                Log.d("ResponseCode",String.valueOf(statusCode));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    JSONObject postData;

    public HttpPostAsyncTask(Map<String,String> postData){
        if (postData != null){
            this.postData = new JSONObject(postData);
        }
    }
}
