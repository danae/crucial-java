package com.dengsn.crucial.core.mouse;

import com.dengsn.crucial.core.event.Event;
import com.dengsn.crucial.util.Vector;

public abstract class MouseEvent extends Event
{
  // Variables
  private final Vector position;
  
  // Constructor
  public MouseEvent(Vector position)
  {
    this.position = position;
  }
  
  // Management
  public final Vector getPosition()
  {
    return this.position;  
  }
}
