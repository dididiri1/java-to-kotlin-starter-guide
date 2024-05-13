package com.lannstark.lec09

fun main() {
    val person2 = Person2("cc", 30)
    person2.name = "aa"
    println(person2.name)

}

class Person2(
    name: String,
    var age: Int,
) {
    var name: String = name
        set(value) {
            field = value.uppercase()
        }

}