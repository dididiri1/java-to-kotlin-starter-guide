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

### 2. kotlin에서의 Primitive Type

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


### 3. Kotlin에서의 nullable 변수
- null이 변수에 들어갈 수 있다면 "타입?"를 사용해야 한다.
``` kotlin
fun main() {

    var number1 = 1_000L
    number1 = null // error

    var number2: Long? = 1_000L
    number2 = null
}
```

### 4. Kotlin에서의 객체 인스턴스화
- 객체 인스턴스화를 할 떄에는 new를 붙이지 않아야 한다.
``` kotlin
var person = Person("김강민")
``` 

## 2강. 코틀린에서 null을 다루는 방법

### 1. Kotlin에서의 null 체크
#### Java
``` 
public boolean startsWithA1(String str) {
    if (str == null) {
      throw new IllegalArgumentException("null이 들어왔습니다");
    }
    return str.startsWith("A");
}
```

#### Kotlin
``` 
fun startsWithA1(str: String?): Boolean {
    if(str == null) {
        throw IllegalArgumentException("null이 들어왔습니다")
    }
    return str.startsWith("A")
}
``` 

#### Java
``` 
public Boolean startsWithA2(String str) {
    if (str == null) {
      return null;
    }
    return str.startsWith("A");
}
```

#### Kotlin
``` 
fun startsWithA2(str: String?): Boolean? {
    if(str == null) {
        return null
    }
    return str.startsWith("A")
}
``` 

#### Java
``` 
public boolean startsWithA3(String str) {
    if (str == null) {
      return false;
    }
    return str.startsWith("A");
}
```

#### Kotlin
``` 
fun startsWithA3(str: String?): Boolean {
    if(str == null) {
        return false
    }
    return str.startsWith("A")
}
``` 
> Kotlin에서는 null이 가능한 타입을 완전히 다르게 취급한다!
> null이 가능한 타입만을 위한 기능으 없나?

### 2. Safe Call과 Elvis 연산자
#### Safe Call
- null이 아니면 실행하고, null이면 실행 하지 않는다(그대로 null)
``` 
fun main() {
    val str: String? = "ABC"
    str.length // 불가능
    str?.length // 가능
}
``` 
#### Elvis 연산자
- 앞의 연산 결과가 null이면 뒤에 값을 사용
``` 
fun main() {
    val str: String? = "ABC"
    str?.length ?: 0
    println(str) // ABC 출력
}
```
``` 
fun main() {
    val str: String? = null
    str?.length ?: 0
    println(str) // 0 출력
}
``` 

### Safe Call과 Elvis를 사용해서 간결하게 바꾸기
``` 
fun startsWithA1(str: String?): Boolean {
    return str?.startsWith("A") ?: throw IllegalArgumentException("null이 들어왔습니다")
}
```
``` 
fun startsWithA2(str: String?): Boolean? {
    return str?.startsWith("A")
}
```
``` 
fun startsWithA3(str: String?): Boolean {
    return str?.startsWith("A") ?: false
}
```

### 3. 널 아님 단언!!
- nullable type이지만, 아무리 생각해도 null이 될 수 없는 경우
- 이 변수는 절대 null 아니야라는 뜻!
```
fun startsWithA1(str: String?): Boolean {
    return str!!.startsWith("A")
}
```

### 4. 플랫폼 타입
- Kotlin에서 Java 코드를 가져다 사용할 때 어떻케 처리될까?!
- @Nullalbe이 없다면 Kotlin에서는 이 값이 nullalble인지 non-nullalble인지 알 수가 없다~
- 코틀린이 null 관련 정보를 알 수 없는 타입이면 Runtime 시 Exception이 일어 날 수 있다.
```
public class Person {

  private final String name;

  public Person(String name) {
      this.name = name;
  }
  
  @Nullable
  public String getName() {
      return name;
  }

}
```
```
fun main() {
  var person = Person("공부하는 개발자")
  startsWithA(person.name)
}

fun startsWithA(str: String): Boolean {
  return str.startsWith("A")
}
```
> 참고: 코틀린에서 자바 코드를 사용할 떄는 null 관련 정보를 꼼꼼히 작성하거나   
> null이 들어갈 수 있는건지, 없는건지 확인 해야됨

## 3강. 코틀린에서 Type을 다루는 방법
1. 기본 타입
2. 타입 캐스팅
3. Kotlin의 3가지 특이한 타입(Java에는 특이한 타입)
4. String Interplation, String indexing


### 1. 기본 타입
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

- to변환타입()을 사용한다 (명시적으로 타입 변화 가능)
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

### 2. 타입 캐스팅
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

