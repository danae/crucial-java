package com.dengsn.crucial.util;

public interface Dimension<T extends Number>
{
  // Returns the width of this dimension
  public T getWidth();
  
  // Returns the height of this dimension
  public T getHeight();
  
  // Sets the dimension
  public Dimension<T> setSize(T width, T height);
}
