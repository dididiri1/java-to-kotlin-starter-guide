package com.lannstark.lec08

fun main() {
    printAll("A", "B", "C")

    val array = arrayOf("A", "B", "C")
    // printAll(array) // error
    printAll(*array)
}

fun printAll(vararg string: String) {
    for (str in string) {
        println(str)
    }
}