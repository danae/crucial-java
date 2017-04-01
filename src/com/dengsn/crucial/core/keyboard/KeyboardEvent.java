package com.dengsn.crucial.core.keyboard;

import com.dengsn.crucial.core.event.Event;

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
