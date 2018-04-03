package com.example.grzegorz.calculator

/**
 * Created by Grzegorz on 2018-04-03.
 */
class InputString {
    private var value : String = ""
    private var hasComma : Boolean = false

    fun toDouble() : Double?
    {
        return value.toDoubleOrNull()
    }

    override fun toString(): String {
        return value
    }

    fun deleteLast()
    {
        if(value.length > 0)
        {
            if (value.endsWith(",") || value.endsWith("."))
            {
                hasComma = false
            }
            value = value.substring(0, value.length - 1)
        }
    }

    fun addChar(c : Char)
    {
        if ( c == '.' || c == ',')
        {
            if (!hasComma)
            {
             if (value.length == 0)
             {
                 value += "0."
             }
             else
             {
                 value += "."
             }
            }
        }
        else {
            if (c >= '0' && c <= '9')
            {
                value = value + c
            }
        }
    }
}