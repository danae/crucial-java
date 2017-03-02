package com.dengsn.crucial.window.mouse;

import com.dengsn.crucial.util.Point;

public class MouseScrollEvent extends MouseMoveEvent
{
  // Variables
  private final double offset;
  
  // Constructor
  public MouseScrollEvent(Point position, double offset)
  {
    super(position);
    this.offset = offset;
  }
  
  // Management
  public final double getOffset()
  {
    return this.offset;  
  }
  public final double getDirection()
  {
    return Math.signum(this.offset);
  }
}
