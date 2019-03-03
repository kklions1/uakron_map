package com.example.kevin.mapdatabasesproject.manager;

import android.os.AsyncTask;

import com.example.kevin.mapdatabasesproject.database.dao.CourseDAO;
import com.example.kevin.mapdatabasesproject.model.Course;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Class the makes a call to the server, and then parses the json response
 */
public class HTTPRequestManager extends AsyncTask<Void, Void, String> {
    private final String appURL = "https://secure-outpost-229516.appspot.com/";

    public HTTPRequestManager() {

    }

    @Override
    public String doInBackground(Void... uselessArgs) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(appURL)
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
    public void onPostExecute(String result){
        List<Course> courseList = new MeanderDataManager().parseJsonResponse(result);
        CourseDAO dao = new CourseDAO();

        for (Course c : courseList) {
            dao.save(c);
        }
    }
}

