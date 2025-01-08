![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/00_00.jpg?raw=true)

# 섹션 6. 추가적으로 알아두어야 할 코틀린 특성

## Lec 19. 코틀린의 이모저모
1. Type Alias와 as import
2. 구조분해와 componentN 함수
3. Jump와 Label
4. Takelf와 TakeUnless

### 1. Type Alias와 as import
간 이름의 클래스 혹은 함수 타입이 있을때
축약하거나 더 좋은 이름을 쓰고 싶다면

- (Fruit) -> Boolean 이란 타입이 너무 길고, 파라미터가 더 많아진다면?
```
fun filterFruits(fruits: List<Fruit>, filter: (Fruit) -> Boolean) {

}
```

#### Type Alias - 예시 1
```
typealias FruitFilter = (Fruit) -> Boolean

fun filterFruits(fruits: List<Fruit>, filter: FruitFilter) {

}
```

#### Type Alias - 예시 2
```
daata class UltraSuperGuardianTribe(
  val name: String
)

typealias USGMap = Map<String, UltaSuperGuardianTribe>
```
이름 긴 클래스를 컬렉션에 사용할 때도 간단히 줄일 수 있다.
타입 별칭을 사용해서 USGTMap이라고 줄일 도 있다.


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/19_01.png?raw=true)

#### as import - 예시
```
import com.lannstark.lec19.a.printHelloWorld as printHelloWorldA
import com.lannstark.lec19.a.printHelloWorld as printHelloWorldB

fun main() {
    printHelloWorldA()
    printHelloWorldB()
}
```

### 구조분해와 componentN 함수
구조분해 : 복합적인 값을 분해하여 여러 변수를 한 번에 초기화하는 것
```
data class Person(
    val name: String,
    val age: Int,
)
fun main() {
    val person = Person("홍길동", 100)
    val (name, age) = person
    println("이름: ${name}, 나이 : ${age}")
}
```
```
이름: 홍길동, 나이 : 100
```

```
//val (name, age) = person 밑에 있는걸 구조 분법으로 나타낸거 componentN 함수 호출
val name = person.componect1()
val age = person.componect2()
```
> data 클래스는 기본적으로 field에 대해서 componentN이라는 함수를 만들어주는다
> 예를 들어 componect1은 첫 번째 프로퍼티를 가져오는 거고(name)
> component2는 두 번재 프로퍼티를 가지고 온다(name)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/19_02.png?raw=true)

Data Class가 아닌데 구조분해를 사용하고 싶다면, componentN 함수를 직접 구현해줄 수도 있다.

```
class Person(
    val name: String,
    val age: Int,
) {
    operator fun component1(): String {
        return this.name
    }
    operator fun component2(): Int {
        return this.age
    }
}
fun main() {
    val person = Person("홍길동", 100)
    val (name, age) = person
    
    println("이름: ${name}, 나이 : ${age}")
}
```

### 3. Jump와 Label

```
fun main() {
    val numbers = listOf(1,2,3)
    numbers.map { number -> number + 1 }
    for (number in numbers) {
        println(number)
    }
}
```

```
fun main() {
    val numbers = listOf(1,2,3)
    numbers.map { number -> number + 1 }
        .forEach{ number -> println(number) }
}
```


> forEach 주의점 contiune 혹은 break를 쓸 수 없다.
> 기본적인 for에서는 되지만  

forEach문과 함께 break 또는 continue를 꼭 쓰고 싶다면?!

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/19_03.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/19_04.png?raw=true)

break, continue를 사용할 때엔 가극적 익숙하신 for문 사용을 추천!

### Label
- 코틀린에는 라벨이라는 기능이 있다.
- 특정 expression에 라벨이름@ 을 붙여 하나의 라벨로 간주하고 break, contiune, return 등을 사용하는 기능

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/19_05.png?raw=true)

라벨을 사용한 Jump는 사용하지 않는 것을 추천!


### 4. TakeIf와 TakeUnless

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/19_06.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/19_07.png?raw=true)

### 정리
- 타입에 대한 별칭을 줄 수 있는 typealias 라는 키워드가 존재한다.
- Import 당시 이름을 바꿀 수 있는 as import 기능이 존재한다.
- 변수를 한 번에 선언할 수 있는 구조분해 기능이 있으며 componentN 함수를 사용한다.
- for문, while문과 달리 forEach에는 break와 continue를 사용할 수 없다.
- takeIf와 takeUnless를 활용해 코드양을 줄이고
  method chaning을 활용할 수 있다.

### Reference
- [인프런 | 자바 개발자를 위한 코틀린 입문(Java to Kotlin Starter Guide)](https://inf.run/1UQiA)