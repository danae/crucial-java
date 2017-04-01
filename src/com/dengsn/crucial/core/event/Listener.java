package com.dengsn.crucial.core.event;

@FunctionalInterface
public interface Listener<E extends Event>
{
  // Handle an incoming event
  public void handle(E event) throws EventException;
}
