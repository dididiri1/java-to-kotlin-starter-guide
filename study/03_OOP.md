# 섹션 3 코틀린에서의 OOP

## 9강. 코틀린에서 클래스를 다루는 방법
1. 클래스와프로퍼티
2. 생성자와 init
3. 커스텀 getter, setter
4. backing field

### 1.클래스와프로퍼티

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/09_01.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/09_02.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/09_03.png?raw=true)

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

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/09_04.png?raw=true)


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

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/09_05.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/09_06.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/09_07.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/09_08.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/09_09.png?raw=true)
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

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/09_11.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/09_12.png?raw=true)

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

## 10.코틀린에서 상속을다루는방법
1. 추상 클래스
2. 인터페이스
3. 클래스를 상속할때 주의할 점
4. 상속 관련 지시어 정리

### 1.추상 클래스

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/10_01.png?raw=true)

Java
```
public abstract class JavaAnimal {

    protected final String species;
    protected final int legCount;

    public JavaAnimal(String species, int legCount) {
        this.species = species;
        this.legCount = legCount;
    }

    abstract public void move();

    public String getSpecies() {
        return species;
    }

    public int getLegCount() {
        return legCount;
    }

}

```

Kotlin
```
abstract class Animal(
    protected val species: String,
    protected val legCount: Int,

) {
    abstract fun move()
    
}
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/10_02.png?raw=true)


Java
```
public class JavaCat extends JavaAnimal {

   public JavaCat(String species) {
       super(species, 4);
   }

   @Override
   public void move() {
       System.out.println("고양이가 사뿐 사뿐 걸어가~");
   }

}

```
- extends 키워드를 사용하지 않고 : 을 사용한다.
- 코틀린에서는 어떤 클래스를 상속받을 떄 무조건 상위 클래스의 생성자를 바로 호출한다.
- override를 필수적으로 붙여 주어야 한다.
Kotlin
```
class Cat (
    species: String
) : Animal(species, 4){

    override fun move() {
        println("고양이가 사뿐 사뿐 걸어가~")
    }
}
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/10_03.png?raw=true)

Java
```
public final class JavaPenguin extends JavaAnimal implements JavaSwimable, JavaFlyable {

  private final int wingCount;

  public JavaPenguin(String species) {
    super(species, 2);
    this.wingCount = 2;
  }

  @Override
  public void move() {
    System.out.println("펭귄이 움직입니다~ 꿱꿱");
  }

  @Override
  public int getLegCount() {
    return super.legCount + this.wingCount;
  }

  @Override
  public void act() {
    JavaSwimable.super.act();
    JavaFlyable.super.act();
  }

}
```
Kotlin
```
abstract class Animal(
    protected val species: String,
    protected open val legCount: Int,

) {
    abstract fun move()

}

class Penguin(
    species: String
) : Animal(species, 2) {

    private val wingCount: Int = 2

    override fun move() {
        println("펭권이 움직인다~")
    }

    override val legCount: Int
        get() = super.legCount + this.wingCount
}
```
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/10_04.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/10_05.png?raw=true)

- 상위 클래스에 접근하는 키워드는 supper로 Java와 똑같다.
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/10_06.png?raw=true)

#### Java, Kotlin 모두 추상 클래스는인 스턴스화 할 수 없다

### 2. 인터페이스
#### Flyable과 Swimmable을 구현한 Penguin

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/10_07.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/10_08.png?raw=true)

Java
```
public interface JavaSwimable {

  default void act() {
    System.out.println("어푸 어푸");
  }

}

public interface JavaFlyable {

  default void act() {
    System.out.println("파닥 파닥");
  }

}


```

- default 키워드 없이 메소드 구현이 가능
- Kotlin에서도 추상 메소드를 만들 수 있다.
Kotlin
```
interface Swimable {

    fun act() {
        println("어푸 어푸")
    }
}

interface Flyable {

    fun act() {
        println("파닥 파닥")
    }
}
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/10_09.png?raw=true)

Java
```
public final class JavaPenguin extends JavaAnimal implements JavaSwimable, JavaFlyable {

