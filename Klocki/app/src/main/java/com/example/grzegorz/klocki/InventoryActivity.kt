package com.example.grzegorz.klocki

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.grzegorz.klocki.DataBaseControllers.KlockiDBHandler
import com.example.grzegorz.klocki.DataTypes.Inventory
import com.example.grzegorz.klocki.adapters.InventoriesPartsAdapter
import kotlinx.android.synthetic.main.activity_inventory.*
import kotlinx.android.synthetic.main.content_main.*

class InventoryActivity : AppCompatActivity() {

    private val inventoryIdParameter = "INVENTORY_ID_PARAMETER"

    private var dbHandler : KlockiDBHandler? = null
    private var inventory : Inventory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)
        dbHandler = KlockiDBHandler(this)

        val inventoryId = intent.getIntExtra(inventoryIdParameter, -1)
        inventory = dbHandler!!.getInventory(inventoryId)

        applyInventoryInfo()
        refreshList()
    }

    private fun showToastMessage(text : String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun confirmButtonClick(v: View){
        inventory!!.active = if (activeCheckBox.isChecked) 1 else 0
        dbHandler!!.updateInventory(inventory!!)

        for (inventoriesPart in inventory!!.parts!!){
            dbHandler!!.updateInventoryPart(inventoriesPart)
        }

        showToastMessage("Zmiany zapisane!")
    }

    fun exportButtonClick(v:View){
        inventory!!.Serizalize(dbHandler!!, this)
    }

    private fun applyInventoryInfo(){
        nameTextView.text = inventory!!.name
        activeCheckBox.isChecked = inventory!!.active == 1
    }

    private fun refreshList(){
        val items = arrayOfNulls<String>(inventory!!.parts!!.count())
        val adapter = InventoriesPartsAdapter(this, ArrayList(inventory!!.parts!!))
        inventoriesPartsListView.adapter = adapter
    }
}
