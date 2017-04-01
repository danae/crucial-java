package com.dengsn.crucial.core.mouse;

import org.lwjgl.glfw.GLFW;

public enum MouseButton
{
  // Values
  LEFT, RIGHT, MIDDLE, UNKNOWN;
  
  // Convert GLFW constant to button
  public static MouseButton ofGLFW(int glfwButton)
  {
    switch (glfwButton)
    {
      case GLFW.GLFW_MOUSE_BUTTON_LEFT:
        return LEFT;
      case GLFW.GLFW_MOUSE_BUTTON_RIGHT:
        return RIGHT;
      case GLFW.GLFW_MOUSE_BUTTON_MIDDLE:
        return MIDDLE;
      default:
        return UNKNOWN;
    }
  }
}
