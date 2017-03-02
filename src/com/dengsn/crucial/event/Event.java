package com.dengsn.crucial.event;

import java.util.Date;

public abstract class Event 
{
  // Variables
  private transient final Date timestamp;
  private transient boolean cancelled;
  
  // Constructor
  public Event()
  {
    this.timestamp = new Date();
    this.cancelled = false;  
  }
  
  // Management
  public Date getTimestamp()
  {
    return this.timestamp;
  }
  public boolean isCancelled()
  {
    return this.cancelled;  
  }
  public void setCancelled(boolean cancelled)
  {
    this.cancelled = cancelled;  
  }
}
