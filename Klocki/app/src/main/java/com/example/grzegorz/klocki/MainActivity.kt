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

class MainActivity : AppCompatActivity() {

    private val ADD_NEW_PROJECT_CODE = 1
    private val SETTING_REQUEST_CODE = 2
    private val urlParameter = "URL_PARAMETER"
    private val requestCodeParameter = "REQUEST_CODE_PARAMETER"

    private var urlString : String = "http://fcds.cs.put.poznan.pl/MyWeb/BL/"

    private fun doPermissionRequest(reqCode : Int, permission : String) {
        val p = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)

        if (p != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(permission), reqCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun addNewProjectButtonClick(v:View)
    {
        startActivityWithUrl(NewProjectActivity::class.java, ADD_NEW_PROJECT_CODE)
    }

    private fun startSettingsActivity()
    {
        startActivityWithUrl(SettingActivity::class.java, SETTING_REQUEST_CODE)
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
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        doPermissionRequest(1, Manifest.permission.INTERNET)
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
