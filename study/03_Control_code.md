![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/00_00.jpg?raw=true)

# 섹션 2 코틀린에서 코드를 제어하는 방법

## 5강. 코틀린에서 제어문을 다루는 방법
1. if문
2. Expression과 Statement
3. switch와 when

### if문

Java
```
private void validateScoreIsNotNegative(int score) {
     if (score < 0) {
        throw new IllegalArgumentException(String.format("%s는 0보다 작을 수 없습니다.", socre));
     }
}
```

Kotlin
```
fun validateScoreIsNotNegative(score: Int) {
    if(score < 0) {
        throw IllegalArgumentException("${score}는 0보다 작을수 없다")
    }
}
```
> new 생략말고는 Java와 차이가 없다

Java
```
private String getPassOrFail(int score) {
    if(score >= 50) {
        return "P";
    } else {
        return "F";
    }
}
```

Kotlin
```
fun getPassOrFail(score: Int): String{
    if (score >= 50) {
        return "P"
    } else {
        return "F"
    }
}
```

2. Expression과 Statement

> Java에서 if-else는 Statement이지만,  
> Kotlin에서는 Expression입니다

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/05_01.png?raw=true))

- if 문을 하나의 값으로 취급하지 않으니 에러가 난다 Statement
Java
```
String grade = if (score >= 50) {
   "P";
} else {
   "F";
}   
```

- 3항 연산자는 하나의 값으로 취급하므로 에러가 없다.
- Expression이면서 Statement이다
```
String greade = score >- 50 ? "P" : "F";
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/05_02.png?raw=true))


#### Kotlin에서는 if-else를 expression으로 사용할 수 있기 때문에 3항연산자가없습니다

#### if – else if – else 문도 문법이 동일합니다

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/05_03.png?raw=true)

### switch와 when

Java
```
private String getGradeWithSwitch(int score) {
    switch (score / 10) {
        case 9:
           return "A";
        case 8:
           return "B";
        case 7:
           return "C";
        default:
           return "D";
    }
}
```

```
when (값) {
   조건부 -> 어떠한구문
   조건부 -> 어떠한구문
   else -> 어떠한 구문
}


fun getGradeWithSwitch(score: Int): String {
    return when (score / 10) {
        9 -> "A"
        8 -> "B"
        7 -> "C"
        else -> "D"
    }
}

fun getGradeWithSwitch2(score: Int): String {
    return when (score) {
        in 90..99 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        else -> "D"
    }
}
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/05_04.png?raw=true)

Kotlin
```
fun startsWithA(obj: Any): Boolean {
    return when (obj) {
        is String -> obj.startsWith("A")
        else -> false
    }
}
```
Java
```
private void judgeNumber(int number) {
   if(number == 1 || number == 0 || number == -1) {
       System.out.println("어디서 많이 본 숫자입니다"); 
   } else {
       System.out.println("1, 0, -1 아닙니다");
   }
}

```
Kotlin
```
fun judgeNumber(number: Int) {
    when (number) {
        1, 0, -1 -> println("어디서 많이 본 숫자입니다")
        else -> println("1, 0, -1이 아닙니다")
    }
}

```

```
private void judgeNumber2(int number) {
    if (number == 0) {
        System.out.println("주어진 숫자는 0입니다");
        return;
    }

    if (number % 2 == 0) {
        System.out.println("주어진 숫자는 짝수입니다");
        return;
    }
    System.out.println("주어지는 숫자는 홀수입니다");
}
```

Kotlin
```
fun judgeNumber2(number: Int) {
    when {
        number == 0 -> println("주어진 숫자는 0입니다")
        number % 2 == 0 -> println("주어진 숫자는 짝수 입니다")
        else -> println("주어지는 숫자는 홀수입니다")
    }
}
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/05_05.png?raw=true)

### 정리 
- if / if – else / if - else if – else 모두 Java와 문법이 동일하다.
- 단 Kotlin에서는 Expression으로 취급된다.
  - 때문에 Kotlin에서는 삼항연산자가 없다
- Java의 switch는 Kotlin에서 when으로 대체되었고, when은 더강력한기능을갖는다


## 6강. 코틀린에서 반복문을 다루는 방법
1. for-each문
2. 전통적인for문
3. Progression과 Range
4. while 문

### 1. for each문
Java
```
List<Long> numbers = Arrays.asList(1L, 2L, 3L);

