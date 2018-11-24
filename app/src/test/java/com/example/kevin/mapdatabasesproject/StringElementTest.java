package com.example.kevin.mapdatabasesproject;

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
}
