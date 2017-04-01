package com.dengsn.crucial.graphics.shape;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.util.Vector;
import com.dengsn.crucial.graphics.GL;
import java.util.LinkedList;
import java.util.List;

public class Polyline extends Shape
{
  // Variables
  private final List<Vector> points = new LinkedList<>();
  
  // Adds a point
  public Polyline addVector(Vector point)
  {
    this.points.add(point);
    return this;
  }
  
  // Sets the fill color
  @Override public Polyline setFillColor(Color fillColor)
  {
    return (Polyline)super.setFillColor(fillColor);
  }
  
  // Sets the stroke color
  @Override public Polyline setStrokeColor(Color strokeColor)
  {
    return (Polyline)super.setStrokeColor(strokeColor);
  }
  
  // Sets the stroke width
  @Override public Polyline setStrokeWidth(double strokeWidth)
  {
    return (Polyline)super.setStrokeWidth(strokeWidth);
  }
  
  // Draws the polyline
  @Override public void draw() throws GameException
  {
    GL.color(this.getStrokeColor());
    GL.width(this.getStrokeWidth());
    
    for (int i = 1; i < this.points.size(); i ++)
      GL.line(this.points.get(i - 1),this.points.get(i));
  }  
}
