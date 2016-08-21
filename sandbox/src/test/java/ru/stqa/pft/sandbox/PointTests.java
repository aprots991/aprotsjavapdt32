package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void distanceTests() {
    Point p1 = new Point(2, 3.5);
    Point p2 = new Point(2, 5.5);
    Assert.assertEquals(Point.distance(p1, p2), 2.0);
    Assert.assertEquals((Point.distance(p1, p2) > 0), true, " > 0");
    System.out.println("Расстояние между точками p1 и p2: " + Point.distance(p1, p2));
  }
}
