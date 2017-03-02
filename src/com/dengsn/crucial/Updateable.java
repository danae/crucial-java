package com.dengsn.crucial;

@FunctionalInterface public interface Updateable
{
  // Update methods
  public void update(long elapsedTime) throws GameException;
}
