package com.dengsn.crucial.window.mouse;

import com.dengsn.crucial.window.mouse.MouseButton;
import com.dengsn.crucial.util.Point;

public class MousePressEvent extends MouseButtonEvent
{
  // Constructor
  public MousePressEvent(Point position, MouseButton button)
  {
    super(position,button);
  }
}
