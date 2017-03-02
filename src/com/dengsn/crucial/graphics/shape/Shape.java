package com.dengsn.crucial.graphics.shape;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.graphics.color.Brush;
import com.dengsn.crucial.graphics.color.Brushable;
import com.dengsn.crucial.graphics.color.Color;

public abstract class Shape implements Brushable, Drawable
{
  // Variables
  private Brush brush = new Brush();
  
  // Management
  @Override public Brush getBrush()
  {
    return this.brush;
  }
  @Override public void setBrush(Brush brush)
  {
    this.brush = brush;
  }
  
  // Overrides
  @Override public Shape withBrush(Brush brush)
  {
    return (Shape)Brushable.super.withBrush(brush);
  }
  @Override public Shape withFillColor(Color fillColor)
  {
    return (Shape)Brushable.super.withFillColor(fillColor);
  }
  @Override public Shape withStrokeColor(Color strokeColor)
  {
    return (Shape)Brushable.super.withStrokeColor(strokeColor);
  }
  @Override public Brushable withStrokeWidth(double strokeWidth)
  {
    return (Shape)Brushable.super.withStrokeWidth(strokeWidth);
  }
}
