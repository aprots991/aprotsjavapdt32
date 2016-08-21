package ru.stqa.pft.sandbox;

public class Dz2 {
  public static void main(String[] args) {

    Point p1 = new Point(5, 2);
    Point p2 = new Point(15, 7);
    System.out.println("Расстояние между точками: " + distance(p1, p2));
  }

  private static double distance(Point p1, Point p2) {
    double a = Math.pow((p1.x - p2.x), 2) - Math.pow((p1.y - p2.y), 2);
    return Math.sqrt(a);
  }
}
