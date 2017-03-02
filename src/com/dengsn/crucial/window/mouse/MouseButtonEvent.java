package com.dengsn.crucial.window.mouse;

import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.window.mouse.MouseButton;

public abstract class MouseButtonEvent extends MouseEvent
{
  // Variables
  private final MouseButton button;
  
  // Constructor
  public MouseButtonEvent(Point position, MouseButton button)
  {
    super(position);
    this.button = button;
  }
  
  // Management
  public final MouseButton getButton()
  {
    return this.button;  
  }
}
