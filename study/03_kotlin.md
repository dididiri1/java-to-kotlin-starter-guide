# 3강. 코틀린에서 Type을 다루는 방법
1. 기본 타입
2. 타입 캐스팅
3. Kotlin의 3가지 특이한 타입(Java에는 특이한 타입)
4. String Interplation, String indexing


## 1. 기본 타입
- 코틀린에서는 선언된 기본값을 보고 타입을 추론한다.
```
val number1 = 3 // Int
val number2 = 3L // Long

val number3 = 3.0f // Float
val number4 = 3.0 // Double 
``` 

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/03_01.png?raw=true)

```
int number1 = 4;
long number2 = number1;

System.out.println(number1 + number2);
``` 
> int 타입의 값이 long 타입으로 암시적으로 변경 되었다.  
> Java에서 더 큰 타입으로 암시적으로 변경 가능

``` 
val number1 = 4;
val number2: Long = number1 // Type mismatch

println(number1 + number2)
``` 
> Kotlin에서는 암시적 타입 변경이 불가능 하다. 

### to변환타입()을 사용한다 (명시적으로 타입 변화 가능) 
``` 
fun main() {
    val number1 = 3
    var number2: Long = number1.toLong()
    
    val number3: Int? = 3
    var number4: Long = number3?.toLong() ?: 0L
}
``` 

``` 
val number1 = 3
val number2 = 5
val result = number1/ number2.toDouble()

println(result)
``` 

## 2. 타입 캐스팅
- 기본 타입이 아닌 일반 타입은 어떻케 처리할까

#### Java
```
public static void printAgeIfPerson(Object obj) {
    if (obj instanceof Person) {
      Person person = (Person) obj;
      System.out.println(person.getAge());
    }
}
```

- is는 Java의 instanceof와 같은 의미 true : false 반환
- as Person은 Java에 (Person) obj 같은 의미
#### Kotlin
```
fun printAgeIfPerson(obj: Any) {
    if (obj is Person) {
        val person = obj as Person
        println(person)
    }
}
```
- as 생략해도 인식 가능
- **스마트 캐스트**라고 한다.
```
fun printAgeIfPerson(obj: Any) {
    if (obj is Person) {
        println(obj.age)
    }
}
```
- null 처리 
```
fun main() {
    printAgeIfPerson(null)
}

fun printAgeIfPerson(obj: Any?) {
    val person = obj as? Person
    println(person?.age)
}

```

## 3. Kotlin의 특이한 타입 3가지
- Any
- Unit
- Nothing

## 3. Kotlin의 특이한 타입 3가지 - Any
- Java의 Object 역할. (모든 객체의 최상위 타입)
- 모든 Primitive Type의 최상의 타입도 Any이다.
- Any 자체로는 null을 포함할 수 없어 null을 포함하고 싶다면, Any?로 표현
- Any 에 equals / hashCode / toString 존재

## 3. Kotlin의 특이한 타입 3가지 - Unit
- Unit은 Java의 void와 동일한 역할
- (살짝 어려운 내용) void와 다르게 Unit은 그 자체로 타입 인자로 사용 가능하다.
- 함수형 프로그래밍에서 Unit 은 단 하나의 인스턴스만 갖는 타입을 의미    
  즉, 코틀린의 Unit은 실제 존재하는 타입이라는 것을 표현

## 3. Kotlin의 특이한 타입 3가지 - Nothing
- Nothing은 함수가 정상적으로 끝나지 않았다는 사실을 표현하는 역할
- 무조건 예외를 반환하는 함수 / 무한 루프 함수 등

## 4. String Interplation, String indexing
```
val person = Person("김강민", 100)
val log = "사람의 이름은 ${person.name}이고 나이는 ${person.age}세 입니다"
println(log)

val name = "김구라"
println("이름 $name")
```
> TIP : 변수 이름만 사용하더라도 ${변수}를 사용하는 것이 좋다  
> 가독성, 일괄 변환, 정규식 활용 측면에서 좋음

### Kotlin에서 문자열의 특정 문자 가져오기
#### Java
```
String str = "ABCDE";
char ch = str.charAt(1)
```

#### Kotlin
```
val str = "ABCDE"
println(str[0])
println(str[1])
```