package com.dengsn.crucial.util;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import org.lwjgl.opengl.GL11;

public class Viewport
{
  // Variables
  private Point translation = Point.origin();
  private Point scale = new Point(1.0,1.0);
  
  // Functions
  public Viewport withTranslation(Point translation)
  {
    this.translation = translation;
    return this;
  }
  public Viewport withScale(Point scale)
  {
    this.scale = scale;
    return this;
  }
  
  // Transform point
  public Point transform(Point p)
  {
    return p
      .multiply(this.scale)
      .add(this.translation);
  }
  
  // Draw
  public void draw(Drawable d) throws GameException
  {
    GL11.glPushMatrix(); 
      GL11.glScaled(this.scale.x,this.scale.y,1.0);
      GL11.glTranslated(this.translation.x,this.translation.y,0.0);
    
      d.draw();
    GL11.glPopMatrix(); 
  }
  
  // To string
  @Override public String toString()
  {
    return "translation: " + this.translation + "; scale: " + this.scale;
  }
}
