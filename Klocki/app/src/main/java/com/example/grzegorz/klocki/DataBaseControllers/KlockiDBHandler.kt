package com.example.grzegorz.klocki.DataBaseControllers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import android.content.ContextWrapper
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import com.example.grzegorz.klocki.DataTypes.*


class KlockiDBHandler(context : Context) : SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "BrickList.db"

        private val TABLE_CATEGORIES = "Categories"
        private val TABLE_CODES = "Codes"
        private val TABLE_COLORS = "Colors"
        private val TABLE_INVENTORIES = "Inventories"
        private val TABLE_INVENTORIESPARTS = "InventoriesParts"
        private val TABLE_ITEMTYPES = "ItemTypes"
        private val TABLE_PARTS = "Parts"

        private val COLUMN_ID = "id"
        private val COLUMN_CODE = "Code"
        private val COLUMN_NAME = "Name"
        private val COLUMN_NAMEPL = "NamePL"

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

    private fun getById(id : Int, tableName : String, columnsNames : Array<String>) : Cursor
    {
        val db = readableDatabase
        val qb = SQLiteQueryBuilder()
        val sqlSelect = columnsNames
        qb.tables = tableName
        val selection = "$COLUMN_ID = ? "
        val selectionParams = arrayOf(id.toString())
        val c = qb.query(db, sqlSelect, selection, selectionParams, null, null, null)
        c.moveToFirst()
        return c
    }

    fun getCategory(id : Int) : Category
    {
        val c = getById(id, TABLE_CATEGORIES, CATEGORIES_COLUMNS)
        return Category(c)
    }

    fun getColor(id : Int) : Color
    {
        val c = getById(id, TABLE_COLORS, COLORS_COLUMNS)
        return Color(c)
    }

    fun getCode(id : Int) : Code
    {
        val c = getById(id, TABLE_CODES, CODES_COLUMNS)
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

    fun getPart(id : Int) : Part
    {
        val c = getById(id, TABLE_PARTS, PARTS_COLUMNS)
        return Part(c)
    }
}