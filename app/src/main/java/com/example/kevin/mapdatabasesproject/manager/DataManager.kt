package com.example.kevin.mapdatabasesproject.manager

import com.example.kevin.mapdatabasesproject.model.Course
import com.example.kevin.mapdatabasesproject.model.CourseTime
import com.google.gson.Gson
import com.google.gson.JsonArray

//@file:JvmName("DataManager")

class DataManagerConstants {

    companion object {
        @JvmField val COURSE_JSON_NAME = "name"
        @JvmField val COURSE_JSON_START = "startTime"
        @JvmField val COURSE_JSON_END = "endTime"
        @JvmField val COURSE_JSON_DAYS = "days"
        @JvmField val COURSE_JSON_LOCATION = "location"

        @JvmField val PUBLIC_KEY_E = 17
        @JvmField val PUBLIC_KEY_N = 1147
    }

    fun parseJsonResponse(json: String): List<Course> {
        var gson = Gson()
        var result = ArrayList<Course>()

        var responseArray = gson.fromJson(json, JsonArray::class.java)
        for (i in 0..responseArray.size()) {
            var jsonObject = responseArray.get(i).asJsonObject
            var currentCourse = Course.Builder()
                    .setName(jsonObject.get(COURSE_JSON_NAME).asString)
                    .setStartTime(CourseTime(jsonObject.get(COURSE_JSON_START).asString))
                    .setEndTime(CourseTime(jsonObject.get(COURSE_JSON_END).asString))
                    .setLocationName(jsonObject.get(COURSE_JSON_LOCATION).asString)
                    .setDays(jsonObject.get(COURSE_JSON_DAYS).asString)
                    .build()

            result.add(currentCourse)
        }

        return result
    }

    fun encryptPassword(password: List<Int>): List<Int> {
        var result = ArrayList<Int>()

        // The formula for encrypting a block is
        //
        for (item in password) {

        }

        return result
    }

    private fun padString(string: String): List<Int> {
        var result = ArrayList<Int>()

        for (c in string) {
            var ascii = c.toInt()
            result.add(ascii)
        }

        return result
    }
}