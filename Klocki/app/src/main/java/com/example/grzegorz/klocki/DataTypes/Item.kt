package com.example.grzegorz.klocki.DataTypes

import com.example.grzegorz.klocki.Interfaces.Colorable
import org.simpleframework.xml.Element

class Item : Colorable{
    @set:Element(name = "ITEMTYPE")
    @get:Element(name = "ITEMTYPE")
    var itemType : String = ""

    @set:Element(name = "ITEMID")
    @get:Element(name = "ITEMID")
    var itemId : String = ""

    @set:Element(name = "QTY")
    @get:Element(name = "QTY")
    var qty : Int = 0

    @set:Element(name = "COLOR")
    @get:Element(name = "COLOR")
    var colorID : Int = 0

    @set:Element(name = "EXTRA")
    @get:Element(name = "EXTRA")
    var extra : String = ""

    @set:Element(name = "ALTERNATE")
    @get:Element(name = "ALTERNATE")
    var alternate : String = ""

    @set:Element(name = "MATCHID")
    @get:Element(name = "MATCHID")
    var matchId : String = ""

    @set:Element(name = "COUNTERPART")
    @get:Element(name = "COUNTERPART")
    var counterPart : String = ""

    override fun getColor() : Int
    {
        return colorID
    }
}