package com.example.kevin.mapdatabasesproject.manager

import android.os.AsyncTask
import com.example.kevin.mapdatabasesproject.database.contract.CourseContract
import com.example.kevin.mapdatabasesproject.database.dao.CourseDAO
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException

class SaveToCloud: AsyncTask<Unit, Unit, Unit>() {
    override fun doInBackground(vararg params: Unit?): Unit {
        val dao = CourseDAO()
        val courses = dao.all
        val gson = Gson()
        val encodedString: String
        val courseMapList = ArrayList<HashMap<String, String>>()

        for (course in courses) {
            var courseAsMap = HashMap<String, String>()
            courseAsMap[CourseContract.START_TIME_FULL] = course.startTime.toString()
            courseAsMap[CourseContract.END_TIME_FULL] = course.endTime.toString()
            courseAsMap[CourseContract.COURSE_DAYS] = course.days
            courseAsMap[CourseContract.COURSE_NAME] = course.name
            courseAsMap[CourseContract.COURSE_ID] = course.courseId.toString()
            courseAsMap[CourseContract.LOCATION_NAME] = course.locationName

            courseMapList.add(courseAsMap)
        }

        encodedString = gson.toJson(courseMapList)

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), encodedString)
        val request = Request.Builder()
                .url("https://secure-outpost-229516.appspot.com/")
                .post(body)
                .build()

        val client = OkHttpClient()

        try {
            client.newCall(request).execute()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}