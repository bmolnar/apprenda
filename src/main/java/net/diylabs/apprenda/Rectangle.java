package net.diylabs.apprenda;

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

  public int left()
  {
    return this.m_left;
  }
  public int top()
  {
    return this.m_top;
  }
  public int right()
  {
    return this.m_left + this.m_width;
  }
  public int bottom()
  {
    return this.m_top + this.m_height;
  }


  public int width()
  {
    return this.m_width;
  }
  public int height()
  {
    return this.m_height;
  }




  public boolean isPoint()
  {
    return (this.width() == 0) && (this.height() == 0);
  }

  public boolean isVerticalLine()
  {
    return (this.width() == 0) && (this.height() > 0);
  }
  public boolean isHorizontalLine()
  {
    return (this.height() == 0) && (this.width() > 0);
  }
  public boolean isLine()
  {
    return (this.isVerticalLine() || this.isHorizontalLine());
  }


  public boolean isProper()
  {
    return ((this.width() > 0) && (this.height() > 0));
  }


  public Rectangle leftSide()
  {
    return Rectangle.withDiagonal(this.m_left, this.m_top, this.m_left, (this.m_top + this.m_height));
  }
  public Rectangle topSide()
  {
    return Rectangle.withDiagonal(this.m_left, this.m_top, (this.m_left + this.m_width), this.m_top);
  }
  public Rectangle rightSide()
  {
    return Rectangle.withDiagonal((this.m_left + this.m_width), this.m_top, (this.m_left + this.m_width), (this.m_top + this.m_height));
  }
  public Rectangle bottomSide()
  {
    return Rectangle.withDiagonal(this.m_left, (this.m_top + this.m_height), (this.m_left + this.m_width), (this.m_top + this.m_height));
  }

  public boolean hasSide(Rectangle rect)
  {
    return ((this.leftSide().equals(rect))
            || (this.topSide().equals(rect))
            || (this.rightSide().equals(rect))
            || (this.bottomSide().equals(rect)));
  }






  public Rectangle intersection(Rectangle rect)
  {
    return Rectangle.withDiagonal(Math.max(this.left(), rect.left()),
                                  Math.max(this.top(), rect.top()),
                                  Math.min(this.right(), rect.right()),
                                  Math.min(this.bottom(), rect.bottom()));
  }

  public boolean contains(Rectangle rect)
  {
    return ((this.left() <= rect.left())
            && (this.top() <= rect.top())
            && (this.right() >= rect.right())
            && (this.bottom() >= rect.bottom()));
  }

  public boolean isAdjacentTo(Rectangle rect)
  {
    Rectangle isect = this.intersection(rect);
    return (this.hasSide(isect) || rect.hasSide(isect));
  }


  public static Rectangle withDiagonal(int left, int top, int right, int bottom)
  {
    return new Rectangle(left, top, (right-left), (bottom-top));
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
    return 0;
  }
}
