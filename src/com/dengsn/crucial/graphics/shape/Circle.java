package com.dengsn.crucial.graphics.shape;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.color.Brush;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.graphics.opengl.GL;

public class Circle extends Shape
{
  // Variables
  private Point position = Point.origin();
  private double radius = 0.0;
  
  // Management
  public Point getPosition()
  {
    return this.position;
  }
  public void setPosition(Point position)
  {
    this.position = position;
  }
  public Circle withPosition(Point position)
  {
    this.setPosition(position);
    return this;
  }
  public double getRadius()
  {
    return this.radius;
  }
  public void setRadius(double radius)
  {
    this.radius = radius;
  }
  public Circle withRadius(double radius)
  {
    this.setRadius(radius);
    return this;
  }
  
  // Overrides
  @Override public Circle withBrush(Brush brush)
  {
    return (Circle)super.withBrush(brush);
  }
  @Override public Circle withFillColor(Color fillColor)
  {
    return (Circle)super.withFillColor(fillColor);
  }
  @Override public Circle withStrokeColor(Color strokeColor)
  {
    return (Circle)super.withStrokeColor(strokeColor);
  }
  @Override public Circle withStrokeWidth(double strokeWidth)
  {
    return (Circle)super.withStrokeWidth(strokeWidth);
  }
  
  // Draw
  @Override public void draw() throws GameException
  {
    this.brush(
      () -> GL.circle(this.getPosition(),this.getRadius() - this.getStrokeWidth()),
      () -> GL.circle(this.getPosition(),this.getRadius())
    );
  }  
}
