package com.dengsn.crucial.util;

import java.util.Objects;

public class Rect implements Position<Double>, Dimension<Double>
{
  // Variables
  public Point topLeft;
  public Point bottomRight;
  
  // Constructor
  public Rect(double x1, double y1, double x2, double y2)
  {
    this.topLeft = new Point(x1,y1);
    this.bottomRight = new Point(x2,y2);
  }
  public Rect(Point topLeft, Point bottomRight)
  {
    this.topLeft = new Point(topLeft);
    this.bottomRight = new Point(bottomRight);
  }
  public Rect(Rect rect)
  {
    this.topLeft = new Point(rect.topLeft);
    this.bottomRight = new Point(rect.bottomRight);
  }
  
  // Returns the X position
  @Override public Double getX()
  {
    return this.topLeft.x;
  }
  
  // Returns the Y position
  @Override public Double getY()
  {
    return this.topLeft.y;
  }
  
  // Sets the position
  @Override public Rect setPosition(Double x, Double y)
  {
    // Retreive the current size
    double width = this.getWidth();
    double height = this.getHeight();
    
    // Set the top left corner
    this.topLeft.x = x;
    this.topLeft.y = y;
    this.bottomRight.x = x + width;
    this.bottomRight.y = y + height;
    
    return this;
  }
  
  // Returns the width
  @Override public Double getWidth()
  {
    return this.bottomRight.x - this.topLeft.x;  
  }
  
  // Returns the height
  @Override public Double getHeight()
  {
    return this.bottomRight.y - this.topLeft.y;  
  }
  
  // Sets the dimension
  @Override public Rect setSize(Double width, Double height)
  {
    this.bottomRight.x = this.topLeft.x + width;
    this.bottomRight.y = this.topLeft.y + height;
    return this;
  }
  
  // Returns the center of this rect
  public Point getCenter()
  {
    return new Point((this.topLeft.x + this.bottomRight.x) / 2.0,(this.topLeft.y + this.bottomRight.y) / 2.0);  
  }
  
  // Return sif a point is contained in this rect
  public boolean contains(Point point)
  {
    return
      point.x >= this.topLeft.x && point.x <= this.bottomRight.x
      && point.y >= this.topLeft.y && point.y <= this.bottomRight.y;  
  }
  
  // Checks if two objects are equal
  @Override public boolean equals(Object o)
  {
    if (o == null || !(o instanceof Rect))
      return false;
    
    Rect other = (Rect)o;
    if (!Objects.equals(this.topLeft,other.topLeft))
      return false;
    else if (!Objects.equals(this.bottomRight,other.bottomRight))
      return false;
    else
      return true;
  }
  @Override public int hashCode()
  {
    int hash = 7;
    hash = 83 * hash + Objects.hashCode(this.topLeft);
    hash = 83 * hash + Objects.hashCode(this.bottomRight);
    return hash;
  }
  
  // Manipulation
  public Rect mirrorX()
  {
    return new Rect(this.topLeft.mirrorX(),this.bottomRight.mirrorX());  
  }
  
  public Rect mirrorY()
  {
    return new Rect(this.topLeft.mirrorY(),this.bottomRight.mirrorY());  
  }
  
  public Rect invert()
  {
    return this.mirrorX().mirrorY();
  }
  
  public Rect add(Rect rect)
  {
    return new Rect(this.topLeft.add(rect.topLeft),this.bottomRight.add(rect.bottomRight));
  }
  public Rect add(double x1, double y1, double x2, double y2)
  {
    return new Rect(this.topLeft.add(x1,y1),this.bottomRight.add(x2,y2));
  }
  public Rect add(Point pt)
  {
    return new Rect(this.topLeft.add(pt),this.bottomRight.add(pt));
  }
  public Rect add(double x, double y)
  {
    return new Rect(this.topLeft.add(x,y),this.bottomRight.add(x,y));
  }
  public Rect add(double xy)
  {
    return this.add(new Point(xy,xy));
  }
  
  public Rect subtract(Rect rect)
  {
    return new Rect(this.topLeft.subtract(rect.topLeft),this.bottomRight.subtract(rect.bottomRight));
  }
  public Rect subtract(double x1, double y1, double x2, double y2)
  {
    return new Rect(this.topLeft.subtract(x1,y1),this.bottomRight.subtract(x2,y2));
  }
  public Rect subtract(Point pt)
  {
    return new Rect(this.topLeft.subtract(pt),this.bottomRight.subtract(pt));
  }
  public Rect subtract(double x, double y)
  {
    return this.subtract(new Point(x,y));
  }
  public Rect subtract(double xy)
  {
    return this.subtract(new Point(xy,xy));
  }
  
  public Rect multiply(double x, double y)
  {
    return new Rect(this.topLeft.multiply(x,y),this.bottomRight.multiply(x,y));
  }
  public Rect multiply(double xy)
  {
    return this.multiply(xy,xy);
  }
  
  public Rect divide(double x, double y)
  {
    return this.multiply(1.0/x,1.0/y);
  }
  public Rect divide(double xy)
  {
    return this.divide(xy,xy);
  }
  
  public Rect combine(Rect rect)
  {
    return new Rect(Point.min(this.topLeft,rect.topLeft),Point.max(this.bottomRight,rect.bottomRight)); 
  }
  
  public Point map(Point position, Rect positionBounds)
  {
    Point relativePosition = position.subtract(positionBounds.topLeft);
    Point fraction = relativePosition.divide(positionBounds.getWidth(),positionBounds.getHeight());
    return this.topLeft.add(fraction.multiply(this.getWidth(),this.getHeight()));
  }
  
  // Converts to string
  @Override public String toString()
  {
    return String.format("%s(%f;%f;%f;%f)",this.getClass().getName(),this.topLeft.x,this.topLeft.x,this.bottomRight.x,this.bottomRight.y);
  }
}
