package com.dengsn.crucial.core.mouse;

import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.util.Viewport;
import com.dengsn.crucial.core.Window;
import org.lwjgl.glfw.GLFW;

public final class Mouse
{
  // Variables
  private final Window window;
  private final Point position;
  private MouseMode mode;
  
  // Constructor
  public Mouse(Window window)
  {    
    this.window = window;    
    this.position = Point.origin();
    this.mode = MouseMode.NORMAL;
    
    // Set callbacks for events
    GLFW.glfwSetCursorPosCallback(this.window.getHandle(),this::glfwCursorPosCallback);
    GLFW.glfwSetMouseButtonCallback(this.window.getHandle(),this::glfwMouseButtonCallback);
    GLFW.glfwSetScrollCallback(this.window.getHandle(),this::glfwScrollCallback);
  }
  
  // Mouse callback methods
  private void glfwCursorPosCallback(long windowHandle, double xpos, double ypos)
  {
    this.window.registerEvent(new MouseMoveEvent(Mouse.this.position.setPosition(xpos,ypos)));
  }
  private void glfwMouseButtonCallback(long windowHandle, int button, int action, int mods)
  {
    if (action == GLFW.GLFW_RELEASE)
      this.window.registerEvent(new MouseReleaseEvent(this.getPosition(),MouseButton.ofGLFW(button)));
    else
      this.window.registerEvent(new MousePressEvent(this.getPosition(),MouseButton.ofGLFW(button)));
  }
  private void glfwScrollCallback(long windowHandle, double xoffset, double yoffset)
  {
    this.window.registerEvent(new MouseScrollEvent(Mouse.this.getPosition(),yoffset));
  }
  
  // Get the current position of the cursor
  public Point getPosition()
  {
    return this.position;
  }
  
  // Get the position relative to a viewport
  public Point getViewportPosition(Viewport viewport)
  {
    return viewport.transform(this.position);
  }
  public Point getViewportPosition(Window window)
  {
    return window.toViewport().transform(this.position);
  }
  
  // Cursor modes
  public MouseMode getMode()
  {
    return this.mode;
  }
  public void setMode(MouseMode mode)
  {
    this.mode = mode;
    if (this.mode == MouseMode.NORMAL)
      GLFW.glfwSetInputMode(this.window.getHandle(),GLFW.GLFW_CURSOR,GLFW.GLFW_CURSOR_NORMAL);
    else if (this.mode == MouseMode.HIDDEN)
      GLFW.glfwSetInputMode(this.window.getHandle(),GLFW.GLFW_CURSOR,GLFW.GLFW_CURSOR_HIDDEN);
    else if (this.mode == MouseMode.FIXED)
      GLFW.glfwSetInputMode(this.window.getHandle(),GLFW.GLFW_CURSOR,GLFW.GLFW_CURSOR_DISABLED);
  }
}
