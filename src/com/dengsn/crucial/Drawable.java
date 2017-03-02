package com.dengsn.crucial;

import com.dengsn.crucial.util.Viewport;
import com.dengsn.crucial.util.Point;

@FunctionalInterface public interface Drawable
{
  // Drawable methods
  public void draw() throws GameException;
  public default void drawAt(Viewport viewport) throws GameException
  {
    viewport.draw(this);
  }
  public default void drawAt(Point position) throws GameException
  {
    this.drawAt(new Viewport().withTranslation(position));
  }
}
