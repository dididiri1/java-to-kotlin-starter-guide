package com.lannstark.lec12;

public class Lec12Main {

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
}

