package com.lannstark.lec04;

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
