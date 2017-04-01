package com.dengsn.crucial.core.event;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class EventController extends EventQueue
{
  // Variables
  private final List<RegisteredListener> listeners = new LinkedList<>();
  
  // Registers a listener
  public <E extends Event> void registerListener(Listener<E> listener, Class<E> eventClass, ListenerPriority priority)
  {
    this.listeners.add(new RegisteredListener(listener,eventClass,priority));
  }
  public <E extends Event> void registerListener(Listener<E> listener, Class<E> eventClass)
  {
    this.listeners.add(new RegisteredListener(listener,eventClass,ListenerPriority.NORMAL));
  }
  
  // Unregisters a listener
  public <E extends Event> void unregisterListener(Listener<E> listener)
  {
    this.listeners.stream()
      .filter(l -> l.getListener().equals(listener))
      .collect(Collectors.toList())
      .forEach(l -> this.listeners.remove(l));
  }
  
  // Execute events
  protected void executeEvent(Event event) throws EventException
  {
    // Get all events that can handle this event
    Iterator<RegisteredListener> it = this.listeners.stream()
      .filter(l -> l.getEventClass().isAssignableFrom(event.getClass()))
      .sorted(Comparator.comparing(RegisteredListener::getPriority))
      .iterator();
    
    // Execute events in order
    while (it.hasNext())
      it.next().handle(event);
    
    // Unregister event
    this.unregisterEvent(event);
  }
  protected void executeEvents() throws EventException
  {
    while (!this.getQueue().isEmpty())
      this.executeEvent(this.getQueue().poll());
  }
}
