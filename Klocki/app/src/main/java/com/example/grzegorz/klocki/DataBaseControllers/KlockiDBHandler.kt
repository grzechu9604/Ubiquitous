package com.example.grzegorz.klocki.DataBaseControllers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import com.example.grzegorz.klocki.DataTypes.*
import com.example.grzegorz.klocki.Interfaces.Colorable


class KlockiDBHandler(context : Context) : SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "BrickList.db"

        private const val TABLE_CATEGORIES = "Categories"
        private const val TABLE_CODES = "Codes"
        private const val TABLE_COLORS = "Colors"
        private const val TABLE_INVENTORIES = "Inventories"
        private const val TABLE_INVENTORIESPARTS = "InventoriesParts"
        private const val TABLE_ITEMTYPES = "ItemTypes"
        private const val TABLE_PARTS = "Parts"

        private const val COLUMN_ID = "id"
        private const val COLUMN_CODE = "Code"
        private const val COLUMN_NAME = "Name"
        private const val COLUMN_NAMEPL = "NamePL"

        private val CATEGORIES_COLUMNS = arrayOf("id", "Code", "Name", "NamePL")
        private val CODES_COLUMNS = arrayOf("id", "ItemID", "ColorID", "Code", "Image")
        private val COLORS_COLUMNS = arrayOf("id", "Code", "Name", "NamePL")
        private val INVENTORIES_COLUMNS = arrayOf("id", "Name", "Active", "LastAccessed")
        private val INVENTORIESPARTS_COLUMNS = arrayOf("id", "InventoryID", "TypeID", "ItemID", "QuantityInSet", "QuantityInStore", "ColorID", "Extra")
        private val ITEMTYPES_COLUMNS = arrayOf("id", "Code", "Name", "NamePL")
        private val PARTS_COLUMNS = arrayOf("id", "TypeID", "Code", "Name", "NamePL", "CategoryID")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion)
        {
        }
    }

    private fun getByColumnFirstElement(value : String, tableName : String, columnsToSelect : Array<String>, column : String) : Cursor
    {
        val db = readableDatabase
        val qb = SQLiteQueryBuilder()
        qb.tables = tableName
        val selection = "$column = ? "
        val selectionParams = arrayOf(value)
        val c = qb.query(db, columnsToSelect, selection, selectionParams, null, null, null)
        c.moveToFirst()
        return c
    }

    private fun getById(id : Int, tableName : String, columnsNames : Array<String>) : Cursor
    {
        return getByColumnFirstElement(id.toString(), tableName, columnsNames, COLUMN_ID)
    }

    private fun getByCode(code : String, tableName : String, columnsNames : Array<String>) : Cursor
    {
        return getByColumnFirstElement(code, tableName, columnsNames, COLUMN_CODE)
    }

    fun getCategory(id : Int) : Category
    {
        val c = getById(id, TABLE_CATEGORIES, CATEGORIES_COLUMNS)
        return Category(c)
    }

    fun getCategoryByCode(code : Int) : Category
    {
        val c = getByCode(code.toString(), TABLE_CATEGORIES, CATEGORIES_COLUMNS)
        return Category(c)
    }

    fun getColor(id : Int) : Color
    {
        val c = getById(id, TABLE_COLORS, COLORS_COLUMNS)
        return Color(c)
    }

    fun getColorByCode(code : Int) : Color
    {
        val c = getByCode(code.toString(), TABLE_COLORS, COLORS_COLUMNS)
        return Color(c)
    }

    fun getCode(id : Int) : Code
    {
        val c = getById(id, TABLE_CODES, CODES_COLUMNS)
        return Code(c)
    }

    fun getCodeByCode(code : Int) : Code
    {
        val c = getByCode(code.toString(), TABLE_CODES, CODES_COLUMNS)
        return Code(c)
    }

    fun getInventoriesPart(id : Int) : InventoriesPart
    {
        val c = getById(id, TABLE_INVENTORIESPARTS, INVENTORIESPARTS_COLUMNS)
        return InventoriesPart(c)
    }

    fun getItemType(id : Int) : ItemType
    {
        val c = getById(id, TABLE_ITEMTYPES, ITEMTYPES_COLUMNS)
        return ItemType(c)
    }

    fun getItemType(code : String) : ItemType
    {
        val c = getByCode(code, TABLE_ITEMTYPES, ITEMTYPES_COLUMNS)
        return ItemType(c)
    }

    fun getPart(id : Int) : Part
    {
        val c = getById(id, TABLE_PARTS, PARTS_COLUMNS)
        return Part(c)
    }

    fun getPartByCode(code : String) : Part
    {
        val c = getByCode(code, TABLE_PARTS, PARTS_COLUMNS)
        return Part(c)
    }

    fun getColorForColorable(colorable: Colorable) : Color
    {
        return getColor(colorable.getColor())
    }
}