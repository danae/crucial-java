package com.dengsn.crucial.core.mouse;

import com.dengsn.crucial.util.Point;

public class MouseReleaseEvent extends MouseButtonEvent
{
  // Constructor
  public MouseReleaseEvent(Point position, MouseButton button)
  {
    super(position,button);
  }
}
