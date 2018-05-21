package com.example.grzegorz.klocki.DataTypes

import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import com.example.grzegorz.klocki.DataBaseControllers.KlockiDBHandler
import com.example.grzegorz.klocki.Interfaces.Colorable
import java.io.IOException
import java.net.HttpURLConnection

class InventoriesPart() : Colorable {
    constructor(inventory: Inventory, item : Item, dbHandler: KlockiDBHandler) : this(
            -2, inventory.id, dbHandler.getItemType(item.itemType).id, dbHandler.getPartByCode(item.itemId).id, 0, item.qty, dbHandler.getColorByCode(item.colorID).id, 0, dbHandler)
    {
        id = dbHandler.saveInventoryPart(this)

        val img = getImage()
        if (img!= null && img.second == null){
            val link = generateImageLink(img.first)
            val bgWorker = ImageDownloader(link, img.first)
            bgWorker.execute()
        }
    }

    constructor(id : Int, inventoryID : Int, typeID : Int, itemID : Int, quantityInSet : Int, quantityInStore : Int, colorID : Int, extra : Int, dbHandler : KlockiDBHandler) : this()
    {
        this.id = id
        this.inventoryID = inventoryID
        this.typeID = typeID
        this.itemID = itemID
        this.quantityInSet = quantityInSet
        this.quantityInStore = quantityInStore
        this.colorID = colorID
        this.extra = extra
        this.dbHandler = dbHandler
    }

    constructor(c : Cursor, dbHandler: KlockiDBHandler)
            : this(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3),c.getInt(4),c.getInt(5),c.getInt(6),c.getInt(7), dbHandler)

    var id : Int = -1
    var inventoryID : Int = -1
    var typeID : Int = -1
    var itemID : Int = -1
    var quantityInSet : Int = -1
    var quantityInStore : Int = -1
    var colorID : Int = -1
    var extra : Int = -1
    var dbHandler: KlockiDBHandler? = null

    override fun getColor() : Int
    {
        return colorID
    }

    fun getText() : String{
        return dbHandler!!.getPart(itemID).getTranslatedName() + " " + quantityInSet.toString() + "/" + quantityInStore.toString()
    }

    private fun generateImageLink(code : String) = "https://www.lego.com/service/bricks/5/2/$code"

    private fun generateAlternativeImageLink(code : String, color : String) = "http://img.bricklink.com/P/$color/$code.gif"


    fun getImage() : Pair<String, Bitmap?>?{
        val pair = dbHandler!!.getImage(itemID.toString(), colorID.toString())
        if (pair != null){
            if (pair.second != null){
                return pair.first to BitmapFactory.decodeByteArray(pair.second, 0, pair.second!!.size)
            }
            return pair.first to null as Bitmap?
        }

        return null
    }

    inner class ImageDownloader(primaryLink : String, code : String) : AsyncTask<String, Int, String>(){
        private val link = primaryLink
        private val codeToUpdate = code

        private fun downloadImage(link : String) : Bitmap?{
            return getBitmapFromURL(link)
        }

        override fun doInBackground(vararg params: String?): String {
            downloadImageToDatabase()
            return "DONE"
        }

        fun downloadImageToDatabase(){
            val image = downloadImage(link)

            if (image != null)
                dbHandler!!.updateImageByCode(codeToUpdate, image)

        }

        private fun getBitmapFromURL(src: String): Bitmap? {
            try {
                val url = java.net.URL(src)
                val connection = url
                        .openConnection() as HttpURLConnection
                connection.setDoInput(true)
                connection.connect()
                val input = connection.getInputStream()
                return BitmapFactory.decodeStream(input)
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
        }

    }
}