### 3. Kotlin의 특이한 타입 3가지
- Any
- Unit
- Nothing

#### Any
- Java의 Object 역할. (모든 객체의 최상위 타입)
- 모든 Primitive Type의 최상의 타입도 Any이다.
- Any 자체로는 null을 포함할 수 없어 null을 포함하고 싶다면, Any?로 표현
- Any 에 equals / hashCode / toString 존재

#### Unit
- Unit은 Java의 void와 동일한 역할
- (살짝 어려운 내용) void와 다르게 Unit은 그 자체로 타입 인자로 사용 가능하다.
- 함수형 프로그래밍에서 Unit 은 단 하나의 인스턴스만 갖는 타입을 의미    
  즉, 코틀린의 Unit은 실제 존재하는 타입이라는 것을 표현

#### Nothing
- Nothing은 함수가 정상적으로 끝나지 않았다는 사실을 표현하는 역할
- 무조건 예외를 반환하는 함수 / 무한 루프 함수 등

### 4. String Interplation, String indexing
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

## 4강. 코틀린에서 연산자를 다루는 방법
1. 단항 연산자 / 산술 연산자
2. 비교 연산자와 동등성, 동일성
3. 논리 연산자 / 코틀린에 있는 특이한 연산자
4. 연산자 오버로딩

### 1. 단항 연산자 / 산술 연산자
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/04_01.png?raw=true)


### 2. 비교 연산자와 동등성, 동일성
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/04_02.png?raw=true)

#### Java
```
public class Lec04Main {

  public static void main(String[] args) {
      JavaMoney money1 = new JavaMoney(1_000L);
      JavaMoney money2 = new JavaMoney(2_000L);
      
      if (money.compareTo(money2) > 0) {
          System.out.println("Money1이 Money2보다 금액이 큽니다");
      }
  }

}
```
#### Kotlin
```
fun main() {

    val money1 = JavaMoney(2_000L)
    val money2 = JavaMoney(1_000L)

    if (money1 > money2) {
        println("Money1이 Money2보다 금액이 큽니다")
    }
}
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/04_03.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/04_04.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/04_05.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/04_06.png?raw=true)

#### Java
```
public class Lec04Main {
    public static void main(String[] args) {
        JavaMoney money1 = new JavaMoney(1_000L);
        JavaMoney money2 = money1;
        JavaMoney money3 = new JavaMoney(1_000L);

        System.out.println(money1 == money2); // true
        System.out.println(money1 == money3); // false
        System.out.println(money2.equals(money3)); // true
    }

}
```

#### Kotlin
```
fun main() {

    val money1 = JavaMoney(1_000L)
    val money2: JavaMoney = money1
    val money3 = JavaMoney(1_000L)

    println(money1 == money2)
    println(money1 === money3) // == 
    println(money1 == money3) // equals  

}
```

### 3. 논리 연산자와 코틀린에 있는 특이한 연산자

```
fun main() {

    if (fun1() || fun2()) {
        println("본문")
    }

    if (fun1() && fun2()) {
        println("본문")
    }

}

fun fun1(): Boolean {
    println("fun 1")
    return true
}

fun fun2(): Boolean {
    println("fun 2")
    return false
}
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/04_07.png?raw=true)

- in / !in
    - 컬렉션이나 범위에 포함되어 있다, 포함되어 있지 않다
- a..b
    - a부터 b 까지의 범위 객체를 생성한다
    - <코틀린에서 반복문을 다루는 방법> 강의에서 다룹니다.
- a[i]
    - a에서 특정 index i로 값을 가져온다
- a[i] = b
    - a의 특정 index i에 b를 넣는다

### 4. 연산자 오버로딩
- Kotlin에서는 객체마다 연산자를 직접 정의할 수 있다
#### Java
```
public class Lec04Main {
    
    public static void main(String[] args) {
        JavaMoney money1 = new JavaMoney(1_000L);
        JavaMoney money2 = new JavaMoney(2_000L);
        System.out.prinln(money1.plus(money2)); // Money(amount=3000)
    }
}

public class JavaMoney implements Comparable<JavaMoney> {

    private final long amount;

    ```

    public JavaMoney plus(JavaMoney other) {
        return new JavaMoney(this.amount + other.amount);
    }

    ```

}
```

#### Kotlin
```
fun main() {

    val money1 = Money(1_000L)
    val money2 = Money(2_000L)

    println(money1.plus(money2)) 
    println(money1 + money2) // Money(amount=3000)
}
```
> 코틀린에서는 객체 마다 직접 플러스 연산, 마이너스 연산, 단항 연산, 비교 연산 등  
> 다 직접 정의할 수 있다.
