package com.example.kevin.mapdatabasesproject.manager;

import android.os.AsyncTask;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HTTPRequestManager {
    private String appURL = "https://secure-outpost-229516.appspot.com/";


    public class TestNetworkResponse extends AsyncTask<String, Void, String> {
        @Override
        public String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }

        @Override
        public void onPostExecute(String result) {
            // TODO things
        }
    }
}
