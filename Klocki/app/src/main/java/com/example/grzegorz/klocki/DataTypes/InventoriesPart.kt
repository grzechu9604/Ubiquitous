package com.example.grzegorz.klocki.DataTypes

import android.database.Cursor

class InventoriesPart() {
    constructor(id : Int, inventoryID : Int, typeID : Int, itemID : Int, quantityInSet : Int, quantityInStore : Int, colorID : Int, extra : Int) : this()
    {
        this.id = id
        this.inventoryID = inventoryID
        this.typeID = typeID
        this.itemID = itemID
        this.quantityInSet = quantityInSet
        this.quantityInStore = quantityInStore
        this.colorID = colorID
        this.extra = extra
    }

    constructor(c : Cursor) : this(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3),c.getInt(4),c.getInt(5),c.getInt(6),c.getInt(7))

    var id : Int = -1
    var inventoryID : Int = -1
    var typeID : Int = -1
    var itemID : Int = -1
    var quantityInSet : Int = -1
    var quantityInStore : Int = -1
    var colorID : Int = -1
    var extra : Int = -1
}