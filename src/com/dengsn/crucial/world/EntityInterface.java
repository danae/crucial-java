package com.dengsn.crucial.world;

import java.util.UUID;

public interface EntityInterface
{
  // Returns the world of this entity
  public World getWorld();
  
  // Returns the unique identifier of this entity
  public UUID getUUID();
}
