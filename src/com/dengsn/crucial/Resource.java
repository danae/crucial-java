package com.dengsn.crucial;

public interface Resource extends AutoCloseable
{
  // Closing the resource
  @Override public void close() throws GameException;
}
