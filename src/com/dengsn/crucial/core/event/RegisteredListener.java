package com.dengsn.crucial.core.event;

public class RegisteredListener<E extends Event> implements Listener<E>
{
  // Variables
  private final Listener<E> listener;
  private final Class<E> eventClass;
  private final ListenerPriority priority;
  
  // Constructor
  RegisteredListener(Listener listener, Class<E> eventClass, ListenerPriority priority)
  {
    this.listener = listener;
    this.eventClass = eventClass;
    this.priority = priority;
  }
  
  // Management
  public Listener<E> getListener()
  {
    return this.listener;
  }
  public Class<E> getEventClass()
  {
    return this.eventClass;
  }
  public ListenerPriority getPriority()
  {
    return this.priority;
  }

  // Listen for an event
  @Override public void handle(E event) throws EventException
  {
    this.listener.handle(event);
  }
}
