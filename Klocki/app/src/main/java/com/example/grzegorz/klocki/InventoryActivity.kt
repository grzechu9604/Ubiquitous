package com.example.grzegorz.klocki

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.grzegorz.klocki.DataBaseControllers.KlockiDBHandler
import com.example.grzegorz.klocki.DataTypes.Inventory
import kotlinx.android.synthetic.main.activity_inventory.*

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
    }

    private fun applyInventoryInfo(){
        nameTextView.text = inventory!!.name
        activeCheckBox.isChecked = inventory!!.active == 1
    }
}
