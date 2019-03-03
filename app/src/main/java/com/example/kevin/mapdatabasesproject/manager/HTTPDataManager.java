package com.example.kevin.mapdatabasesproject.manager;

import com.example.kevin.mapdatabasesproject.model.Course;
import com.example.kevin.mapdatabasesproject.model.CourseTime;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The class where all the JSON parsing happens
 */
public class HTTPDataManager {
    private static final String COURSE_JSON_NAME = "name";
    private static final String COURSE_JSON_START = "startTime";
    private static final String COURSE_JSON_END = "endTime";
    private static final String COURSE_JSON_DAYS = "days";
    private static final String COURSE_JSON_LOCATION = "location";

    public HTTPDataManager() { }

    public List<Course> parseJsonResponse(String json) {
        Gson gson = new Gson();
        List<Course> result = new ArrayList<>();

        JsonArray courseList = gson.fromJson(json, JsonArray.class);
        for (int i = 0; i < courseList.size(); ++i) {
            JsonObject object = courseList.get(i).getAsJsonObject();
            Course course = new Course.Builder()
                    .setName(object.get(COURSE_JSON_NAME).getAsString())
                    .setStartTime(new CourseTime(object.get(COURSE_JSON_START).getAsString()))
                    .setEndTime(new CourseTime(object.get(COURSE_JSON_END).getAsString()))
                    .setLocationName(object.get(COURSE_JSON_LOCATION).getAsString())
                    .setDays(object.get(COURSE_JSON_DAYS).getAsString())
                    .build();

            result.add(course);
        }
        return new ArrayList<>();
    }
}
