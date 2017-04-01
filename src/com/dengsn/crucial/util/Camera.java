package com.dengsn.crucial.util;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import org.lwjgl.opengl.GL11;

public class Camera
{
  // Variables
  private Vector target;
  private double rotation;
  private double scale;
  
  // Constructor
  public Camera(Vector target)
  {
    this.target = target;
    this.rotation = 0.0;
    this.scale = 1.0;
  }
  
  // Sets the target of this camera
  public Camera setTarget(Vector target)
  {
    this.target = target;
    return this;
  }
  
  // Sets the rotation of this camera
  public Camera setRotation(double rotation)
  {
    this.rotation = rotation;
    return this;
  }
  
  // Sets the scale of this camera
  public Camera setScale(double scale)
  {
    this.scale = scale;
    return this;
  }
  
  // Draw
  public void draw(Drawable d) throws GameException
  {
    GL11.glPushMatrix(); 
      GL11.glRotated(this.rotation,0.0,0.0,1.0);
      GL11.glScaled(this.scale,this.scale,1.0);
      GL11.glTranslated(this.target.x,this.target.y,0.0);
    
      d.draw();
    GL11.glPopMatrix(); 
  }
  
  // Convert to string
  @Override public String toString()
  {
    return "translation: " + this.target + "; scale: " + this.scale;
  }
}
