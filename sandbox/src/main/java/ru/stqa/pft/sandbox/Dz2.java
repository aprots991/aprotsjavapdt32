package ru.stqa.pft.sandbox;

public class Dz2 {
  public static void main(String[] args) {

    Point p1 = new Point(2, 3);
    Point p2 = new Point(2, 7);
    System.out.println("Расстояние между точками p1 и p2: " + Point.distance(p1, p2));

  }
}
