package com.dengsn.crucial.graphics.shape;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.color.Brush;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.graphics.opengl.GL;

public class Line extends Shape
{
  // Variables
  private Point start = Point.origin();
  private Point end = Point.origin();
  
  // Management
  public Point getStart()
  {
    return this.start;
  }
  public void setStart(Point start)
  {
    this.start = start;
  }
  public Line withStart(Point start)
  {
    this.setStart(start);
    return this;
  }
  public Point getEnd()
  {
    return this.end;
  }
  public void setEnd(Point end)
  {
    this.end = end;
  }
  public Line withEnd(Point end)
  {
    this.setEnd(end);
    return this;
  }
  
  // Overrides
  @Override public Line withBrush(Brush brush)
  {
    return (Line)super.withBrush(brush);
  }
  @Override public Line withFillColor(Color fillColor)
  {
    return (Line)super.withFillColor(fillColor);
  }
  @Override public Line withStrokeColor(Color strokeColor)
  {
    return (Line)super.withStrokeColor(strokeColor);
  }
  @Override public Line withStrokeWidth(double strokeWidth)
  {
    return (Line)super.withStrokeWidth(strokeWidth);
  }
  
  // Draw
  @Override public void draw() throws GameException
  {
    GL.color(this.getStrokeColor());
    GL.width(this.getStrokeWidth());
    GL.line(this.getStart(),this.getEnd());
  }  
}
