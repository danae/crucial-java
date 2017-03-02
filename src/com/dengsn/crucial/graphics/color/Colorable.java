package com.dengsn.crucial.graphics.color;

public interface Colorable
{
  public Color getColor();
  public void setColor(Color color);
  public default Colorable withColor(Color color)
  {
    this.setColor(color);
    return this;
  }
}
