package com.dengsn.crucial.core.mouse;

import com.dengsn.crucial.core.event.Event;
import com.dengsn.crucial.util.Point;

public abstract class MouseEvent extends Event
{
  // Variables
  private final Point position;
  
  // Constructor
  public MouseEvent(Point position)
  {
    this.position = new Point(position);
  }
  
  // Management
  public final Point getPosition()
  {
    return this.position;  
  }
}