  private final int wingCount;

  public JavaPenguin(String species) {
      super(species, 2);
      this.wingCount = 2;
  }

  @Override
  public void move() {
      System.out.println("펭귄이 움직입니다~ 꿱꿱");
  }

  @Override
  public int getLegCount() {
      return super.legCount + this.wingCount;
  }

  @Override
  public void act() {
      JavaSwimable.super.act();
      JavaFlyable.super.act();
  }

}

```

- 인터페이스 구현도 : 을 사용한다.
- 중복되는 인터페이스를 특정할때 **super<타입>.함수** 사용
- Java, Kotlin 모두 인터페이스를 인스턴스화할수없다
Kotlin
```
class Penguin(
    species: String
) : Animal(species, 2), Swimable, Flyable {

    private val wingCount: Int = 2

    override fun move() {
        println("펭권이 움직인다~")
    }

    override val legCount: Int
        get() = super.legCount + this.wingCount

    override fun act() {
        super<Swimable>.act()
        super<Flyable>.act()
    }
}
```

### 3. 클래스를상속받을때주의할점

Kotlin
```
fun main() {
    Derived(300)
}

open class Base(
    open val number: Int = 100
) {
    init {
        println("Base Class")
        println(number)
    }
}

class Derived(
    override val number: Int
) : Base(number) {
    init {
        println("Derived Class")
    }
}
```
출력
```
Base Class
0
Derived Class
```
> 참고: 상위 클래스를 설계 할때 생성자 또는 초기화 블록에 사용되는 프로퍼티에는  
> open을 피해야 한다.

### 4. 상속관련키워드4가지정리

1. final : override를 할 수 없게 한다. default로 보이지 않게 존재한다.
2. open : override를 열어 준다.
3. abstract : 반드시 override 해야 한다.
4. override : 상위 타입을 오버라이드하고있다.

### 정리
- 상속 또는 구현을 할 때에 :을사용해야한다.
- 상위 클래스 상속을 구현할 때 생성자를 반드시 호출해야 한다.
- override를 필수로 붙여야 한다.
- 추상 멤버가 아니면기본적으로 오버라이드가 불가능하다.
  - open을 사용해주어야 한다.
- 상위 클래스의 생성자 또는 초기화 블록에서 open 프로퍼티를
  - 사용하면 얘기치 못한 버그가 생길 수 있다.

## 11. 코틀린에서 접근 제어를 다루는 방법
1. 자바와 코틀린의 가시성 제어
2. 코틀린 파일의 접근 제어
3. 다양한 구성요소의 접근 제어
4. Java와 Kotlin을 함께 사용할 경우 주의할 점

### 1. 자바와 코틀린의 가시성 제어

#### JAVA
| 접근 제어     |            설명             |
|:----------|:-------------------------:|
| public    |       모든 곳에서 접근 가능        |
| protected | 같은 패키지 또는 하위 클래스에서만 접근 가능 |
| default   |      같은 패키지에서만 접근 가능      |
| private   |    선언된 클래스 내에서만 접근 가능     |

#### Kotlin
| 접근 제어     |               설명               |
|:----------|:------------------------------:|
| public    |          모든 곳에서 접근 가능          |
| protected | **선언된 클래스** 또는 하위 클래스에서만 접근 가능 |
| internal  |       **같은 모듈에서만 접근 가능**       |
| private   |       선언된 클래스 내에서만 접근 가능       |

### 2. 코틀린 파일의 접근 제어
코틀린은 .kt 파일에 변수, 함수, 클래스 여러개를 바로 만들 수 있다.

### 3. 다양한 구성요소의 접근 제어 - 생성자
Java에서 유틸성 코드를 만들때 abstract class + private constuctor를 사용해서 인스턴스화를 막았죠?

```
public abstract class StringUtils {
   private StringUtrils() {}
   
