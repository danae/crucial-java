package com.dengsn.crucial;

import com.dengsn.crucial.util.Camera;
import com.dengsn.crucial.util.Vector;

@FunctionalInterface public interface Drawable
{
  // Draws this drawable
  public void draw() throws GameException;
  
  // Draws the drawable using a camera
  public default void drawAt(Camera camera) throws GameException
  {
    camera.draw(this);
  }
  
  // Draws this drawabale at a position
  public default void drawAt(Vector v) throws GameException
  {
    this.drawAt(new Camera(v));
  }
}
