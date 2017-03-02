package com.dengsn.crucial.window.keyboard;

import com.dengsn.crucial.event.Event;

public abstract class KeyboardEvent extends Event
{
  // Variables 
  private final int key;
  
  // Constructor
  public KeyboardEvent(int key)
  {
    this.key = key;
  }
  
  // Management
  public final int getKey()
  {
    return this.key;  
  }
}
