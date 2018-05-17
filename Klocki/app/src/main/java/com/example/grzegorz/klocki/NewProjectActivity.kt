package com.example.grzegorz.klocki

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.grzegorz.klocki.DataTypes.Inventory
import com.example.grzegorz.klocki.DataTypes.Item
import kotlinx.android.synthetic.main.activity_new_project.*
import org.xmlpull.v1.XmlSerializer
import java.net.HttpURLConnection
import java.net.URL
import org.simpleframework.xml.core.Persister

class NewProjectActivity : AppCompatActivity() {

    private var requestCode : Int = -1
    private val requestCodeParameter = "REQUEST_CODE_PARAMETER"
    private val urlParameter = "URL_PARAMETER"

    private var urlPrefix : String = ""
    private val projectParameter = "PROJECT_PARAMETER"
    private val extension = ".xml"
    var invenory : Inventory = Inventory()

    private fun prepareLink() : String{
        return urlPrefix + fileNameBox.text + extension
    }

    fun downloadFileButtonClick(v: View) {
        val d = Downloader(prepareLink())
        d.execute()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_project)

        urlPrefix = intent.getStringExtra(urlParameter)
        requestCode = intent.getIntExtra(requestCodeParameter, -1)
    }

    override fun finish() {
        val resultData = Intent()
        setResult(requestCode, resultData)

        super.finish()
    }

    private inner class Downloader(urlPath: String) : AsyncTask<String, Int, String>(){
        private var path : String = ""
        var downloadedFile : String? = null

        init {
            path = urlPath
        }

        override fun onPostExecute(result: String?) {

        }

        override fun doInBackground(vararg params: String?): String {
            val url = URL(path)
            val connection = url.openConnection() as HttpURLConnection
            try {
                downloadedFile = connection.inputStream.bufferedReader().use { it.readText() }
            } finally {
                connection.disconnect()
            }

            deserializeInventory(downloadedFile.toString())

            return downloadedFile.toString()
    }


        private fun deserializeInventory(xmlString : String) {
            val mapper = Persister()
            invenory = mapper.read(Inventory::class.java, xmlString)
        }
}
}
