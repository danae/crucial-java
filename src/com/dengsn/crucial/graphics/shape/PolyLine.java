package com.dengsn.crucial.graphics.shape;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.color.Brush;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.graphics.opengl.GL;
import java.util.LinkedList;
import java.util.List;

public class PolyLine extends Shape
{
  // Variables
  private final List<Point> points = new LinkedList<>();
  
  // Management
  public void addPoint(Point point)
  {
    this.points.add(point);
  }
  public PolyLine withPoint(Point point)
  {
    this.addPoint(point);
    return this;
  }
  
  // Overrides
  @Override public PolyLine withBrush(Brush brush)
  {
    return (PolyLine)super.withBrush(brush);
  }
  @Override public PolyLine withFillColor(Color fillColor)
  {
    return (PolyLine)super.withFillColor(fillColor);
  }
  @Override public PolyLine withStrokeColor(Color strokeColor)
  {
    return (PolyLine)super.withStrokeColor(strokeColor);
  }
  @Override public PolyLine withStrokeWidth(double strokeWidth)
  {
    return (PolyLine)super.withStrokeWidth(strokeWidth);
  }
  
  // Draw
  @Override public void draw() throws GameException
  {
    GL.color(this.getStrokeColor());
    GL.width(this.getStrokeWidth());
    
    for (int i = 1; i < this.points.size(); i ++)
      GL.line(this.points.get(i - 1),this.points.get(i));
  }  
}
