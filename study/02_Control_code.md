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

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/05_01.png?raw=true)

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

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/05_02.png?raw=true)


#### Kotlin에서는 if-else를 expression으로 사용할 수 있기 때문에 3항연산자가없습니다

#### if – else if – else 문도 문법이 동일합니다

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/05_03.png?raw=true

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

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/05_04.png?raw=true

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

![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/05_05.png?raw=true

### 정리 
- if / if – else / if - else if – else 모두 Java와 문법이 동일하다.
- 단 Kotlin에서는Expression으로 취급된다.
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


![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/06_01.png?raw=true
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/06_02.png?raw=true
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/06_03.png?raw=true
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/06_04.png?raw=true
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/06_05.png?raw=true
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/06_06.png?raw=true
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/06_07.png?raw=true
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/06_08.png?raw=true
![](https://github.com/dididiri1/java-to-kotlin-starter-guide/tree/main/study/images/06_09.png?raw=true

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

## 8강. 코틀린에서 함수를 다루는 방법