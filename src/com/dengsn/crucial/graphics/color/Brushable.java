package com.dengsn.crucial.graphics.color;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;

public interface Brushable
{
  // Brushed methods
  public Brush getBrush();
  public void setBrush(Brush brush);
  public default Brushable withBrush(Brush brush)
  {
    this.setBrush(brush);
    return this;
  }
  
  // Fill management
  public default Color getFillColor()
  {
    return this.getBrush().getFillColor();
  }
  public default void setFillColor(Color fillColor)
  {
    this.getBrush().setFillColor(fillColor);
  }
  public default Brushable withFillColor(Color fillColor)
  {
    this.setFillColor(fillColor);
    return this;
  }
  public default boolean isFilled()
  {
    return this.getBrush().isFilled();
  }
  
  // Stroke management
  public default Color getStrokeColor()
  {
    return this.getBrush().getStrokeColor();
  }
  public default void setStrokeColor(Color strokeColor)
  {
    this.getBrush().setStrokeColor(strokeColor);
  }
  public default Brushable withStrokeColor(Color strokeColor)
  {
    this.setStrokeColor(strokeColor);
    return this;
  }
  public default boolean isStroked()
  {
    return this.getStrokeColor() != null && this.getStrokeWidth() > 0;  
  }
  
  // Stroke width management
  public default double getStrokeWidth()
  {
    return this.getBrush().getStrokeWidth();
  }
  public default void setStrokeWidth(double strokeWidth)
  {
    this.getBrush().setStrokeWidth(strokeWidth);
  }
  public default Brushable withStrokeWidth(double strokeWidth)
  {
    this.setStrokeWidth(strokeWidth);
    return this;
  }
  
  // Draw using brush
  public default void fill(Drawable d) throws GameException
  {
    this.getBrush().fill(d);
  }
  public default void stroke(Drawable d) throws GameException
  {
    this.getBrush().stroke(d);
  }
  public default void brush(Drawable fill, Drawable stroke) throws GameException
  {
    this.getBrush().fillAndStroke(fill,stroke);
  }
}
