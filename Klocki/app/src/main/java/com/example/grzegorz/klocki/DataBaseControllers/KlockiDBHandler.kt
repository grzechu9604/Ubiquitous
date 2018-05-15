package com.example.grzegorz.klocki.DataBaseControllers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.grzegorz.klocki.DataTypes.Color
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import android.content.ContextWrapper
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder


class KlockiDBHandler(context : Context) : SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "BrickList.db"
        val TABLE_COLORS = "Colors"
        val COLUMN_ID = "id"
        val COLUMN_CODE = "Code"
        val COLUMN_NAME = "Name"
        val COLUMN_NAMEPL = "NamePL"
        val COLORS_COLUMNS = arrayOf("id", "Code", "Name", "NamePL")
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

    fun getColor(id : Int) : Color
    {
        val c = getById(id, TABLE_COLORS, COLORS_COLUMNS)
        val color = Color()

        color.id = c.getInt(0)
        color.code = c.getInt(1)
        color.name = c.getString(2)
        if (c.getString(3) != null)
        {
            color.namePL = c.getString(3)
        }
        else
        {
            color.namePL = ""
        }

        return color
    }


}