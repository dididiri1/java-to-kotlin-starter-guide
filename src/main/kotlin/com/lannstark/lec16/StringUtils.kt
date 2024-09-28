package com.lannstark.lec16

fun main() {
 val str = "ABC"
 println(str.lastChat())
}

fun String.lastChat(): Char {
    return this[this.length -1]
}