package com.lannstark.lec19



fun main() {
    val numbers = listOf(1,2,3)
    numbers.map { number -> number + 1 }
        .forEach{ number -> println(number) }
}