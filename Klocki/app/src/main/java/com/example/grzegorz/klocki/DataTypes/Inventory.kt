package com.example.grzegorz.klocki.DataTypes

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false)
class Inventory {
    @field:ElementList(entry = "ITEM", inline = true)
    var items : List<Item>? = null
    var id : Int = -1
    var name : String = ""
    var active : Int = -1
}