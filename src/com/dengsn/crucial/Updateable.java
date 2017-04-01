package com.dengsn.crucial;

@FunctionalInterface public interface Updateable
{
  // Updates this updateable
  public void update(long elapsedTime) throws GameException;
}
