package com.dengsn.crucial.core.mouse;

import com.dengsn.crucial.util.Vector;

public class MousePressEvent extends MouseButtonEvent
{
  // Constructor
  public MousePressEvent(Vector position, MouseButton button)
  {
    super(position,button);
  }
}
