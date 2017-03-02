package com.dengsn.crucial.event;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class EventManager extends EventQueue
{
  // Variables
  private final List<RegisteredListener> listeners = new LinkedList<>();
  
  // Registers a listener
  public <E extends Event> void registerListener(Listener<E> listener, Class<E> eventClass, Listener.Priority priority)
  {
    this.listeners.add(new RegisteredListener(listener,eventClass,priority));
  }
  public <E extends Event> void registerListener(Listener<E> listener, Class<E> eventClass)
  {
    this.registerListener(listener,eventClass,Listener.Priority.NORMAL);
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
    // Execute events in order
    try
    {
      this.listeners.stream()
        .filter(l -> l.getEventClass().isAssignableFrom(event.getClass()))
        .sorted(Comparator.comparing(RegisteredListener::getPriority))
        .forEach(l ->
      {
        try
        {
          l.listen(event);
        }
        catch (EventException ex)
        {
          throw new EventException.Runtime(ex);
        }
      });
    }
    catch (EventException.Runtime ex)
    {
      throw new EventException("The event could not be executed: " + ex.getCause().getMessage(),ex.getCause());
    }
    
    // Unregister event
    this.unregisterEvent(event);
  }
  protected void executeEvents() throws EventException
  {
    while (!this.getQueue().isEmpty())
      this.executeEvent(this.getQueue().poll());
  }
}
