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

## 6강. 코틀린에서 반복문을 다루는 방법















## 7강. 코틀린에서 예외를 다루는 방법

## 8강. 코틀린에서 함수를 다루는 방법