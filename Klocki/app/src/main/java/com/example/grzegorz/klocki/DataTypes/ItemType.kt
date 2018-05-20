package com.example.grzegorz.klocki.DataTypes

import android.database.Cursor
import com.example.grzegorz.klocki.Interfaces.Translatable

class ItemType() : Translatable {
    constructor(id : Int, code : String, name : String, namePL : String) : this(){
        this.id = id
        this.code = code
        this.name = name
        this.namePL = namePL
    }

    constructor(c : Cursor) : this(c.getInt(0), c.getString(1), c.getString(2), (if (c.getString(3) != null)  c.getString(3) else ""))
    var id : Int = -1
    var code : String = ""
    var name : String = ""
    var namePL : String = ""

    override fun getTranslatedName() : String
    {
        return if (namePL != "") namePL else name
    }
}