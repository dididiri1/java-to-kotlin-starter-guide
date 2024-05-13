# 섹션 3 코틀린에서의 OOP

## 9강. 코틀린에서 클래스를 다루는 방법
1. 클래스와프로퍼티
2. 생성자와 init
3. 커스텀 getter, setter
4. backing field

### 1.클래스와프로퍼티

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/09_01.png?raw=true


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/09_02.png?raw=true


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/09_03.png?raw=true

```
public class JavaPerson {

    private final String name;
    private int age;

    public JavaPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```
- 프로퍼티=필드+getter + setter
- 코틀린에서는 필드만 만들면 getter, setter를 자동으로 만들어준다

Kotlin 
```
class Person constructor(name: String, age: Int){

    val name = name
    val age = age
    
}
```
- constructor는 생략할 수 있다
```
class Person(name: String, age: Int){

    val name = name
    val age = age

}
```
- 코틀린에서는 생성자에서 프로퍼티를 만들수 있기 때문에
- 바로 val name, var age릴 해줄수 있다.
- {} body는 아무것도 없어서 생략 가능
```
class Person (
    val name: String, var age: Int
)
```

- Java 클래스에 대해서도 .필드로 getter, setter를 사용한다
```
fun main() {
    val javaPerson = JavaPerson("홍길동" , 100)
    println(javaPerson.name) // 홍길동
    javaPerson.age = 10
    println(javaPerson.age) // 10
}
```

### 2. 생성자와 init
#### 2-1. 클래스가생성되는시점에나이를검증해보자!

Java 
```
public JavaPerson(String name, int age) {
    if (age <= 0) {
      throw new IllegalArgumentException(String.format("나이는 %s일 수 없습니다", age));
    }
    this.name = name;
    this.age = age;
}
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/09_04.png?raw=true


- init(초기화) 블록은 생성자가 호출되는 시점에 호출된다.
Kotlin
```
class Person (
    val name: String,
    var age: Int
) {
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }
    }
}
```
> 클래스가 생성되는 시점에 무언가 검증 로직을 해주고 싶다면 코틀린에 존재하는 init 블록(초기화 블록)에  
> 로직을 작성해주면 된다. 생성자가 호출되는 시점에 호출됨.


#### 2-2. 최초로태어나는아기는무조건1살이니,생성자를하나더만들자!

Java
```
public JavaPerson(String name, int age) {
    if (age <= 0) {
      throw new IllegalArgumentException(String.format("나이는 %s일 수 없습니다", age));
    }
    this.name = name;
    this.age = age;
}

public JavaPerson(String name) {
    this(name, 1);
}
```
- **consturctor(파라미터)**로 생성자를 추가
- **: this(name, 1)**로 위에 있는 생성자를 호출 한다.
Kotlin
```
class Person (
    val name: String,
    var age: Int
) {
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }
    }
    
    constructor(name: String) : this(name, 1)
}
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/09_05.png?raw=true


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/09_06.png?raw=true


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/09_07.png?raw=true


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/09_08.png?raw=true


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/09_09.png?raw=true
```
초기화 블록
부생성자 1
부생성자 2
```

> 본문은역순으로실행됩니다!

#### 그런데 사실... 부생성자 보다는 default parameter를 권장한다
```
class Person (
    val name: String = "홍길동",
    var age: Int = 1,
) {
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }
        println("초기화 블록")
    }
}
```

#### Converting과 같은 경우 부생성자를 사용할 수 있지만, 그보다는 정적팩토리 메소드를 추천 한다.

### 3. 커스텀 getter, setter
#### 성인인지 확인하는 기능을 추가 해본다.
Java
```
public boolean isAdult() {
    return this.age >= 20;
}
```
- 함수 처럼 만들기
Kotlin
```
fun isAdult(): Boolean {
   return this.age >= 20
}
```

- 함수 대신 프로퍼티로도 만들 수 있다.
Kotlin(Property 표현)
```
val isAdult: Boolean
  get() = this.age >= 20

```
Kotlin(Expresstion 표현)
```
val isAdult2: Boolean
  get() {
     return this.age >= 20
  }
```

- 모두 동일한 기능이고 표현 방법만 다르다 어떤 방법이 가독성이 나을까?
- 객체의 속성이라면 custom getter 그렇지 않다면 함수 

- Custom getter를 사용하면 자기 자신을 변형해 줄 수도 있다.
  - 예시로 name을 get 할때 무조건 대문자로 바꾸어서 get을 하게 만들 수 있다.

```
fun main() {
    val person = Person2("cc", 30)
    println(person.name)

}

class Person(
    name: String,
    var age: Int,
) {
    val name: String = name
        get() = field.uppercase()

}
```

> person.name 하면 name에 대한 getter가 호출된다.   
> 즉 name은 name에 대한 getter를 호출 하니깐 다시 get를 부른다.

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/09_11.png?raw=true

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/09_12.png?raw=true

- 개인적으로는 custom getter에서 backing field를 쓰는 경우는 드물다.
- 함수로 처리할수도 있다.
```
fun main() {
    val person = Person("cc", 30)
    println(person2.upperCaseName)

}

class Person(
    val name: String,
    var age: Int,
) {
    val upperCaseName: String
        get() = this.name.uppercase()

}
```

#### name을 set할때 무조건 대문자로 바꾸어 보기
```
class Person(
    name: String,
    var age: Int,
) {
    var name: String = name
        set(value) {
            field = value.uppercase()
        }

}
```
> 참고: Setter 자체를 잘 안쓰고 그러다보니 backing setter를 활용하는 custom setter도 잘 안쓰게 된다.

### 정리
- 코틀린에서는 필드를 만들면 getter와 (필요에 따라) setter가 자동으로 생긴다.
  - 떄문에 이를 **프로퍼티** 라고 부른다.
- 코틀린에서는 **주생성자**가 필수이다.
- 코틀린에서는 consturctor 키워드를 사용해 **부생성**를 추가로 만들 수 있다.
  - 단, default parameter나 정적 팩토리 메소드를 추천한다.
-  실제 메모리에 존재하는 것과 무관하게 custom getter 와 custom setter를 만들 수 있다.
- custom getter, custom setter 에서 무한 루프를 막기 위해 field라닌 키워드를 사용한다.
  - 이름 **backing field** 라고 부른다.