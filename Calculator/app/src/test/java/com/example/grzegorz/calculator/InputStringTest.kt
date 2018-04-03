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
    fun CorrectAddTest() {
        input.addChar('9')
        Assert.assertEquals(9.0, input.toDouble())
    }

    @Test
    fun IncorrectAddTest() {
        input.addChar('a')
        Assert.assertEquals(null, input.toDouble())
        Assert.assertEquals("", input.toString())
    }

    @Test
    fun CommaAddTest() {
        input.addChar('.')
        input.addChar('7')
        Assert.assertEquals(0.7, input.toDouble())
        Assert.assertEquals("0.7", input.toString())
    }

    @Test
    fun CommaAddAndEraseTest() {
        input.addChar('.')
        input.addChar('7')
        input.deleteLast()
        input.addChar('8')
        Assert.assertEquals(0.8, input.toDouble())
        Assert.assertEquals("0.8", input.toString())
    }
}