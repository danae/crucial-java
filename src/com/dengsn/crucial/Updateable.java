package com.dengsn.crucial;

@FunctionalInterface public interface Updateable
{
  // Updates this object
  public void update(long elapsedTime) throws GameException;
}
