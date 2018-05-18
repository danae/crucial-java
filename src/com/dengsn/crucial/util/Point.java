package com.dengsn.crucial.util;

public class Point implements Position<Double>
{
  // Variables
  public double x;
  public double y;
  
  // Constructor
  public Point(double x, double y)
  {
    this.x = x;
    this.y = y;
  }
  public Point(Point pt)
  {
    this.x = pt.x;
    this.y = pt.y;
  }
  
  // Returns the X position
  @Override public Double getX()
  {
    return this.x;
  }
  
  // Returns the Y position
  @Override public Double getY()
  {
    return this.y;
  }
  
  // Sets the position
  @Override public Point setPosition(Double x, Double y)
  {
    this.x = x;
    this.y = y;
    return this;
  }
  
  // Returns if two objects are equal
  @Override public boolean equals(Object o)
  {
    if (o == null || !(o instanceof Point))
      return false;
    
    Point other = (Point)o;
    if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x))
      return false;
    else if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y))
      return false;
    else
      return true;
  }
  @Override public int hashCode()
  {
    int hash = 7;
    hash = 53 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
    hash = 53 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
    return hash;
  }
  
  // Manipulation
  public Point mirrorX()
  {
    return new Point(this.x * -1,this.y);
  }
  
  public Point mirrorY()
  {
    return new Point(this.x,this.y * -1);
  }
  
  public Point mirror45()
  {
    return new Point(this.y,this.x);
  }
  
  public Point invert()
  {
    return this.mirrorX().mirrorY();
  }
  
  public Point add(Point pt)
  {
    return new Point(this.x + pt.x,this.y + pt.y);
  }
  public Point add(double x, double y)
  {
    return this.add(new Point(x,y));  
  }
  public Point add(double xy)
  {
    return this.add(new Point(xy,xy));
  }
  
  public Point subtract(Point pt)
  {
    return this.add(pt.invert());
  }
  public Point subtract(double x, double y)
  {
    return this.subtract(new Point(x,y));
  }
  public Point subtract(double xy)
  {
    return this.subtract(new Point(xy,xy));
  }
  
  public Point multiply(Point pt)
  {
    return new Point(this.x * pt.x, this.y * pt.y);
  }
  public Point multiply(double x, double y)
  {
    return this.multiply(new Point(x,y));
  }
  public Point multiply(double xy)
  {
    return this.multiply(new Point(xy,xy));
  }
  
  public Point divide(Point pt)
  {
    return this.multiply(1.0/pt.x,1.0/pt.y);
  }
  public Point divide(double x, double y)
  {
    return this.divide(new Point(x,y));
  }
  public Point divide(double xy)
  {
    return this.divide(new Point(xy,xy));
  }
  
  // Converts to string
  @Override public String toString()
  {
    return String.format("%s(%f;%f)",this.getClass().getName(),this.x,this.y);
  }
  
  // Returns a point at the origin
  public static Point origin()
  {
    return new Point(0.0,0.0);
  }
  
  // Returns the distance between two points
  public static final double distance(Point p1, Point p2)
  {
    return Math.sqrt(Math.pow(p2.x-p1.x,2) + Math.pow(p2.y-p1.y,2));
  }
  
  // Returns the angle between two points
  public static final double angle(Point p1, Point p2)
  {
    double angle1 = Math.atan2(p1.y,p1.x);
    double angle2 = Math.atan2(p2.y,p2.x);
    return Math.abs(angle2 - angle1);
  }
  
  // Returns the minimum point between two points
  public static Point min(Point p1, Point p2)
  {
    return new Point(Math.min(p1.x,p2.x),Math.min(p1.y,p2.y));
  }
  
  // Returns the maximum point between two points
  public static Point max(Point p1, Point p2)
  {
    return new Point(Math.max(p1.x,p2.x),Math.max(p1.y,p2.y));
  }
}