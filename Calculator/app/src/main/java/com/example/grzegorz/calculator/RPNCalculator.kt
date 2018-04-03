package com.example.grzegorz.calculator

import android.support.v4.math.MathUtils
import java.lang.Math as nativeMath
/**
 * Created by Grzegorz on 2018-04-03.
 */
class RPNCalculator {
    constructor()
    {
    }

    fun GetTopElement()
    : Double? {
        if (stack.size > 0)
            return stack[0]
        else
            return null
    }

    fun GetSecondElement()
            : Double? {
        if (stack.size > 1)
            return stack[1]
        else
            return null
    }

    fun GetThirdElement()
            : Double? {
        if (stack.size > 2)
            return stack[2]
        else
            return null
    }

    fun SwapTop() {
        if (stack.size > 1)
        {
            var top : Double  = stack[0]
            stack[0] = stack[1]
            stack[1] = top
        }
    }

    fun DropTop() {
        if (stack.size > 0)
            stack.removeAt(0)
    }

    fun Sum() {
        if (stack.size > 2)
        {
            stack[1] = stack[0] + stack[1]
            DropTop()
        }
    }

    fun Diffrence() {
        if (stack.size > 2)
        {
            stack[1] = stack[0] - stack[1]
            DropTop()
        }
    }

    fun Multiply() {
        if (stack.size > 2)
        {
            stack[1] = stack[0] * stack[1]
            DropTop()
        }
    }

    fun Divide() {
        if (stack.size > 2)
        {
            stack[1] = stack[0] / stack[1]
            DropTop()
        }
    }

    fun Pow() {
        if (stack.size > 2)
        {
            stack[1] = nativeMath.pow(stack[0], stack[1])
            DropTop()
        }
    }

    fun Sqrt() {
        if (stack.size > 2)
        {
            stack[0] = nativeMath.sqrt(stack[0])
        }
    }

    fun Enter(value : String)
    {
        val doubleValue = value.toDoubleOrNull()
        if (doubleValue != null)
            Enter(doubleValue)
    }

    fun Enter(value : Double)
    {
        stack.add(0, value)
    }

    fun ClearAll()
    {
        stack.clear()
    }

    private var stack : ArrayList<Double> = ArrayList()
}