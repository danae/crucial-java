package com.dengsn.crucial.util;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import org.lwjgl.opengl.GL11;

public class Viewport
{
  // Variables
  private Point translation = Point.origin();
  private Point scale = new Point(1.0,1.0);
  
  // Returns the translation of this viewport
  public Point getTranslation()
  {
    return this.translation;
  }
  
  // Sets the translation of this viewport
  public Viewport setTranslation(Point translation)
  {
    this.translation = translation;
    return this;
  }
  
  // Returns the scale of this viewport
  public Point getScale()
  {
    return this.scale;
  }
  
  // Sets the scale of this viewport
  public Viewport setScale(Point scale)
  {
    this.scale = scale;
    return this;
  }
  
  // Transforms a point in this viewport
  public Point transform(Point p)
  {
    return p
      .multiply(this.scale)
      .add(this.translation);
  }
  
  // Draws an object in this viewport
  public void draw(Drawable d) throws GameException
  {
    GL11.glPushMatrix(); 
      GL11.glScaled(this.scale.x,this.scale.y,1.0);
      GL11.glTranslated(this.translation.x,this.translation.y,0.0);
    
      d.draw();
    GL11.glPopMatrix(); 
  }
}
