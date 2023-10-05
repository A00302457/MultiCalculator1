package com.example.multicalculator //Imported package
//make a class of Calculator
class Calculator
{
    //Add function for addition and pass two interger
     fun add(left: Int , right : Int) : Int
     {
         val sum = left + right
         return (sum)
     }
     fun subtract(left : Int , right : Int) = left - right

     fun multiply(left : Int , right : Int) : Int
     {
         return (left * right)
     }
     fun divide(left : Int , right : Int) : Float{
         return (left.toFloat() / right.toFloat())
     }
}
fun main(){
        val calculator = Calculator()
        val left = 20
        val right = 6
        println("Sum is = ${calculator.add(left, right)}")
        println("Substraction is = ${calculator.subtract(left, right)}")
        println("multiplication is = ${calculator.multiply(left, right)}")
        println("Divison is = ${calculator.divide(left, right)}")
}