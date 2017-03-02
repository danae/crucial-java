package com.dengsn.crucial.util;

public class Point
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
  
  // Convenient set method
  public Point with(double x, double y)
  {
    this.x = x;
    this.y = y;
    return this;
  }
  
  // Checks
  @Override public boolean equals(Object o)
  {
    if (!(o instanceof Point))
      return false;
    
    Point p = (Point)o;
    return (Double.doubleToLongBits(p.x) == Double.doubleToLongBits(this.x) && Double.doubleToLongBits(p.y) == Double.doubleToLongBits(this.y));
  }
  @Override public int hashCode()
  {
    int hash = 7;
    hash = 53 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
    hash = 53 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
    return hash;
  }
  public boolean isEmpty()
  {
    return (this.x == 0 && this.y == 0);  
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
  
  // To string
  @Override public String toString()
  {
    return String.format("%s(%f;%f)",this.getClass().getSimpleName(),this.x,this.y);
  }
  
  // Static methods
  public static Point origin()
  {
    return new Point(0.0,0.0);
  }
  public static final double distance(Point p1, Point p2)
  {
    return Math.sqrt(Math.pow(p2.x-p1.x,2) + Math.pow(p2.y-p1.y,2));
  }
  public static final double angle(Point p1, Point p2)
  {
    double angle1 = Math.atan2(p1.y,p1.x);
    double angle2 = Math.atan2(p2.y,p2.x);
    
    return Math.abs(angle2 - angle1);
  }
  public static Point min(Point p1, Point p2)
  {
    return new Point(Math.min(p1.x,p2.x),Math.min(p1.y,p2.y));
  }
  public static Point max(Point p1, Point p2)
  {
    return new Point(Math.max(p1.x,p2.x),Math.max(p1.y,p2.y));
  }
}