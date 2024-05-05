package com.lannstark.lec02

import java.lang.IllegalArgumentException

fun main() {
    val person = Person("공부하는 개발자")



}
fun startsWithA0(str: String?): Boolean {
    return str!!.startsWith("A")
}

fun startsWithA1(str: String?): Boolean {
    return str?.startsWith("A") ?: throw IllegalArgumentException("null이 들어왔습니다")
}

fun startsWithA2(str: String?): Boolean? {
    return str?.startsWith("A")
}

fun startsWithA3(str: String?): Boolean {
    return str?.startsWith("A") ?: false
}