package com.dengsn.crucial.util;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import org.lwjgl.opengl.GL11;

public class Camera
{
  // Variables
  private Vector target;
  private Vector orientation;
  
  // Constructor
  public Camera(Vector target)
  {
    this.target = target;
    this.orientation = Vector.unit();
  }
  
  // Sets the target of this camera
  public Camera setTarget(Vector target)
  {
    this.target = target;
    return this;
  }
  
  // Sets the orientation of this camera
  public Camera setOrientation(Vector orientation)
  {
    this.orientation = orientation;
    return this;
  }
  
  // Sets the rotation of this camera
  public Camera rotate(double angle)
  {
    this.orientation = this.orientation.rotate(angle);
    return this;
  }
  
  // Sets the scale of this camera
  public Camera scale(double scalar)
  {
    this.orientation = this.orientation.scale(scalar);
    return this;
  }
  
  // Transform a vector
  public Vector map(Vector v)
  {
    return null;
  }
  
  // Draw
  public void draw(Drawable d) throws GameException
  {
    GL11.glPushMatrix(); 
      GL11.glRotated(this.orientation.getDirection() * 180.0/Math.PI,0.0,0.0,1.0);
      GL11.glScaled(this.orientation.getMagnitude(),this.orientation.getMagnitude(),1.0);
      GL11.glTranslated(this.target.x,this.target.y,0.0);
    
      d.draw();
    GL11.glPopMatrix(); 
  }
  
  // Convert to string
  @Override public String toString()
  {
    return "target: " + this.target + "; orientation: " + this.orientation;
  }
}
