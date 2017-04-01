package com.dengsn.crucial.util;

import java.util.Locale;

public class Vector
{
  // Variables
  public double x;
  public double y;
  
  // Constructor
  public Vector(double x, double y)
  {
    this.x = x;
    this.y = y;
  }
  
  // Returns the x component of this vector
  public double getX()
  {
    return this.x;
  }
  
  // Returns the y component of this vector
  public double getY()
  {
    return this.y;
  }
  
  // Returns the length of this vector
  public double getLength()
  {
    return Math.sqrt(Math.pow(this.x,2) + Math.pow(this.y,2));
  }
  
  // Returns the direction of this vector
  public double getDirection()
  {
    return Math.atan2(this.y,this.x);
  }
  
  // Inverts the direction of this vector
  public Vector invert()
  {
    return new Vector(-this.x,-this.y);
  }
  
  // Adds a vector to this vector
  public Vector add(Vector v)
  {
    return new Vector(this.x + v.x,this.y + v.y);
  }
  public Vector add(double x, double y)
  {
    return new Vector(this.x + x, this.y + y);
  }
  
  // Subtracts a vector from this vector
  public Vector subtract(Vector v)
  {
    return new Vector(this.x - v.x,this.y - v.y);
  }
  public Vector subtract(double x, double y)
  {
    return new Vector(this.x - x, this.y - y);
  }
  
  // Multiplies this vector with a scalar value
  public Vector multiply(double scalar)
  {
    return new Vector(this.x * scalar, this.y * scalar);
  }
  
  // Divides this vector with a scalar value
  public Vector divide(double scalar)
  {
    return new Vector(this.x / scalar, this.y / scalar);
  }
  
  // Calculates the dot product of this vector and another one
  public double product(Vector v)
  {
    return this.x * v.x + this.y * v.y;
  }
  
  // Returns if another vector is equal to this vector
  @Override public boolean equals(Object o)
  {
    if (o == null || !o.getClass().equals(this.getClass()))
      return false;
    
    Vector vector = (Vector)o;
    if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(vector.x))
      return false;
    else if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(vector.y))
      return false;
    else
      return true;
  }
  
  // Convert to string
  @Override public String toString()
  {
    return String.format(Locale.ENGLISH,"(%f,%f)",this.x,this.y);
  }
  
  // Returns an origin vector
  public static Vector origin()
  {
    return new Vector(0.0,0.0);
  }
  
  // Returns the disctance between two vectors
  public static double distance(Vector a, Vector b)
  {
    return b.getLength() - a.getLength();
  }
  
  // Returns the angle between to vectors
  public static double angle(Vector a, Vector b)
  {
    return b.getDirection() - a.getDirection();
  }
}
