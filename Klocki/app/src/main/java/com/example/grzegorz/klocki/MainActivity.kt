package com.example.grzegorz.klocki

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.grzegorz.klocki.DataBaseControllers.KlockiDBHandler
import com.example.grzegorz.klocki.DataTypes.Inventory
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val ADD_NEW_PROJECT_CODE = 1
    private val SETTING_REQUEST_CODE = 2
    private val urlParameter = "URL_PARAMETER"
    private val requestCodeParameter = "REQUEST_CODE_PARAMETER"

    private var db : KlockiDBHandler? = null
    private var inventories : List<Inventory>? = null

    private var showOnlyUnarchivedInventories = true

    private var urlString : String = "http://fcds.cs.put.poznan.pl/MyWeb/BL/"

    private fun doPermissionRequest(reqCode : Int, permission : String) {
        val p = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)

        if (p != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(permission), reqCode)
    }

    fun addNewProjectButtonClick(v:View)
    {
        startActivityWithUrl(NewProjectActivity::class.java, ADD_NEW_PROJECT_CODE)
    }

    private fun startSettingsActivity()
    {
        startActivityWithUrl(SettingActivity::class.java, SETTING_REQUEST_CODE)
    }

    private fun refreshInventoriesList()
    {
        inventories = if (showOnlyUnarchivedInventories) db?.getUnarchivedInventories() else db?.getInventories()
        showInventories()
    }

    private fun showInventories()
    {
        val items = arrayOfNulls<String>(inventories!!.count())

        for (i in 0 until this!!.inventories!!.count()){
            items[i] = inventories!![i].name
        }

        InventoriesListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
    }

    fun listClicked(v:View){
        val selected = InventoriesListView.checkedItemPosition
    }

    fun onlyActiveCheckBoxClicked(v:View){
        showOnlyUnarchivedInventories = onlyActiveCheckBox.isChecked
        refreshInventoriesList()
    }

    private fun startActivityWithUrl(activityToStart : Class<*>, requestCode : Int)
    {
        val intent = Intent(this, activityToStart)

        intent.putExtra(urlParameter, urlString)
        intent.putExtra(requestCodeParameter, requestCode)

        startActivityForResult(intent, requestCode)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null && requestCode == SETTING_REQUEST_CODE)
        {
            urlString = data.getStringExtra(urlParameter)
        }
        else
        {
            refreshInventoriesList()
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        doPermissionRequest(101, Manifest.permission.INTERNET)
        doPermissionRequest(102, Manifest.permission.READ_EXTERNAL_STORAGE)
        doPermissionRequest(103, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        db = KlockiDBHandler(this)
        refreshInventoriesList()

        InventoriesListView.setOnItemClickListener{_,_,position, _ ->
            val selected = inventories!![position]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                startSettingsActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
