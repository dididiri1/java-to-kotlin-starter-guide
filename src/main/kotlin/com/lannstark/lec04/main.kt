package com.lannstark.lec04

fun main() {

    val money1 = Money(1_000L)
    val money2 = Money(2_000L)

    println(money1.plus(money2))
    println(money1 + money2)

}

