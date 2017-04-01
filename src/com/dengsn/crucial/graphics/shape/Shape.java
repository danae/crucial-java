package com.dengsn.crucial.graphics.shape;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.GL;
import com.dengsn.crucial.graphics.Color;
import org.lwjgl.opengl.GL11;

public abstract class Shape implements Drawable
{
  // Variables
  private Color fillColor = Color.WHITE;
  private Color strokeColor = null;
  private double strokeWidth = 1.0;
  
  // Returns the fill color of this shape
  public Color getFillColor()
  {
    return this.fillColor;  
  }
  
  // Sets the fill color of this shape
  public Shape setFillColor(Color fillColor)
  {
    this.fillColor = fillColor;
    return this;
  }
  
  // Returns if this shape is filled
  public boolean isFilled()
  {
    return this.fillColor != null;  
  }
  
  // Returns the stroke color of this shape
  public Color getStrokeColor()
  {
    return this.strokeColor;  
  }
  
  // Sets the stroke color of this shape
  public Shape setStrokeColor(Color strokeColor)
  {
    this.strokeColor = strokeColor;
    return this;
  }
  
  // Returns the stroke width of this shape
  public double getStrokeWidth()
  {
    return this.strokeWidth;
  }
  
  // Sets the stroke width of this shape
  public Shape setStrokeWidth(double strokeWidth)
  {
    this.strokeWidth = strokeWidth;
    return this;
  }
  
  // Returns if this shape is stroked
  public boolean isStroked()
  {
    return this.strokeColor != null;  
  }
  
  // Draw using brush
  public void fill(Drawable d) throws GameException
  {
    if (this.isFilled())
    {
      GL.color(this.fillColor);
      d.draw();
    }
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
  public void brush(Drawable fill, Drawable stroke) throws GameException
  {
    this.stroke(stroke);
    this.fill(fill);
  }
}
