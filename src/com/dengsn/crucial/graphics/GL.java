package com.dengsn.crucial.graphics;

import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.util.Rect;
import static org.lwjgl.opengl.GL11.*;

public final class GL 
{    
  // Set color
  public static void color(Color color)
  {
    double r = color.r / 255.0;
    double g = color.g / 255.0;
    double b  = color.b / 255.0;

    glColor4d(r,g,b,color.a);
  }
  public static void color(Colorable c)
  {
    GL.color(c.getColor());
  }
  
  // Set line width
  public static void width(double width)
  {
    glLineWidth((float)width);
  }
  
  // Draw pixel
  public static void pixel(Point pixel)
  {
    glBegin(GL_LINES);
      glVertex2d(pixel.x,pixel.y);
      glVertex2d(pixel.x,pixel.y+1);
    glEnd();  
  }
  
  // Draw line
  public static void line(Point begin, Point end)
  {
    glBegin(GL_LINES);
      glVertex2d(begin.x,begin.y);
      glVertex2d(end.x,end.y);
    glEnd();  
  }
  
  // Draw circle
  public static void circle(Point position, double radius)
  {
    glBegin(GL_TRIANGLE_FAN);
      for (int angle = 0; angle <= 360; angle ++)
        glVertex2d(position.x + Math.sin(angle*Math.PI/180.0) * radius,position.y + Math.cos(angle*Math.PI/180.0) * radius);
    glEnd();  
  }
  
  // Draw circle stroke
  public static void circleStroke(Point position, double radius)
  {
    for (int angle = 0; angle <= 360; angle ++)
    {
      Point begin = new Point(position.x + Math.sin(angle*Math.PI/180.0) * radius,position.y + Math.cos(angle*Math.PI/180.0) * radius);  
      Point end = new Point(position.x + Math.sin((angle+1)*Math.PI/180.0) * radius,position.y + Math.cos((angle+1)*Math.PI/180.0) * radius);  
      GL.line(begin,end);
    }
  }
  
  // Draw pie
  public static void pie(Point center, double radius, double startAngle, double endAngle)
  {
    glBegin(GL_TRIANGLE_FAN);
      glVertex2d(center.x,center.y);
      for (double angle = startAngle; angle <= endAngle; angle += Math.PI/180.0)
        glVertex2d(center.x + Math.sin(angle) * radius,center.y + Math.cos(angle) * radius);
    glEnd(); 
  }
  public static void pie(Rect rect, double startAngle, double endAngle)
  {
    GL.pie(rect.getCenter(),Math.min(rect.getWidth(),rect.getHeight()) / 2.0,startAngle,endAngle);
  }
  
  // Draw rectangle
  public static void rectangle(Rect rect)
  {
    glBegin(GL_QUADS);
      glVertex2d(rect.topLeft.x,rect.topLeft.y);
      glVertex2d(rect.topLeft.x,rect.bottomRight.y);
      glVertex2d(rect.bottomRight.x,rect.bottomRight.y);
      glVertex2d(rect.bottomRight.x,rect.topLeft.y);
    glEnd();  
  }
  
  // Draw rectangle stroke
  public static void rectangleStroke(Rect rect)
  {
    GL.line(new Point(rect.topLeft.x,rect.topLeft.y),new Point(rect.topLeft.x,rect.bottomRight.y));
    GL.line(new Point(rect.topLeft.x,rect.topLeft.y),new Point(rect.bottomRight.x,rect.topLeft.y));
    GL.line(new Point(rect.bottomRight.x,rect.topLeft.y),new Point(rect.bottomRight.x,rect.bottomRight.y));
    GL.line(new Point(rect.topLeft.x,rect.bottomRight.y),new Point(rect.bottomRight.x,rect.bottomRight.y));
  }
    
  // Collide circles
  public static boolean collideCircles(Point c1, double r1, Point c2, double r2)
  {
    return (Point.distance(c1,c2) <= r1 + r2);
  }
  
  // Collide rectangles
  public static boolean collideRectangles(Rect r1, Rect r2)
  {
    return (
      (r2.topLeft.x >= r1.topLeft.x && r2.topLeft.x <= r1.bottomRight.x)
      || (r2.bottomRight.x >= r1.topLeft.x && r2.bottomRight.x <= r1.bottomRight.x)
      || (r2.topLeft.y >= r1.topLeft.y && r2.topLeft.y <= r1.bottomRight.y)
      || (r2.bottomRight.y >= r1.topLeft.y && r2.bottomRight.y <= r1.bottomRight.y)
    );  
  }
}
