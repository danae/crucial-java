package com.dengsn.crucial.graphics.shape;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.graphics.GL;

public class Line extends Shape
{
  // Variables
  private Point start = Point.origin();
  private Point end = Point.origin();
  
  // Constructor
  public Line()
  {
    this.start = Point.origin();
    this.end = Point.origin();
  }
  
  // Returns the starting point
  public Point getStart()
  {
    return this.start;
  }
  
  // Sets the starting point
  public Line setStart(Point start)
  {
    this.start = start;
    return this;
  }
  
  // Returns the ending point
  public Point getEnd()
  {
    return this.end;
  }
  
  // Sets the ending point
  public Line setEnd(Point end)
  {
    this.end = end;
    return this;
  }
  
  // Sets the fill color
  @Override public Line setFillColor(Color fillColor)
  {
    return (Line)super.setFillColor(fillColor);
  }
  
  // Sets the stroke color
  @Override public Line setStrokeColor(Color strokeColor)
  {
    return (Line)super.setStrokeColor(strokeColor);
  }
  
  // Sets the stroke width
  @Override public Line setStrokeWidth(double strokeWidth)
  {
    return (Line)super.setStrokeWidth(strokeWidth);
  }
  
  // Draws the line
  @Override public void draw() throws GameException
  {
    GL.color(this.getStrokeColor());
    GL.width(this.getStrokeWidth());
    GL.line(this.getStart(),this.getEnd());
  }  
}
