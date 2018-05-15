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
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion)
        {
        }
    }

    fun getDataInventories(): Cursor {
        val db = readableDatabase
        val qb = SQLiteQueryBuilder()
        val sqlSelect = arrayOf("id", "Name", "Active", "LastAccessed")
        val sqlTables = "Inventories"
        qb.tables = sqlTables
        val c = qb.query(db, sqlSelect, null, null, null, null, null)
        c.moveToFirst()
        return c
    }

    fun getColor(id : Int) : Color?
    {
        val query = "Select * from $TABLE_COLORS where $COLUMN_ID = $id"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        var color = Color()

        if (cursor.moveToFirst()){
            val code = Integer.parseInt(cursor.getString(1))
            val name = cursor.getString(2)
            val namePL = cursor.getString(3)

            color = Color(id, code, name, namePL)
        }

        db.close()
        return color
    }


}