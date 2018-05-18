package com.dengsn.crucial.core.event;

@FunctionalInterface
public interface Listener<E extends Event>
{
  // Handles an event
  public void handle(E event) throws EventException;
}
