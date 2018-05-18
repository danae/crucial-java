package com.dengsn.crucial.util;

import com.dengsn.crucial.graphics.GL;
import com.dengsn.crucial.Drawable;

public class Curve implements Drawable
{
  // Variables
  private final Point start;
  private final Point end;
  private final Point control;
  
  // Constructor
  public Curve(Point start, Point control, Point end)
  {
    this.start = start;
    this.end = end;
    this.control = control;
  }
  public Curve(Point start, double startAngle, Point end, double endAngle)
  {
    this(start,Curve.calculateIntersection(start,startAngle,end,endAngle),end);
  }
  
  // Management
  public Point getStart()
  {
    return this.start;
  }
  public Point getEnd()
  {
    return this.end;
  }
  public Point getControl()
  {
    return this.control;
  }
  
  // Calculate Bézier point
  public Point calculateBezier(double t)
  {
    return new Point(
      Math.pow(1 - t,2) * this.start.x + 2 * (1 - t) * t * this.control.x + Math.pow(t,2) * this.end.x,
      Math.pow(1 - t,2) * this.start.y + 2 * (1 - t) * t * this.control.y + Math.pow(t,2) * this.end.y
    );
  }
    
  // Draw
  @Override public void draw()
  {
    Point currentStart = this.start;
    Point currentEnd;
    for (double t = 0.0; t < 1.0; t += 0.01)
    {
      currentEnd = this.calculateBezier(t);
      GL.line(currentStart,currentEnd);
      currentStart = currentEnd;
    }
  }
  
  // Calculate intersection of two lines
  public static Point calculateIntersection(Point p1, Point p2, Point p3, Point p4)
  {
    double parallel = (p1.x - p2.x) * (p3.y - p4.y) - (p1.y - p2.y ) * (p3.x - p4.x);
    if (parallel == 0)
      return new Point(
        0.5 * (p1.x + p2.x),
        0.5 * (p1.y + p2.y)
      );
    else
      return new Point(
        ((p1.x * p2.y - p1.y * p2.x) * (p3.x - p4.x) - (p1.x - p2.x) * (p3.x * p4.y - p3.y * p4.x)) / parallel,
        ((p1.x * p2.y - p1.y * p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x * p4.y - p3.y * p4.x)) / parallel
      );
  }
  public static Point calculateIntersection(Point p1, double angle1, Point p2, double angle2)
  {
    Point p1a = p1.add(new Point(Math.cos(angle1),Math.sin(angle1)));
    Point p2a = p2.add(new Point(Math.cos(angle2),Math.sin(angle2)));
    return Curve.calculateIntersection(p1,p1a,p2,p2a);
  }
}
