package com.example.kevin.mapdatabasesproject.database.contract;

/**
 * provides values used for storing days in database as a bit field
 *
 * for example: a class held on Tuesday and Thursday would be represented as
 * Tuesday(2) + Thursday(8) = 10 (0b01010)
 */
public enum Days {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(4),
    THURSDAY(8),
    FRIDAY(16);

    private int value;

    Days(int value) {
        this.value = value;
    }

    public int getValue() { return this.value; }
}
