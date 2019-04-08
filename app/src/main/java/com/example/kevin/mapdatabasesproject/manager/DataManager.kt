package com.example.kevin.mapdatabasesproject.manager

import com.example.kevin.mapdatabasesproject.model.Course
import com.example.kevin.mapdatabasesproject.model.CourseTime
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlin.math.exp
import kotlin.math.pow

//@file:JvmName("DataManager")

class DataManager {

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

    fun encryptText(password: String): List<Int> {
        var result = ArrayList<Int>()
        var paddedString = padString(password)

        // The formula for encrypting a block is
        // character^e mod n
        for (item in paddedString) {
            var encodedItem = modularDiv(item, PUBLIC_KEY_E, PUBLIC_KEY_N)
            result.add(encodedItem)

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

    fun modularDiv(base: Int, exponent: Int, modulus: Int): Int {
        var result = 1
        var newBase = base
        var newExp = exponent

        while (newExp > 0) {
            if ((newExp % 2) == 1) {
                result = (result * newBase) % modulus
            }
            newBase = (newBase * newBase) % modulus
            newExp = Math.floorDiv(newExp, 2)
        }
        return result
    }
}

/**
 *
function mpmod(base, exponent, modulus) {
if ((base < 1) || (exponent < 0) || (modulus < 1)) {
return("invalid");
}
result = 1;
while (exponent > 0) {
if ((exponent % 2) == 1) {
result = (result * base) % modulus;
}
base = (base * base) % modulus;
exponent = Math.floor(exponent / 2);
}
return (result);
}

function eulerphi(x) {
result = 0;
for (i = 1; i < x; i++) {
if (isunit(i,x)) {
result++;
}
}
return (result);
}

 */
