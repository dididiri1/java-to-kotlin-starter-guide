package com.lannstark.lec17

fun main() {
    val fruits = listOf(
        Fruit("사과", 1_000),
        Fruit("사과", 1_200),
        Fruit("사과", 1_200),
        Fruit("사과", 1_500),
        Fruit("바나나", 3_000),
        Fruit("바나나", 3_200),
        Fruit("바나나", 2_500),
        Fruit("수박", 10_000),
    )

    val isApple = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    filterFruits(fruits, isApple)

    filterFruits(fruits) {fruit -> fruit.name == "사과" }

    filterFruits(fruits) {fruit -> fruit.name == "사과" }

    filterFruits(fruits) {a -> a.name == "사과" } // 이름을 명시해주고 화살표 쓰는 방법

    filterFruits(fruits) {it.name == "사과" } // 익명함수를 만들떄 파라미터가 1개인 경우 it 사용해도됨
}

private fun filterFruits(fruits: List<Fruit>, filter: (Fruit) -> Boolean
):List<Fruit> {
    val results = mutableListOf<Fruit>()
    for (fruit in fruits) {
        if(filter(fruit)) {
            results.add(fruit)
        }
    }
    return results
}