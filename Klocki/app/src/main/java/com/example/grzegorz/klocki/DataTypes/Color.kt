package com.example.grzegorz.klocki.DataTypes

import android.database.Cursor

class Color() {
    var id : Int = -1
    var code : Int = -1
    var name : String = ""
    var namePL : String = ""

    constructor(id : Int, Code : Int, Name : String, NamePL : String) : this() {
        this.id = id
        this.code = Code
        this.name = Name
        this.namePL = NamePL
    }

    constructor(c : Cursor) : this(c.getInt(0), c.getInt(1), c.getString(2), (if (c.getString(3) != null)  c.getString(3) else ""))

}