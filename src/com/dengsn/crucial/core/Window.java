package com.dengsn.crucial.core;

import com.dengsn.crucial.core.mouse.Mouse;
import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.Updateable;
import com.dengsn.crucial.core.event.EventException;
import com.dengsn.crucial.core.event.EventController;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.core.keyboard.Keyboard;
import com.dengsn.crucial.util.Camera;
import com.dengsn.crucial.util.Vector;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.system.MemoryUtil.NULL;

public abstract class Window extends EventController implements Drawable, Updateable, AutoCloseable
{
  // Variables
  private final long handle;
  private final Keyboard keyboard;
  private final Mouse mouse;
  private Color background;
  
  // Constructor
  public Window(int width, int height) throws WindowException
  {
    // Create window
    GLFW.glfwDefaultWindowHints();
    GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE,GL11.GL_FALSE);
    GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE,GL11.GL_TRUE); 
    GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES,4);
    
    // Set the window handle
    this.handle = GLFW.glfwCreateWindow(width,height,"Window",NULL,NULL);
    if (this.handle == NULL)
      throw new WindowException("Unable to create the GLFW window");
        
    // Center the window
    GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
    GLFW.glfwSetWindowPos(this.handle,(vidmode.width() - width) / 2,(vidmode.height() - height) / 2);
    
    // Initialize the OpenGL context
    GLFW.glfwMakeContextCurrent(this.handle);
    GLFW.glfwSwapInterval(1);
    this.initializeGL();
    
    // Set callbacks for events
    GLFW.glfwSetWindowCloseCallback(this.handle,this::glfwWindowCloseCallback);
    
    // Set input callbacks and other variables
    this.keyboard = new Keyboard(this);    
    this.mouse = new Mouse(this);
    this.background = Color.BLACK;
    
    // Show the window
    GLFW.glfwShowWindow(this.handle);
  }
  
  // Initializes OpenGL
  private void initializeGL() throws WindowException
  {
    GL.createCapabilities();
    
    GL11.glShadeModel(GL11.GL_SMOOTH);        
    GL11.glDisable(GL11.GL_DEPTH_TEST);
    GL11.glDisable(GL11.GL_LIGHTING);
    
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);

    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glOrtho(-0.5 * this.getWidth(),0.5 * this.getWidth(),0.5 * this.getHeight(),-0.5 * this.getHeight(),1,-1);
    GL11.glViewport(0,0,this.getWidth(),this.getHeight());
  }
  
  // Window callback methods
  private void glfwWindowCloseCallback(long handle)
  {
    this.registerEvent(new WindowCloseEvent(this));
  }
  
  // Closes the window
  @Override public void close()
  {
    GLFW.glfwDestroyWindow(this.handle);
  }
  
  // Returns the GLFW window handle
  public final long getHandle()
  {
    return this.handle;
  }
  
  // Returns the keyboard for this window
  public Keyboard getKeyboard()
  {
    return this.keyboard;
  }
  
  // Returns the mouse for this window
  public Mouse getMouse()
  {
    return this.mouse;
  }
  
  // Returns the background color
  public Color getBackground()
  {
    return this.background;
  }
  
  // Sets the background color
  public Window setBackground(Color background)
  {
    this.background = background;
    return this;
  }
  
  // Returns the width of the window
  public int getWidth()
  {
    IntBuffer width = BufferUtils.createIntBuffer(1);
    GLFW.glfwGetWindowSize(this.handle,width,null);
    return width.get(0);
  }
  
  // Returns the height of the window
  public int getHeight()
  {
    IntBuffer height = BufferUtils.createIntBuffer(1);
    GLFW.glfwGetWindowSize(this.handle,null,height);
    return height.get(0);
  }
  
  // Sets the size of the window
  public Window setSize(int width, int height)
  {
    GLFW.glfwSetWindowSize(this.handle,width,height);
    return this;
  }
  
  // Returns the X position of the window
  public int getX()
  {
    IntBuffer left = BufferUtils.createIntBuffer(1);
    GLFW.glfwGetWindowPos(this.handle,left,null);
    return left.get(0);
  }
  
  // Returns the Y position of the window
  public int getY()
  {
    IntBuffer top = BufferUtils.createIntBuffer(1);
    GLFW.glfwGetWindowPos(this.handle,null,top);
    return top.get(0);
  }
  
  // Sets the position of the window
  public Window setPosition(int x, int y)
  {
    GLFW.glfwSetWindowPos(this.handle,x,y);
    return this;
  }
  
  // Sets the title of the window
  public Window setTitle(String title)
  {
    GLFW.glfwSetWindowTitle(this.handle,title);
    return this;
  }
  
  // Returns if this window should close
  public boolean isClosing()
  {
    return GLFW.glfwWindowShouldClose(this.handle);
  }
  
  // Sets if this window should close
  public Window setClosing(boolean closing)
  {
    GLFW.glfwSetWindowShouldClose(this.handle,closing);
    return this;
  }
  
  // Returns if the window is focused
  public boolean isFocused()
  {
    return GLFW.glfwGetWindowAttrib(this.handle,GLFW.GLFW_FOCUSED) == GLFW.GLFW_TRUE;
  }
  
  // Focuses the input on this window
  public Window focus()
  {
    GLFW.glfwFocusWindow(this.handle);
    return this;
  }
  
  // Returns if the window is minimized
  public boolean isMinimized()
  {
    return GLFW.glfwGetWindowAttrib(this.handle,GLFW.GLFW_ICONIFIED) == GLFW.GLFW_TRUE;
  }
  
  // Minimizes the window
  public Window minimize()
  {
    GLFW.glfwIconifyWindow(this.handle);
    return this;
  }
  
  // Returns if the window is minimized
  public boolean isMaximized()
  {
    return GLFW.glfwGetWindowAttrib(this.handle,GLFW.GLFW_MAXIMIZED) == GLFW.GLFW_TRUE;
  }
  
  // Maximizes the window
  public Window maximize()
  {
    GLFW.glfwMaximizeWindow(this.handle);
    return this;
  }
  
  // Restores the window
  public Window restore()
  {
    GLFW.glfwRestoreWindow(this.handle);
    return this;
  }
  
  // Handle events
  @Override protected void executeEvents() throws EventException
  {
    // Poll for input events
    GLFW.glfwPollEvents();
    
    // Execute the events
    super.executeEvents();
    
    // Check for closing
    if (this.isClosing())
      this.close();
  }
  
  // Clears the render buffer and draws the background color
  void clearBuffers()
  {
    GL11.glClearColor(this.getBackground().r / 255.0f,this.getBackground().g / 255.0f,this.getBackground().b / 255.0f,0.0f);
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
  }
  
  // Swaps the internal render buffers
  void swapBuffers()
  {
    GLFW.glfwSwapBuffers(this.getHandle());
  }
  
  // Get viewport for this graphics area
  public Camera toCamera()
  {
    return new Camera(new Vector(this.getWidth() * 0.5,this.getHeight() * 0.5).invert());
  }
  
  // Draw and update methods
  @Override public void draw() throws GameException
  {
    // Implementation left for the user
  }
  @Override public void update(long elaspedTime) throws GameException
  {
    // Implementation left for the user
  }
}
