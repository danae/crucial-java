package com.dengsn.crucial.world;

import java.util.UUID;

public abstract class AbstractEntity implements Entity
{
  // Variables 
  private final World world;
  private final UUID uuid;
  
  // Constructor
  public AbstractEntity(World world)
  {
    this.world = world;
    this.uuid = UUID.randomUUID();
    
    // Add this entity to the world
    this.world.getEntities().add(this);
  }
  
  // Returns the world of this entity
  @Override public final World getWorld()
  {
    return this.world;  
  }
  
  // Returns the unique identifier of this entity
  @Override public final UUID getUUID()
  {
    return this.uuid;  
  }
}
