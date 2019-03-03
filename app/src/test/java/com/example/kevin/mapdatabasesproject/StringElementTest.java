package com.example.kevin.mapdatabasesproject;

import android.app.assist.AssistStructure;

import com.example.kevin.mapdatabasesproject.model.Course;
import com.example.kevin.mapdatabasesproject.model.CourseTime;

import org.junit.Assert;
import org.junit.Test;

/**
 * Testing a suggestion by Matt Britton for storing days of the week in database
 *
 * Instead of using a bit field, use a single column that stores a string.
 * if the user checks Tuesday, Thursday for example, the string "tr" will be stored
 * When reading this out, operate on a string element by element and make note of what is present
 */
public class StringElementTest {
    @Test
    public void stringElement() {
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

    @Test
    public void timeFormatTest() {
        CourseTime time = new CourseTime(4, 30);
        CourseTime time2 = new CourseTime(13, 1);
        CourseTime time3 = new CourseTime(4, 0);

        Assert.assertEquals("4:30", time.toString());
        Assert.assertEquals("13:01", time2.toString());
        Assert.assertEquals( "4:00", time3.toString());

    }

    @Test
    public void stringTimeTest() {
        CourseTime time = new CourseTime("12:00");
        CourseTime time1 = new CourseTime("12:34");
        CourseTime time2 = new CourseTime("05:01");
        CourseTime time3 = new CourseTime("14:00");

        Assert.assertEquals(12, time.getHour());
        Assert.assertEquals(0, time.getMinute());
        Assert.assertEquals(34, time1.getMinute());
        Assert.assertEquals(5, time2.getHour());
        Assert.assertEquals(1, time2.getMinute());
        Assert.assertEquals(14, time3.getHour());
    }
}
