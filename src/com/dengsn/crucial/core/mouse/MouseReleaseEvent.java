package com.dengsn.crucial.core.mouse;

import com.dengsn.crucial.util.Vector;

public class MouseReleaseEvent extends MouseButtonEvent
{
  // Constructor
  public MouseReleaseEvent(Vector position, MouseButton button)
  {
    super(position,button);
  }
}
