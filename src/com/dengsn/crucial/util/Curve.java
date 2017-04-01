package com.dengsn.crucial.util;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.graphics.GL;

public class Curve implements Drawable
{
  // Variables
  private final Vector start;
  private final Vector end;
  private final Vector control;
  
  // Constructor
  public Curve(Vector start, Vector control, Vector end)
  {
    this.start = start;
    this.end = end;
    this.control = control;
  }
  public Curve(Vector start, double startAngle, Vector end, double endAngle)
  {
    this(start,Curve.calculateIntersection(start,startAngle,end,endAngle),end);
  }
  
  // Management
  public Vector getStart()
  {
    return this.start;
  }
  public Vector getEnd()
  {
    return this.end;
  }
  public Vector getControl()
  {
    return this.control;
  }
  
  // Calculate Bézier point
  public Vector calculateBezier(double t)
  {
    return new Vector(
      Math.pow(1 - t,2) * this.start.x + 2 * (1 - t) * t * this.control.x + Math.pow(t,2) * this.end.x,
      Math.pow(1 - t,2) * this.start.y + 2 * (1 - t) * t * this.control.y + Math.pow(t,2) * this.end.y
    );
  }
    
  // Draw
  @Override public void draw()
  {
    Vector currentStart = this.start;
    Vector currentEnd;
    for (double t = 0.0; t < 1.0; t += 0.01)
    {
      currentEnd = this.calculateBezier(t);
      GL.line(currentStart,currentEnd);
      currentStart = currentEnd;
    }
  }
  
  // Calculate intersection of two lines
  public static Vector calculateIntersection(Vector p1, Vector p2, Vector p3, Vector p4)
  {
    double parallel = (p1.x - p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x - p4.x);
    if (parallel == 0)
      return new Vector(
        0.5 * (p1.x + p2.x),
        0.5 * (p1.y + p2.y)
      );
    else
      return new Vector(
        ((p1.x * p2.y - p1.y * p2.x) * (p3.x - p4.x) - (p1.x - p2.x) * (p3.x * p4.y - p3.y * p4.x)) / parallel,
        ((p1.x * p2.y - p1.y * p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x * p4.y - p3.y * p4.x)) / parallel
      );
  }
  public static Vector calculateIntersection(Vector p1, double angle1, Vector p2, double angle2)
  {
    Vector p1a = p1.add(new Vector(Math.cos(angle1),Math.sin(angle1)));
    Vector p2a = p2.add(new Vector(Math.cos(angle2),Math.sin(angle2)));
    return Curve.calculateIntersection(p1,p1a,p2,p2a);
  }
}