   public boolean isDirectoryPath(String path) {
       return path.endsWith("/");
   }
}

```
프로퍼티도 가시성 범위는 동일합니다. 단!    
프로퍼티의 가시성을 제어하는 방법으로는...

### 3. 다양한 구성요소의 접근 제어 - 생성자
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/11_01.png?raw=true)

### 3. 다양한 구성요소의 접근 제어 - 생성자
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/11_02.png?raw=true)

### 4. Java와 Kotlin을 함께 사용할 때주의할점

Internal은 바이트 코드 상public이 된다.  
때문에Java 코드에서는Kotlin 모듈의internal 코드를가져올수있다

### 4. Java와 Kotlin을 함께 사용할 경우 주의할 점
- Kotlin의 protected와 Java의 protected는 다르다.  
- Java는 같은패키지의Kotlin protected 멤버에 접근할수있다.

### 정리
- Kotlin에서 패키지는 namespace 관리용이기때문에 **protected**는 의미가 달라졌다.
- Kotlin에서는 **default**가 사라지고, 모듈간의 접근을 통제하는 **internal**이 새로 생겼다.
- 생성자에 접근 지시어를 붙일 때는 **constructor**를 명시적으로 써주어야 한다.
- 유틸성 함수를 만들 때는 파일 최상단을 이용하면 편리하다.
- 프로퍼티의 custom setter에 접근 지시어를 붙일 수 있다.  
- Java에서 Kotlin 코드를 사용할 때 internal과 protected는 주의해야 한다.

## Lec 12. 코틀린에서 object 키워드를 다루는 방법
1. static 함수의 변수
2. 싱글톤
3. 익명 클래스 

### 1. static 함수와 변수
- 코틀린에서는 static이 없다 대신 companion object 사용
- static: 클래스가 인스턴스화 될 떄 새로운 값이 복제되는게 아니라  
          정적으로 인스턴스 끼리의 값을 공유
- companion object: 클래스와 동행하는 유일한 오브젝트

```
class Person private constructor(
    var name: String,
    var age: Int,
) {

    companion object {
        val MIN_AGE = 1 <--- 런타임 시에 변수가 할당
        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }
    }
}
```

```
class Person private constructor(
    var name: String,
    var age: Int,
) {

    companion object {
        private const val MIN_AGE = 1 <--- 컴파일 시에 변수가 할당
        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }
    }
}
```
> 참고: 진짜 상수에 붙이기 위한 용도 기본 타입과 String에 붙일 수 있음
#### Java와 차이점
companion object 즉 동반객체도 하나의 객체로 간주된다 떄문에  
이름을 붙일 수도 있고, interface를 구현할 수도 있다.

```
interface Log {

    fun log()
}

class Person private constructor(
    var name: String,
    var age: Int,
) {

    companion object Factory : Log {
        private const val MIN_AGE = 1
        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }

        override fun log() {
            println("나는 Person 클래스의 동행 객체 Factory에요")
        }
    }
}
```
companion object에 유틸성 함수들을 넣어도 되지만, 최상단 파일을 활용하는 것을 추천

Java에서 Kotlin copanion object를 사용하려면 
@JvmStatic 을 붙여야 한다~

### 2. 싱글톤
#### Java
```
public class JavaSingleton {

  private static final JavaSingleton INSTANCE = new JavaSingleton();

  private JavaSingleton() { }

  public static JavaSingleton getInstance() {
    return INSTANCE;
  }

}

```
#### Kotlin
```
fun main() {
    println(Singleton.a)
    Singleton.a += 10
    println(Singleton.a)
}

