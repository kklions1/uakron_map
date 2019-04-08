package com.example.kevin.mapdatabasesproject

import com.example.kevin.mapdatabasesproject.manager.DataManager
import junit.framework.Assert
import org.junit.Test

class RSATest {

    @Test
    fun modDivTest() {
        var manager = DataManager()

        var result = manager.modularDiv(220, 17, 1147)
        Assert.assertEquals(611, result)
        print("Expected: 611\n")
        print("Result: $result")

    }

    @Test
    fun rsaCiphertextText() {
        var testInput = "Hello"
        var result = ArrayList<Int>()


        val manager = DataManager()

        manager.encryptText(testInput)

        for (i in result) {
            println("$i, ")
        }
        print('\n')
    }
}