package com.example.grzegorz.klocki.DataTypes

import android.database.Cursor
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false)
class Inventory() {
    @field:ElementList(entry = "ITEM", inline = true)
    var items : List<Item>? = null
    var parts : List<Part>? = null
    var id : Int = -1
    var name : String = ""
    var active : Int = -1
    var lastAccessed = -1

    constructor(parts: List<Part>, id : Int, name : String, active : Int, lastAccessed : Int) : this()
    {
        this.parts = parts
        this.id = id
        this.name = name
        this.active = active
        this.lastAccessed = lastAccessed
    }

    constructor(c : Cursor, parts : List<Part>) : this(parts, c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3))
        {
        }

    }
