package com.example.grzegorz.klocki.DataTypes

import android.database.Cursor
import com.example.grzegorz.klocki.Interfaces.Translatable

class Part() : Translatable {
    constructor(id : Int, typeID : Int, code : String, name : String, namePL : String, categoryID : Int) : this(){
        this.id = id
        this.code = code
        this.name = name
        this.namePL = namePL
        this.typeID = typeID
        this.categoryID = categoryID
    }

    constructor(c : Cursor) : this(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3),  (if (c.getString(4) != null)  c.getString(4) else ""), c.getInt(5))
    var id : Int = -1
    var typeID : Int = -1
    var code : String = ""
    var name : String = ""
    var namePL : String = ""
    var categoryID : Int = -1

    override fun getTranslatedName() : String
    {
        return if (namePL != "") namePL else name
    }
}