for (Long number : numbers) {
     System.out.println("number = " + number);
}
```

Kotlin
```
fun main() {
    val numbers = listOf(1L, 2L, 3L)
    for (number in numbers) {
        println(number)
    }
}
```

### 2.전통적인for문
Java
```
for (int i = 1; i<=3; i++) {
    System.out.println(i);
}
```

Kotlin
```
for (i in 1..3) {
   println(i)
}

```
내려가는경우는?!
```
for (i in 3 downTo 1) {
     println(i)
}
```
2칸씩올라가는경우는?!

```
for (i in 1..5 step 2) {
     println(i)
}
```

### 3. Progression과 Range
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/06_01.png?raw=true)
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/06_02.png?raw=true)
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/06_03.png?raw=true)
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/06_04.png?raw=true)
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/06_05.png?raw=true)
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/06_06.png?raw=true)
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/06_07.png?raw=true)
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/06_08.png?raw=true)
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/06_09.png?raw=true)

### 4. While문

Java
```
int i = 1;
while (i <= 3) {
   System.out.println(i);
   i++;
}
```

Kotlin
```
var i = 1; // i는 아래서 변경되어야 하니깐 val 대신에 var로 써야된다.
while (i <= 3) {
   println(i)
   i++
}
```

### 정리
- for each 문에서Java는 : Kotlin은 in 을 사용한다.
- 전통적인 for문에서 Kotlin은 등차수열과 in을 사용한다.
- 그 외 for문 문법은 모두 동일하다.
- while문과 do while문은 더욱더 놀랍도록 동일하다.


## 7강. 코틀린에서 예외를 다루는 방법

1. try catch finally 구문
2. Checked Exception과 Unchecked Exception
3. try with resources


### 1. try catch finally구문
Java 
```
private int parseIntOrThrow(@NotNull String str) {
     try {
         return Integer.parseInt(str);
     } catch (NumberFormatException e) {
         throw new IllegalArgumentException(String.format("주어진 %s는 숫자가 아닙니다", str));
     }
}
```

- 타입이뒤에위치하고
- new를사용하지않음
- 포맷팅이간결함
Kotlin
```
fun parseIntOrThrow(str: String): Int {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("주어진 ${str}는 숫자가 아닙니다")
    }
}
```

- 주어진 문자열을 정수로 변경하는 예제 실패하면null을반환
```
fun parseIntOrThrow(str: String): Int? {
    return try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}
```
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/07_01.png?raw=true)

### 2. Checked Exception과 Unchecked Exception
Kotlin
```
fun readFile() { // throws 구문이 없다!!
    val currentFile = File(".")
    val file = File(currentFile.absolutePath + "/a.txt")
    val reader = BufferedReader(FileReader(file))
    println(reader.readLine())
    reader.close()
}
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/07_02.png?raw=true)

### 3. try with resources
Java
```
public void readFile(String path) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
        System.out.println(reader.readLine());
    }
}
```
Kotlin
```
fun readFile(path: String) {
    BufferedReader(FileReader(path)).use { reader ->
        println(reader.readLine())
    }
}
```
> 참고: Kotlin에는 try with resources 사라지고 use를 사용하는구나 정도만 기억하기   
> 대신 use라는 inline 확장함수를 사용한다. 섹션 4에서 자세히 설명한다함.


### 정리 
- try catch finally 구문은 문법적으로 완전 히동일하다.
  - Kotlin에서는 try catch가 expression이다. 
- Kotlin에서 모든 예외는 Unchecked Exception이다.
- Kotlin에서는 try with resources 구문이 없다. 대신 코틀린의 언어적 특징을 활용해 close를 호출해준다.


