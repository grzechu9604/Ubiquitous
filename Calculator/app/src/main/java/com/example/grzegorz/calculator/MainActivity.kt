package com.example.grzegorz.calculator

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    val rpnCalculator : RPNCalculator = RPNCalculator()
    val inputString : InputString = InputString()

    fun updateTextBoxes()
    {
        inputTextView.text = inputString.toString()
        firstElementTextView.text = rpnCalculator.GetTopElement()?.toString()
        secondElementTextView.text = rpnCalculator.GetSecondElement()?.toString()
        thirdElementTextView.text = rpnCalculator.GetThirdElement()?.toString()
    }

    fun updateInputTextBox()
    {
        inputTextView.text = inputString.toString()
    }

    fun onEnterClick(v : View)
    {
        rpnCalculator.Enter(inputString.toString())
        updateTextBoxes()
    }

    fun onSwapClick(v : View)
    {
        rpnCalculator.SwapTop()
        updateTextBoxes()
    }

    fun onDropClick(v : View)
    {
        rpnCalculator.DropTop()
        updateTextBoxes()
    }

    fun onClearAllClick(v : View)
    {
        rpnCalculator.ClearAll()
        updateTextBoxes()
    }


    fun onSqrtClick(v : View)
    {
        rpnCalculator.Sqrt()
        updateTextBoxes()
    }


    fun onPowClick(v : View)
    {
        rpnCalculator.Pow()
        updateTextBoxes()
    }


    fun onDivideClick(v : View)
    {
        rpnCalculator.Divide()
        updateTextBoxes()
    }

    fun onDiffrenceClick(v : View)
    {
        rpnCalculator.Diffrence()
        updateTextBoxes()
    }

    fun onMultiplyClick(v : View)
    {
        rpnCalculator.Multiply()
        updateTextBoxes()
    }

    fun onSumClick(v : View)
    {
        rpnCalculator.Sum()
        updateTextBoxes()
    }

    fun onOneClick(v : View)
    {
        numberClicked('1')
    }

    fun onTwoClick(v : View)
    {
        numberClicked('2')
    }

    fun onThreeClick(v : View)
    {
        numberClicked('3')
    }

    fun onFourClick(v : View)
    {
        numberClicked('4')
    }

    fun onFiveClick(v : View)
    {
        numberClicked('5')
    }

    fun onSixClick(v : View)
    {
        numberClicked('6')
    }

    fun onSevenClick(v : View)
    {
        numberClicked('7')
    }

    fun onEightClick(v : View)
    {
        numberClicked('8')
    }

    fun onNineClick(v : View)
    {
        numberClicked('9')
    }

    fun onZeroClick(v : View)
    {
        numberClicked('0')
    }

    fun onCommaClick(v : View)
    {
        numberClicked('.')
    }

    fun onEraseClick(v : View)
    {
        inputString.deleteLast()
        updateInputTextBox()
    }

    private fun numberClicked(c : Char)
    {
        inputString.addChar(c)
        updateInputTextBox()
    }





}
