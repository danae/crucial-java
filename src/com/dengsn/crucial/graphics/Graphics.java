package com.dengsn.crucial.graphics;

import com.dengsn.crucial.Resource;
import com.dengsn.crucial.window.WindowException;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucial.util.Rect;
import com.dengsn.crucial.util.Viewport;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public final class Graphics implements Resource
{
  // Variables
  private final long windowHandle;
  private Color background = Color.BLACK;
  
  // Constructor
  public Graphics(long windowHandle) throws WindowException
  {
    this.windowHandle = windowHandle;
    this.initializeGL();
  }
  
  // Return the window handle
  public long getHandle()
  {
    return this.windowHandle;
  }
  
  // Closing the resource
  @Override public void close() throws WindowException
  {
    // Do nothing
  }
  
  // OpenGL intialization
  public void initializeGL() throws WindowException
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
  
  // Size of the graphics area
  public Rect getSize()
  {
    return new Rect(0,0,this.getWidth(),this.getHeight());
  }
  public int getWidth()
  {
    IntBuffer width = BufferUtils.createIntBuffer(1);
    GLFW.glfwGetWindowSize(this.windowHandle,width,null);
    return width.get(0);
  }
  public int getHeight()
  {
    IntBuffer height = BufferUtils.createIntBuffer(1);
    GLFW.glfwGetWindowSize(this.windowHandle,null,height);
    return height.get(0);
  }
  
  // Background color
  public Color getBackground()
  {
    return this.background;
  }
  public void setBackground(Color background)
  {
    this.background = background;
  }
  
  // Render loop methods
  public void clear()
  {
    GL11.glClearColor(this.getBackground().r / 255.0f,this.getBackground().g / 255.0f,this.getBackground().b / 255.0f,0.0f);
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
  }
  public void swap()
  {
    GLFW.glfwSwapBuffers(this.windowHandle);
  }
  
  // Get viewport for this graphics area
  public Viewport toViewport()
  {
    return new Viewport().withTranslation(this.getSize().center().invert());
  }
}
