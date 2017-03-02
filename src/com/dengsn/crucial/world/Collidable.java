package com.dengsn.crucial.world;

public interface Collidable<T extends Collidable>
{
  public boolean collide(T target);
}
