package com.dengsn.crucial.core.event;

public abstract class Event 
{
  // Variables
  private boolean cancelled;
  
  // Constructor
  public Event()
  {
    this.cancelled = false;  
  }
  
  // Returns if this event is cancelled
  public boolean isCancelled()
  {
    return this.cancelled;  
  }
  
  // Sets if this event is cancelled
  public void setCancelled(boolean cancelled)
  {
    this.cancelled = cancelled;  
  }
}
