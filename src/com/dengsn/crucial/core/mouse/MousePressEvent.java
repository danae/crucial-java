package com.dengsn.crucial.core.mouse;

import com.dengsn.crucial.util.Point;

public class MousePressEvent extends MouseButtonEvent
{
  // Constructor
  public MousePressEvent(Point position, MouseButton button)
  {
    super(position,button);
  }
}
