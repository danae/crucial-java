package com.dengsn.crucial.core.mouse;

import com.dengsn.crucial.core.Window;
import com.dengsn.crucial.util.Vector;
import org.lwjgl.glfw.GLFW;

public final class Mouse
{
  // Variables
  private final Window window;
  private Vector position;
  private MouseMode mode;
  
  // Constructor
  public Mouse(Window window)
  {    
    this.window = window;    
    this.position = Vector.origin();
    this.mode = MouseMode.NORMAL;
    
    // Set callbacks for events
    GLFW.glfwSetCursorPosCallback(this.window.getHandle(),this::glfwCursorPosCallback);
    GLFW.glfwSetMouseButtonCallback(this.window.getHandle(),this::glfwMouseButtonCallback);
    GLFW.glfwSetScrollCallback(this.window.getHandle(),this::glfwScrollCallback);
  }
  
  // Mouse callback methods
  private void glfwCursorPosCallback(long windowHandle, double xpos, double ypos)
  {
    this.position = new Vector(xpos,ypos);
    this.window.registerEvent(new MouseMoveEvent(Mouse.this.position));
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
  public Vector getPosition()
  {
    return this.position;
  }
  
  // Get the position relative to a viewport
  /*public Vector getViewportPosition(Viewport viewport)
  {
    return viewport.transform(this.position);
  }
  public Vector getViewportPosition(Window window)
  {
    return window.toCamera().transform(this.position);
  }*/
  
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
