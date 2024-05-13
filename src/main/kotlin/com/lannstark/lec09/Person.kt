package com.lannstark.lec09

import java.lang.IllegalArgumentException

class Person (
    val name: String = "홍길동",
    var age: Int
) {
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }
        println("초기화 블록")
    }

    constructor(name: String) : this(name, 1) {
        println("부생성자 1")
    }

    constructor() : this("최태현") {
        println("부생성자 2")
    }

    fun isAdult(): Boolean {
        return this.age >= 20
    }

    val isAdult2: Boolean
        get() = this.age >= 20

    val isAdult3: Boolean
        get() {
            return this.age >= 20
        }

}