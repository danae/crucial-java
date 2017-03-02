package com.dengsn.crucial.graphics.shape;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.color.Brush;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.graphics.opengl.GL;
import com.dengsn.crucial.util.Rect;

public class Rectangle extends Shape
{
  // Variables
  private Rect bounds = new Rect(Point.origin(),Point.origin());
  
  // Management
  public Rect getBounds()
  {
    return this.bounds;
  }
  public void setBounds(Rect bounds)
  {
    this.bounds = bounds;
  }
  public Rectangle withBounds(Rect bounds)
  {
    this.setBounds(bounds);
    return this;
  }
  
  // Overrides
  @Override public Rectangle withBrush(Brush brush)
  {
    return (Rectangle)super.withBrush(brush);
  }
  @Override public Rectangle withFillColor(Color fillColor)
  {
    return (Rectangle)super.withFillColor(fillColor);
  }
  @Override public Rectangle withStrokeColor(Color strokeColor)
  {
    return (Rectangle)super.withStrokeColor(strokeColor);
  }
  @Override public Rectangle withStrokeWidth(double strokeWidth)
  {
    return (Rectangle)super.withStrokeWidth(strokeWidth);
  }
  
  // Draw
  @Override public void draw() throws GameException
  {
    this.brush(
      () -> GL.rectangle(this.getBounds()),
      () -> GL.rectangle(this.getBounds())
    );
  }  
}
