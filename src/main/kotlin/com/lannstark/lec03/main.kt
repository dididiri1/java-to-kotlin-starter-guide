package com.lannstark.lec03

import com.lannstark.lec03.Person
import java.util.Objects

fun main() {
    printAgeIfPerson(null)

    val person = Person("김강민", 100)
    val log = "사람의 이름은 ${person.name}이고 나이는 ${person.age}세 입니다"
    println(log)

    val name = "김구라"
    println("이름 $name")

    val str = "ABCDE"
    println(str[0])
    println(str[1])

}

fun printAgeIfPerson(obj: Any?) {
    val person = obj as? Person
    println(person?.age)
}
