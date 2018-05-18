package com.dengsn.crucial;

import com.dengsn.crucial.util.Viewport;
import com.dengsn.crucial.util.Point;

@FunctionalInterface public interface Drawable
{
  // Draws this object
  public void draw() throws GameException;
  
  // Draws this object at the center of a viewport
  public default void drawAt(Viewport viewport) throws GameException
  {
    viewport.draw(this);
  }
  
  // Draws this object at a given position
  public default void drawAt(Point position) throws GameException
  {
    new Viewport().setTranslation(position).draw(this);
  }
}