object Singleton {
    var a: Int = 0
}
```

3. 익명 클래스
#### Java
```
 public static void main(String[] args) {
    moveSomething(new Movable() {
      @Override
      public void move() {
        System.out.println("움직인다~~");
      }

      @Override
      public void fly() {
        System.out.println("난다~~");
      }
    });
  }

  private static void moveSomething(Movable movable) {
    movable.move();
    movable.fly();
  }
```
#### Kotlin

### Lec 12. 코틀린에서 object 키워드를 다루는 방법
- Java의 static 변수와 함수를 만드려면,  
  Kotlin에서는 companion object를 사용해야 한다.
- companion object도 하나의 객체로 간주되기 떄문에 이름을 붙일 수 있고, 다른 타입을 상속 받을 수도 있다.
- Kotlin에서 싱글톤 클래스를 만들 떄 object 키워드를 사용한다.
- Kotlin애서 익명 클래스를 만들 떄 **object : 타입**을 사용한다.

## Lec 13. 코틀린에서 중첩 클래스를 다루는 방법
1. 중첩 클래스의 종류
2. 코틀린의 중첩 클래스와 내부 클래스

### 2. 코틀린의 중첩 클래스와 내부 클래스
Java의 static 중첩 클래스(권장되는 클래스 안에 클래스)

```
fun main() {

}

class JavaHouse(
    val  address: String,
    val livingRoom: LivingRoom,
) {
    class LivingRoom(
        private val area: Double,
    )
}
```
Java의 내부 클래스(권장되지 않는 클래스 안의 클래스)
```
class JavaHouse(
    val  address: String,
    val livingRoom: LivingRoom,
) {
    inner class LivingRoom(
        private val area: Double,
    ) {
        val address: String
            get() = this@JavaHouse.address
        
    }
}
```

### 정리
- 클래스 안에 클래가 있는 경우 종류는 두 가지 였다.
  - (Java기준) static을 사용하는 클래스
  - (Java기준) static을 사용하지 않는 클래스
- 코틀린에서는 이러한 가이드를 따르기 위해
  - 클래스 안에 기본 클래스를 사용하면 바깥 클래스에 대한 참조가 없고
  - 바깥 클래스를 참조하고 싶다면, inner 키워드를 붙여야 한다
- 코틀린 inner class에서 바깥 클래스를 참조하려면 **this@바깥클래스**를 사용한다.

## Lec 14. 코틀린에서 다양한 클래스를 다루는 방법

### 1. Data Class
- 계층간의 데이터를 전달하기 위한 DTO(Data transfer Object)
데이터(필드)
생성자와 getter, equals, hashCode, toString

#### Java
```
public class JavaPersonDto {

  private final String name;
  private final int age;

