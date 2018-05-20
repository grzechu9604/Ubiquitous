package com.example.grzegorz.klocki.DataTypes

import android.content.Context
import android.database.Cursor
import android.os.Environment
import com.example.grzegorz.klocki.DataBaseControllers.KlockiDBHandler
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

@Root(strict = false)
class Inventory() {
    @field:ElementList(entry = "ITEM", inline = true)
    var items : List<Item>? = null
    var parts : List<InventoriesPart>? = null
    var id : Int = -1
    var name : String = ""
    var active = 1
    var lastAccessed = -1

    constructor(parts: List<InventoriesPart>, id : Int, name : String, active : Int, lastAccessed : Int) : this()
    {
        this.parts = parts
        this.id = id
        this.name = name
        this.active = active
        this.lastAccessed = lastAccessed
    }

    constructor(c : Cursor) : this(c, listOf<InventoriesPart>())

    constructor(c : Cursor, parts : List<InventoriesPart>) : this(parts, c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3))

    fun Serizalize(dbHandler: KlockiDBHandler, context: Context){
        val docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc = docBuilder.newDocument()

        val rootElement = doc.createElement("INVENTORY")

        for (inventoryPart in parts!!){
            if (inventoryPart.quantityInSet < inventoryPart.quantityInStore)
            {
                val itemElement = doc.createElement("ITEM")

                val itemType = doc.createElement("ITEMTYPE")
                val itemId = doc.createElement("ITEMID")
                val color = doc.createElement("COLOR")
                val qtyfilled = doc.createElement("QTYFILLED")

                itemType.appendChild(doc.createTextNode(dbHandler.getItemType(inventoryPart.typeID).code))
                itemId.appendChild(doc.createTextNode(dbHandler.getPart(inventoryPart.typeID).code))
                color.appendChild(doc.createTextNode(dbHandler.getColor(inventoryPart.colorID).code.toString()))
                qtyfilled.appendChild(doc.createTextNode((inventoryPart.quantityInStore - inventoryPart.quantityInSet).toString()))

                itemElement.appendChild(itemType)
                itemElement.appendChild(itemId)
                itemElement.appendChild(color)
                itemElement.appendChild(qtyfilled)

                rootElement.appendChild(itemElement)
            }
        }

        doc.appendChild(rootElement)

        val transformer = TransformerFactory.newInstance().newTransformer()

        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")

        val outDir = Environment.getExternalStorageDirectory()
        val file  = File(outDir, "tester.xml")

        transformer.transform(DOMSource(doc), StreamResult(file))
    }
}
