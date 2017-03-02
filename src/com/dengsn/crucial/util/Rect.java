package com.dengsn.crucial.util;

public class Rect 
{
  // Variables
  public Point tl;
  public Point br;
  
  // Constructor
  public Rect(double x1, double y1, double x2, double y2)
  {
    this.tl = new Point(x1,y1);
    this.br = new Point(x2,y2);
  }
  public Rect(Point tl, Point br)
  {
    this.tl = new Point(tl);
    this.br = new Point(br);
  }
  public Rect(Rect rect)
  {
    this.tl = new Point(rect.tl);
    this.br = new Point(rect.br);
  }
  
  // Object methods
  @Override public boolean equals(Object o)
  {
    if (!(o instanceof Rect))
      return false;
    
    Rect r = (Rect)o;
    return (r.tl.equals(this.tl) && r.br.equals(this.br));
  }
  
  // Getters
  public Point center()
  {
    return new Point((this.tl.x + this.br.x) / 2.0,(this.tl.y + this.br.y) / 2.0);  
  }
  public double width()
  {
    return Math.abs(this.br.x - this.tl.x);  
  }
  public double height()
  {
    return Math.abs(this.br.y - this.tl.y);  
  }
  
  // Checks
  public boolean isEmpty()
  {
    return (this.tl.isEmpty() && this.br.isEmpty());  
  }
  public boolean contains(Point pt)
  {
    return (
      pt.x >= this.tl.x && pt.x <= this.br.x
      && pt.y >= this.tl.y && pt.y <= this.br.y
    );  
  }
  
  // Manipulation
  public Rect mirrorX()
  {
    return new Rect(this.tl.mirrorX(),this.br.mirrorX());  
  }
  
  public Rect mirrorY()
  {
    return new Rect(this.tl.mirrorY(),this.br.mirrorY());  
  }
  
  public Rect invert()
  {
    return this.mirrorX().mirrorY();
  }
  
  public Rect add(Rect rect)
  {
    return new Rect(this.tl.add(rect.tl),this.br.add(rect.br));
  }
  public Rect add(double x1, double y1, double x2, double y2)
  {
    return new Rect(this.tl.add(x1,y1),this.br.add(x2,y2));
  }
  public Rect add(Point pt)
  {
    return new Rect(this.tl.add(pt),this.br.add(pt));
  }
  public Rect add(double x, double y)
  {
    return new Rect(this.tl.add(x,y),this.br.add(x,y));
  }
  public Rect add(double xy)
  {
    return this.add(new Point(xy,xy));
  }
  
  public Rect subtract(Rect rect)
  {
    return new Rect(this.tl.subtract(rect.tl),this.br.subtract(rect.br));
  }
  public Rect subtract(double x1, double y1, double x2, double y2)
  {
    return new Rect(this.tl.subtract(x1,y1),this.br.subtract(x2,y2));
  }
  public Rect subtract(Point pt)
  {
    return new Rect(this.tl.subtract(pt),this.br.subtract(pt));
  }
  public Rect subtract(double x, double y)
  {
    return this.subtract(new Point(x,y));
  }
  public Rect subtract(double xy)
  {
    return this.subtract(new Point(xy,xy));
  }
  
  public Rect multiply(double x, double y)
  {
    return new Rect(this.tl.multiply(x,y),this.br.multiply(x,y));
  }
  public Rect multiply(double xy)
  {
    return this.multiply(xy,xy);
  }
  
  public Rect divide(double x, double y)
  {
    return this.multiply(1.0/x,1.0/y);
  }
  public Rect divide(double xy)
  {
    return this.divide(xy,xy);
  }
  
  public Rect combine(Rect rect)
  {
    return new Rect(Point.min(this.tl,rect.tl),Point.max(this.br,rect.br)); 
  }
  
  public Point map(Point position, Rect positionBounds)
  {
    Point relativePosition = position.subtract(positionBounds.tl);
    Point fraction = relativePosition.divide(positionBounds.width(),positionBounds.height());
    return this.tl.add(fraction.multiply(this.width(),this.height()));
  }
  
  // To string
  @Override public String toString()
  {
    return String.format("%s(%s;%s)",this.getClass().getSimpleName(),this.tl.toString(),this.br.toString());
  }
}
