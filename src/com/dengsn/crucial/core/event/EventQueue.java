package com.dengsn.crucial.core.event;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public abstract class EventQueue implements Iterable<Event>
{
  // Variables
  private final Queue<Event> queue = new LinkedList<>();
  
  // Returns the event queue
  protected Queue<Event> getQueue()
  {
    return this.queue;
  }
  
  // Returns the iterator of the event queue
  @Override public Iterator<Event> iterator()
  {
    return this.queue.iterator();
  }
  
  // Registers an event
  public void registerEvent(Event event)
  {
    this.queue.add(event);
  }
  
  // Unregisters an event
  public void unregisterEvent(Event event)
  {
    this.queue.remove(event);
  }
}
