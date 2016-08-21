package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public static double distance(Point p1, Point p2) {
    double a = 0;
    double b = 0;
    double xd = 0;
    double yd = 0;
    xd = (p1.x - p2.x);
    yd = (p1.y - p2.y);
    if
            (xd == 0) a = 0;
    else
      a = Math.pow(xd, 2);
    if
            (yd == 0) b = 0;
    else
      b = Math.pow(yd, 2);
    return Math.sqrt(a + b);
  }
}
