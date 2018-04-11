package com.example.grzegorz.calculator

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

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
        updateInputTextBox()

        setTextOfTextView(firstElementTextView, rpnCalculator.GetTopElement())
        setTextOfTextView(secondElementTextView, rpnCalculator.GetSecondElement())
        setTextOfTextView(thirdElementTextView, rpnCalculator.GetThirdElement())
    }

    fun setTextOfTextView(tv : TextView, o : Double?) {
        if (o != null)
            tv.text = o.toString()
        else
            tv.text = ""
    }

    fun updateInputTextBox()
    {
        inputTextView.text = inputString.toString()
    }

    fun onEnterClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        val insertedValue = inputString.toString()
        if (insertedValue.isNotEmpty())
            rpnCalculator.Enter(inputString.toString())
        else
            rpnCalculator.AddTopElementAgain()

        inputString.clear()
        updateTextBoxes()
    }

    fun onSwapClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        inputString.clear()
        rpnCalculator.SwapTop()
        updateTextBoxes()
    }

    fun onDropClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        inputString.clear()
        rpnCalculator.DropTop()
        updateTextBoxes()
    }

    fun onClearAllClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        inputString.clear()
        rpnCalculator.ClearAll()
        updateTextBoxes()
    }


    fun onSqrtClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        inputString.clear()
        rpnCalculator.Sqrt()
        updateTextBoxes()
    }


    fun onPowClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        inputString.clear()
        rpnCalculator.Pow()
        updateTextBoxes()
    }


    fun onDivideClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        inputString.clear()
        rpnCalculator.Divide()
        updateTextBoxes()
    }

    fun onDifferenceClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        inputString.clear()
        rpnCalculator.Difference()
        updateTextBoxes()
    }

    fun onMultiplyClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        inputString.clear()
        rpnCalculator.Multiply()
        updateTextBoxes()
    }

    fun onSumClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        inputString.clear()
        rpnCalculator.Sum()
        updateTextBoxes()
    }

    fun onOneClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        numberClicked('1')
    }

    fun onTwoClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        numberClicked('2')
    }

    fun onThreeClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        numberClicked('3')
    }

    fun onFourClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        numberClicked('4')
    }

    fun onFiveClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        numberClicked('5')
    }

    fun onSixClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        numberClicked('6')
    }

    fun onSevenClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        numberClicked('7')
    }

    fun onEightClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        numberClicked('8')
    }

    fun onNineClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        numberClicked('9')
    }

    fun onZeroClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        numberClicked('0')
    }

    fun onCommaClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        numberClicked('.')
    }

    fun onEraseClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        inputString.deleteLast()
        updateInputTextBox()
    }

    fun onPlusMinusClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        rpnCalculator.ChangeSignOfTopElement()
        updateInputTextBox()
    }

    fun onUndoClick(@Suppress("UNUSED_PARAMETER")v : View)
    {
        rpnCalculator.Undo()
        updateTextBoxes()
    }

    private fun numberClicked(c : Char)
    {
        inputString.addChar(c)
        updateInputTextBox()
    }





}
