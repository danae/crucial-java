package com.dengsn.crucial.window.mouse;

import com.dengsn.crucial.event.Event;
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
