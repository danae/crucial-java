package com.dengsn.crucial.core;

import com.dengsn.crucial.core.event.Event;

public abstract class WindowEvent extends Event
{
  // Variables 
  private final Window window;
  
  // Constructor
  public WindowEvent(Window window)
  {
    this.window = window;
  }
  
  // Returns the window associated with this event
  public final Window getWindow()
  {
    return this.window;  
  }
  
  // Convert to string
  @Override public String toString()
  {
    return super.toString() + " (window: " + this.window.toString() + ")";
  }
}
