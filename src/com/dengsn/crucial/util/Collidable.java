package com.dengsn.crucial.util;

public interface Collidable<T extends Collidable>
{
  // Returns if two objects collide
  public boolean collidesWith(T target);
}
