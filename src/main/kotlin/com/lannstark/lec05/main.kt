package com.lannstark.lec05

import java.lang.IllegalArgumentException

fun main() {
    validateScoreIsNotNegative(0)
    println(getPassOrFail(51));
}

fun startsWithA(obj: Any): Boolean {
    return when (obj) {
        is String -> obj.startsWith("A")
        else -> false
    }
}

fun judgeNumber(number: Int) {
    when (number) {
        1, 0, -1 -> println("어디서 많이 본 숫자입니다")
        else -> println("1, 0, -1이 아닙니다")
    }
}

fun judgeNumber2(number: Int) {
    when {
        number == 0 -> println("주어진 숫자는 0입니다")
        number % 2 == 0 -> println("주어진 숫자는 짝수 입니다")
        else -> println("주어지는 숫자는 홀수입니다")
    }
}

fun getGradeWithSwitch(score: Int): String {
    return when (score / 10) {
        9 -> "A"
        8 -> "B"
        7 -> "C"
        else -> "D"
    }
}

fun getGradeWithSwitch2(score: Int): String {
    return when (score) {
        in 90..99 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        else -> "D"
    }
}

fun validateScoreIsNotNegative(score: Int) {
    if (score < 0) {
        throw IllegalArgumentException("${score}는 0보다 작을수 없다")
    }
}

fun getPassOrFail(score: Int): String{
    if (score >= 50) {
        return "P"
    } else {
        return "F"
    }
}

fun getPassOrFail2(score: Int): String{
    return if (score >= 50) {
        "P"
    } else {
        "F"
    }
}