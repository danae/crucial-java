package com.dengsn.crucial.window.mouse;

import com.dengsn.crucial.window.mouse.MouseButton;
import com.dengsn.crucial.util.Point;

public class MouseReleaseEvent extends MouseButtonEvent
{
  // Constructor
  public MouseReleaseEvent(Point position, MouseButton button)
  {
    super(position,button);
  }
}
