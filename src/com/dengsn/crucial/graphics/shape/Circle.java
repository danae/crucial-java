package com.dengsn.crucial.graphics.shape;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.graphics.GL;

public class Circle extends Shape
{
  // Variables
  private Point position;
  private double radius;
  
  // Constructor
  public Circle()
  {
    this.position = Point.origin();
    this.radius = 0.0;
  }
  
  // Returns the position
  public Point getPosition()
  {
    return this.position;
  }
  
  // Sets the position
  public Circle setPosition(Point position)
  {
    this.position = position;
    return this;
  }
  
  // Returns the ratius
  public double getRadius()
  {
    return this.radius;
  }
  
  // Sets the radius
  public Circle setRadius(double radius)
  {
    this.radius = radius;
    return this;
  }
  
  // Sets the fill color
  @Override public Circle setFillColor(Color fillColor)
  {
    return (Circle)super.setFillColor(fillColor);
  }
  
  // Sets the stroke color
  @Override public Circle setStrokeColor(Color strokeColor)
  {
    return (Circle)super.setStrokeColor(strokeColor);
  }
  
  // Sets the stroke width
  @Override public Circle setStrokeWidth(double strokeWidth)
  {
    return (Circle)super.setStrokeWidth(strokeWidth);
  }
  
  // Draws the circle
  @Override public void draw() throws GameException
  {
    this.brush(
      () -> GL.circle(this.getPosition(),this.getRadius() - this.getStrokeWidth()),
      () -> GL.circle(this.getPosition(),this.getRadius())
    );
  }  
}
