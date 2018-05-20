package com.example.grzegorz.klocki.DataBaseControllers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import com.example.grzegorz.klocki.DataTypes.*
import com.example.grzegorz.klocki.Interfaces.Colorable
import android.content.ContentValues




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
        private const val COLUMN_NAME_PL = "NamePL"
        private const val COLUMN_COLOR_ID = "ColorID"
        private const val COLUMN_INVENTORY_ID = "InventoryID"
        private const val COLUMN_TYPE_ID = "TypeID"
        private const val COLUMN_ITEM_ID = "ItemID"
        private const val COLUMN_QUANTITY_IN_SET = "QuantityInSet"
        private const val COLUMN_QUANTITY_IN_STORE = "QuantityInStore"
        private const val COLUMN_EXTRA = "Extra"
        private const val COLUMN_IMAGE = "Image"
        private const val COLUMN_ACTIVE = "Active"
        private const val COLUMN_lAST_ACCESSED = "LastAccessed"
        private const val COLUMN_CATEGORY_ID = "CategoryID"
        private const val UNARCHIVED_INVENTORY = "1"


        private val CATEGORIES_COLUMNS = arrayOf(COLUMN_ID, COLUMN_CODE, COLUMN_NAME, COLUMN_NAME_PL)
        private val CODES_COLUMNS = arrayOf(COLUMN_ID, COLUMN_ITEM_ID, COLUMN_COLOR_ID, COLUMN_CODE, COLUMN_IMAGE)
        private val COLORS_COLUMNS = arrayOf(COLUMN_ID, COLUMN_CODE, COLUMN_NAME, COLUMN_NAME_PL)
        private val INVENTORIES_COLUMNS = arrayOf(COLUMN_ID, COLUMN_NAME, COLUMN_ACTIVE, COLUMN_lAST_ACCESSED)
        private val INVENTORIESPARTS_COLUMNS = arrayOf(COLUMN_ID, COLUMN_INVENTORY_ID, COLUMN_TYPE_ID, COLUMN_ITEM_ID, COLUMN_QUANTITY_IN_SET, COLUMN_QUANTITY_IN_STORE, COLUMN_COLOR_ID, COLUMN_EXTRA)
        private val ITEMTYPES_COLUMNS = arrayOf(COLUMN_ID, COLUMN_CODE, COLUMN_NAME, COLUMN_NAME_PL)
        private val PARTS_COLUMNS = arrayOf(COLUMN_ID, COLUMN_TYPE_ID, COLUMN_CODE, COLUMN_NAME, COLUMN_NAME_PL, COLUMN_CATEGORY_ID)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) {
        }
    }

    private fun updateColumnByColumn(tableName: String, contentValues: ContentValues, columnName : String, value : String){
        val selection = "$columnName = ? "
        writableDatabase.update(tableName, contentValues, selection, arrayOf(value))
    }

    fun updateInventory(inventory: Inventory){
        val content = generateContentValues(inventory)
        updateColumnByColumn(TABLE_INVENTORIES, content, COLUMN_ID, inventory.id.toString())
    }

    fun updateInventoryPart(inventoriesPart: InventoriesPart){
        val content = generateContentValues(inventoriesPart)
        updateColumnByColumn(TABLE_INVENTORIESPARTS, content, COLUMN_ID, inventoriesPart.id.toString())

    }

    private fun getByColumnFirstElement(value: String, tableName: String, columnsToSelect: Array<String>, column: String): Cursor {
        val c = getByColumn(value, tableName, columnsToSelect, column)
        c.moveToFirst()
        return c
    }

    private fun appendInventroiesPartToList(c: Cursor, parts: MutableList<InventoriesPart>) {
        parts.add(InventoriesPart(c))
    }

    private fun createInventroiesPartToList(c: Cursor): List<InventoriesPart> {
        val parts = mutableListOf<InventoriesPart>()

        while (c.moveToNext()) {
            appendInventroiesPartToList(c, parts)
        }

        return parts
    }

    private fun createInventroiesList(c: Cursor): List<Inventory> {
        val inventories = mutableListOf<Inventory>()

        while (c.moveToNext()) {
            inventories.add(Inventory(c))
        }

        return inventories
    }

    private fun getTable(tableName : String, columnsToSelect : Array<String>) : Cursor
    {
        val db = readableDatabase
        val qb = SQLiteQueryBuilder()
        qb.tables = tableName
        return qb.query(db, columnsToSelect, null, null, null, null, null)
    }

    private fun getByColumn(value : String, tableName : String, columnsToSelect : Array<String>, column : String) : Cursor
    {
        val db = readableDatabase
        val qb = SQLiteQueryBuilder()
        qb.tables = tableName
        val selection = "$column = ? "
        val selectionParams = arrayOf(value)
        return qb.query(db, columnsToSelect, selection, selectionParams, null, null, null)
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

    fun getInventory(id : Int) : Inventory
    {
        val parts = getInventoriesPartsForInventory(id)
        val c = getById(id, TABLE_INVENTORIES, INVENTORIES_COLUMNS)
        return Inventory(c, parts)
    }

    private fun getInventoriesPartsByInventoryId(id : Int) : Cursor{
        return getByColumn(id.toString(), TABLE_INVENTORIESPARTS, INVENTORIESPARTS_COLUMNS, COLUMN_INVENTORY_ID)
    }

    private fun getInventoriesPartsForInventory(id : Int) : List<InventoriesPart>
    {
        val c = getInventoriesPartsByInventoryId(id)
        return createInventroiesPartToList(c)
    }

    fun getInventories() : List<Inventory>
    {
        val c = getTable(TABLE_INVENTORIES, INVENTORIES_COLUMNS)
        return createInventroiesList(c)
    }

    fun getUnarchivedInventories() : List<Inventory>
    {
        val c = getByColumn(UNARCHIVED_INVENTORY, TABLE_INVENTORIES, INVENTORIES_COLUMNS, COLUMN_ACTIVE)
        return createInventroiesList(c)
    }

    fun saveInventoryWitItems(inventory: Inventory)
    {
        inventory.id = saveInventory(inventory)
        for (item in inventory.items!!){
            InventoriesPart(inventory, item, this)
        }
    }

    private fun saveInventory(inventory: Inventory) : Int
    {
        val values = generateContentValues(inventory)
        return saveContentToTable(TABLE_INVENTORIES, values)
    }

    fun saveInventoryPart(inventoriesPart: InventoriesPart) : Int
    {
        val values = generateContentValues(inventoriesPart)
        return saveContentToTable(TABLE_INVENTORIESPARTS, values)
    }


    private fun generateContentValues(inventory: Inventory) : ContentValues {
        val values = ContentValues()

        values.put(COLUMN_ACTIVE, inventory.active)
        values.put(COLUMN_NAME, inventory.name)
        values.put(COLUMN_lAST_ACCESSED, inventory.lastAccessed)

        return values
    }

        private fun generateContentValues(inventoriesPart: InventoriesPart) : ContentValues
    {
        val values = ContentValues()

        values.put(COLUMN_COLOR_ID, inventoriesPart.colorID)
        values.put(COLUMN_EXTRA, inventoriesPart.extra)
        values.put(COLUMN_INVENTORY_ID, inventoriesPart.inventoryID)
        values.put(COLUMN_ITEM_ID, inventoriesPart.itemID)
        values.put(COLUMN_QUANTITY_IN_SET, inventoriesPart.quantityInSet)
        values.put(COLUMN_QUANTITY_IN_STORE, inventoriesPart.quantityInStore)
        values.put(COLUMN_TYPE_ID, inventoriesPart.typeID)

        return values
    }

    private fun saveContentToTable(tableName : String, contentValues: ContentValues) : Int
    {
        return writableDatabase.insert(tableName, null, contentValues).toInt()
    }
}