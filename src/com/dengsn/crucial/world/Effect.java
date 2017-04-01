package com.dengsn.crucial.world;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.Updateable;
import java.util.UUID;

public interface Effect extends Updateable
{
  // Returns the world of this entity
  public World getWorld();
  
  // Returns the unique identifier of this entity
  public UUID getUUID();
  
  // Updates this entity
  @Override public void update(long elaspedTime) throws GameException;
}
