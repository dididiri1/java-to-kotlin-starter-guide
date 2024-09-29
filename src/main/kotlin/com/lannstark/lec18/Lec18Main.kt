package com.lannstark.lec18

import com.lannstark.lec17.Fruit



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

    // filter : 사과만 주세요!
    val apples = filterFruits(fruits) {fruit -> fruit.name == "사과" }

    // map : 사과의 가격들을 알려주세요!
    val apples2 = fruits.filter { fruit -> fruit.name == "사과" }
        .map { fruit -> fruit.price }

    //필터에서 인덱스가 필요하다면?!
    val apples3 = fruits.filterIndexed { idx, fruit ->
        println(idx)
        fruit.name == "사과"
    }

    // 맵에서 인덱스가 필요하다면?!
    val applePrices = fruits.filter { fruit -> fruit.name == "사과" }
        .mapIndexed { idx, fruit ->
            //println(idx)
            fruit.price
        }

    // all : 조건을 모두 만족하면 true 그렇지 않으면 false
    val isAllApple = fruits.all { fruit -> fruit.name == "사과" }

    // none : 조건을 모두 불만족하면 true 그렇지 않으면 false
    val isNoApple = fruits.none { fruit -> fruit.name == "시과" }

    // any : 조건을 하나라도 만족하면 true 그렇지 않으면 false
    val isNoApple2 = fruits.any { fruit -> fruit.price >= 10_000 }

    // count : 개수를 센다
    val fruitCount = fruits.count()

    // sortedBy : (오른차순) 정렬을 한다
    val fruitCount2 = fruits.sortedBy { fruit ->
        fruit.price
    }

    // sortedByDescending : (내림차순) 정렬을 한다
    val fruitCount3 = fruits.sortedByDescending { fruit -> fruit.price }

    // distinctBy : 변형된 값을 기준으로 중복을 제거한다
    val distinctFruitNames = fruits.distinctBy { fruit -> fruit.name}
        .map { fruit -> fruit.name }

    // first : 첫번째 값을 가져온다 (무조건 null이 아니어야함)
    // firstOrNull : 첫번째 값 또는 null을 가져온다
    fruits.first()
    fruits.firstOrNull()

    // last : 마지막 값을 가져온다 (무조건 null이 아니어야함)
    // lastOrNull : 마지막 값 또는 null을 가져온다
    fruits.last()
    fruits.lastOrNull()

    // groupBy: 이름을 기준으로 그풉핑이 됨
    val map: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }

    // associateBy : id를 통해서 매핑해야 할때 중복돠지 않는 키를 가지고 map를 만들때 사용함
   //val map2: Map<Long, Fruit> = fruits.associateBy { fruit -> fruit.id }

    val map3: Map<String, List<Int>> = fruits
        .groupBy({fruit -> fruit.name}, {fruit -> fruit.price })

    // Map에 대해서도 앞선 기능들 대부분 사용할 수 있음
    val map4: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }
        .filter { (key, vale) -> key == "사과" }


}

private fun filterFruits(fruits: List<Fruit>, filter: (Fruit) -> Boolean
):List<Fruit> {

    return fruits.filter(filter)
}

