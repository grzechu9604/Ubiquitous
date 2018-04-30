package com.example.grzegorz.klocki

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.grzegorz.klocki.DataTypes.Project
import kotlinx.android.synthetic.main.activity_new_project.*
import java.net.HttpURLConnection
import java.net.URL

class NewProjectActivity : AppCompatActivity() {


    private var urlPrefix : String? = String()
    private val AddNewProjectResult = 1001
    private val UrlParameter = "URL_PARAMETER"
    private val projectParameter = "PROJECT_PARAMETER"
    private val extension = ".xml"
    private var newProject : Project = Project()

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

        urlPrefix = intent.getStringExtra(UrlParameter)
    }

    override fun finish() {
        val resultData = Intent()
        resultData.putExtra(projectParameter, newProject)
        setResult(AddNewProjectResult, resultData)

        super.finish()
    }

    private inner class Downloader(urlPath: String) : AsyncTask<String, Int, String>(){
        private var path : String
        var downloadedFile : String? = null
        init {
            path = urlPath
        }

        override fun doInBackground(vararg params: String?): String {
            val url = URL(path)
            val connection = url.openConnection() as HttpURLConnection
            try {
                downloadedFile = connection.inputStream.bufferedReader().use { it.readText() }
            } finally {
                connection.disconnect()
            }
            return downloadedFile.toString()
    }
}
}