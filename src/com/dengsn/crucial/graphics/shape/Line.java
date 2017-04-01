package com.dengsn.crucial.graphics.shape;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.util.Vector;
import com.dengsn.crucial.graphics.GL;

public class Line extends Shape
{
  // Variables
  private Vector start = Vector.origin();
  private Vector end = Vector.origin();
  
  // Constructor
  public Line()
  {
    this.start = Vector.origin();
    this.end = Vector.origin();
  }
  
  // Returns the starting point
  public Vector getStart()
  {
    return this.start;
  }
  
  // Sets the starting point
  public Line setStart(Vector start)
  {
    this.start = start;
    return this;
  }
  
  // Returns the ending point
  public Vector getEnd()
  {
    return this.end;
  }
  
  // Sets the ending point
  public Line setEnd(Vector end)
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
