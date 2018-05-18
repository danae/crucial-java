package com.dengsn.crucial.world;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.Updateable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class World implements Drawable, Updateable
{
  // Variables
  private final List<Effect> effects;
  private final List<Entity> entities;

  // Constructor
  public World()
  {
    this.effects = new CopyOnWriteArrayList<>();
    this.entities = new CopyOnWriteArrayList<>();
  }
  
  // Returns all effects
  public final List<Effect> getEffects()
  {
    return this.effects;
  }
  
  // Returns entities of this type
  public final <T extends Effect> List<T> getEffects(Class<T> type)
  {
    return (List<T>)this.effects.stream()
      .filter(e -> type.isInstance(e))
      .collect(Collectors.toCollection(LinkedList::new));
  }
  
  // Returns an entity by its identifier
  public final Effect getEffect(UUID uuid)
  {
    return this.effects.stream()
      .filter(e -> e.getUUID().equals(uuid))
      .findFirst()
      .orElse(null);
  }

  // Returns all entities
  public final List<Entity> getEntities()
  {
    return this.entities;
  }
  
  // Returns entities of this type
  public final <T extends Entity> List<T> getEntities(Class<T> type)
  {
    return (List<T>)this.entities.stream()
      .filter(e -> type.isInstance(e))
      .collect(Collectors.toCollection(LinkedList::new));
  }
  
  // Returns an entity by its identifier
  public final Entity getEntity(UUID uuid)
  {
    return this.entities.stream()
      .filter(e -> e.getUUID().equals(uuid))
      .findFirst()
      .orElse(null);
  }

  // Draws this world
  @Override public final void draw() throws GameException
  {
    // Draw entities
    for (Entity e : this.getEntities())
      e.draw();
  }
  
  // Updateable methods
  @Override public final void update(long elaspedTime) throws GameException
  {
    // Update effects
    for (Effect f : this.getEffects())
      f.update(elaspedTime);
    
    // Update entities
    for (Entity e : this.getEntities())
      e.update(elaspedTime);
  }
}
