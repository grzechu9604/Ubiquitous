package com.example.grzegorz.klocki.DataTypes

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
}