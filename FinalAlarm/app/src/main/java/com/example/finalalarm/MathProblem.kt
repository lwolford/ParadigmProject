package com.example.finalalarm

import kotlin.random.Random

class MathProblem {

    private lateinit var mathProblem : String

    // Generates a random number using millisecond time to create new seeds
    var randomGenerator = Random(System.currentTimeMillis())

    var randomOperator : Int = randomGenerator.nextInt(4)
    var random1 : Int = 0
    var random2 : Int = 0

    // Returns the equation that will be displayed in MathProblem
    fun displayEquation() : String {
        makeRandomNumbers()
        mathProblem = random1.toString()
        if (randomOperator == 0) mathProblem += " + "
        if (randomOperator == 1) mathProblem += " - "
        if (randomOperator == 2) mathProblem += " * "
        if (randomOperator == 3) mathProblem += " ÷ "
        mathProblem += random2.toString()
        return mathProblem
    }

    // Generates the random numbers for the equation
    // Addition and Subtraction can be any number from 0 to 50
    // Multiplication can be any number from 0 to 14
    // Division can be any number from 1 to 170 with 2 caveats
    // 1. The first number can not be prime
    // 2. The second number must be a factor of the first number
    private fun makeRandomNumbers() {
        if (randomOperator == 0 || randomOperator == 1) {
            random1 = randomGenerator.nextInt(50)
            random2 = randomGenerator.nextInt(50)
        }
        else if (randomOperator == 2){
            random1 = randomGenerator.nextInt(14)
            random2 = randomGenerator.nextInt(14)
        }
        else {
            random1 = randomGenerator.nextInt(1, 170)
            while (isPrimeNumber(random1)) {
                random1 = randomGenerator.nextInt(1, 170)
            }
            random2 = randomGenerator.nextInt(1, random1+1)
            while (!isDivisible(random1, random2)) {
                random2 = randomGenerator.nextInt(1, random1+1)
            }
        }
    }

    // Return function used inside of MathActivity
    fun mathCheckerReturn() : Int {
        return mathChecker(random1, random2)
    }

    // Returns the value of the operation so the user's input can be checked
    private fun mathChecker(value1 : Int, value2 : Int) : Int {
        return when (randomOperator) {
            0 -> value1 + value2
            1 -> value1 - value2
            2 -> value1 * value2
            else -> value1 / value2
        }
    }

    // Returns whether a number is a prime number
    // Only used for division operations
    private fun isPrimeNumber(num: Int): Boolean {
        var flag = true
        for (i in 2..num / 2) {
            if (num % i == 0) {
                flag = false
                break
            }
        }
        return flag
    }

    // Returns if a number is divisible
    // Only used for division operations
    fun isDivisible(num: Int, num2: Int): Boolean {
        return num%num2 == 0
    }

}