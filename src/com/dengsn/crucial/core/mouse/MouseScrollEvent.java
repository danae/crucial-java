package com.dengsn.crucial.core.mouse;

import com.dengsn.crucial.util.Vector;

public class MouseScrollEvent extends MouseMoveEvent
{
  // Variables
  private final double offset;
  
  // Constructor
  public MouseScrollEvent(Vector position, double offset)
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
