package com.example.grzegorz.calculator

import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Grzegorz on 2018-04-03.
 */
class RPNCalculatorTest {
    val calculator : RPNCalculator = RPNCalculator()

    @Before
    fun Prepare()
    {
        calculator.Enter(1.0)
        calculator.Enter(2.0)
        calculator.Enter(3.0)
    }

    @Test
    fun GetTopElementTest() {
        Assert.assertEquals(3.0, calculator.GetTopElement())
    }

    @Test
    fun GetSecondElementTest() {
        Assert.assertEquals(2.0, calculator.GetSecondElement())
    }

    @Test
    fun GetThirdElementTest() {
        Assert.assertEquals(1.0, calculator.GetThirdElement())
    }

    @Test
    fun DropTest() {
        calculator.DropTop()
        Assert.assertEquals(2.0, calculator.GetTopElement())
        calculator.DropTop()
        Assert.assertEquals(1.0, calculator.GetTopElement())
        calculator.DropTop()
        Assert.assertEquals(null, calculator.GetTopElement())
    }

    @Test
    fun SumTest() {
        calculator.Sum()
        Assert.assertEquals(5.0, calculator.GetTopElement())
        Assert.assertEquals(1.0, calculator.GetSecondElement())
    }

    @Test
    fun MultiplyTest() {
        calculator.Multiply()
        Assert.assertEquals(6.0, calculator.GetTopElement())
        Assert.assertEquals(1.0, calculator.GetSecondElement())
    }

    @Test
    fun EnterStringTest() {
        calculator.Enter("3.3")
        Assert.assertEquals(3.3, calculator.GetTopElement())
    }

    @Test
    fun ClearAllTest() {
        calculator.ClearAll()
        Assert.assertEquals(null, calculator.GetTopElement())
    }

    @Test
    fun DiffrenceTest() {
        calculator.Difference()
        Assert.assertEquals(1.0, calculator.GetTopElement())
        Assert.assertEquals(1.0, calculator.GetSecondElement())
    }

    @Test
    fun DivideTest() {
        calculator.Divide()
        Assert.assertEquals(1.5, calculator.GetTopElement())
        Assert.assertEquals(1.0, calculator.GetSecondElement())
    }

    @Test
    fun PowTest() {
        calculator.Pow()
        Assert.assertEquals(9.0, calculator.GetTopElement())
        Assert.assertEquals(1.0, calculator.GetSecondElement())
    }

    @Test
    fun SqrtTest() {
        calculator.Enter(4.0)
        calculator.Sqrt()
        Assert.assertEquals(2.0, calculator.GetTopElement())
        Assert.assertEquals(3.0, calculator.GetSecondElement())
    }

    @Test
    fun SwapTopTest() {
        calculator.SwapTop()
        Assert.assertEquals(2.0, calculator.GetTopElement())
        Assert.assertEquals(3.0, calculator.GetSecondElement())
    }

    @Test
    fun WrongEnterTest() {
        calculator.Enter("abc")
        Assert.assertEquals(3.0, calculator.GetTopElement())
    }

    @Test
    fun EmptyEnterTest() {
        calculator.Enter("")
        Assert.assertEquals(3.0, calculator.GetTopElement())
    }

    @Test
    fun MultiSumTest(){
        calculator.Sum()
        calculator.Sum()
        Assert.assertEquals(6.0, calculator.GetTopElement())
        Assert.assertEquals(null, calculator.GetSecondElement())
        Assert.assertEquals(null, calculator.GetThirdElement())
    }

    @Test
    fun MultiSumAndAddElementAndSumTest(){
        calculator.Sum()
        calculator.Sum()
        Assert.assertEquals(6.0, calculator.GetTopElement())
        Assert.assertEquals(null, calculator.GetSecondElement())
        Assert.assertEquals(null, calculator.GetThirdElement())
        calculator.Enter(9.0)
        calculator.Sum()
        Assert.assertEquals(15.0, calculator.GetTopElement())
        Assert.assertEquals(null, calculator.GetSecondElement())
        Assert.assertEquals(null, calculator.GetThirdElement())
    }

    @Test
    fun AddTopElementAgainTest()
    {
        calculator.AddTopElementAgain()
        Assert.assertEquals(3.0, calculator.GetTopElement())
        Assert.assertEquals(3.0, calculator.GetSecondElement())
        Assert.assertEquals(2.0, calculator.GetThirdElement())
    }

    @Test
    fun ChangeSignTest()
    {
        calculator.ChangeSignOfTopElement()
        Assert.assertEquals(-3.0, calculator.GetTopElement())
        Assert.assertEquals(2.0, calculator.GetSecondElement())
        Assert.assertEquals(1.0, calculator.GetThirdElement())

        calculator.ChangeSignOfTopElement()
        Assert.assertEquals(3.0, calculator.GetTopElement())
        Assert.assertEquals(2.0, calculator.GetSecondElement())
        Assert.assertEquals(1.0, calculator.GetThirdElement())

        calculator.ChangeSignOfTopElement()
        Assert.assertEquals(-3.0, calculator.GetTopElement())
        Assert.assertEquals(2.0, calculator.GetSecondElement())
        Assert.assertEquals(1.0, calculator.GetThirdElement())

        calculator.ChangeSignOfTopElement()
        Assert.assertEquals(3.0, calculator.GetTopElement())
        Assert.assertEquals(2.0, calculator.GetSecondElement())
        Assert.assertEquals(1.0, calculator.GetThirdElement())
    }

    @Test
    fun UndoSumTest()
    {
        calculator.Sum()
        calculator.Undo()
        Assert.assertEquals(3.0, calculator.GetTopElement())
        Assert.assertEquals(2.0, calculator.GetSecondElement())
        Assert.assertEquals(1.0, calculator.GetThirdElement())
    }

    @Test
    fun UndoDropTest()
    {
        calculator.DropTop()
        calculator.Undo()
        Assert.assertEquals(3.0, calculator.GetTopElement())
        Assert.assertEquals(2.0, calculator.GetSecondElement())
        Assert.assertEquals(1.0, calculator.GetThirdElement())
    }

    @Test
    fun UndoSwapTest()
    {
        calculator.SwapTop()
        calculator.Undo()
        Assert.assertEquals(3.0, calculator.GetTopElement())
        Assert.assertEquals(2.0, calculator.GetSecondElement())
        Assert.assertEquals(1.0, calculator.GetThirdElement())
    }

    @Test
    fun UndoSqrtTest()
    {
        calculator.Sqrt()
        calculator.Undo()
        Assert.assertEquals(3.0, calculator.GetTopElement())
        Assert.assertEquals(2.0, calculator.GetSecondElement())
        Assert.assertEquals(1.0, calculator.GetThirdElement())
    }
}