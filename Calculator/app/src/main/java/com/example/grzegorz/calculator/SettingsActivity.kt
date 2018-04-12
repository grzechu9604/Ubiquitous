package com.example.grzegorz.calculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*
class SettingsActivity : AppCompatActivity() {

    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        precision = intent.getIntExtra(precisionKey, -1)
        buttonsColor = intent.getIntExtra(buttonsColorKey, -1)
        stackColor = intent.getIntExtra(stackColorKey, -1)

        updateButtons()
    }

    private fun updateButtons()
    {
        precissionBar.progress = precision

        when(buttonsColor)
        {
            android.graphics.Color.RED -> redButtonColorButton.isChecked = true
            android.graphics.Color.GREEN -> greenButtonColorButton.isChecked = true
            android.graphics.Color.YELLOW -> yellowButtonColorButton.isChecked = true
        }

        when(stackColor)
        {
            android.graphics.Color.RED -> redStackColorButton.isChecked = true
            android.graphics.Color.GREEN -> greenStackColorButton.isChecked = true
            android.graphics.Color.YELLOW -> yellowStackColorButton.isChecked = true
        }
    }

    private fun updateData()
    {
        precision = precissionBar.progress

        if (redButtonColorButton.isChecked)
            buttonsColor = android.graphics.Color.RED
        else if (yellowButtonColorButton.isChecked)
            buttonsColor = android.graphics.Color.YELLOW
        else
            buttonsColor = android.graphics.Color.GREEN

        if (redStackColorButton.isChecked)
            stackColor = android.graphics.Color.RED
        else if (yellowStackColorButton.isChecked)
            stackColor = android.graphics.Color.YELLOW
        else
            stackColor = android.graphics.Color.GREEN
    }

    override fun finish() {
        updateData()

        val data = Intent()

        data.putExtra(stackColorKey, stackColor)
        data.putExtra(buttonsColorKey, buttonsColor)
        data.putExtra(precisionKey, precision)

        setResult(REQUEST_CODE, data)

        super.finish()
    }

    private var precision = 3
    private var buttonsColor = android.graphics.Color.RED
    private var stackColor = android.graphics.Color.RED

    private val precisionKey = "Precision"
    private val buttonsColorKey = "ButtonsColor"
    private val stackColorKey = "StackColor"
}
