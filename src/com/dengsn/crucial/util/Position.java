package com.dengsn.crucial.util;

public interface Position<T extends Number>
{
  // Returns the X position of this object
  public T getX();
  
  // Returns the Y position of this object
  public T getY();
  
  // Sets the position
  public Position<T> setPosition(T x, T y);
}
