package com.dengsn.crucial.core;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.Updateable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public class WindowController implements Drawable, Updateable, AutoCloseable
{
  // Variables
  private final List<Window> windows;
  private long lastLoop;
  private long currentLoop;
  
  // Constructor
  public WindowController() throws WindowException
  {
    this.windows = new CopyOnWriteArrayList<>();
    this.lastLoop = this.getMillis();
    this.currentLoop = this.getMillis();
    
    // Initialize GLFW
    GLFWErrorCallback.createPrint(System.err).set();
    if (!GLFW.glfwInit())
      throw new WindowException("Unable to initialize GLFW");
  }
  
  // Closes the resource
  @Override public void close() throws WindowException
  {
    // Close all windows in the list
    for (Window w : this.windows)
      w.close();
    
    // Terminate GLFW
    GLFW.glfwTerminate();
  }
  
  // Adds a window
  public WindowController addWindow(Window w)
  {
    this.windows.add(w);
    return this;
  }
  
  // Removes a window
  public WindowController removeWindow(Window w)
  {
    this.windows.remove(w);
    return this;
  }
  
  // Returns if all windows are closing
  public final boolean isClosing()
  {
    return this.windows.stream()
      .allMatch(w -> w.isClosing());
  }
  
  // Returns the current millisecond since the loop is running
  public final long getMillis()
  {
    return System.currentTimeMillis();
  }
  
  // Returns the current frame rate
  public final double getFrameRate()
  {
    return 1000.0 / (this.currentLoop - this.lastLoop);
  }
  
  // Executes the loop for drawing and updating
  public final void run() throws GameException
  {
    // Main loop
    while (!this.isClosing())
    {
      // Set the loop
      this.lastLoop = this.currentLoop;
      this.currentLoop = this.getMillis();
      
      // Update game logic
      long elapsedTime = this.currentLoop - this.lastLoop;
      this.update(elapsedTime);

      // Draw
      this.draw();
    }
    
    // Close
    this.close();
  }

  // Draws all windows
  @Override public final void draw() throws GameException
  {
    // Iterate over windows
    for (Window w : this.windows)
    {
      // Set the current draw context to the window
      GLFW.glfwMakeContextCurrent(w.getHandle());
      
      // Draw the window
      w.clearBuffers();
      w.draw();
      w.swapBuffers();
    }
  }
  
  // Updates all windows
  @Override public final void update(long elapsedTime) throws GameException
  {
    // Iterate over windows
    for (Window w : this.windows)
    {
      // Update the window
      w.update(elapsedTime);
      
      // Execute the events in the window
      w.executeEvents();
      
      // Check if the window should close
      if (w.isClosing())
        this.removeWindow(w);
    }
  }
}
