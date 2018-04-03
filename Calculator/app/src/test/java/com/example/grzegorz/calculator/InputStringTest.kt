package com.example.grzegorz.calculator

import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Grzegorz on 2018-04-03.
 */
class InputStringTest {
    var input : InputString = InputString()

    @Before
    fun Prepare()
    {
        input = InputString()
    }

    @Test
    fun WrongEnterTest() {
        input.addChar('9')
        Assert.assertEquals(9.0, input.toDouble())
    }
}