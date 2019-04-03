package com.example.kevin.mapdatabasesproject;

import android.app.assist.AssistStructure;
import android.support.v4.widget.TextViewCompat;

import com.example.kevin.mapdatabasesproject.model.Course;
import com.example.kevin.mapdatabasesproject.model.CourseTime;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Generally just a place where I can run different unit tests on various things
 * Nothing else to see here, move along
 */
public class GeneralTestCases {
    // Testing a suggestion by Matt Britton for storing days of the week in database
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

    /**
     * Test of a very simple padding scheme for RSA
     */
    @Test
    public void paddingTest() {
        String padMe = "Hello H";
        BigInteger paddedNumber = new BigInteger("0");

        for (char c : padMe.toCharArray()) {
            // Get the ascii code for a character
            int asciiCode = (int) c;
            String asciiString = Integer.toString(asciiCode); // Type conversion
            paddedNumber = paddedNumber.add(new BigInteger(asciiString));
            paddedNumber = paddedNumber.multiply(new BigInteger("1000"));
        }

        System.out.println(paddedNumber);
    }

    private BigInteger padString(String s) {
        BigInteger paddedString = new BigInteger("0");

        for (char c : s.toCharArray()) {
            int asciiCode = (int) c;
            String asciiString = Integer.toString(asciiCode);
            paddedString = paddedString.add(new BigInteger(asciiString));
            paddedString = paddedString.multiply(new BigInteger("1000"));
        }

        return paddedString;
    }

    @Test
    public void encryptionTest() {
        BigInteger encryptMe = padString("Hello World");



    }

    @Test
    public void testTest() {
        String test = "Hello";
        List<Integer> result = new ArrayList<>();

        for (char c: test.toCharArray()) {
            result.add((int) c);
        }

        for (int i : result) {
            System.out.println(i);
        }
    }

    @Test
    public void moduloTest() {
        int test1 = 220;
        int test2 = 426;

        double result1 = Math.pow(test1, 17);
//        result1 = result1 % 1147;
        result1 = modulo((int) result1, 1147);

        Assert.assertEquals(611, (int) result1);
    }

    private int modulo(int a, int b) {
        if (a == 0) {
            return b;
        }
        return modulo(b%a, a);
    }
}
