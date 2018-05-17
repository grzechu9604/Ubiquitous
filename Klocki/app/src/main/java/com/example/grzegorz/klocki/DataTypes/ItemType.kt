package com.example.grzegorz.klocki.DataTypes

import android.database.Cursor

class ItemType() {
    constructor(id : Int, code : Int, name : String, namePL : String) : this(){
        this.id = id
        this.code = code
        this.name = name
        this.namePL = namePL
    }

    constructor(c : Cursor) : this(c.getInt(0), c.getInt(1), c.getString(2), (if (c.getString(3) != null)  c.getString(3) else ""))
    var id : Int = -1
    var code : Int = -1
    var name : String = ""
    var namePL : String = ""
}