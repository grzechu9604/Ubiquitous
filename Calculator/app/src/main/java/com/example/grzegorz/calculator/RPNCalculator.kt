package com.example.grzegorz.calculator

import android.support.v4.math.MathUtils
import java.lang.Math as nativeMath
/**
 * Created by Grzegorz on 2018-04-03.
 */
class RPNCalculator  {

    fun GetTopElement()
    : Double? {
        if (stack.any())
            return stack[0]
        else
            return null
    }

    fun GetSecondElement()
            : Double? {
        if (stack.size > 1)
            return stack[1]
        else
            return null
    }

    fun GetThirdElement()
            : Double? {
        if (stack.size > 2)
            return stack[2]
        else
            return null
    }

    fun SwapTop() {
        if (stack.size > 1)
        {
            PrepareDataForUndoing(CalculatorOperations.SwapTop, 2)

            val top : Double  = stack[0]
            stack[0] = stack[1]
            stack[1] = top
        }
    }

    fun DropTop() {
        if (stack.any())
        {
            PrepareDataForUndoing(CalculatorOperations.DropTop, 1)
            deleteTop()
        }
    }

    fun Sum() {
        if (stack.size > 1)
        {
            PrepareDataForUndoing(CalculatorOperations.Sum, 2)

            stack[1] = stack[0] + stack[1]
            deleteTop()
        }
    }

    fun Difference() {
        if (stack.size > 1)
        {
            PrepareDataForUndoing(CalculatorOperations.Difference, 2)

            stack[1] = stack[0] - stack[1]
            deleteTop()
        }
    }

    fun Multiply() {
        if (stack.size > 1)
        {
            PrepareDataForUndoing(CalculatorOperations.Multiply, 2)

            stack[1] = stack[0] * stack[1]
            deleteTop()
        }
    }

    fun Divide() {
        if (stack.size > 1)
        {
            PrepareDataForUndoing(CalculatorOperations.Divide, 2)

            stack[1] = stack[0] / stack[1]
            deleteTop()
        }
    }

    fun Pow() {
        if (stack.size > 1)
        {
            PrepareDataForUndoing(CalculatorOperations.Pow, 2)

            stack[1] = nativeMath.pow(stack[0], stack[1])
            deleteTop()
        }
    }

    fun Sqrt() {
        if (stack.any())
        {
            PrepareDataForUndoing(CalculatorOperations.Sqrt, 1)

            stack[0] = nativeMath.sqrt(stack[0])
        }
    }

    fun Enter(value : String)
    {
        PrepareDataForUndoing(CalculatorOperations.Enter, 0)

        val doubleValue = value.toDoubleOrNull()
        if (doubleValue != null)
            Enter(doubleValue)
    }

    fun Enter(value : Double)
    {
        PrepareDataForUndoing(CalculatorOperations.Enter, 0)
        stack.add(0, value)
    }

    fun ClearAll()
    {
        lastOperation = CalculatorOperations.NULL
        undoStack.clear()
        stack.clear()
    }

    fun AddTopElementAgain()
    {
        if (stack.any())
        {
            PrepareDataForUndoing(CalculatorOperations.AddTopElementAgain, 0)

            Enter(stack[0])
        }
    }

    fun ChangeSignOfTopElement()
    {
        if (stack.any())
        {
            PrepareDataForUndoing(CalculatorOperations.ChangeSignOfTopElement, 0)

            stack[0] = -stack[0]
        }
    }

    private fun PrepareDataForUndoing(operation : CalculatorOperations, elementsToStore : Int)
    {
        lastOperation = operation
        undoStack.clear()
        undoStack.addAll(stack.take(elementsToStore))
    }

    fun restoreElementsFromUndoStack()
    {
        for (e in undoStack.reversed())
        {
            stack.add(0, e)
        }
    }

    private fun deleteTop()
    {
        stack.removeAt(0)
    }



    fun Undo()
    {
        when (lastOperation)
        {
            RPNCalculator.CalculatorOperations.Enter -> deleteTop()
            RPNCalculator.CalculatorOperations.AddTopElementAgain -> deleteTop()
            RPNCalculator.CalculatorOperations.ChangeSignOfTopElement -> ChangeSignOfTopElement()
            RPNCalculator.CalculatorOperations.Sqrt -> { deleteTop(); restoreElementsFromUndoStack() }
            RPNCalculator.CalculatorOperations.Pow ->  { deleteTop(); restoreElementsFromUndoStack() }
            RPNCalculator.CalculatorOperations.Divide ->  { deleteTop(); restoreElementsFromUndoStack() }
            RPNCalculator.CalculatorOperations.Multiply ->  { deleteTop(); restoreElementsFromUndoStack() }
            RPNCalculator.CalculatorOperations.Difference ->  { deleteTop(); restoreElementsFromUndoStack() }
            RPNCalculator.CalculatorOperations.DropTop -> { restoreElementsFromUndoStack() }
            RPNCalculator.CalculatorOperations.SwapTop ->  { deleteTop(); deleteTop(); restoreElementsFromUndoStack() }
            RPNCalculator.CalculatorOperations.Sum ->  { deleteTop(); restoreElementsFromUndoStack() }
            else -> { //do nothing
            }
        }
    }

    fun restoreData(newStack : FloatArray, newUndoStack : FloatArray, newLastOp : Int)
    {
        stack = convert(newStack)
        undoStack = convert(newUndoStack)
        lastOperation = CalculatorOperations.values()[newLastOp]
    }

    private fun convert(array : FloatArray) : ArrayList<Double>
    {
        val arrayList : ArrayList<Double> = ArrayList<Double>()
        array.forEach { element -> arrayList.add(element.toDouble()) }
        return arrayList
    }

    var stack : ArrayList<Double> = ArrayList()
        private set(value) {
            field = value
        }
    var undoStack : ArrayList<Double> = ArrayList()
        private set(value) {
            field = value
        }
    var lastOperation : CalculatorOperations = CalculatorOperations.NULL
        private set(value) {
            field = value
        }

    enum class CalculatorOperations
    {
        NULL, Enter, AddTopElementAgain, ChangeSignOfTopElement, Sqrt, Pow, Divide, Multiply, Difference, DropTop, SwapTop, Sum
    }
}