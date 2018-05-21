package com.example.grzegorz.klocki.DataTypes

import android.database.Cursor
import android.provider.Settings.Global.getString
import com.example.grzegorz.klocki.Interfaces.Colorable
import com.example.grzegorz.klocki.R

class Code() : Colorable {
    constructor(id : Int, itemID : Int, colorID : Int, code : Int) : this()
    {
        this.id = id
        this.itemID = itemID
        this.colorID = colorID
        this.code = code
    }

    constructor(c : Cursor) : this(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3))

    var id : Int = -1
    var itemID : Int = -1
    var colorID : Int = -1
    var code : Int = -1

    override fun getColor() : Int
    {
        return colorID
    }
}