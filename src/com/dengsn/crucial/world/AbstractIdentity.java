package com.dengsn.crucial.world;

import java.util.UUID;

public abstract class AbstractIdentity
{
  // Variables 
  protected final World world;
  protected final UUID uuid;
  
  // Constructor
  public AbstractIdentity(World world)
  {
    this.world = world;
    this.uuid = UUID.randomUUID();
  }
  
  // Returns the world of this entity
  public final World getWorld()
  {
    return this.world;  
  }
  
  // Returns the unique identifier of this entity
  public final UUID getUUID()
  {
    return this.uuid;  
  }
}
