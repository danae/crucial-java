package com.dengsn.crucial.window.keyboard;

import com.dengsn.crucial.window.Window;
import org.lwjgl.glfw.GLFW;

public final class Keyboard
{
  // Variables
  private final Window window;
  
  // Constructor
  public Keyboard(Window window)
  {
    this.window = window;
    
    // Set GLFW callbacks
    GLFW.glfwSetCharCallback(this.window.getWindowHandle(),this::glfwChar);
    GLFW.glfwSetKeyCallback(this.window.getWindowHandle(),this::glfwKey);
  }
  
  // Callbacks
  private void glfwChar(long windowHandle, int codepoint)
  {
   this.window.registerEvent(new CharacterEvent(codepoint));
  }
  private void glfwKey(long windowHandle, int key, int scancode, int action, int mods)
  {
    if (action == GLFW.GLFW_RELEASE)
      this.window.registerEvent(new KeyboardReleaseEvent(key));
    else
      this.window.registerEvent(new KeyboardPressEvent(key));
  }
}
