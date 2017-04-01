package com.dengsn.crucial.world;

import java.util.UUID;

public abstract class AbstractEffect implements Effect
{
  // Variables 
  private final World world;
  private final UUID uuid;
  
  // Constructor
  public AbstractEffect(World world)
  {
    this.world = world;
    this.uuid = UUID.randomUUID();
    
    // Add this entity to the world
    this.world.getEffects().add(this);
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
