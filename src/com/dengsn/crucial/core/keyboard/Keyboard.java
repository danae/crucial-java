package com.dengsn.crucial.core.keyboard;

import com.dengsn.crucial.core.Window;
import org.lwjgl.glfw.GLFW;

public final class Keyboard
{
  // Variables
  private final Window window;
  
  // Constructor
  public Keyboard(Window window)
  {
    this.window = window;
    
    // Set callbacks for events
    GLFW.glfwSetCharCallback(this.window.getHandle(),this::glfwCharCallback);
    GLFW.glfwSetKeyCallback(this.window.getHandle(),this::glfwKeyCallback);
  }
  
  // Keyboard callback methods
  private void glfwCharCallback(long windowHandle, int codepoint)
  {
   this.window.registerEvent(new KeyboardCharacterEvent(codepoint));
  }
  private void glfwKeyCallback(long windowHandle, int key, int scancode, int action, int mods)
  {
    if (action == GLFW.GLFW_RELEASE)
      this.window.registerEvent(new KeyboardReleaseEvent(key));
    else if (action == GLFW.GLFW_PRESS)
      this.window.registerEvent(new KeyboardPressEvent(key));
  }
}
