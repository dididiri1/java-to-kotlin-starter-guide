# 2강. 코틀린에서 null을 다루는 방법

## 1. Kotlin에서의 null 체크
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

## 2. Safe Call과 Elvis 연산자
### Safe Call 
- null이 아니면 실행하고, null이면 실행 하지 않는다(그대로 null)
``` 
fun main() {
    val str: String? = "ABC"
    str.length // 불가능
    str?.length // 가능
}
``` 
### Elvis 연산자
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

## 3. 널 아님 단언!!
- nullable type이지만, 아무리 생각해도 null이 될 수 없는 경우
- 이 변수는 절대 null 아니야라는 뜻!
```
fun startsWithA1(str: String?): Boolean {
    return str!!.startsWith("A")
}
```

## 4. 플랫폼 타입
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