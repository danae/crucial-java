package com.dengsn.crucial.event;

public class RegisteredListener<E extends Event> implements Listener<E>
{
  // Variables
  private final Listener<E> listener;
  private final Class<E> eventClass;
  private final Listener.Priority priority;
  
  // Constructor
  RegisteredListener(Listener listener, Class<E> eventClass, Listener.Priority priority)
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
  public Listener.Priority getPriority()
  {
    return this.priority;
  }

  // Listen for an event
  @Override public void listen(E event) throws EventException
  {
    this.listener.listen(event);
  }
}
