package com.example.grzegorz.klocki

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    private var requestCode : Int = -1
    private val requestCodeParameter = "REQUEST_CODE_PARAMETER"
    private val urlParameter = "URL_PARAMETER"

    private var urlString : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requestCode = intent.getIntExtra(requestCodeParameter, -1)
        urlString = intent.getStringExtra(urlParameter)

        setContentView(R.layout.activity_setting)

        urlPrefixTextBox.setText(urlString)
    }

    override fun finish() {
        val data = Intent()

        urlString = urlPrefixTextBox.editableText.toString()

        data.putExtra(urlParameter, urlString)
        data.putExtra(requestCodeParameter, requestCode)

        setResult(requestCode, data)

        super.finish()
    }
}
