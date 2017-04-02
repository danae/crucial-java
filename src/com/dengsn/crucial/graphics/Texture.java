package com.dengsn.crucial.graphics;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.util.Rect;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public final class Texture implements Drawable, AutoCloseable
{
  // Variables
  private final int id;
  private final int width;
  private final int height;

  // Constructor
  public Texture(int width, int height, ByteBuffer buffer)
  {
    this.id = GL11.glGenTextures();
    this.width = width;
    this.height = height;
    
    this.bind();
    
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_REPEAT);
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_T,GL11.GL_REPEAT);
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_NEAREST);
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER,GL11.GL_NEAREST);
    
    GL11.glTexImage2D(GL11.GL_TEXTURE_2D,0,GL11.GL_RGBA,this.getWidth(),this.getHeight(),0,GL11.GL_RGBA,GL11.GL_UNSIGNED_BYTE,buffer);
  }
  public Texture(int width, int height)
  {
    this(width,height,BufferUtils.createByteBuffer(width * height * 4));
  }
  
  // Close the resources
  @Override public void close()
  {
    GL11.glDeleteTextures(this.id);
  }
 
  // Management
  public int getId()
  {
    return this.id;
  }
  public int getWidth()
  {
    return this.width;
  }
  public int getHeight()
  {
    return this.height;
  }
  
  // Bind texture
  public final void bind()
  {
    GL11.glBindTexture(GL11.GL_TEXTURE_2D,this.id);
  }
  
  // Draw
  @Override public void draw()
  {
    GL.color(Color.WHITE);
    this.drawWithoutColor();
  }
  public void drawWithoutColor()
  {
    GL11.glEnable(GL11.GL_TEXTURE_2D);
    this.bind();
    GL11.glBegin(GL11.GL_QUADS);
      GL11.glTexCoord2f(0,0);
      GL11.glVertex2f(0,0);
      GL11.glTexCoord2f(0,1);
      GL11.glVertex2f(0,this.getHeight());
      GL11.glTexCoord2f(1,1);
      GL11.glVertex2f(this.getWidth(),this.getHeight());
      GL11.glTexCoord2f(1,0);
      GL11.glVertex2f(this.getWidth(),0);
    GL11.glEnd();
    GL11.glDisable(GL11.GL_TEXTURE_2D);
  }
  
  // Draw region
  public Drawable region(Rect region)
  {
    double x1 = 0;
    double y1 = 0;
    double x2 = region.getWidth();
    double y2 = region.getHeight();

    double s1 = region.x1 / this.getWidth();
    double t1 = region.y1 / this.getHeight();
    double s2 = region.x2 / this.getWidth();
    double t2 = region.y2 / this.getHeight();

    return () ->
    {
      //GL.color(Color.WHITE);
      GL11.glEnable(GL11.GL_TEXTURE_2D);
      this.bind();
      GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2d(s1,t1);
        GL11.glVertex2d(x1,y1);
        GL11.glTexCoord2d(s1,t2);
        GL11.glVertex2d(x1,y2);
        GL11.glTexCoord2d(s2,t2);
        GL11.glVertex2d(x2,y2);
        GL11.glTexCoord2d(s2,t1);
        GL11.glVertex2d(x2,y1);
      GL11.glEnd();
      GL11.glDisable(GL11.GL_TEXTURE_2D);
    };
  }
  
  // Create texture from image
  public static Texture fromImage(BufferedImage image)
  {
    ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);
    for (int y = 0; y < image.getHeight(); y ++)
      for (int x = 0; x < image.getWidth(); x ++)
      {
        int pixel = image.getRGB(x,y);
      
        buffer.put((byte)((pixel >> 16) & 0xFF));
        buffer.put((byte)((pixel >> 8) & 0xFF));
        buffer.put((byte)(pixel & 0xFF));
        buffer.put((byte)((pixel >> 24) & 0xFF));
      }
    
    return new Texture(image.getWidth(),image.getHeight(),(ByteBuffer)buffer.rewind());
  }
  public static Texture fromImage(InputStream in) throws TextureException
  {
    try
    {
      return fromImage(ImageIO.read(in));
    }
    catch (IOException ex)
    {
      throw new TextureException("Input error: " + ex.getMessage(),ex);
    }
  }
  public static Texture fromImage(File file) throws TextureException
  {
    try
    {
      return fromImage(new FileInputStream(file));
    }
    catch (IOException ex)
    {
      throw new TextureException("Input error: " + ex.getMessage(),ex);
    }
  }
  public static Texture fromImage(URL url) throws TextureException
  {
    try
    {
      return fromImage(url.openStream());
    }
    catch (IOException ex)
    {
      throw new TextureException("Input error: " + ex.getMessage(),ex);
    }
  }
}
