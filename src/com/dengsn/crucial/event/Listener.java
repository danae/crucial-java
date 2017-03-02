package com.dengsn.crucial.event;

@FunctionalInterface
public interface Listener<E extends Event>
{
  // Priority enum
  public static enum Priority {MONITOR, HIGHEST, HIGH, NORMAL, LOW, LOWEST};
  
  // Listen for an event
  public void listen(E event) throws EventException;
}
