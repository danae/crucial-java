package com.dengsn.crucial.graphics.color;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Brush
{
  // Variables
  private Color fillColor = Color.WHITE;
  private Color strokeColor = null;
  private double strokeWidth = 1.0;
  
  // Fill management
  public Color getFillColor()
  {
    return this.fillColor;  
  }
  public void setFillColor(Color fillColor)
  {
    this.fillColor = fillColor;
  }
  public Brush withFillColor(Color fillColor)
  {
    this.setFillColor(fillColor);
    return this;
  }
  public boolean isFilled()
  {
    return this.fillColor != null;  
  }
  
  // Stroke management
  public Color getStrokeColor()
  {
    return this.strokeColor;  
  }
  public void setStrokeColor(Color strokeColor)
  {
    this.strokeColor = strokeColor;
  }
  public Brush withStrokeColor(Color strokeColor)
  {
    this.setStrokeColor(strokeColor);
    return this;
  }
  public boolean isStroked()
  {
    return this.strokeColor != null;  
  }
  
  // Stroke width management
  public double getStrokeWidth()
  {
    return this.strokeWidth;
  }
  public void setStrokeWidth(double strokeWidth)
  {
    this.strokeWidth = strokeWidth;
  }
  public Brush withStrokeWidth(double strokeWidth)
  {
    this.setStrokeWidth(strokeWidth);
    return this;
  }
  
  // Draw using brush
  public void fill(Drawable d) throws GameException
  {
    if (this.isFilled())
      GL.color(this.fillColor);
    //else
    //  GL.color(Window2.getInstance().getBackground());
    d.draw();
  }
  public void stroke(Drawable d) throws GameException
  {
    if (this.isStroked())
    {
      GL.color(this.strokeColor);
      GL11.glLineWidth((float)this.strokeWidth);
      d.draw();
    }
  }
  public void fillAndStroke(Drawable fill, Drawable stroke) throws GameException
  {
    this.stroke(stroke);
    this.fill(fill);
  }
}
