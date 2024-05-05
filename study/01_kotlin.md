# 섹션 1 코틀린에서 변수와 타입, 연산자를 다루는 방법

## 1강. 코틀린에서 변수를 다루는 방법

### 1. 변수 선언 키워드 - var과 val의 차이점

#### Java
``` java
long number1 = 10L; // (1)
final long number2 = 10L; // (2)

Long number3 = 1_000L; // (3)
Person person = new Person("김강민"); // (4)
```

### 코틀린에서는 모든 변수에 수정 가능 여부 (var / val)를 명시해주어야 한다.
``` kotiln
fun main(>) {

    var number1 = 10L
    number1 = 5L

    val number2 = 10L
    number2 = 10L // error
}
```
> 

### 초기 값을 지정해주 않는 경우는 ?!
- 초기값을 지정해주지 않는 경우에는 무조건 타입을 명시해 줘야됨
``` kotlin
fun main() {

    var number1: Long
    println(number1) // 초기화 되지 않아서 error
    
    var number2: Long
    number2 = 5
    println(number2)
}
```

### val 컬렉션에는 element를 추가할 수 있다.

> 간단한 TIP  
> 모든 변수는 우선 val로 만들고 꼭 필요한 경우 var로 변경한다.

## 2. kotlin에서의 Primitive Type

``` kotlin
fun main() {

    var number1: Long = 10L
}
```

> '이텍티브 자바' 책에서는 연산을 할 때는 reference type을 쓰지 마라 한다  
> 왜냐하면 boxing과 unboxing이 일어나면서 불필요한 객체생성이 이루어지기 때문이다.
> 하지만 코틀린은 그런 구분이 없다. 예를 들어 Kotlin에서는 모두 Long 인데 성능상 문제 없는것인가?
> 
> 코틀린 공식 문서에서는 즉, primitive type과 reference type에 대해서 이렇케 얘기한다.
> 숫자, 문자, 불리언과 같은 몇몇 타입은 내부적으로 특별한 표현을 갖는다.  
> 이 타입들은 실행시에 Primitive Value로 표현되지만, 코드에서는 평범한 클래스 처럼 보인다.
> 즉, Long 타입으로 하나로 합쳐져 있지만 연산을 할 경우 코틀린이 상황에 따라서 primitive type으로 바꿔주기 때문에  
> 프로그래머가 boxing / unboxing을 고려하지 않아도 된다.


## 3. Kotlin에서의 nullable 변수
### null이 변수에 들어갈 수 있다면 "타입?"를 사용해야 한다.
``` kotlin
fun main() {

    var number1 = 1_000L
    number1 = null // error

    var number2: Long? = 1_000L
    number2 = null
}
```

## 4. Kotlin에서의 객체 인스턴스화
### 객체 인스턴스화를 할 떄에는 new를 붙이지 않아야 한다.
``` kotlin
var person = Person("김강민")
``` 