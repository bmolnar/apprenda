package net.diylabs.apprenda;

/**
 * Representation of axis-aligned 2D rectangular region
 *
 * This class also allows for the following degenerate cases:
 *   (width == 0) && (height > 0) - vertical line
 *   (width > 0) && (height == 0) - horizontal line
 *   (width == 0) && (height == 0) - single point
 *   (width < 0) || (height < 0) - empty
 */
public class Rectangle
{
  protected final int m_left;
  protected final int m_top;
  protected final int m_width;
  protected final int m_height;

  public Rectangle(int left, int top, int width, int height)
  {
    this.m_left = left;
    this.m_top = top;
    this.m_width = width;
    this.m_height = height;
  }

  @Override
  public boolean equals(Object o)
  {
    if (o instanceof Rectangle)
    {
      Rectangle r = (Rectangle) o;
      return ((r.m_left == this.m_left) && (r.m_top == this.m_top) && (r.m_width == this.m_width) && (r.m_height == this.m_height));
    }
    else
    {
      return false;
    }
  }

  @Override
  public int hashCode()
  {
    final int prime = 17;
    int hash = 1;
    hash = prime * hash + this.m_left;
    hash = prime * hash + this.m_top;
    hash = prime * hash + this.m_width;
    hash = prime * hash + this.m_height;
    return hash;
  }

  /**
   * Returns new rectangle with (directed) diagonal extending from the
   * point ({@code left}, {@code top}) to ({@code right}, {@code bottom})
   */
  public static Rectangle withDiagonal(int left, int top, int right, int bottom)
  {
    return new Rectangle(left, top, (right - left), (bottom - top));
  }

  /**
   * Returns x-coordinate of the left side
   */
  public int left()
  {
    return this.m_left;
  }
  /**
   * Returns y-coordinate of the top side
   */
  public int top()
  {
    return this.m_top;
  }
  /**
   * Returns x-coordinate of the right side
   */
  public int right()
  {
    return this.m_left + this.m_width;
  }
  /**
   * Returns y-coordinate of the bottom side
   */
  public int bottom()
  {
    return this.m_top + this.m_height;
  }


  /**
   * Returns (signed) width of rectangle.
   */
  public int width()
  {
    return this.m_width;
  }
  /**
   * Returns (signed) height of rectangle.
   */
  public int height()
  {
    return this.m_height;
  }


  /**
   * Returns true if the rectangle contains no points.
   */
  public boolean isEmpty()
  {
    return ((this.width() < 0) || (this.height() < 0));
  }

  /**
   * Returns true if the rectangle is degenerate and has dimension
   * zero (i.e. a point).
   */
  public boolean isPoint()
  {
    return (this.width() == 0) && (this.height() == 0);
  }

  /**
   * Returns true if the rectangle is degenerate, has dimension
   * one (i.e. a line) as is parallel to the vertical axis.
   */
  public boolean isVerticalLine()
  {
    return (this.width() == 0) && (this.height() > 0);
  }
  /**
   * Returns true if the rectangle is degenerate, has dimension
   * one (i.e. a line) as is parallel to the horizontal axis.
   */
  public boolean isHorizontalLine()
  {
    return (this.height() == 0) && (this.width() > 0);
  }
  /**
   * Returns true if the rectangle is degenerate and has dimension
   * one (i.e. a line).
   */
  public boolean isLine()
  {
    return (this.isVerticalLine() || this.isHorizontalLine());
  }

  /**
   * Returns true if the rectangle is non-degenerate and has a positive
   * area.
   */
  public boolean isRect()
  {
    return ((this.width() > 0) && (this.height() > 0));
  }


  /**
   * Returns a degenerate 1-D rectangle (line) representing the
   * left side of this rectangle
   */
  public Rectangle leftSide()
  {
    return Rectangle.withDiagonal(this.left(), this.top(), this.left(), this.bottom());
  }
  /**
   * Returns a degenerate 1-D rectangle (line) representing the
   * top side of this rectangle
   */
  public Rectangle topSide()
  {
    return Rectangle.withDiagonal(this.left(), this.top(), this.right(), this.top());
  }
  /**
   * Returns a degenerate 1-D rectangle (line) representing the
   * right side of this rectangle
   */
  public Rectangle rightSide()
  {
    return Rectangle.withDiagonal(this.right(), this.top(), this.right(), this.bottom());
  }
  /**
   * Returns a degenerate 1-D rectangle (line) representing the
   * bottom side of this rectangle
   */
  public Rectangle bottomSide()
  {
    return Rectangle.withDiagonal(this.left(), this.bottom(), this.right(), this.bottom());
  }
  /**
   * Returns true if {@code rect} is a degenerate 1-D rectangle
   * (line) coinciding with a side of this rectangle
   */
  public boolean hasSide(Rectangle rect)
  {
    return ((this.leftSide().equals(rect))
            || (this.topSide().equals(rect))
            || (this.rightSide().equals(rect))
            || (this.bottomSide().equals(rect)));
  }


  /**
   * Returns rectangle object representing the intersection
   * of this rectangle and {@code rect}. The resulting rectangle
   * may be degenerate. If the intersection is empty, calling
   * {@code isEmpty()} on the resulting object will return true.
   */
  public Rectangle intersection(Rectangle rect)
  {
    return Rectangle.withDiagonal(Math.max(this.left(), rect.left()),
                                  Math.max(this.top(), rect.top()),
                                  Math.min(this.right(), rect.right()),
                                  Math.min(this.bottom(), rect.bottom()));
  }

  /**
   * Returns true if the intersection of this rectangle and
   * {@code rect} is non-empty (i.e. is either a point, line
   * or rectangle).
   */
  public boolean intersects(Rectangle rect)
  {
    return !this.intersection(rect).isEmpty();
  }

  /**
   * Returns true if {@code rect} is contained entirely within
   * this rectangle.
   */
  public boolean contains(Rectangle rect)
  {
    return ((this.left() <= rect.left())
            && (this.top() <= rect.top())
            && (this.right() >= rect.right())
            && (this.bottom() >= rect.bottom()));
  }

  /**
   * Returns true if {@code rect} is adjacent to this rectangle.
   * Two rectangles are adjacent if they intersect only in a line
   * and the intersection coincides with one of the sides of either
   * rectangle.
   */
  public boolean isAdjacentTo(Rectangle rect)
  {
    Rectangle isect = this.intersection(rect);
    return (this.hasSide(isect) || rect.hasSide(isect));
  }
}
