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

## 10.코틀린에서 상속을다루는방법
1. 추상 클래스
2. 인터페이스
3. 클래스를 상속할때 주의할 점
4. 상속 관련 지시어 정리

### 1.추상 클래스

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/10_01.png?raw=true

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

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/10_02.png?raw=true
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

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/10_03.png?raw=true

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
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/10_04.png?raw=true


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/10_05.png?raw=true

- 상위 클래스에 접근하는 키워드는 supper로 Java와 똑같다.
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/10_06.png?raw=true

#### Java, Kotlin 모두 추상 클래스는인 스턴스화 할 수 없다

### 2. 인터페이스
#### Flyable과 Swimmable을 구현한 Penguin

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/10_07.png?raw=true

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/10_08.png?raw=true

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

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/10_09.png?raw=true

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
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/11_01.png?raw=true

### 3. 다양한 구성요소의 접근 제어 - 생성자
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/11_02.png?raw=true

### 4. Java와 Kotlin을 함께 사용할 때주의할점

Internal은 바이트 코드 상public이 된다.  
때문에Java 코드에서는Kotlin 모듈의internal 코드를가져올수있다

### 4. Java와 Kotlin을 함께 사용할 경우 주의할 점
Kotlin의 protected와 Java의 protected는 다르다.  
Java는 같은패키지의Kotlin protected 멤버에 접근할수있다.

### 정리
Kotlin에서 패키지는namespace 관리용이기때문에  
**protected**는 의미가 달라졌다.

Kotlin에서 패키지는namespace 관리용이기때문에  
protected는 의미가 달라졌다.   
Kotlin에서는 **default**가 사라지고, 모듈간의접근을통제하는  
**internal**이 새로 생겼다.

Kotlin에서 패키지는namespace 관리용이기때문에
protected는 의미가 달라졌다.   
Kotlin에서는 default가 사라지고, 모듈간의 접근을통제하는  
internal이 새로 생겼다.  
생성자에 접근 지시어를 붙일 때는 **constructor**를명시적으로 써주어야한다.

Kotlin에서 패키지는namespace 관리용이기때문에  
protected는 의미가 달라졌다.  
Kotlin에서는 default가 사라지고, 모듈간의 접근을통제하는  
internal이 새로 생겼다.  
생성자에 접근지시어를 붙일때는 constructor를명시적으로  
써주어야한다.   
유틸성 함수를 만들 때는 파일 최상단을 이용하면 편리하다.

프로퍼티의custom setter에접근지시어를붙일수있다.  
Java에서 Kotlin 코드를 사용할 때 internal과 protected는 주의해야 한다.
