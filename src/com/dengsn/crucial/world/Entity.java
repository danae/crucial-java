package com.dengsn.crucial.world;

import java.util.UUID;

public abstract class Entity implements EntityInterface
{
  // Variables 
  private final World world;
  private final UUID uuid;
  
  // Constructor
  public Entity(World world)
  {
    this.world = world;
    this.world.getEntities().add(this);
    
    this.uuid = UUID.randomUUID();
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
