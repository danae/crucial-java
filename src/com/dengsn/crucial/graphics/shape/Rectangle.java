package com.dengsn.crucial.graphics.shape;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.util.Vector;
import com.dengsn.crucial.graphics.GL;
import com.dengsn.crucial.util.Rect;

public class Rectangle extends Shape
{
  // Variables
  private Rect bounds;
  
  // Constructor
  public Rectangle()
  {
    this.bounds = new Rect(0.0,0.0,0.0,0.0);
  }
  
  // Returns the boundaries
  public Rect getBounds()
  {
    return this.bounds;
  }
  
  // Sets the boundaries
  public Rectangle setBounds(Rect bounds)
  {
    this.bounds = bounds;
    return this;
  }
  
  // Sets the fill color
  @Override public Rectangle setFillColor(Color fillColor)
  {
    return (Rectangle)super.setFillColor(fillColor);
  }
  
  // Sets the stroke color
  @Override public Rectangle setStrokeColor(Color strokeColor)
  {
    return (Rectangle)super.setStrokeColor(strokeColor);
  }
  
  // Sets the stroke width
  @Override public Rectangle setStrokeWidth(double strokeWidth)
  {
    return (Rectangle)super.setStrokeWidth(strokeWidth);
  }
  
  // Draws the rectangle
  @Override public void draw() throws GameException
  {
    this.brush(
      () -> GL.rectangle(this.getBounds()),
      () -> GL.rectangle(this.getBounds())
    );
  }  
}
