package com.dengsn.crucial.window;

import com.dengsn.crucial.Resource;
import com.dengsn.crucial.audio.Audio;
import com.dengsn.crucial.audio.AudioException;
import com.dengsn.crucial.event.EventException;
import com.dengsn.crucial.event.EventManager;
import com.dengsn.crucial.graphics.Graphics;
import com.dengsn.crucial.window.keyboard.Keyboard;
import com.dengsn.crucial.window.mouse.Mouse;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window extends EventManager implements Resource
{
  // Variables
  private final long windowHandle;
  
  private final Graphics graphics;
  private final Audio audio;
  private final Keyboard keyboard;
  private final Mouse mouse;
  
  // Constructor
  public Window(int width, int height, String title) throws WindowException, AudioException
  {
    // Initialize GLFW
    GLFWErrorCallback.createPrint(System.err).set();
    if (!GLFW.glfwInit())
      throw new WindowException("Unable to initialize GLFW");
    
    // Create window
    GLFW.glfwDefaultWindowHints();
    GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE,GL11.GL_FALSE);
    GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE,GL11.GL_FALSE); 
    GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES,4);
    
    this.windowHandle = GLFW.glfwCreateWindow(width,height,title,NULL,NULL);
    if (this.windowHandle == NULL)
      throw new WindowException("Unable to create the GLFW window");
        
    // Center the window
    GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
    GLFW.glfwSetWindowPos(this.windowHandle,(vidmode.width() - width) / 2,(vidmode.height() - height) / 2);
    
    // Set context
    GLFW.glfwMakeContextCurrent(this.windowHandle);
    GLFW.glfwShowWindow(this.windowHandle);
    
    // Set graphics and input
    this.graphics = new Graphics(this.windowHandle);
    this.audio = new Audio();
    this.keyboard = new Keyboard(this);    
    this.mouse = new Mouse(this);
  }
  
  // Closing the resource
  @Override public void close() throws WindowException
  {
    GLFW.glfwDestroyWindow(this.windowHandle);
    GLFW.glfwTerminate();
    
    this.graphics.close();
    this.audio.close();
  }
  
  // Returns the GLFW window handle
  public final long getWindowHandle()
  {
    return this.windowHandle;
  }
  
  // Returns the graphics object
  public final Graphics getGraphics()
  {
    return this.graphics;
  }
  
  // Returns the keyboard object
  public final Keyboard getKeyboard()
  {
    return this.keyboard;
  }
  
  // Returns the mouse object
  public final Mouse getMouse()
  {
    return this.mouse;
  }
  
  // Closing management
  public final boolean isClosing()
  {
    return GLFW.glfwWindowShouldClose(this.windowHandle);
  }
  public final void setClosing()
  {
    GLFW.glfwSetWindowShouldClose(this.windowHandle,true);
  }
  
  // Title management
  public final void setTitle(String title)
  {
    GLFW.glfwSetWindowTitle(this.windowHandle,title);
  }
  
  // Handle events
  @Override protected void executeEvents() throws EventException
  {
    // Poll for input events
    GLFW.glfwPollEvents();
    
    // Execute the events
    super.executeEvents();
  }
}