  public JavaPersonDto(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JavaPersonDto that = (JavaPersonDto) o;
    return age == that.age && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age);
  }

  @Override
  public String toString() {
    return "JavaPersonDto{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
```

#### Kotlin
```
fun main() {
    val dto1 = PersonDto("홍길동", 100)
    val dto2 = PersonDto("홍길동", 200)

    println(dto1)
}

data class PersonDto(
    val name: String,
    val age: Int,
)
```
```
false
PersonDto(name=홍길동, age=100)
```

> toString이 자동으로 구현되기 때문에 내부값을 확인할 수 있다.

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/14_01.png?raw=true)

### 2. Enum Class
#### Java
```
public enum JavaCountry {

  KOREA("KO"),
  AMERICA("US");

  private final String code;

  JavaCountry(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

}
```
#### Kotlin
```
enum class Country (
    private val code: String,
){

    KOREA("KO"),
    AMERICA("US");
}
```
> when은 Eunm Class 혹은 Sealed Class와 함께 사용할 경우, 더욱더 진가를 발휘한다.

```
fun handleCountry(country: Country) {
    when (country) {
        Country.KOREA -> TODO()
        Country.AMERICA -> TODO()
    }
}
```

> 컴파일러가 country의 모든 타입을 알고 있어
> 다른 타입에 대한 로직(else)을 작성하지 않아도 된다

3. Sealed Class, Sealed Interface

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/14_02.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/14_03.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/14_04.png?raw=true)



> 추상화가 필요한 Entity or DTO에 sealed class를 활용  
> 추가로, JDK17 에서도 Sealed Class가 추가되었다.

### 정리 
- Kotlin의 Data class를 사용하면 equals, hashCode, toString을 자동으로 만들어준다.
- Kotlin의 Enum Class는 Java의 Enum Class와 동일하지만, when과 함께사용함으로써큰장점을갖게된다.
- Enum Class보다 유연하지만, 하위클래스를 제한하는 Sealed Class 역시 when과 함께 주로사용된다.

## lec 15. 코틀린에서 배열과 컬렉션을 다루는 방법
1. 배열
2. 코틀린에서 Collection - List, Set, Map
3. 컬렉션의 null 가능성, Java와 함께 사용하기 

### 1. 배열

#### Java
```
int[] array = {100, 200};

for(int i = 0; i< array.length; i++) {
   System.out.printf(array[i]);
}
```

#### Kotlin
```
fun main() {

    val array = arrayOf(100, 200)

    for (i in array.indices) {
        println("${i} ${array[i]}")
    }
    
    array.plus(300)
    for ((idx, value ) in array.withIndex()) {
        println("$idx $value")
    }
}
```

### 2. 코틀린에서의 Collection
컬렉션을 만들어줄 때 불변인지, 가변인지를 설정해야 한다.

가변(Mutable) 컬렉션 : 컬렉션에 element를 추가, 삭제할 수 있다.
불변 컬렉션: 컬렉션에 element를 추가, 삭제할 수 없다. 

### 2. 코틀린에서의 Collection - List

#### Java
```
final List<Integer> numbers = Array.asList(100,200);
```

### Kotlin
```
fun main() {

    val array = arrayOf(100, 200)

    for (i in array.indices) {
        println("${i} ${array[i]}")
    }

    array.plus(300)
    for ((idx, value ) in array.withIndex()) {
        println("$idx $value")
    }

}
```

```
fun main() {

    val numbers = listOf(100, 200)


    println(numbers[0])

    for (number in numbers) {
        println(number)
    }

    for ((idx, value) in numbers.withIndex()) {
        println("${idx} $value")
    }
}
```

### 정리
- 코틀린에서는 컬렉션을 만들 때도 불변/가변을 지정해야 한다.
- List, Set, Map 에 대한 사용법이 변경, 확장되었다.
- Java와 Kotiln 코드를 섞어 컬렉션을 사용할 때에는 주의해야 한다.
  - Java에서 Kotiln 컬렉션을 가져갈 때는 불변 컬렉션을 수정할 수도 있고, non-nullable 컬렉션에 null을 넣을 수도 있다.
  - Kotiln 에서 Java 컬렉션을 가져갈 때는 플랫폼타입을 주의해야 한다.

## Lec 16. 코틀린에서 다양한 함수를 다루는 방법

1. 확장함수
2. infix 함수
3. inline 함수
4. 지역함수

### 1. 확장함수

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_01.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_02.png?raw=true)



```
fun main() {
 val str = "ABC"
 println(str.lastChat())
}

fun String.lastChat(): Char {
    return this[this.length -1]
}

```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_03.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_04.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_05.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_06.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_07.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_08.png?raw=true)

- 함자함수가 pulbic이고, 확장함수에서 수신객체클래스의 private 함수를 가져오면 캡슐화가 깨지는거 아닌가?
- 확장함수는 클래스에 있는 private 또는 protected 멤버를 가져 올수 없다.
- 멤버함수와 확장함수의 시그니처 동일하다면 멤버함수가 우선적으로 호출된다.

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_09.png?raw=true)

> 주의점:
> 화장함수를 만들었지만, 다른 기능의 똑같은 멤버함수가 생기면 오류가 발생할 수 있다.

#### 확장함수가 오버라이드 된다면?
- 해당 변수의 현재 타입 즉, 정적인 타입에 의해 어떤 확장함수가 호출될지 결정된다.
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_10.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_11.png?raw=true)

#### 확장함수 - 중간정라
1. 확장함수는 원본 클래스의 private, protected 멤버 접근이 안된다.
2. 멤버함수, 확장함수 중 멤버함수에 우선권이 있다.
3. 확장함수는 현재 타입을 기준으로 호출된다.

#### Java에서 Kotiln 확장함수를 가져다 사용할수 있다.

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/16_12.png?raw=true)

- Java 코드가 있는 상황에서, Kotlin 코드로 추가 기능 개발을 하기
  위해 확장함수와 확장프로퍼티가 등장했다.
- 확장함수는 원본 클래스의 private, protected 멤버 접근이 안된다!
- 멤버함수, 확장함수 중 멤버함수에 우선권이 있다!
- 확장함수는 현재 타입을 기준으로 호출된다!
- Java에서는 static 함수를 쓰는것처럼
  Kotlin의 확장함수를 쓸 수 있다

- 함수 호출 방식을 바꿔조는 infix 함수가 존재한다.
- 함수를 복사-붙여넣기 하는 inline 함수가 존재한다.
- Kotlin에서는 함수 안에 함수를 선언할 수 있고,
  지역함수라고 부른다

## Lec 17. 코틀린에서 람다 함수를 다루는 방법
1. Java에서 람다를 다루기 위한 노력
2. 코틀린에서의 람다
3. Closure
4. 다시 try wihth resources

### 1. Java에서 람다를 다루기 위한 노력

### 2. 코틀린에서의 람다

```
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
    
    // 람다를 만드는 방법 1
    val isApple = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }
    
    // 람다를 만드는 방법 2
    val isApple2 = { fruit: Fruit -> fruit.name == "사과"}
    
    // 람다를 직접 호출하는 방법 1
    isApple(fruits[0])
    
    // 람다를 직접 호출하는 방법 2
    isApple.invoke(fruits[0])

}
```

```
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
```

### 3. Closure

#### Java
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/17_01.png?raw=true)

#### Kotiln
- 코틀린에서는 람다가 시작하는 지점에 참조하고 있는 변수들을 모두 포획하여 그 정보를 가지고 있다.
- 이렇케 해야만 람다를 진정한 일급 시민으로 간주할 수 있다. 이데이터 구조를 Closure라고 부른다.
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/17_02.png?raw=true)

### 4. 다시 try with resources

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/17_03.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/17_04.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/17_05.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/17_06.png?raw=true)


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/17_07.png?raw=true)

### Lec17.코틀린에서 람다를 다루는 방법 - 정리

- 코틀린에서 람다는 두 가지 방법으로 만들 수 있고, {} 방법이 더 많이 사용된다.
```
    // 람다를 만드는 방법 1
    val isApple = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }
    
    // 람다를 만드는 방법 2
    val isApple2 = { fruit: Fruit -> fruit.name == "사과"}
