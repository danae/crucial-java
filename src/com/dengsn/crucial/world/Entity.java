package com.dengsn.crucial.world;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.Updateable;
import java.util.UUID;

public interface Entity extends Drawable, Updateable
{
  // Returns the world of this entity
  public World getWorld();
  
  // Returns the unique identifier of this entity
  public UUID getUUID();
  
  // Draws this entity
  @Override public void draw() throws GameException;
  
  // Updates this entity
  @Override public void update(long elaspedTime) throws GameException;
}
