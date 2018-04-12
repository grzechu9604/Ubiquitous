package com.example.grzegorz.calculator

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var precision = 3
    private var buttonsColor = android.graphics.Color.RED
    private var stackColor = android.graphics.Color.RED

    private var stackTextViews : ArrayList<TextView> = ArrayList()

    private val calculatorStackKey = "Calculator.Stack"
    private val calculatorUndoStackKey = "Calculator.UndoStack"
    private val calculatorLastOperationKey = "Calculator.LastOperation"
    private val inputHasCommaKey = "Input.HasComma"
    private val inputStringKey = "Input.String"
    private val precisionKey = "Precision"
    private val buttonsColorKey = "ButtonsColor"
    private val stackColorKey = "StackColor"

    private val REQUEST_CODE = 1

    private fun updateColors()
    {
        buttonsLayout.setBackgroundColor(buttonsColor)
        stackTextViews.forEach { textView -> textView.setBackgroundColor(stackColor)}
    }

    private fun initializeArrays()
    {
        stackTextViews = ArrayList()

        stackTextViews.add(thirdElementTextView)
        stackTextViews.add(secondElementTextView)
        stackTextViews.add(firstElementTextView)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initializeArrays()
        updateColors()
        updateTextBoxes()
    }

    private fun toStringWithPrecision(value : Double?) : String
    {
        if (value == null)
            return ""

        return String.format("%.${precision}f", value)
    }

    private fun CovertToFloatArray(list : ArrayList<Double>) : FloatArray
    {
        val floats : FloatArray = FloatArray(list.size)
        var i = 0
        for (element : Double in list)
        {
            floats[i++] = element.toFloat()
        }

        return floats
    }

    override fun onSaveInstanceState(outState: Bundle) {

        val calculatorStack = CovertToFloatArray(rpnCalculator.stack)
        val calculatorUndoStack = CovertToFloatArray(rpnCalculator.undoStack)

        outState.putFloatArray(calculatorStackKey, calculatorStack)
        outState.putFloatArray(calculatorUndoStackKey, calculatorUndoStack)
        outState.putInt(calculatorLastOperationKey, rpnCalculator.lastOperation.ordinal)
        outState.putBoolean(inputHasCommaKey, inputString.hasComma)
        outState.putString(inputStringKey, inputString.value)
        outState.putInt(precisionKey, precision)
        outState.putInt(stackColorKey, stackColor)
        outState.putInt(buttonsColorKey, buttonsColor)

        super.onSaveInstanceState(outState)
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        rpnCalculator.restoreData(
                savedInstanceState.getFloatArray(calculatorStackKey),
                savedInstanceState.getFloatArray(calculatorUndoStackKey),
                savedInstanceState.getInt(calculatorLastOperationKey))

        inputString.value = savedInstanceState.getString(inputStringKey)
        inputString.hasComma = savedInstanceState.getBoolean(inputHasCommaKey)

        precision = savedInstanceState.getInt(precisionKey)
        stackColor = savedInstanceState.getInt(stackColorKey)
        buttonsColor = savedInstanceState.getInt(buttonsColorKey)

        initializeArrays()
        updateColors()
        updateTextBoxes()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data !=  null)
        {
            if (data.hasExtra(precisionKey))
                precision = data.getIntExtra(precisionKey,-1)

            if (data.hasExtra(buttonsColorKey))
                buttonsColor = data.getIntExtra(buttonsColorKey,-1)

            if (data.hasExtra(precisionKey))
                stackColor = data.getIntExtra(stackColorKey,-1)
        }

        updateColors()
        updateTextBoxes()
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
                val intent = Intent(this, SettingsActivity::class.java)

                intent.putExtra(stackColorKey, stackColor)
                intent.putExtra(buttonsColorKey, buttonsColor)
                intent.putExtra(precisionKey, precision)

                startActivityForResult(intent, REQUEST_CODE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private var rpnCalculator : RPNCalculator = RPNCalculator()
    private var inputString : InputString = InputString()

    fun updateTextBoxes()
    {
        updateInputTextBox()

        setTextOfTextView(firstElementTextView, rpnCalculator.GetTopElement())
        setTextOfTextView(secondElementTextView, rpnCalculator.GetSecondElement())
        setTextOfTextView(thirdElementTextView, rpnCalculator.GetThirdElement())
    }

    fun setTextOfTextView(tv : TextView, o : Double?) {
        if (o != null)
            tv.text = toStringWithPrecision(o)
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