```
- 함수를 호출하며, 마지막 파리미터인 람다를 쓸 떄는 소괄호 밖으로 람다를 뺄 수 있다.
```

    filterFruits(fruits) {fruit -> fruit.name == "사과" }

    filterFruits(fruits) {it.name == "사과" } 
```
- 람다의 마지막 expression 결과는 람다의 반환 값이다
```
filterFruits(fruits) { fruit -> 
  println("사과만 받는다..!!)
  fruit.name == "사과"
```
- 코틀린에서는 Closure를 사용하여 non-final 변수도 람다에서 사용할 수 있다

## Lec 18. 코틀린에서 컬렉션을 함수형으로 다루는 방법
1. 필터와 맵
2. 다양한 컬렉션 처리 기능
3. List를 Map으로
4. 중첩된 컬렉션 처리

### 1. 필터와 맵
> filer / filterIndexed / map / mapIndexed / mapNotNull

```
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
            println(idx)
            fruit.price
        }
    
}

private fun filterFruits(fruits: List<Fruit>, filter: (Fruit) -> Boolean
):List<Fruit> {

    return fruits.filter(filter)
}

```

### 2. 다양한 컬렉션 처리 기능
- 모든 과일이 사과인가요?!
- 혹시 출고가 10,000원 이상의 과일이 하나라도 있나요?!

#### all : 조건을 모두 만족하면 true 그렇지 않으면 false
```
val isAllApple = fruits.all { fruit -> fruit.name == "사과" }
```
#### none : 조건을 모두 불만족하면 true 그렇지 않으면 false
```
val isNoApple = fruits.none { fruit -> fruit.name == "시과" }
```
#### any : 조건을 하나라도 만족하면 true 그렇지 않으면 false
```
val isNoApple2 = fruits.any { fruit -> fruit.price >= 10_000 }
```

- 과일이 몇 종류 있죠?!
#### count : 개수를 센다
```
val fruutCount = fruits.count()
```


#### sortedBy : (오른차순) 정렬을 한다
```
val fruitCount = fruits.sortedBy { fruit -> fruit.price
```


#### sortedByDescending : (내림차순) 정렬을 한다
```
val fruitCount3 = fruits.sortedByDescending { fruit -> fruit.price }
```


#### distinctBy : 변형된 값을 기준으로 중복을 제거한다
```
val distinctFruitNames = fruits.distinctBy { fruit -> fruit.name}
        .map { fruit -> fruit.name }
```

- 첫번째 과일만 주세요!
- 마지막 과일만 주세요!
#### first : 첫번째 값을 가져온다 (무조건 null이 아니어야함)
#### firstOrNull : 첫번째 값 또는 null을 가져온다
```
val distinctFruitNames = fruits.distinctBy { fruit -> fruit.name}
        .map { fruit -> fruit.name }
```

#### last : 마지막 값을 가져온다 (무조건 null이 아니어야함)
#### lastOrNull : 마지막 값 또는 null을 가져온다
```
val distinctFruitNames = fruits.distinctBy { fruit -> fruit.name}
        .map { fruit -> fruit.name }
```

> all / none / any / count / sortedBy / sortedByDescending / distinctBy
> first / firstOrNull / last / lastOrNull

### 3. List를 Map으로
- 과일이름 -> List<과일> Map이 필요해요!

#### groupBy: 이름을 기준으로 그풉핑이 됨
```
val map: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }
```

- id -> 과일 Map이 필요해요!
#### associateBy : id를 통해서 매핑해야 할때 중복돠지 않는 키를 가지고 map를 만들때 사용함
```
val map: Map<Long, Fruit> = fruits.associateBy { fruit -> fruit.id }
```

- 과일이름 -> List<출고가> Map이 필요해요!
#### associateBy : id를 통해서 매핑해야 할때 중복돠지 않는 키를 가지고 map를 만들때 사용함
```
val map: Map<String, List<Int>> = fruits
        .groupBy({fruit -> fruit.name}, {fruit -> fruit.price })
```

- Map에 대해서도 앞선 기능들 대부분 사용할 수 있음
```
val map: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }
  .filter { (key, vale) -> key == "사과" }
```

### 4. 중첩된 컬렉션 처리

- 이 상황에서, 출고가와 현재가가 동일한 과일을 골라주세요
```
val samePriceFruits = fruitsInList.flatMap { 
  list.filter { fruit -> fruit.factoryPrice == fruit.currentPrice}
}
```

#### 리팩토링 - 확장함수
```
data class Fruit(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long,
) {
    val isSamePrice: Boolean
        get() = factoryPrice == currentPrice
}
```
```
val samePriceFruits = fruitsInList.flatMap { list -> list.samePriceFilter }
```

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