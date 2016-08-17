package net.diylabs.apprenda;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RectangleTest
{
  @Test
  public void testLeftSide()
  {
    Rectangle r = new Rectangle(0, 0, 100, 100);
    Rectangle expected = new Rectangle(0, 0, 0, 100);
    assertEquals(r.leftSide(), expected);
  }
  @Test
  public void testTopSide()
  {
    Rectangle r = new Rectangle(0, 0, 100, 100);
    Rectangle expected = new Rectangle(0, 0, 100, 0);
    assertEquals(r.topSide(), expected);
  }
  @Test
  public void testRightSide()
  {
    Rectangle r = new Rectangle(0, 0, 100, 100);
    Rectangle expected = new Rectangle(100, 0, 0, 100);
    assertEquals(r.rightSide(), expected);
  }
  @Test
  public void testBottomSide()
  {
    Rectangle r = new Rectangle(0, 0, 100, 100);
    Rectangle expected = new Rectangle(0, 100, 100, 0);
    assertEquals(r.bottomSide(), expected);
  }


  //
  // Rectangle.intersection() tests
  //
  @Test
  public void testIntersectionWithSelfIsSelf()
  {
    Rectangle r = new Rectangle(0, 0, 100, 100);
    assertEquals(r.intersection(r), r);
  }
  @Test
  public void testPointIntersection()
  {
    Rectangle topleft = new Rectangle(0, 0, 100, 100);
    Rectangle btmright = new Rectangle(100, 100, 100, 100);
    Rectangle expected = new Rectangle(100, 100, 0, 0);
    assertEquals(topleft.intersection(btmright), expected);
    assertEquals(btmright.intersection(topleft), expected);
  }
  @Test
  public void testVerticalLineIntersection()
  {
    Rectangle left = new Rectangle(0, 0, 100, 100);
    Rectangle right = new Rectangle(100, 0, 100, 100);
    Rectangle expected = new Rectangle(100, 0, 0, 100);
    assertEquals(left.intersection(right), expected);
    assertEquals(right.intersection(left), expected);
  }
  @Test
  public void testVerticalSublineIntersection()
  {
    Rectangle left = new Rectangle(0, 0, 100, 100);
    Rectangle right = new Rectangle(100, 25, 100, 50);
    Rectangle expected = new Rectangle(100, 25, 0, 50);
    assertEquals(left.intersection(right), expected);
    assertEquals(right.intersection(left), expected);
  }
  @Test
  public void testHorizontalLineIntersection()
  {
    Rectangle above = new Rectangle(0, 0, 100, 100);
    Rectangle below = new Rectangle(0, 100, 100, 100);
    Rectangle expected = new Rectangle(0, 100, 100, 0);
    assertEquals(above.intersection(below), expected);
    assertEquals(below.intersection(above), expected);
  }
  @Test
  public void testRectangularIntersection()
  {
    Rectangle topleft = new Rectangle(0, 0, 200, 200);
    Rectangle btmright = new Rectangle(100, 100, 200, 200);
    Rectangle expected = new Rectangle(100, 100, 100, 100);
    assertEquals(topleft.intersection(btmright), expected);
    assertEquals(btmright.intersection(topleft), expected);
  }
  @Test
  public void testDisjointIntersection()
  {
    Rectangle topleft = new Rectangle(0, 0, 100, 100);
    Rectangle btmright = new Rectangle(200, 200, 100, 100);
    Rectangle expected = new Rectangle(200, 200, -100, -100);
    assertEquals(topleft.intersection(btmright), expected);
    assertEquals(btmright.intersection(topleft), expected);
  }
  @Test
  public void testDisjointIntersectionBtmleftAndTopright()
  {
    Rectangle btmleft = new Rectangle(0, 200, 100, 100);
    Rectangle topright = new Rectangle(200, 0, 100, 100);
    Rectangle expected = new Rectangle(200, 200, -100, -100);
    assertEquals(btmleft.intersection(topright), expected);
    assertEquals(topright.intersection(btmleft), expected);
  }


  //
  // Rectangle.intersects() tests
  //
  @Test
  public void testPointInersectionIntersects()
  {
    Rectangle topleft = new Rectangle(0, 0, 100, 100);
    Rectangle btmright = new Rectangle(100, 100, 100, 100);
    assertTrue(topleft.intersects(btmright));
    assertTrue(btmright.intersects(topleft));
  }
  @Test
  public void testDisjointRectanglesDoNotIntersect()
  {
    Rectangle btmleft = new Rectangle(0, 200, 100, 100);
    Rectangle topright = new Rectangle(200, 0, 100, 100);
    assertFalse(btmleft.intersects(topright));
    assertFalse(topright.intersects(btmleft));
  }


  //
  // Rectangle.contains() tests
  //
  @Test
  public void testContainsSelf()
  {
    Rectangle r = new Rectangle(0, 0, 100, 100);
    assertTrue(r.contains(r));
  }
  @Test
  public void testOutsideContainsInside()
  {
    Rectangle outside = new Rectangle(0, 0, 100, 100);
    Rectangle inside = new Rectangle(1, 1, 98, 98);
    assertTrue(outside.contains(inside));
  }
  @Test
  public void testInsideDoesNotContainOutside()
  {
    Rectangle outside = new Rectangle(0, 0, 100, 100);
    Rectangle inside = new Rectangle(1, 1, 98, 98);
    assertFalse(inside.contains(outside));
  }


  //
  // Rectangle.isAdjacentTo() tests
  //
  @Test
  public void testAdjacentUnitSqaures()
  {
    Rectangle left = new Rectangle(0, 0, 1, 1);
    Rectangle right = new Rectangle(1, 0, 1, 1);
    assertTrue(left.isAdjacentTo(right));
    assertTrue(right.isAdjacentTo(left));
  }
  @Test
  public void testAdjacentContainsSubline()
  {
    Rectangle left = new Rectangle(0, 0, 100, 100);
    Rectangle right = new Rectangle(100, 25, 100, 50);
    assertTrue(left.isAdjacentTo(right));
    assertTrue(right.isAdjacentTo(left));
  }
  @Test
  public void testSkewedSquaresAreNotAdjacent()
  {
    Rectangle left = new Rectangle(0, 0, 100, 100);
    Rectangle right = new Rectangle(100, 25, 100, 100);
    assertFalse(left.isAdjacentTo(right));
    assertFalse(right.isAdjacentTo(left));
  }
  @Test
  public void testSeparatedSquaresAreNotAdjacent()
  {
    Rectangle left = new Rectangle(0, 0, 100, 100);
    Rectangle right = new Rectangle(101, 0, 100, 100);
    assertFalse(left.isAdjacentTo(right));
    assertFalse(right.isAdjacentTo(left));
  }
}
