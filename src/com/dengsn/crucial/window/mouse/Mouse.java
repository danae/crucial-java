package com.dengsn.crucial.window.mouse;

import com.dengsn.crucial.window.Window;
import com.dengsn.crucial.graphics.Graphics;
import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.util.Viewport;
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

    // Set GLFW callbacks
    GLFW.glfwSetCursorPosCallback(this.window.getWindowHandle(),this::glfwCursorPos);
    GLFW.glfwSetCursorEnterCallback(this.window.getWindowHandle(),this::glfwCursorEnter);
    GLFW.glfwSetMouseButtonCallback(this.window.getWindowHandle(),this::glfwMouseButton);
    GLFW.glfwSetScrollCallback(this.window.getWindowHandle(),this::glfwScroll);
    
    this.position = Point.origin();
    this.mode = MouseMode.NORMAL;
  }
  
  // Get the current position of the cursor
  public Point getPosition()
  {
    return this.position;
  }
  
  // Get the position relative to a viewport
  public Point getViewportPosition(Viewport v)
  {
    return v.transform(this.position);
  }
  public Point getViewportPosition(Graphics graphics)
  {
    return graphics.toViewport().transform(this.position);
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
      GLFW.glfwSetInputMode(this.window.getWindowHandle(),GLFW.GLFW_CURSOR,GLFW.GLFW_CURSOR_NORMAL);
    else if (this.mode == MouseMode.HIDDEN)
      GLFW.glfwSetInputMode(this.window.getWindowHandle(),GLFW.GLFW_CURSOR,GLFW.GLFW_CURSOR_HIDDEN);
    else if (this.mode == MouseMode.FIXED)
      GLFW.glfwSetInputMode(this.window.getWindowHandle(),GLFW.GLFW_CURSOR,GLFW.GLFW_CURSOR_DISABLED);
  }
  
  // Callbacks
  private void glfwCursorPos(long windowHandle, double xpos, double ypos)
  {
    this.window.registerEvent(new MouseMoveEvent(Mouse.this.position.with(xpos,ypos)));
  }
  private void glfwCursorEnter(long windowHandle, boolean entered)
  {
    if (entered)
      this.window.registerEvent(new CursorEnterEvent());
    else
      this.window.registerEvent(new CursorLeaveEvent());
  }
  private void glfwMouseButton(long windowHandle, int button, int action, int mods)
  {
    if (action == GLFW.GLFW_RELEASE)
      this.window.registerEvent(new MouseReleaseEvent(this.getPosition(),MouseButton.ofGLFW(button)));
    else
      this.window.registerEvent(new MousePressEvent(this.getPosition(),MouseButton.ofGLFW(button)));
  }
  private void glfwScroll(long windowHandle, double xoffset, double yoffset)
  {
    this.window.registerEvent(new MouseScrollEvent(Mouse.this.getPosition(),yoffset));
  }
}
