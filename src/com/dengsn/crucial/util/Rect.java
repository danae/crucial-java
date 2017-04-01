package com.dengsn.crucial.util;

import java.util.Locale;

public class Rect implements Collidable<Rect>
{
  // Variables
  public double x1;
  public double y1;
  public double x2;
  public double y2;
  
  // Constructor
  public Rect(double x1, double y1, double x2, double y2)
  {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  // Object methods
  @Override public boolean equals(Object o)
  {
    if (o == null ||!o.getClass().equals(this.getClass()))
      return false;
    
    Rect rect = (Rect)o;
    if (Double.doubleToLongBits(this.x1) != Double.doubleToLongBits(rect.x1))
      return false;
    else if (Double.doubleToLongBits(this.y1) != Double.doubleToLongBits(rect.y1))
      return false;
    else if (Double.doubleToLongBits(this.x2) != Double.doubleToLongBits(rect.x2))
      return false;
    else if (Double.doubleToLongBits(this.y2) != Double.doubleToLongBits(rect.y2))
      return false;
    else
      return true;
  }
  
  // Returns the width of this rect
  public double getWidth()
  {
    return Math.abs(this.x2 - this.x1);  
  }
  
  // Returns the height of this rect
  public double getHeight()
  {
    return Math.abs(this.y2 - this.y1);  
  }
  
  // Returns the aspect ratio of this rect
  public double getRatio()
  {
    return this.getWidth() / this.getHeight();
  }
  
  // Returns the center of this rect
  public Vector getCenter()
  {
    return new Vector((this.x1 + this.x2) / 2,(this.y1 + this.y2) / 2);  
  }
  
  // Returns if this rectangle contains a vector
  public boolean contains(Vector v)
  {
    return this.x1 <= v.x && v.x <= this.x2 && this.y1 <= v.y && v.y <= this.y2;  
  }
  
  // Returns if this rect collides with another rect
  @Override public boolean collidesWith(Rect r)
  {
    return (r.x1 >= this.x1 && r.x1 <= this.x2) || (r.x2 >= this.x1 && r.x2 <= this.x2) || (r.y1 >= this.y1 && r.y1 <= this.y2) || (r.y2 >= this.y1 && r.y2 <= this.y2);  
  }
  
  // Convert to string
  @Override public String toString()
  {
    return String.format(Locale.ENGLISH,"(%f,%f,%f,%f)",this.x1,this.y1,this.x2,this.y2);
  }
}
