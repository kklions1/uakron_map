package com.example.kevin.mapdatabasesproject;

import com.example.kevin.mapdatabasesproject.model.Course;
import com.example.kevin.mapdatabasesproject.model.CourseTime;
import com.google.api.Http;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestingStuffTest {

    /**
     * Testing a suggestion by Matt Britton for storing days of the week in database
     *
     * Instead of using a bit field, use a single column that stores a string.
     * if the user checks Tuesday, Thursday for example, the string "tr" will be stored
     * When reading this out, operate on a string element by element and make note of what is present
     */

    @Test
    public void stringElementTest() {
        String testString = "tr";

        for (char c : testString.toCharArray()) {
            switch (c) {
                case 't':
                    System.out.println("Tuesday");
                    break;
                case 'w':
                    System.out.println("Its Wednesday my dudes");
                    break;
                case 'r':
                    System.out.println("Thursday");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Basically just trying to fix formatting for times
     * The app was showing '4:3' instead of '4:30' which is
     * confusing because we don't write times like that
     */
    @Test
    public void timeFormatTest() {
        CourseTime time = new CourseTime(4, 30);
        CourseTime time2 = new CourseTime(13, 1);
        CourseTime time3 = new CourseTime(4, 0);

        Assert.assertEquals("4:30", time.toString());
        Assert.assertEquals("13:01", time2.toString());
        Assert.assertEquals( "4:00", time3.toString());

    }

    /**
     * I want to make the app connect to an HTTP server I wrote
     * this is where I figure out how that works.
     */
    @Test
    public void httpConnectionTest() {
        try {
            URL url = new URL("http://www.android.com/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(connection.getInputStream());
            readStream
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