## 8강. 코틀린에서 함수를 다루는 방법
1. 함수 선언문법
2. default parameter
3. named argument (parameter)
4. 같은타입의여러파라미터받기(가변인자)

### 1.함수 선언문법

#### 두정수를받아더큰정수를반환하는예제
Java
```
public int max(int a, int b) {
    if(a > b) {
        return a;
    }
    return b; 
}
```

Kotlin
```
fun max(a: Int, b: Int): Int {
    if(a > b) {
        return a
    } 
    return b
}

fun max2(a: Int, b: Int): Int { // Expression
    return if(a > b) {
        a
    } else {
        b
    }
}

fun max2(a: Int, b: Int): Int =
    if(a > b) {
        a
    } else {
        b
    }
}

fun max(a: Int, b: Int): Int = if  a > b) a else b
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/08_01.png?raw=true)

> block { } 을 사용하는 경우에는 반환 타입이 Unit이 아니면     
> 명시적으로 작성해주어야 한다

- 함수는 클래스 안에 있을수도, 파일 최상단에 있을수도 있다.
- 또한, 한 파일안에 여러 함수들이 있을 수도 있다.

### 2. default parameter 

#### 주어진 문자열을 N번 출력하는 예제

```
fun main() {
    repeat("Hello")
}

fun repeat(str: String, num: Int = 3, useNewLine: Boolean = true) {
    for (i in 1..num) {
        if (useNewLine) {
            println(str)
        } else {
            println(str)
        }
    }
}
```

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/08_02.png?raw=true)

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/08_03.png?raw=true)

#### named argument parameter

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/blob/main/study/images/08_04.png?raw=true)


```
fun main() {
    repeat("Hello", useNewLine = false)
}

fun repeat(str: String, num: Int = 3, useNewLine: Boolean = true) {
    for (i in 1..num) {
        if (useNewLine) {
            println("${str} : ture")
        } else {
            println("${str} : false")
        }
    }
}
```

- builder를 직접 만들지 않고 builder의 장점을 가지게 된다.
```
fun main() {
    //printNameAndGender("FEMALE", "홍길동")
    printNameAndGender(name = "홍길동", gender = "MALE")
}

fun printNameAndGender(name: String, gender: String) {
    println(name)
    println(gender)
}
```

- Kotlin에서 Java 함수를 가져다 사용할 떄는 
- named argumnet를 사용할 수 없다.

### 4.같은 타입의 여러 파라미터 받기(가변인자)
- 타입... 을 쓰면 가변인자 사용
Java
```
public static void printAll(String... strings) {
    for (String str: strings) {
        System.out.println(str);
    }
}
```
- 배열을 직접 넣거나, comma를 이용해 여러 파라미터를 넣거나
```
String[] array = new String[]{"A", "B", "C"};
printAll(array);

printAll("A", "B", "C");
```

- 배열을 바로 넣는대신 스프레드연산자(*)를 붙여주어야 한다
Kotlin
```
fun main() {
    printAll("A", "B", "C")

    val array = arrayOf("A", "B", "C")
    // printAll(array) error
    printAll(*array)
}

fun printAll(vararg string: String) {
    for (str in string) {
        println(str)
    }
}
```

### 정리
- 함수의 문법은 Jav와 다르다!
- body가 하나의 값으로 간주되는 경우 block를 없앨 수도 있고, block이 없다면 반환 타입을 없앨 수도 있다.
- 함수 파라미터에 기본값을 설정해줄 수 있다.
- 함수를 호출할때 특정 파라미터를 지정해 넣어줄 수 있다.
- 가변인자에는 vararg 키워드를 사용하며, 가변인자 함수를 배열과 호출할 떄는 *를 붙여주어야 한다.

### Reference
- [인프런 | 자바 개발자를 위한 코틀린 입문(Java to Kotlin Starter Guide)](https://inf.run/1UQiA)